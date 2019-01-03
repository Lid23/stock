package com.noodles.collectdata.utils;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 文件名：JsoupUtil.java
 * 描述：
 * 作者：sz05383
 * 日期：2017年2月23日上午11:14:14
 */
public class JsoupUtil {

    /**
     * 获取jsoup 连接
     * @param url
     * @param map
     * @return
     * 作者：sz05383
     * 日期：2017年2月23日上午11:16:49
     */
    public static Connection getConnection(String url, Map<String, String> map) {
        return Jsoup.connect(url).data(dealNull(map)).validateTLSCertificates(false).ignoreContentType(true)
                .timeout(30000);
    }

    /**
     * 发送http(s)请求
     * @param url
     * @param map
     * @return
     * @throws Exception
     * 作者：李久华
     * 日期：2017年7月5日上午11:20:28
     */
    public static byte[] executeAsBytes(String url, Map<String, String> map) throws Exception {
        return Jsoup.connect(url).data(dealNull(map)).validateTLSCertificates(false).ignoreContentType(true)
                .timeout(30000).method(Method.POST).execute().bodyAsBytes();
    }

    /**
     * 发送http(s)请求
     * @param url
     * @param map
     * @return
     * @throws Exception
     * 作者：李久华
     * 日期：2017年7月5日上午11:20:22
     */
    public static String executeAsString(String url, Map<String, String> map) throws Exception {
        return Jsoup.connect(url).data(dealNull(map)).validateTLSCertificates(false).ignoreContentType(true)
                .timeout(30000).method(Method.POST).execute().body();
    }

    /**
     * 处理null为空字符串
     * @param map
     * @return
     * 作者：李久华
     * 日期：2017年8月31日下午2:05:38
     */
    private static Map<String, String> dealNull(Map<String, String> map) {
        if (map == null) {
            map = new HashMap<String, String>();
        }
        for (Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value == null) {
                map.put(key, "");
            }
        }

        return map;
    }

    /**
     * 获取jsoup 连接
     * @param url
     * @return
     * 作者：李久华
     * 日期：2017年8月24日下午2:12:07
     */
    public static Connection getConnection(String url) {
        return Jsoup.connect(url).validateTLSCertificates(false).ignoreContentType(true).timeout(60000);
    }

    /**
     * 发送http(s)请求
     * @param url
     * @param contentType
     * @param data
     * @return
     * @throws Exception
     * 作者：李久华
     * 日期：2017年8月24日下午2:15:09
     */
    public static String executeAsString(String url, String contentType, String data) throws Exception {
        return getConnection(url).header("Content-Type", contentType).data("test", "test").requestBody(data)
                .method(Method.POST).execute().body();
    }

    public static Response executeAsStringWithAuth(String url, String authorization, String data) throws Exception {
        return getConnection(url).ignoreHttpErrors(true).ignoreContentType(true).header("Authorization", authorization)
                .header("Content-Type", "application/json").data("test", "test").requestBody(data).method(Method.POST)
                .execute();
    }
}
