package by.bsuir.dissertation.entity.graph;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Document(collection = "edge")
public class Edge {

    @Id
    private String id = UUID.randomUUID().toString();

    @Field
    private String uuid;

    @Field
    private double distance;

    @DBRef
    private Node nodeA;

    @DBRef
    private Node nodeB;

    public Edge() {
        this.uuid = UUID.randomUUID().toString();
    }

    public Edge(double distance) {
        this();
        this.distance = distance;
    }

    public Edge(double distance, Node nodeA, Node nodeB) {
        this(distance);
        this.nodeA = nodeA;
        this.nodeB = nodeB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;
        Edge edge = (Edge) o;
        return Double.compare(edge.distance, distance) == 0 &&
                Objects.equals(id, edge.id) &&
                Objects.equals(uuid, edge.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, distance);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Edge{");
        sb.append("id='").append(id).append('\'');
        sb.append(", uuid='").append(uuid).append('\'');
        sb.append(", distance=").append(distance);
        sb.append(", nodeA=").append(nodeA);
        sb.append(", nodeB=").append(nodeB);
        sb.append('}');
        return sb.toString();
    }
}
