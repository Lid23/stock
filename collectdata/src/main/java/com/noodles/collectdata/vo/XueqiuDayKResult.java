package com.noodles.collectdata.vo;

import java.util.List;

/**
 * @program: stock
 * @description: 雪球返回的日K结果
 * @author: Eric
 * @create: 2019-01-04 17:48
 **/
public class XueqiuDayKResult {

    private XueqiuStock stock;

    private boolean success;

    private List<XueqiuDayKBean> chartlist;

    public XueqiuStock getStock() {
        return stock;
    }

    public void setStock(XueqiuStock stock) {
        this.stock = stock;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<XueqiuDayKBean> getChartlist() {
        return chartlist;
    }

    public void setChartlist(List<XueqiuDayKBean> chartlist) {
        this.chartlist = chartlist;
    }
}
