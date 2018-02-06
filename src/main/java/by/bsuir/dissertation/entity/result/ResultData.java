package by.bsuir.dissertation.entity.result;

import by.bsuir.dissertation.entity.Car;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Document(collection = "result")
public class ResultData {

    @Id
    private String id = UUID.randomUUID().toString();

    @DBRef
    private Car car;

    @DBRef
    private List<PartResultData> partResultData;

    public ResultData() {
    }

    public ResultData(Car car, List<PartResultData> partResultData) {
        this.car = car;
        this.partResultData = partResultData;
    }

    public ResultData(String id, Car car, List<PartResultData> partResultData) {
        this.id = id;
        this.car = car;
        this.partResultData = partResultData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultData that = (ResultData) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(car, that.car) &&
                Objects.equals(partResultData, that.partResultData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, car, partResultData);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResultData{");
        sb.append("id='").append(id).append('\'');
        sb.append(", car=").append(car);
        sb.append(", partResultData=").append(partResultData);
        sb.append('}');
        return sb.toString();
    }
}
