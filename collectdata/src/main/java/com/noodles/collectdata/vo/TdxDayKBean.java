package com.noodles.collectdata.vo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: stock
 * @description: 通达信日KBean
 * @author: Eric
 * @create: 2019-01-04 17:35
 **/

@Entity
@Table(name = "TDX_DAY_K_INFO")
public class TdxDayKBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;
    @Column(name="DAY")
    private Date day;
    @Column(name="STOCK_CODE")
    private String stockCode;
    @Column(name="VOLUME")
    private Double volume;
    @Column(name="TOTAL_AMT")
    private Double totalAmt;
    @Column(name="OPEN")
    private Double open;
    @Column(name="HIGH")
    private Double high;
    @Column(name="CLOSE")
    private Double close;
    @Column(name="LOW")
    private Double low;
    @Column(name="DIF")
    private Double dif;
    @Column(name="DEA")
    private Double dea;
    @Column(name="MACD")
    private Double macd;
    @Column(name="CREATED_DATE")
    private Date createdDate;
    @Column(name="UPDATED_DATE")
    private Date updatedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(Double totalAmt) {
        this.totalAmt = totalAmt;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getDif() {
        return dif;
    }

    public void setDif(Double dif) {
        this.dif = dif;
    }

    public Double getDea() {
        return dea;
    }

    public void setDea(Double dea) {
        this.dea = dea;
    }

    public Double getMacd() {
        return macd;
    }

    public void setMacd(Double macd) {
        this.macd = macd;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
