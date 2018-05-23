package by.bsuir.dissertation.entity.neuroph;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data {

    private String id;

    private double normalizeId;

    public Data(String id, double normalizeId) {
        this.id = id;
        this.normalizeId = normalizeId;
    }

    public String toFileString(String delimiter) {
        StringBuilder result = new StringBuilder();
        result.append(id).append(delimiter);
        result.append(normalizeId);
        return result.toString();
    }
}

