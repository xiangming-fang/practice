package com.xm.jy.test.hu;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @ProjectName: practice
 * @Package: com.xm.jy.test.hu
 * @ClassName: HuToolTest
 * @Author: albert.fang
 * @Description:
 * @Date: 2022/5/18 21:46
 */
public class HuToolTest{

    public static void main(String[] args) {
//        ExcelReader reader = ExcelUtil.getReader(FileUtil.file("C:\\Users\\albert\\Desktop\\导入开发医院模板.xlsx"));
//        List<List<Object>> readAll = reader.read();
//        for (List<Object> objects : readAll) {
//            System.out.println(objects);
//        }
//        System.out.println(readAll.size());

//        Excel07SaxReader reader07 = new Excel07SaxReader(createRowHandler());
//        reader07.read("C:\\Users\\albert\\Desktop\\导入开发医院模板.xlsx", 0);

        System.out.println(getMaxSize());
    }

//    private static RowHandler createRowHandler() {
//        return new RowHandler() {
//            @Override
//            public void handle(int sheetIndex, long rowIndex, List<Object> rowlist) {
//                Console.log("[{}] [{}] {}", sheetIndex, rowIndex, rowlist);
//            }
//        };
//    }

    private static int getMaxSize(){
        // 1、通过输入流读取一个excel工作簿
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook(new FileInputStream("C:\\Users\\albert\\Desktop\\导入开发医院模板.xlsx"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 2、获取工作簿的指定名称sheet
        HSSFSheet sheetAt = workbook.getSheetAt(0);
//        HSSFSheet sourceSheet = workbook.getSheet("基本信息");
        System.out.println(sheetAt.getLastRowNum());
        return sheetAt.getLastRowNum();
    }



}
