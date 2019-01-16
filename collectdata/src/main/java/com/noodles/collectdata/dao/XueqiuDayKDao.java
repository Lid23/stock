package com.noodles.collectdata.dao;

import com.noodles.collectdata.vo.EasyMoneyStockCodeAndName;
import com.noodles.collectdata.vo.XueqiuDayKBean;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @program: springboot
 * @description: 用户表dao层
 * @author: Eric
 * @create: 2018-11-27 17:19
 **/
public interface XueqiuDayKDao extends Mapper<XueqiuDayKBean>{

    public int insertList(List<XueqiuDayKBean> xueqiuDayKBeanList);

}
