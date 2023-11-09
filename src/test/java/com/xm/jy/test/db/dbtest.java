package com.xm.jy.test.db;

import com.xm.jy.xhz.mapper.SingleTable2Mapper;
import com.xm.jy.xhz.pojo.SingleTable2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Author: xiangming.fang
 * @Date: 2023/6/29 14:02
 */
@SpringBootTest
public class dbtest {


    @Resource
    private SingleTable2Mapper singleTable2Mapper;


    @Test
    public void insertTest(){
        for (int i = 0; i < 10000; i++) {
            SingleTable2 singleTable = new SingleTable2();
            singleTable.setKey1(i+1 + "");
            singleTable.setKey2(i+1);
            singleTable.setKey3(i+1 + "");
            singleTable.setKeyPart1(i+1 + "");
            singleTable.setKeyPart2(i+1 + "");
            singleTable.setKeyPart3(i+1 + "");
            singleTable.setCommonField(i+1 + "");
            singleTable2Mapper.insert(singleTable);
        }

    }

}
