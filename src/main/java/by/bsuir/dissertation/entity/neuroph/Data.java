package by.bsuir.dissertation.entity.neuroph;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Data {

    private String id;

    private double normalizeId;

    private Date date;

    private double time;

    private String latitude;

    private double latitudeNORM;

    private String longitude;

    private double longitudeNORM;


    public Data(String id, double normalizeId, Date date, double time, String latitude, double latitudeNORM, String longitude, double longitudeNORM) {
        this.id = id;
        this.normalizeId = normalizeId;
        this.date = date;
        this.time = time;
        this.latitude = latitude;
        this.latitudeNORM = latitudeNORM;
        this.longitude = longitude;
        this.longitudeNORM = longitudeNORM;
    }

    public String toFileString(String delimiter) {
        StringBuilder result = new StringBuilder();
        result.append(id).append(delimiter);
        result.append(normalizeId).append(delimiter);
        result.append(date).append(delimiter);
        result.append(time).append(delimiter);
        result.append(latitude).append(delimiter);
        result.append(latitudeNORM).append(delimiter);
        result.append(longitude).append(delimiter);
        result.append(longitudeNORM);
        return result.toString();
    }
}

