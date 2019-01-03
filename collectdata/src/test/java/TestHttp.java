import com.noodles.collectdata.utils.DateUtils;
import com.noodles.collectdata.utils.JsoupUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: stock
 * @description: Teset
 * @author: Eric
 * @create: 2019-01-03 14:56
 **/
public class TestHttp {


    // https://xueqiu.com/stock/forchartk/stocklist.json?symbol=SH600756&period=1day&type=before&begin=1478620800000&end=1510126200000&_=1510126200000

    public static String indexUrl = "https://xueqiu.com/";
    public static String symbolUrl = "https://xueqiu.com/stock/forchartk/stocklist.json?";
    public static void main(String args[]) throws ParseException {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Map<String, String> params = new HashMap<String, String>();
        params.put("symbol", "SH600756");
        params.put("period", "1day");
        params.put("type", "before");
        params.put("begin", String.valueOf(df.parse(df.format(new Date())).getTime()));
        params.put("end", String.valueOf(new Date().getTime()));
        params.put("_", String.valueOf(new Date().getTime()));

        System.out.println(params);


        String url = symbolUrl.concat(getSortParams(params));

        System.out.println(url);
        try {
            Map<String, String> cookies = JsoupUtil.getConnection(indexUrl).execute().cookies();
            String result = JsoupUtil.getConnection(url).
                    cookies(cookies).execute().body();
            System.out.println("result:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static String getSortParams(Map<String, String> params) {
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
