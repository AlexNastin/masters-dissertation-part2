package by.bsuir.dissertation.entity.neuroph;

import java.util.Arrays;
import java.util.Objects;

public class NormalizeRow {

    private double carId;
    private int[] dayOfWeek;
    private double time;
    private double latitude;
    private double longitude;

    public NormalizeRow() {
    }

    public NormalizeRow(double carId, int[] dayOfWeek, double time, double latitude, double longitude) {
        this.carId = carId;
        this.dayOfWeek = dayOfWeek;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getCarId() {
        return carId;
    }

    public void setCarId(double carId) {
        this.carId = carId;
    }

    public int[] getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int[] dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double x) {
        this.latitude = x;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double y) {
        this.longitude = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalizeRow that = (NormalizeRow) o;
        return Double.compare(that.carId, carId) == 0 &&
                Double.compare(that.time, time) == 0 &&
                Double.compare(that.latitude, latitude) == 0 &&
                Double.compare(that.longitude, longitude) == 0 &&
                Arrays.equals(dayOfWeek, that.dayOfWeek);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, dayOfWeek, time, latitude, longitude);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NormalizeRow{");
        sb.append("carId=").append(carId);
        sb.append(", dayOfWeek=").append(Arrays.toString(dayOfWeek));
        sb.append(", time=").append(time);
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append('}');
        return sb.toString();
    }

    public String toFileString(String delimiter) {
        StringBuilder result = new StringBuilder();
        result.append(carId).append(delimiter);
        for (int aDayOfWeek : dayOfWeek) {
            result.append(aDayOfWeek).append(delimiter);
        }
        result.append(time).append(delimiter);
        result.append(latitude).append(delimiter);
        result.append(longitude);
        return result.toString();
    }
}
