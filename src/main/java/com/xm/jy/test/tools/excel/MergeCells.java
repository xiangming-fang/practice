package com.xm.jy.test.tools.excel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: albert.fang
 * @date: 2021/3/16 13:59
 * @description: 合并单元格：读取一个现有的excel文件，获取其中合并的单元格
 */
public class MergeCells {

    private static Logger logger = LoggerFactory.getLogger(MergeCells.class);

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

    private static void getMergeCellFromSheet(){
        HSSFWorkbook workbook = readExcelFile();
        HSSFSheet basicInfo = workbook.getSheet("基本信息");
        int numMergedRegions = basicInfo.getNumMergedRegions();
        System.out.printf("工作表中有%d个合并单元格\n",numMergedRegions);
        for (int i = 0; i < numMergedRegions; i++) {
            CellRangeAddress mergedRegion = basicInfo.getMergedRegion(i);
            int firstRow = mergedRegion.getFirstRow();
            int lastRow = mergedRegion.getLastRow();
            int firstColumn = mergedRegion.getFirstColumn();
            int lastColumn = mergedRegion.getLastColumn();
            System.out.printf("第%d个的位置是开始：（%d，%d）,结束：(%d,%d) \n",i+1,firstRow,firstColumn,lastRow,lastColumn);
        }
    }


    public static void main(String[] args) {
        getMergeCellFromSheet();
    }
}
