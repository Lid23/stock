package com.noodles.collectdata.service.impl;

import com.noodles.collectdata.dao.XueqiuDayKDao;
import com.noodles.collectdata.service.IXueqiuCollectDataService;
import com.noodles.collectdata.utils.*;
import com.noodles.collectdata.vo.XueqiuDayKBean;
import com.noodles.collectdata.vo.XueqiuDayKResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: stock
 * @description: XueqiuCollectDataServiceImpl
 * @author: Eric
 * @create: 2019-01-03 14:53
 **/
@Service
public class XueqiuCollectDataServiceImpl implements IXueqiuCollectDataService {

    @Autowired
    private XueqiuDayKDao xueqiuDayKDao;

    @Override
    public boolean getDayKDataByStockCode(String stockCode) {

        Date begin = DateUtils.parseDate("1990-01-04");
        Date end = DateUtils.addYears(begin, 1);
        while(DateUtils.daysBetween(end, new Date()) > 0){
            collectData(begin, end, stockCode);
            begin = end;
            end = DateUtils.addYears(end, 1);
        }
        collectData(begin, new Date(), stockCode);

        return false;
    }

    private void collectData(Date begin, Date end, String stockCode){
        Map<String, String> params = new HashMap<String, String>();
        String stockType = "6".equals(stockCode.substring(0, 1)) ? "SH" : "SZ";
        params.put("symbol", stockType + stockCode);
        params.put("period", "1day");
        params.put("type", "before");
        params.put("begin", String.valueOf(begin.getTime()));
        params.put("end", String.valueOf(end.getTime()));
        params.put("_", String.valueOf(end.getTime()));
        String url = CollecdataParams.XUEQIU_SYMBOL_URL.concat(CollectDataUtil.getSortParams(params));
        try {
            Map<String, String> cookies = JsoupUtil.getConnection(CollecdataParams.XUEQIU_INDEX_URL).execute().cookies();
            String result = JsoupUtil.getConnection(url).
                    cookies(cookies).execute().body();
            XueqiuDayKResult xueqiuDayKResult = JsonUtil.fromJson(result, XueqiuDayKResult.class );
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
            List<XueqiuDayKBean> xueqiuDaykBeanList = xueqiuDayKResult.getChartlist();
            for(XueqiuDayKBean xueqiuDayKBean : xueqiuDaykBeanList){
                xueqiuDayKBean.setStockCode(stockCode);
                xueqiuDayKBean.setDay(DateUtils.addDays(sdf.parse(xueqiuDayKBean.getTime()), 1));
            }
            if(!CollectionUtils.isEmpty(xueqiuDaykBeanList)){
                xueqiuDayKDao.insertList(xueqiuDaykBeanList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
