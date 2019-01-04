import com.noodles.collectdata.utils.CollectDataUtil;
import com.noodles.collectdata.utils.JsoupUtil;
import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @program: stock
 * @description: 获取股票代码
 * @author: Eric
 * @create: 2019-01-04 09:26
 **/
public class TestEasyMoney {

    public static String easyMoneyUrl = "http://quote.eastmoney.com/stocklist.html";
    public static void main(String args[]){
        try {
            Document document = JsoupUtil.getConnection(easyMoneyUrl).get();

            Elements elements = document.getElementsByAttributeValueMatching("href", "http://quote.eastmoney.com/\\S\\S(.*?).html");


            int shCount = 0;
            int szCount = 0;
            int chCount = 0;
            for(Element element : elements){
                String hrefStr = element.attributes().get("href");
                String text = element.text();
                if(StringUtils.isNotEmpty(hrefStr)){
                    String code = CollectDataUtil.testMatcherFind("s.[0-9]{6}", hrefStr);
                    if(code == null){
                        continue;
                    }

                    if(code.contains("sh6")){
                        shCount++;
                        System.out.println(code + ":" + text);
                        continue;
                    }

                    if(code.contains("sz0")){
                        szCount++;
                        continue;
                    }

                    if(code.contains("sz3")){
                        chCount++;
                        continue;
                    }

                }
            }

            System.out.println("SH：" + shCount);
            System.out.println("SZ：" + szCount);
            System.out.println("CH：" + chCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
