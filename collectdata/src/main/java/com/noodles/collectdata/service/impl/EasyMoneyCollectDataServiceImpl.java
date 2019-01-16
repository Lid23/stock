package com.noodles.collectdata.service.impl;

import com.noodles.collectdata.dao.EasyMoneyStockCodeAndNameDao;
import com.noodles.collectdata.service.IEasyMoneyCollectdataService;
import com.noodles.collectdata.utils.CollecdataParams;
import com.noodles.collectdata.utils.CollectDataUtil;
import com.noodles.collectdata.utils.JsoupUtil;
import com.noodles.collectdata.vo.EasyMoneyStockCodeAndName;
import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: stock
 * @description: EasyMoneyCollectDataServiceImpl
 * @author: Eric
 * @create: 2019-01-03 14:53
 **/

@Service
public class EasyMoneyCollectDataServiceImpl implements IEasyMoneyCollectdataService {

    @Autowired
    private EasyMoneyStockCodeAndNameDao easyMoneyStockCodeAndNameDao;

    /**
    * @Description: 从easyMoney获取所有stock的代码和简称
    * @Param: []
    * @return: void
    * @Author: Eric
    * @Date: 2019/1/7
    */
    public void getAllStockCodeAndName(){
        try {
            Document document = JsoupUtil.getConnection(CollecdataParams.EASY_MONEY_STOCKLIST_URL).get();
            Elements elements = document.getElementsByAttributeValueMatching("href", CollecdataParams.EASY_MONEY_STOCK_INFO_REGEX_URL);
            List<EasyMoneyStockCodeAndName> shList = new ArrayList<>();
            List<EasyMoneyStockCodeAndName> szList = new ArrayList<>();
            List<EasyMoneyStockCodeAndName> chList = new ArrayList<>();
            for(Element element : elements){
                String hrefStr = element.attributes().get("href");
                if(StringUtils.isEmpty(hrefStr)){
                    continue;
                }

                String code = CollectDataUtil.testMatcherFind("s.[0-9]{6}", hrefStr);
                System.out.println(code);
                if(code == null){
                    continue;
                }
                EasyMoneyStockCodeAndName easyMoneyStockCodeAndName = new EasyMoneyStockCodeAndName();
                easyMoneyStockCodeAndName.setStockCode(code.substring(2));
                easyMoneyStockCodeAndName.setStockName(element.text());
                easyMoneyStockCodeAndName.setCreatedDate(new Date());
                if(code.contains("sh6")){
                    shList.add(easyMoneyStockCodeAndName);
                    continue;
                }
                if(code.contains("sz0")){
                    szList.add(easyMoneyStockCodeAndName);
                    continue;
                }
                if(code.contains("sz3")){
                    chList.add(easyMoneyStockCodeAndName);
                    continue;
                }
            }

            if(!CollectionUtils.isEmpty(shList)){
                easyMoneyStockCodeAndNameDao.insertList(shList);
            }
            if(!CollectionUtils.isEmpty(szList)){
                easyMoneyStockCodeAndNameDao.insertList(szList);
            }
            if(!CollectionUtils.isEmpty(chList)){
                easyMoneyStockCodeAndNameDao.insertList(chList);
            }

            System.out.println("done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
