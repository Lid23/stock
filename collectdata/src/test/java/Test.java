import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @program: stock
 * @description: test
 * @author: Eric
 * @create: 2019-01-12 14:12
 **/
public class Test {

    public static void main(String args[]){
        String time = "Thu Sep 26 00:00:00 +0800 1996";

        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        try {
            Date date = sdf.parse(time);

            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
