import com.noodles.collectdata.utils.ArithUtil;
import com.noodles.collectdata.utils.DateUtils;
import com.noodles.collectdata.utils.JsonUtil;
import com.noodles.collectdata.vo.MacdGroupSumBean;
import com.noodles.collectdata.vo.TdxDayKBean;
import org.apache.commons.codec.Charsets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: stock
 * @description: 计算Macd测试类
 * @author: Eric
 * @create: 2019-01-16 09:11
 **/
public class TestCalcMacdFromFile {

    static final boolean singleFileFlag = false;

    static final String stockPath = "D:/tdx/T0002/export/SH#600156.txt";

    static final String tdxExportDirPath = "D:/tdx/T0002/export";

    public static void main(String args[]){
        if(singleFileFlag){
            findMacdGroupHit3Down(stockPath);
            return;
        }

        File dir = new File(tdxExportDirPath);
        File[] fileArr = dir.listFiles();
        for(File file : fileArr){
            if(!file.isFile()){
                continue;
            }
            String filePath = file.getAbsolutePath();
            findMacdGroupHit3Down(filePath);
        }
    }

    private static void findMacdGroupHit3Down(String stockPath){
        try {
            List<TdxDayKBean> tdxDayKBeanList = new ArrayList<>();
            List<String> stockList = Files.readAllLines(Paths.get(stockPath), Charsets.toCharset("GBK"));
            if(stockList.size() > 2){
                for(int i = 2; i<stockList.size(); i++){
                    if("数据来源:通达信".equals(stockList.get(i).trim())){
                        break;
                    }
                    String[] dayKInfoArr = stockList.get(i).split("\\s+");
                    TdxDayKBean tdxDayKBean = new TdxDayKBean();
                    tdxDayKBean.setDay(DateUtils.strToDate(dayKInfoArr[0], DateUtils.SPLIT_DAY_STYLE));
                    tdxDayKBean.setOpen(Double.valueOf(dayKInfoArr[1]));
                    tdxDayKBean.setHigh(Double.valueOf(dayKInfoArr[2]));
                    tdxDayKBean.setLow(Double.valueOf(dayKInfoArr[3]));
                    tdxDayKBean.setClose(Double.valueOf(dayKInfoArr[4]));
                    tdxDayKBean.setVolume(Double.valueOf(dayKInfoArr[5]));
                    tdxDayKBean.setTotalAmt(Double.valueOf(dayKInfoArr[6]));
                    tdxDayKBeanList.add(tdxDayKBean);
                }
            }

            int shortNum = 12;
            int longNum = 26;
            Double emaShortBefore = 0.0;
            Double emaLongBefore = 0.0;
            Double emaShort = 0.0;
            Double emaLong = 0.0;

            Double deaBefore = 0.0;

            List<Double> macdList = new ArrayList<>();
            for(TdxDayKBean tdxDayKBean : tdxDayKBeanList){
                emaShort = ArithUtil.round(emaShortBefore * (shortNum - 1) / (shortNum + 1) + tdxDayKBean.getClose() * 2 / (shortNum + 1), 4);
                emaLong = ArithUtil.round(emaLongBefore * (longNum - 1) / (longNum + 1) + tdxDayKBean.getClose() * 2 / (longNum + 1), 4);

                Double diff = ArithUtil.round(emaShort - emaLong, 4);
                tdxDayKBean.setDif(diff);

                Double dea = ArithUtil.round(deaBefore * 8 / 10 + diff * 2 / 10, 4);
                tdxDayKBean.setDea(dea);

                Double macd = ArithUtil.round(2 * (diff - dea), 2);
                tdxDayKBean.setMacd(macd);
                macdList.add(macd);

                emaShortBefore = emaShort;
                emaLongBefore = emaLong;
                deaBefore = dea;

            }

            /**根据Macd值分组*/
            List<MacdGroupSumBean> macdGroupPositiveSumBeanList = new ArrayList<>();
            List<MacdGroupSumBean> macdGroupNegativeSumBeanList = new ArrayList<>();

            Double positiveSum = 0.0;
            Double negativeSum = 0.0;

            Boolean positiveFlag = false;
            Boolean negativeFlag = false;
            MacdGroupSumBean macdGroupSumBean = null;
            for(int i=tdxDayKBeanList.size() - 1; i>=0; i--){
                TdxDayKBean tdxDayKBean = tdxDayKBeanList.get(i);
                if(tdxDayKBean.getMacd() >= 0.0){
                    /***positive*/
                    if(!positiveFlag){
                        negativeFlag = false;
                        if(macdGroupSumBean != null){
                            macdGroupSumBean.setEndDate(tdxDayKBean.getDay());
                            macdGroupSumBean.setSum(ArithUtil.round(negativeSum, 2));
                            macdGroupSumBean.setType("negative");
                            macdGroupNegativeSumBeanList.add(macdGroupSumBean);

                            negativeSum = 0.0;
                        }
                        macdGroupSumBean = new MacdGroupSumBean();
                        macdGroupSumBean.setBeginDate(tdxDayKBean.getDay());
                        positiveFlag = true;
                    }
                    positiveSum += tdxDayKBean.getMacd();
                }

                if(tdxDayKBean.getMacd() < 0.0){
                    /***negative*/
                    if(!negativeFlag){
                        positiveFlag = false;
                        if(macdGroupSumBean != null){
                            macdGroupSumBean.setEndDate(tdxDayKBean.getDay());
                            macdGroupSumBean.setSum(ArithUtil.round(positiveSum, 2));
                            macdGroupSumBean.setType("positive");
                            macdGroupPositiveSumBeanList.add(macdGroupSumBean);

                            positiveSum = 0.0;
                        }
                        macdGroupSumBean = new MacdGroupSumBean();
                        macdGroupSumBean.setBeginDate(tdxDayKBean.getDay());
                        negativeFlag = true;
                    }
                    negativeSum += tdxDayKBean.getMacd();
                }
            }

            if(singleFileFlag){
                //System.out.println("Today tdxDayKBeanList:" + JsonUtil.toJson(tdxDayKBeanList));
                System.out.println(JsonUtil.toJson(macdList));
               // System.out.println("positive:" + JsonUtil.toJson(macdGroupPositiveSumBeanList));
                System.out.println("negative:" + JsonUtil.toJson(macdGroupNegativeSumBeanList));
            }


            if(macdGroupNegativeSumBeanList.size() > 2 &&
                    DateUtils.daysBetween(macdGroupNegativeSumBeanList.get(0).getBeginDate(), new Date()) == 2 &&
                    macdGroupNegativeSumBeanList.get(0).getSum() > macdGroupNegativeSumBeanList.get(1).getSum() &&
                    macdGroupNegativeSumBeanList.get(1).getSum() > macdGroupNegativeSumBeanList.get(2).getSum()
            ){
                System.out.println("命中:" + stockList.get(0));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
