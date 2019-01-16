package com.noodles.collectdata.vo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: stock
 * @description: 雪球返回的日KBean
 * @author: Eric
 * @create: 2019-01-04 17:35
 **/

@Entity
@Table(name = "XUEQIU_DAY_K_INFO")
public class XueqiuDayKBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;
    @Column(name="STOCK_CODE")
    private String stockCode;
    @Column(name="VOLUME")
    private Integer volume;
    @Column(name="OPEN")
    private Double open;
    @Column(name="HIGH")
    private Double high;
    @Column(name="CLOSE")
    private Double close;
    @Column(name="LOW")
    private Double low;
    @Column(name="CHG")
    private Double chg;
    @Column(name="PERCENT")
    private Double percent;
    @Column(name="TURNRATE")
    private Double turnrate;
    @Column(name="DIF")
    private Double dif;
    @Column(name="DEA")
    private Double dea;
    @Column(name="MACD")
    private Double macd;
    @Column(name="LOT_VOLUME")
    private Double lot_volume;
    @Column(name="TIMESTAMP")
    private Long timestamp;
    @Column(name="TIME")
    private String time;
    @Column(name="DAY")
    private Date day;
    @Column(name="CREATED_DATE")
    private Date createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
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

    public Double getChg() {
        return chg;
    }

    public void setChg(Double chg) {
        this.chg = chg;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Double getTurnrate() {
        return turnrate;
    }

    public void setTurnrate(Double turnrate) {
        this.turnrate = turnrate;
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

    public Double getLot_volume() {
        return lot_volume;
    }

    public void setLot_volume(Double lot_volume) {
        this.lot_volume = lot_volume;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
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

    @Column(name="UPDATED_DATE")
    private Date updatedDate;


}
