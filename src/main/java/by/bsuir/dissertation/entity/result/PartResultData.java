package by.bsuir.dissertation.entity.result;

import by.bsuir.dissertation.entity.graph.Node;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Document(collection = "partResult")
public class PartResultData {

    @Id
    private String id = UUID.randomUUID().toString();

    @Field
    private Date date;

    @DBRef
    private Node node;

    public PartResultData() {
    }

    public PartResultData(Date date, Node node) {
        this.date = date;
        this.node = node;
    }

    public PartResultData(String id, Date date, Node node) {
        this.id = id;
        this.date = date;
        this.node = node;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PartResultData)) return false;
        PartResultData that = (PartResultData) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(date, that.date) &&
                Objects.equals(node, that.node);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, node);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PartResultData{");
        sb.append("id='").append(id).append('\'');
        sb.append(", date=").append(date);
        sb.append(", node=").append(node);
        sb.append('}');
        return sb.toString();
    }
}
