package by.bsuir.dissertation.entity.neuroph;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

/**
 * @author Stepanov Nickita on 18.02.2018.
 * @version 1.0
 */
@Getter
@Setter
public class RequestData {

    private String id;
    private Date date;

    public RequestData() {
    }

    public RequestData(String id, Date date) {
        this.id = id;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestData that = (RequestData) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RequestData{");
        sb.append("id='").append(id).append('\'');
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}
