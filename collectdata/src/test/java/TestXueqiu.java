import com.noodles.collectdata.utils.DateUtils;
import com.noodles.collectdata.utils.JsonUtil;
import com.noodles.collectdata.utils.JsoupUtil;
import com.noodles.collectdata.vo.XueqiuDayKBean;
import com.noodles.collectdata.vo.XueqiuDayKResult;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.util.*;

/**
 * @program: stock
 * @description: Teset
 * @author: Eric
 * @create: 2019-01-03 14:56
 **/
public class TestXueqiu {


    // https://xueqiu.com/stock/forchartk/stocklist.json?symbol=SH600756&period=1day&type=before&begin=1478620800000&end=1510126200000&_=1510126200000

    public static String indexUrl = "https://xueqiu.com/";
    public static String symbolUrl = "https://xueqiu.com/stock/forchartk/stocklist.json?";
    public static void main(String args[]) throws ParseException {
        Date begin = DateUtils.parseDate("1990-01-04");
        Date end = DateUtils.addYears(begin, 1);
        while(DateUtils.daysBetween(end, new Date()) > 0){
            collectData(begin, end);
            begin = end;
            end = DateUtils.addYears(end, 1);
        }
        collectData(begin, new Date());
    }

    private static void collectData(Date begin, Date end){
        Map<String, String> params = new HashMap<String, String>();
        params.put("symbol", "SH600756");
        params.put("period", "1day");
        params.put("type", "before");
        params.put("begin", String.valueOf(begin.getTime()));
        params.put("end", String.valueOf(end.getTime()));
        params.put("_", String.valueOf(end.getTime()));
        String url = symbolUrl.concat(getSortParams(params));
        //System.out.println(url);
        try {
            Map<String, String> cookies = JsoupUtil.getConnection(indexUrl).execute().cookies();
            String result = JsoupUtil.getConnection(url).
                    cookies(cookies).execute().body();
            //System.out.println("result:" + result);
             XueqiuDayKResult xueqiuDayKResult = JsonUtil.fromJson(result, XueqiuDayKResult.class );
            //SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss z", Locale.US);

            List<XueqiuDayKBean> chartlist = xueqiuDayKResult.getChartlist();
            System.out.println(JsonUtil.toJson(xueqiuDayKResult));
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
