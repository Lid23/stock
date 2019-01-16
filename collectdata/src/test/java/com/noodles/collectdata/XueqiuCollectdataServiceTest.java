package com.noodles.collectdata;

import com.noodles.collectdata.service.IEasyMoneyCollectdataService;
import com.noodles.collectdata.service.IXueqiuCollectDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: stock
 * @description: XueqiuCollectdataService测试类s
 * @author: Eric
 * @create: 2019-01-07 16:04
 **/

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CollectDataApplication.class)
public class XueqiuCollectdataServiceTest {
    @Autowired
    private IXueqiuCollectDataService xueqiuCollectDataServiceImpl;

    @Test
    public void getDayKDataByStockCodeTest(){
        String stockCode = "600756";
        xueqiuCollectDataServiceImpl.getDayKDataByStockCode(stockCode);

        System.out.println("done");

    }

}
