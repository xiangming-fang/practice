package com.xm.jy.test.excel;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xm.jy.job_51.util.PropertiesUtil;
import com.xm.jy.test.redis.RedisConnectPool;
import com.xm.jy.test.util.RequestUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: albert.fang
 * @date: 2021/3/10 14:12
 * @description: excel表格的操作
 */
public class POI {

    private final static String EXCEL_PATH = PropertiesUtil.getProperty("base_excel_file_path");

    private final static String GENERATE_PATH = PropertiesUtil.getProperty("generate_excel_path");

    /**
     * 存在redis里的文件后缀
     */
    private final static String INDEX = "index";

    private static InputStream getExcelFileStream() throws FileNotFoundException {
        return new FileInputStream(EXCEL_PATH);
    }

    public static void main(String[] args) throws Exception {
        // 1、通过输入流读取一个excel工作簿
        HSSFWorkbook workbook = new HSSFWorkbook(getExcelFileStream());
        // 2、获取工作簿的指定名称sheet
        HSSFSheet sourceSheet = workbook.getSheet("基本信息");

        // 3、创建一个新的excel工作簿
        HSSFWorkbook newWorkBook = new HSSFWorkbook();
        // 4、将第2步指定的sheet加入到这个工作簿里
        HSSFSheet newSheet = newWorkBook.createSheet("成绩表");
        int lastRowNum = newSheet.getLastRowNum();
        List<HSSFCellStyle> sourceStyles = getSourceStyles(newWorkBook, sourceSheet);
        Integer colIndex = copySheet(sourceSheet, newSheet, sourceStyles, 0, true);
        System.out.println(colIndex);
        // 5、TODO 手动创建对象，遍历该对象，让原工作簿里的多个工作表合并成一个，实际环境需要用真实数据
        JSONArray modules = getJsonArray();
        // 总分信息
        JSONObject totalScoreInfo = getTotalScoreInfo(modules);
        int totalColNum = iteratorModuleAndDimension(workbook, newSheet, modules, totalScoreInfo, sourceStyles, colIndex);
        if (totalScoreInfo != null){
            replaceTotalScoreId(totalScoreInfo,newSheet,totalColNum);
        }
        freezeHeader(newSheet);
        setColumnWidth(newSheet,colIndex,0);
        // 6、创建一个新的excel文件，并在文件中写入该工作簿
        Jedis jedisConnect = RedisConnectPool.getConnection();
        String suffix;
        if (jedisConnect.exists(INDEX)) {
            suffix = String.valueOf(Long.parseLong(jedisConnect.get(INDEX)) + 1L);
        }else {
            suffix = "1";
        }
        jedisConnect.set(INDEX,suffix);
        jedisConnect.close();
        String targetFilePath = GENERATE_PATH + "//" + "s_96_" + suffix + ".xls";
        File targetFile = new File(targetFilePath);
        newWorkBook.write(targetFile);
    }

    /**
     * 设置列宽
     * @param newSheet 新工作表
     * @param colIndex 列
     * @param width 宽度，用默认的话就传0，用实际的就传实际长度
     */
    public static void setColumnWidth(HSSFSheet newSheet, int colIndex, int width) {
        width = width == 0 ? 13 :width;
        width *= 256;
        for (int i = 0; i < colIndex; i++)
        {
            newSheet.setColumnWidth(i, width);
        }
    }

    /**
     * 冻结表头，前三列，正文行
     * @param newSheet
     */
    private static void freezeHeader(HSSFSheet newSheet) {
        //正文行 (工作表行号从0开始)
        int iContentRowNo = newSheet.getLastRowNum();
        int iColCount = 3;
        newSheet.createFreezePane(iColCount, iContentRowNo);
    }

    /**
     * 替换总分维度id
     * @param totalScoreInfo 总分维度信息
     * @param newSheet 新表单
     * @param colIndex 列索引
     */
    private static void replaceTotalScoreId(JSONObject totalScoreInfo,HSSFSheet newSheet,Integer colIndex){
        int lastRowNum = newSheet.getLastRowNum();
        HSSFRow newRow = getOrCreateRow(newSheet, lastRowNum);
        int dimensionId = totalScoreInfo.getIntValue("DimensionID");
        System.out.println(colIndex);
        for (Integer i = 0; i < colIndex; i++) {
            HSSFCell newCell = newRow.getCell(i);
            if (newCell == null){
                continue;
            }
            String newText = newCell.getStringCellValue();
            newText = newText.replace("{TOTALSCOREID}", String.valueOf(dimensionId));
            newCell.setCellValue(newText);
        }
    }

    /**
     * 迭代每个模块以及模块的定制维度
     * @param sourceWorkbook 原工作簿
     * @param newSheet 新的工作表
     * @param modules 各个模块的信息
     */
    private static int iteratorModuleAndDimension(HSSFWorkbook sourceWorkbook,HSSFSheet newSheet,JSONArray modules,JSONObject totalScoreInfo,List<HSSFCellStyle> cellStyles,Integer colIndex){
        ArrayList<String> moduleNames = new ArrayList<>();
        for (Object module : modules) {
            JSONObject moduleInfo = ((JSONObject) module).getJSONObject("ModuleInfo");
            String moduleName = moduleInfo.getString("Name");
            // 根据模块名去原工作簿找对应的工作表
            HSSFSheet moduleSheet = getModuleSheet(sourceWorkbook, moduleName);
            if (moduleSheet == null) {
                continue;
            }
            moduleNames.add(moduleName);
            // 胜任力潜质 补充总分列
            boolean hasTotalScore = false;
            if (("胜任力潜质".equals(moduleName) || "能力和胜任潜质特征".equals(moduleName) || "能力和管理潜质特征".equals(moduleName)) && totalScoreInfo != null && totalScoreInfo.getBooleanValue("Visible"))
            {
                hasTotalScore = true;
                colIndex += copySheet(sourceWorkbook,"胜任力总分",newSheet,cellStyles,colIndex,false);
            }
            JSONArray categories = ((JSONObject) module).getJSONArray("Categories");
            JSONArray dimensions = null;
            if (categories != null && categories.size() != 0){
                dimensions = getDimensions(moduleName,categories);
            }
            if (dimensions == null){
                colIndex += copySheet(moduleSheet,newSheet,cellStyles,colIndex,true);
            }else {
                colIndex += copyCustomizedSheet(moduleSheet,newSheet,cellStyles,colIndex,dimensions,!hasTotalScore);
            }
            // 合并单元格
            if (hasTotalScore){
                int colNum = dimensions.size() + 1;
                mergeCells(newSheet,0,0,colIndex - colNum,colIndex - 1);
            }
        }
        return colIndex;
    }

    /**
     * 复制包含定制维度的工作表
     * @param sourceSheet 原工作表
     * @param newSheet 新工作表
     * @param cellStyles 单元格样式
     * @param fromColIndex 从哪列开始
     * @param dimensions 定制维度
     * @param firstMerged 是否第一次合并
     * @return 返回这次复制的工作表最大列数
     */
    private static Integer copyCustomizedSheet(HSSFSheet sourceSheet,HSSFSheet newSheet,List<HSSFCellStyle> cellStyles,Integer fromColIndex,JSONArray dimensions,boolean firstMerged){
        Integer maxColNum = 0;
        if (dimensions == null || dimensions.size() == 0) {
            return maxColNum;
        }
        int rowCount = sourceSheet.getLastRowNum() + 1;
        int startColIndex = fromColIndex;
        int endColIndex = 0;
        for (Object dimension : dimensions) {
            // 得到维度名称和id
            String dimensionName = ((JSONObject) dimension).getString("DimensionName");
            int dimensionId = ((JSONObject) dimension).getIntValue("DimensionID");
            Integer tempColCount = copySheet(sourceSheet, newSheet, cellStyles, startColIndex, false);
            endColIndex = startColIndex + tempColCount;
            maxColNum += tempColCount;

            // 更新文案
            for (int rIndex = 0; rIndex < rowCount; rIndex++) {
                HSSFRow newRow = newSheet.getRow(rIndex);
                if (newRow == null) {
                    continue;
                }
                for (int cIndex = startColIndex; cIndex < endColIndex; cIndex++) {
                    HSSFCell newCell = newRow.getCell(cIndex);
                    if (newCell == null){
                        continue;
                    }
                    // 首行是合并的
                    String newText = newCell.getStringCellValue();
                    // 新文案
                    newText = newCell.getStringCellValue().replace("{MODULENAME}",dimensionName);
                    newText = newText.replace("{MODULEID}",String.valueOf(dimensionId));
                    newCell.setCellValue(newText);
                }
            }
            startColIndex = endColIndex;
        }
        if (firstMerged){
            // 合并首行单元格
            mergeCells(newSheet,0,0,fromColIndex,endColIndex - 1);
        }
        return maxColNum;
    }

    /**
     * 得到模块下的维度信息
     * @param moduleName
     * @param categories
     * @return
     */
    private static JSONArray getDimensions(String moduleName,JSONArray categories){
        int categoryId = 1;
        switch (moduleName) {
            case "胜任力潜质":
                categoryId = 1;
                break;
            case "能力和胜任潜质特征":
                categoryId = 7;
                break;
            case "能力和管理潜质特征":
                categoryId = 9;
                break;
            case "人岗匹配":
            case "人岗匹配度":
                categoryId = 2;
                break;
            case "结果概览":
                categoryId = 6;
                break;
            case "整体结果":
                categoryId = 8;
                break;
            default:
        }
        for (Object category : categories) {
            int mCategoryId = ((JSONObject) category).getJSONObject("CategoryInfo").getJSONObject("MCategory").getIntValue("ID");
            if (mCategoryId == categoryId){
                return ((JSONObject) category).getJSONArray("MadeDimensions");
            }
        }
        return null;
    }

    /**
     * 获取模块相关的工作表
     * @param sourceWorkbook 原工作簿
     * @param moduleName 模块名
     * @return
     */
    private static HSSFSheet getModuleSheet(HSSFWorkbook sourceWorkbook,String moduleName){
        HSSFSheet sheet = sourceWorkbook.getSheet(moduleName);
        if (sheet == null) {
            int leftBracketIndex = moduleName.indexOf("(");
            if (leftBracketIndex > 0) {
                moduleName = moduleName.substring(0,leftBracketIndex);
                sheet = sourceWorkbook.getSheet(moduleName);
            }
        }
        return sheet;
    }

    /**
     * 直接复用原始模板表中的样式
     * @param newWorkbook 新建的工作簿
     * @param sourceSheet 原始模板表工作表
     * @return 原始模板表工作簿中的样式集合,第一个是表头样式，第二个是正文样式
     */
    private static List<HSSFCellStyle> getSourceStyles(HSSFWorkbook newWorkbook,HSSFSheet sourceSheet){
        int rowCount = sourceSheet.getLastRowNum() + 1;
        int[] rowIndexs = {rowCount - 2, rowCount - 1};
        List<HSSFCellStyle> hssfCellStyles = new ArrayList<>();
        for (int rowIndex : rowIndexs) {
            HSSFCellStyle cellStyle = newWorkbook.createCellStyle();
            HSSFRow sourceRow = sourceSheet.getRow(rowIndex);
            HSSFCell sourceCell = sourceRow.getCell(0);
            cellStyle.cloneStyleFrom(sourceCell.getCellStyle());
            cellStyle.setWrapText(true);
            hssfCellStyles.add(cellStyle);
        }
        return hssfCellStyles;
    }

    /**
     * 复制工作表到另一个工作表
     * @param sourceSheet 原工作表
     * @param newSheet 要完成的新工作表
     * @param cellStyles 原工作表的样式只有表头样式和正文样式：第一个是表头样式，第二个是正文样式
     * @param fromColIndex 从第几列开始
     * @param firstMerged 是否是第一次合并，并且合并开始位置在（0，0）
     * @return 返回工作表的最大列数
     */
    private static Integer copySheet(HSSFSheet sourceSheet,HSSFSheet newSheet,List<HSSFCellStyle> cellStyles,Integer fromColIndex,boolean firstMerged){
        int maxColNum = 0;
        int rowCount = sourceSheet.getLastRowNum() + 1;
        HSSFCellStyle cellStyle = null;
        for (int i = 0; i < rowCount; i++) {
            cellStyle = i + 1 == rowCount ? cellStyles.get(1) : cellStyles.get(0);
            HSSFRow sourceRow = sourceSheet.getRow(i);
            HSSFRow newRow = getOrCreateRow(newSheet, i);
            // 得到原工作表的第i + 1行有几列
            int colCount = sourceRow.getLastCellNum();
            if (maxColNum < colCount) {
                maxColNum = colCount;
            }
            for (int colIndex = 0; colIndex < colCount; colIndex++) {
                HSSFCell sourceCell = sourceRow.getCell(colIndex);
                HSSFCell newCell = getOrCreateCell(newRow, colIndex + fromColIndex);
                copyCell(sourceCell,newCell,cellStyle);
            }
        }
        // 合并单元格
        int regionCount = sourceSheet.getNumMergedRegions();
        for (int i = 0; i < regionCount; i++) {
            CellRangeAddress mergedRegion = sourceSheet.getMergedRegion(i);
            int startX = mergedRegion.getFirstRow();
            int endX = mergedRegion.getLastRow();
            int startY = mergedRegion.getFirstColumn();
            int endY = mergedRegion.getLastColumn();
            if (!firstMerged && startX == 0 && endX == 0){
                continue;
            }
            mergeCells(newSheet,startX,endX,startY + fromColIndex,endY + fromColIndex);
        }
        return maxColNum;
    }

    /**
     * 复制工作簿的一个工作表到另一个工作表中
     * @param sourceWorkbook 原工作簿
     * @param sheetName 要提取的原工作簿中工作表的名字
     * @param newSheet 新的工作表
     * @param cellStyles 单元格样式
     * @param fromColIndex 从第几列开始
     * @param firstMerged 是否是第一次单元格合并
     * @return
     */
    private static Integer copySheet(HSSFWorkbook sourceWorkbook,String sheetName,HSSFSheet newSheet,List<HSSFCellStyle> cellStyles,Integer fromColIndex,boolean firstMerged){
        HSSFSheet originSheet = sourceWorkbook.getSheet(sheetName);
        if (originSheet == null) {
            return 0;
        }
        return copySheet(originSheet,newSheet,cellStyles,fromColIndex,firstMerged);
    }

    /**
     * 得到工作表的第几行，或者给工作表创建第几行
     * @param newSheet
     * @param index
     * @return 返回工作表的第index+1行
     */
    private static HSSFRow getOrCreateRow(HSSFSheet newSheet,int index){
        HSSFRow row = newSheet.getRow(index);
        if (row == null){
            row = newSheet.createRow(index);
        }
        return row;
    }

    /**
     * 得到行中的第几个单元格
     * @param newRow 工作表中的行
     * @param index 列索引
     * @return 具体单元格
     */
    private static HSSFCell getOrCreateCell(HSSFRow newRow,int index){
        HSSFCell cell = newRow.getCell(index);
        if (cell == null){
            cell = newRow.createCell(index);
        }
        return cell;
    }

    /**
     * 复制单元格
     * @param sourceCell 原单元格
     * @param newCell 新建的单元格
     * @param cellStyle 原单元格的样式
     */
    private static void copyCell(HSSFCell sourceCell,HSSFCell newCell,HSSFCellStyle cellStyle){
        newCell.setCellStyle(cellStyle);
        CellType cellTypeEnum = sourceCell.getCellTypeEnum();
        newCell.setCellType(cellTypeEnum);
        switch (cellTypeEnum){
            case BLANK:
                newCell.setCellValue(sourceCell.getStringCellValue());
                break;
            case BOOLEAN:
                newCell.setCellValue(sourceCell.getBooleanCellValue());
                break;
            case ERROR:
                newCell.setCellValue(sourceCell.getErrorCellValue());
                break;
            case FORMULA:
                newCell.setCellValue(sourceCell.getCellFormula());
                break;
            case NUMERIC:
                newCell.setCellValue(sourceCell.getNumericCellValue());
                break;
            case STRING:
                newCell.setCellValue(sourceCell.getRichStringCellValue());
                break;
            default:
        }
    }


    /**
     * 合并单元格
     * @param newSheet 要合并的工作表
     * @param rowStart 要合并的单元格初始行位置
     * @param rowEnd 要合并的单元格行末位置
     * @param colStart 要合并的单元格初始列位置
     * @param colEnd 要合并的单元格末列位置
     */
    private static void mergeCells(HSSFSheet newSheet,int rowStart,int rowEnd,int colStart,int colEnd){
        // 以第一个单元格文案为准，其它单元格文案置空
        for (int rowIndex = rowStart; rowIndex < rowEnd; rowIndex++) {
            HSSFRow row = newSheet.getRow(rowIndex);
            if (row == null){
                continue;
            }
            for (int colIndex = colStart + 1; colIndex < colEnd; colIndex++) {
                HSSFCell cell = row.getCell(colIndex);
                if (cell == null){
                    continue;
                }
                cell.setCellValue("");
            }
        }
        // 合并单元格
        CellRangeAddress cellRangeAddress = new CellRangeAddress(rowStart, rowEnd, colStart, colEnd);
        newSheet.addMergedRegion(cellRangeAddress);
    }

    /**
     * 获取测试数据
     * @throws Exception 调用json-server mock 数据接口异常
     */
    private static JSONArray getJsonArray() throws Exception {
        JSONObject jsonObject = RequestUtil.httpClientGet("http://localhost:3000/testParams",null);
        return jsonObject.getJSONArray("result");
    }

    /**
     * 获取总分维度
     * @param modules 模块信息
     * @return 返回胜任力总分维度信息
     */
    private static JSONObject getTotalScoreInfo(JSONArray modules){
        for (Object module : modules) {
            if (((JSONObject)module).getJSONArray("Categories") == null || ((JSONObject)module).getJSONArray("Categories").size() == 0) {
                continue;
            }
            for (Object itemCategory : ((JSONObject) module).getJSONArray("Categories")) {
                String type = ((JSONObject) itemCategory).getJSONObject("CategoryInfo").getJSONObject("MCategory").getString("Type");
                if (!type.contains("totalscore")){
                    continue;
                }
                if (((JSONObject) itemCategory).getJSONArray("MadeDimensions") == null || ((JSONObject) itemCategory).getJSONArray("MadeDimensions").size() == 0){
                    continue;
                }
                return ((JSONObject) itemCategory).getJSONArray("MadeDimensions").getJSONObject(0);
            }
        }
        return null;
    }

}
