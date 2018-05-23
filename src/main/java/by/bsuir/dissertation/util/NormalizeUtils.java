package by.bsuir.dissertation.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * @author Stepanov Nickita on 04.02.2018.
 * @version 1.0
 */
public class NormalizeUtils {

    public static double normalize(double value, double minValue, double maxValue) {
        return (value - minValue) / (maxValue - minValue);
    }

    public static double denormalize(double value, double minValue, double maxValue) {
        return value * (maxValue - minValue) + minValue;
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

    public static int getTrueHash(Object object) {
	    return Math.abs(Objects.hash(object));
    }

	public static int distanceCalculate(String latitude1, String longitude1, String latitude2, String longitude2) {
//        Радиус сферы (Земли)
		final int rad = 6372795;

//        Координаты в радианах
		double lat1 = toRadian(Double.valueOf(latitude1));
		double lon1 = toRadian(Double.valueOf(longitude1));
		double lat2 = toRadian(Double.valueOf(latitude2));
		double lon2 = toRadian(Double.valueOf(longitude2));

//        Косинусы и синусы широт и разницы долгот
		double cosLat1 = Math.cos(lat1);
		double cosLat2 = Math.cos(lat2);
		double sinLat1 = Math.sin(lat1);
		double sinLat2 = Math.sin(lat2);
		double delta = lon2 - lon1;
		double cosDelta = Math.cos(delta);
		double sinDelta = Math.sin(delta);

//        Вычисление длины большого круга
		double y = Math.sqrt(Math.pow(cosLat2 * sinDelta, 2) + Math.pow(cosLat1 * sinLat2 - sinLat1 * cosLat2 * cosDelta, 2));
		double x = sinLat1 * sinLat2 + cosLat1 * cosLat2 * cosDelta;
		double ad = Math.atan2(y, x);
		return (int) (ad * rad);
	}

	private static double toRadian(double value) {
		return value * Math.PI / 180;
	}
}
