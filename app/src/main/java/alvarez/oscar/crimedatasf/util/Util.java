package alvarez.oscar.crimedatasf.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Oscar Álvarez on 14/01/2016.
 */
public class Util {
    public static String getFloatingTimestampFormat(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        TimeZone tz = TimeZone.getTimeZone("UTC");
        calendar.setTimeInMillis(timestamp * 1000);
        calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
        //TODO: Decrement months
        //calendar.add(Calendar.MONTH, -30);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = calendar.getTime();
        return sdf.format(date).replace(" ", "T");
    }
}
