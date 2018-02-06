package by.bsuir.dissertation.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Stepanov Nickita on 04.02.2018.
 * @version 1.0
 */
public class NormalizeUtils {

    public static double normalize(double value, double minValue, double maxValue) {
        return (value - minValue) / (maxValue - minValue);
    }

    public static double normalizeTime(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return normalize(hour, 0, 23);
    }

    public static int[] normalizeDayOfWeek(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return normalizeBinary(7, dayOfWeek);
    }

    private static int[] normalizeBinary(int rank, int numberOfActiveRank) {
        if (numberOfActiveRank > rank) {
            return null;
        }
        int[] result = new int[rank];
        result[numberOfActiveRank - 1] = 1;
        return result;
    }
}
