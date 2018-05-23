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

    public Data(String id, double normalizeId, Date date, double time) {
        this.id = id;
        this.normalizeId = normalizeId;
        this.date = date;
        this.time = time;
    }

    public String toFileString(String delimiter) {
        StringBuilder result = new StringBuilder();
        result.append(id).append(delimiter);
        result.append(normalizeId).append(delimiter);
        result.append(date).append(delimiter);
        result.append(time);
        return result.toString();
    }
}

