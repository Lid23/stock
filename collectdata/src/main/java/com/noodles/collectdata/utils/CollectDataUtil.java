package com.noodles.collectdata.utils;

import org.apache.commons.lang.StringUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: stock
 * @description: 工具类
 * @author: Eric
 * @create: 2019-01-04 10:18
 **/
public class CollectDataUtil {

    public static String testMatcherFind(String regex, String input){

        /**Pattern对象表示正则表达式的编译版本，compile方法将正则表达式编程成Pattern对象*/
        Pattern pattern = Pattern.compile(regex);

        /**使用matcher方法和输入字符串从编译过的Pattern对象中产生Matcher对象*/
        Matcher matcher = pattern.matcher(input);

        while(matcher.find()){
            /**find 方法扫描输入序列以查找与该模式匹配的下一个子序列*/
            return matcher.group();
        }

        return null;
    }

    public static String getSortParams(Map<String, String> params) {
        Set<String> keySet = params.keySet();
        Iterator<String> iter = keySet.iterator();
        StringBuilder sb = new StringBuilder();
        while (iter.hasNext()) {
            String key = iter.next();
            String value = params.get(key);
            if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)){
                sb.append(key).append("=").append(value).append("&");
            }
        }
        if (sb.length() > 0) {
            return sb.substring(0, sb.length() - 1);
        }
        return "";
    }
}
