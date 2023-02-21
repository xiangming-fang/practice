package com.xm.jy.test.tools.excel;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: albert.fang
 * @date: 2021/3/16 15:02
 * @description: 为了写印象笔记，重新写一次
 */
@Slf4j
public class ExcelNote {

    /**
     * 读取源excel文件，返回源excel文件的workbook对象
     * @return 源excel文件的workbook对象
     */
    private static HSSFWorkbook readExcelFile(){
        String originExcelFile = "E:\\IdeaProjects\\practice\\doc\\template.xls";
        HSSFWorkbook originWorkbook = null;
        try {
            InputStream is = new FileInputStream(originExcelFile);
            originWorkbook = new HSSFWorkbook(is);
        } catch (FileNotFoundException e) {
            log.error("找不到目标excel文件",e);
        } catch (IOException e) {
            log.error("读取excel文件失败",e);
        }
        return originWorkbook;
    }

    /**
     * 生成excel文件
     * @return 返回生成的excel文件
     */
    private static File generateExcelFile(){
        String fileName = String.format("%tF-%tL",new Date(),new Date()) + ".xls";
        String filePathPrefix = "E:\\IdeaProjects\\practice\\excel\\";
        String filePath = filePathPrefix + fileName;
        File file = new File(filePath);
        try {
            if (file.createNewFile()) {
                log.info("生成excel文件成功");
            }
        } catch (IOException e) {
            log.error("生成excel文件失败",e);
        }
        return file;
    }

    private static void comboManySheet() throws IOException {
        // 1、获取基础模板excel文件
        HSSFWorkbook workbook = readExcelFile();
        // 2、假如这个考生所处活动包含模块1,模块3
        List<String> actModules = Stream.of("模块1","模块3").collect(Collectors.toList());
        // 3、创建目标workbook对象
        HSSFWorkbook targetWorkbook = new HSSFWorkbook();
        // 4、给目标workbook对象创建一个sheet
        HSSFSheet targetSheet = targetWorkbook.createSheet("成绩表");
        // 5、因为不管什么活动都包含基本信息这个模块，所以从基础模板中提取该sheet
        HSSFSheet basicInfo = workbook.getSheet("基本信息");
        // 6、获取原工作表的样式（第一个是标题样式，第二个是正文样式）
        List<HSSFCellStyle> cellStyles = getCellStyles(basicInfo, targetWorkbook);
        // 7、将basicInfo复制到targetSheet里，返回列数（注意：不是列的下标，是要复制进去的sheet最大列数）
        int colNum = copySheet(basicInfo, targetSheet, cellStyles, 0);
        // 8、遍历活动模块，复制模块sheet追加到targetSheet末尾
        for (String actModule : actModules) {
            colNum  += copySheet(workbook.getSheet(actModule), targetSheet, cellStyles, colNum);
        }
        // 9、生成目标excel文件
        File targetFile = generateExcelFile();
        // 10、将targetWorkbook对象写进目标文件中
        targetWorkbook.write(targetFile);
    }

    /**
     * 复制原sheet追加到目标sheet中
     * @param originSheet 原sheet
     * @param targetSheet 目标sheet
     * @param cellStyleList 原sheet的标题和正文样式
     * @param fromColIndex 从目标sheet的第几列开始追加
     * @return 此次追加多少列
     */
    private static int copySheet(HSSFSheet originSheet,HSSFSheet targetSheet,List<HSSFCellStyle> cellStyleList,int fromColIndex){
        int targetSheetColNum = 0;
        // 1、获取原sheet工作表总共有多少行,getLastRowNum是获取行的最大索引，索引是从0开始的，所以得加1
        int rowCount = originSheet.getLastRowNum() + 1;
        // 2、对行进行遍历（这个for循环没什么好说的，就是挨个遍历原sheet的单元格，然后复制到目标sheet的对应位置单元格）
        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            HSSFRow originRow = originSheet.getRow(rowIndex);
            HSSFRow targetRow = targetSheet.getRow(rowIndex) == null ? targetSheet.createRow(rowIndex) : targetSheet.getRow(rowIndex);
            // 3、getLastCellNum 获取的就是该行最大的列数，不需要加1
            int colCount = originRow.getLastCellNum();
            if (colCount > targetSheetColNum){
                targetSheetColNum = colCount;
            }
            for (int colIndex = 0; colIndex < colCount; colIndex++) {
                HSSFCell cell = originRow.getCell(colIndex);
                HSSFCell targetCell = targetRow.getCell(fromColIndex + colIndex) == null ? targetRow.createCell(fromColIndex + colIndex)
                        : targetRow.getCell(fromColIndex + colIndex);
                copyCellValue(cell,targetCell);
                if (rowIndex != rowCount - 1){
                    targetCell.setCellStyle(cellStyleList.get(0));
                }else {
                    targetCell.setCellStyle(cellStyleList.get(1));
                }
            }
        }
        // 4、获取原sheet有几个合并的单元格,在targetSheet中也得合并
        int numMergedRegions = originSheet.getNumMergedRegions();
        for (int i = 0; i < numMergedRegions; i++) {
            CellRangeAddress mergedRegion = originSheet.getMergedRegion(i);
            int firstRow = mergedRegion.getFirstRow();
            int lastRow = mergedRegion.getLastRow();
            int firstColumn = mergedRegion.getFirstColumn();
            int lastColumn = mergedRegion.getLastColumn();
            mergeCells(targetSheet,firstRow,lastRow,firstColumn + fromColIndex,lastColumn + fromColIndex);
        }
        return targetSheetColNum;
    }

    /**
     * 合并单元格
     * @param targetSheet 要合并单元格的sheet
     * @param firstRow 开始行
     * @param lastRow 最后行
     * @param firstColumn 开始列
     * @param lastColumn 结束列
     */
    private static void mergeCells(HSSFSheet targetSheet,int firstRow,int lastRow,int firstColumn,int lastColumn){
        // 待合并的单元格内容，以第一个为主，其它的置空
        for (int rowIndex = firstRow; rowIndex <= lastRow; rowIndex++) {
            if (targetSheet.getRow(rowIndex) == null){
                continue;
            }
            for (int colIndex = firstColumn + 1; colIndex <= lastColumn; colIndex++) {
                HSSFCell cell = targetSheet.getRow(rowIndex).getCell(colIndex);
                if (cell == null){
                    continue;
                }
                cell.setCellValue("");
            }
        }
        CellRangeAddress cellRangeAddress = new CellRangeAddress(firstRow, lastRow, firstColumn, lastColumn);
        targetSheet.addMergedRegion(cellRangeAddress);
    }

    /**
     * 复制原单元格的内容到目标单元格；主要是判断元单元格内容的类型，然后调用获取单元格类型值的方法
     * @param originCell 原单元格
     * @param targetCell 目标单元格
     */
    private static void copyCellValue(HSSFCell originCell,HSSFCell targetCell){
        CellType cellTypeEnum = originCell.getCellTypeEnum();
        switch (cellTypeEnum){
            case BLANK:
                targetCell.setCellValue(originCell.getStringCellValue());
                break;
            case BOOLEAN:
                targetCell.setCellValue(originCell.getBooleanCellValue());
                break;
            case ERROR:
                targetCell.setCellValue(originCell.getErrorCellValue());
                break;
            case FORMULA:
                targetCell.setCellValue(originCell.getCellFormula());
                break;
            case NUMERIC:
                targetCell.setCellValue(originCell.getNumericCellValue());
                break;
            case STRING:
                targetCell.setCellValue(originCell.getRichStringCellValue());
                break;
            default:
        }
    }

    /**
     * 复制原工作表中的单元格样式
     * @param originSheet 原工作表
     * @param targetWorkbook 目标工作簿
     * @return 原工作表中的样式，第一个是标题样式，第二个是正文样式
     */
    private static List<HSSFCellStyle> getCellStyles(HSSFSheet originSheet,HSSFWorkbook targetWorkbook){
        HSSFCellStyle titleStyle = targetWorkbook.createCellStyle();
        HSSFCellStyle contentStyle = targetWorkbook.createCellStyle();
        int lastRowIndex = originSheet.getLastRowNum();
        // 倒数第二行及之前都是标题，复制原sheet单元格的标题样式
        titleStyle.cloneStyleFrom(originSheet.getRow(lastRowIndex - 1).getCell(0).getCellStyle());
        // 最后一行肯定是正文，复制原sheet单元格的正文样式
        contentStyle.cloneStyleFrom(originSheet.getRow(lastRowIndex).getCell(0).getCellStyle());
        return Stream.of(titleStyle,contentStyle).collect(Collectors.toList());
    }


    public static void main(String[] args) throws IOException {
        comboManySheet();
    }
}
