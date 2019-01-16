package com.noodles.collectdata.dao;

import com.noodles.collectdata.vo.City;
import com.noodles.collectdata.vo.EasyMoneyStockCodeAndName;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @program: springboot
 * @description: 用户表dao层
 * @author: Eric
 * @create: 2018-11-27 17:19
 **/
public interface EasyMoneyStockCodeAndNameDao extends Mapper<EasyMoneyStockCodeAndName>{

    public int insertList(List<EasyMoneyStockCodeAndName> EasyMoneyStockCodeAndNameList);

}
