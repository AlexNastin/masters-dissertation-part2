package by.bsuir.dissertation.entity.exchange;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * @author Stepanov Nickita on 18.02.2018.
 * @version 1.0
 */
@Getter
@Setter
public class ResponseData {

    private double latitude;
    private double longitude;

    public ResponseData() {
    }

    public ResponseData(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseData that = (ResponseData) o;
        return Double.compare(that.latitude, latitude) == 0 &&
                Double.compare(that.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResponseData{");
        sb.append("latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append('}');
        return sb.toString();
    }
}
