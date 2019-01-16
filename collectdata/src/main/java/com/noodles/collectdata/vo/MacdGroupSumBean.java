package com.noodles.collectdata.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: stock
 * @description: Macd分组求和Bean
 * @author: Eric
 * @create: 2019-01-16 13:47
 **/
public class MacdGroupSumBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**分组开始日期*/
    private Date beginDate;
    /**分组结束日期*/
    private Date endDate;
    /**求和*/
    private Double sum;
    /**正数-positive / 负数-negative */
    private String type;

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
