package com.noodles.collectdata;

import com.netflix.discovery.converters.Auto;
import com.noodles.collectdata.service.IEasyMoneyCollectdataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: stock
 * @description: EasyMoneyCollectdataService单元测试类
 * @author: Eric
 * @create: 2019-01-07 16:04
 **/

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CollectDataApplication.class)
public class EasyMoneyCollectdataServiceTest {
    @Autowired
    private IEasyMoneyCollectdataService easyMoneyCollectDataServiceImpl;

    @Test
    public void getAllStockCodeAndNameTest(){
        easyMoneyCollectDataServiceImpl.getAllStockCodeAndName();

    }

}
