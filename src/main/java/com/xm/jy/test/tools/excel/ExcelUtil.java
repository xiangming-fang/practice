package com.xm.jy.test.tools.excel;

import com.xm.jy.test.tools.redis.RedisConnectPool;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: albert.fang
 * @date: 2021/3/12 14:11
 * @description: 利用POI生成想要的excel文件
 */
public class ExcelUtil {

    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    private static Jedis jedis =  RedisConnectPool.getConnection();

    private static List<Integer> rowAndColNumList = Stream.of(4,18).collect(Collectors.toList());

    private static List<String> secondTitleList = Stream.of("简历编号","考生编号","考生姓名","身份证号","性别",
            "年龄","学历","手机号","邮箱","职级","部门","职位","工作年限",
            "专业","所属单位/院校","应聘部门","提交时间","窗口切换次数").collect(Collectors.toList());

    public static void main(String[] args) {
        File file = generateExcelFile();
        excelOperator(file);
    }

    /**
     * excel操作
     * @param file excel文件路径
     */
    private static void excelOperator(File file){
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet personalInformation = workbook.createSheet("个人信息");
        List<HSSFCellStyle> cellStyleList = generateCellStyles(workbook);
        int colCount = fillSheet(personalInformation, cellStyleList);
        mergeCells(0,0,0,rowAndColNumList.get(1) - 1,personalInformation);
        // 固定左边三列，上边0行；从左边数起索引为3（第四行，因为从0开始）开始展示，上边从索引为0（也就是第一行）开始展示
        personalInformation.createFreezePane(3,0,3,0);
        HSSFWorkbook originWorkbook = readExcelFile();
        colCount += addSheet(originWorkbook,personalInformation,colCount,cellStyleList);
        setColWidth(personalInformation,colCount);
        try {
            workbook.write(file);
        } catch (IOException e) {
            logger.error("往excel文件里写如工作簿对象失败",e);
        }
    }

    /**
     * 将originWorkbook中的工作簿里的某一工作表，追加到workSheet中
     * @param originWorkbook
     * @param workSheet
     * @return 此次追加几列
     */
    private static int addSheet(HSSFWorkbook originWorkbook,HSSFSheet workSheet,int colCount,List<HSSFCellStyle> styles){
        HSSFSheet originSheet = originWorkbook.getSheet("能力和胜任潜质特征");
        // 一个sheet有几行，是getlastRowNum + 1,因为直接获取的是行数索引
        int rowNum = originSheet.getLastRowNum() + 1;
        // 此次追加的列数
        int thisColNum = 0;
        for (int rowIndex = 0; rowIndex < rowNum; rowIndex++) {
            HSSFRow originRow = originSheet.getRow(rowIndex);
            HSSFRow targetRow = workSheet.getRow(rowIndex);
            // 得到的就是列数
            int rowCellNum = originRow.getLastCellNum();
            for (int colIndex = 0; colIndex < rowCellNum; colIndex++) {
                if (thisColNum <= colIndex){
                    thisColNum = colIndex + 1;
                }
                HSSFCell targetCell = createOrGetDefaultCell(targetRow, ++colCount);
                copyCellValue(originRow.getCell(colIndex),targetCell);
                targetCell.setCellStyle( rowIndex == 0 ? styles.get(0) : styles.get(1) );
            }
            // 为下一行的新增做准备
            colCount = colCount - rowCellNum;
        }
        colCount += thisColNum;
        // 合并单元格
        mergeCells(1,2,colCount,colCount,workSheet);
        return thisColNum;
    }

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
     * 读取具体单元格，如果列数没有单元格，那么创建一个新的
     * @param row 行
     * @param colIndex 列索引
     * @return 读取或者是新创建的单元格
     */
    private static HSSFCell createOrGetDefaultCell(HSSFRow row,int colIndex){
        HSSFCell cell = row.getCell(colIndex);
        if (cell == null) {
            cell = row.createCell(colIndex);
        }
        return cell;
    }

    /**
     * 设置样式，工作簿有两种样式，一个是头部样式（0），一个是正文样式（1）
     * @param workbook 工作簿
     */
    private static List<HSSFCellStyle> generateCellStyles(HSSFWorkbook workbook){
        List<HSSFCellStyle> cellStyleList = Stream.of(workbook.createCellStyle(), workbook.createCellStyle()).collect(Collectors.toList());
        HSSFFont fontContent = workbook.createFont();
        // 设置字体大小
        fontContent.setFontHeightInPoints((short)12);
        setCommonCellStyle(cellStyleList.get(1),fontContent);
        HSSFFont fontTitle = workbook.createFont();
        fontTitle.setBold(true);
        // 设置字体大小
        fontTitle.setFontHeightInPoints((short)16);
        // 设置前景色
        cellStyleList.get(0).setFillForegroundColor((short)150);
        cellStyleList.get(0).setFillPattern(FillPatternType.SOLID_FOREGROUND);
        setCommonCellStyle(cellStyleList.get(0),fontTitle);
        return cellStyleList;
    }

    /**
     * 设置表格的列宽
     * @param sheet 表格
     * @param colNum 前colNum列
     */
    private static void setColWidth(HSSFSheet sheet,int colNum){
        for (int i = 0; i <= colNum; i++) {
            sheet.setColumnWidth(i,13 * 256);
        }
    }

    /**
     * 公共样式
     * @param cellStyle 单元格样式
     * @param font 字体
     */
    private static void setCommonCellStyle(HSSFCellStyle cellStyle,Font font){
        // 8 表示黑色
        font.setFontName("宋体");
        cellStyle.setFont(font);
        // 文字自动换行
        cellStyle.setWrapText(true);
        // 文字水平居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 文字垂直居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    }

    /**
     * 合并工作表的单元格
     * @param startRow 开始行
     * @param enRow 结束行
     * @param startCol 开始列
     * @param endCol 结束列
     * @param sheet 要合并单元格的工作表
     */
    private static void mergeCells(int startRow,int enRow,int startCol,int endCol,HSSFSheet sheet){
        CellRangeAddress cellRangeAddress = new CellRangeAddress(startRow, enRow, startCol, endCol);
        sheet.addMergedRegion(cellRangeAddress);
    }

    /**
     * 填充一个4行20列的一个excel sheet
     * @param sheet sheet入参对象
     */
    private static int fillSheet(HSSFSheet sheet,List<HSSFCellStyle> cellStyleList){
        int colCount = 0;
        for (int rowNum = 0; rowNum < rowAndColNumList.get(0); rowNum++) {
            HSSFRow row = sheet.createRow(rowNum);
            for (int colNum = 0; colNum < rowAndColNumList.get(1); colNum++) {
                HSSFCell cell = row.createCell(colNum);
                if (rowNum == 0){
                    row.setHeight((short)600);
                    cell.setCellValue("基本信息");
                    cell.setCellStyle(cellStyleList.get(0));
                    break;
                }else {
                    cell.setCellValue(secondTitleList.get(colNum));
                    cell.setCellStyle(cellStyleList.get(1));
                    if (colCount < colNum){
                        colCount = colNum;
                    }
                }
            }
        }
        return colCount;
    }

    /**
     * 生成excel文件
     * @return 返回生成的excel文件
     */
    private static File generateExcelFile(){
        String fileName = String.format("%tF", new Date()) + "-" + getAndUpdateRedisValue() + ".xls";
        String filePathPrefix = "E:\\personalfile\\about_company\\function\\题库系统——报告微定制\\HSSFworkbook\\";
        String filePath = filePathPrefix + fileName;
        File file = new File(filePath);
        try {
            if (file.createNewFile()) {
                logger.info("生成excel文件成功");
            }
        } catch (IOException e) {
            logger.error("生成excel文件失败",e);
        }
        return file;
    }

    /**
     * 得到当前redis最新的值，得到之后将值更改
     * @return 当前redis的值
     */
    private static int getAndUpdateRedisValue(){
        int curValue = Integer.parseInt(jedis.get("index"));
        jedis.set("index",String.valueOf(curValue + 1));
        return curValue;
    }

    /**
     * 读取源excel文件，返回源excel文件的workbook对象
     * @return 源excel文件的workbook对象
     */
    private static HSSFWorkbook readExcelFile(){
        String originExcelFile = "E:\\personalfile\\about_company\\function\\题库系统——报告微定制\\HSSFworkbook\\campus-new-result.xls";
        HSSFWorkbook originWorkbook = null;
        try {
            InputStream is = new FileInputStream(originExcelFile);
            originWorkbook = new HSSFWorkbook(is);
        } catch (FileNotFoundException e) {
            logger.error("找不到目标excel文件",e);
        } catch (IOException e) {
            logger.error("读取excel文件失败",e);
        }
        return originWorkbook;
    }
}
