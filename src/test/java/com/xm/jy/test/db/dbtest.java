package com.xm.jy.test.db;

import com.xm.jy.xhz.mapper.SingleTableMapper;
import com.xm.jy.xhz.pojo.SingleTable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author: xiangming.fang
 * @Date: 2023/6/29 14:02
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class dbtest {

    @Resource
    private SingleTableMapper singleTableMapper;

    @Test
    public void insertTest(){
        for (int i = 0; i < 10000; i++) {
            SingleTable singleTable = new SingleTable();
            singleTable.setKey1(i+1 + "");
            singleTable.setKey2(i+1);
            singleTable.setKey3(i+1 + "");
            singleTable.setKeyPart1(i+1 + "");
            singleTable.setKeyPart2(i+1 + "");
            singleTable.setKeyPart3(i+1 + "");
            singleTable.setCommonField(i+1 + "");
            singleTableMapper.insert(singleTable);
        }

    }

}
