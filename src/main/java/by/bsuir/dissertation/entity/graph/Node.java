package by.bsuir.dissertation.entity.graph;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Document(collection = "node")
public class Node {

    @Id
    private String id = UUID.randomUUID().toString();

    @Field
    private String number;

    @Field
    private String latitude;

    @Field
    private String longitude;

    @Field
    private String region;

    @Field
    private boolean isCamera;

    @DBRef(lazy = true)
    private Set<Edge> edges;

    public Node() {
    }

    public Node(String number, String latitude, String longitude, String region, boolean isCamera) {
        this.number = number;
        this.latitude = latitude;
        this.longitude = longitude;
        this.region = region;
        this.isCamera = isCamera;
    }

    public void addRelationship(Edge edge) {
        if (edges == null) {
            edges = new HashSet<>();
        }
        edges.add(edge);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return isCamera == node.isCamera &&
                Objects.equals(id, node.id) &&
                Objects.equals(number, node.number) &&
                Objects.equals(latitude, node.latitude) &&
                Objects.equals(longitude, node.longitude) &&
                Objects.equals(region, node.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, latitude, longitude, region, isCamera);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Node{");
        sb.append("id='").append(id).append('\'');
        sb.append(", number='").append(number).append('\'');
        sb.append(", latitude='").append(latitude).append('\'');
        sb.append(", longitude='").append(longitude).append('\'');
        sb.append(", region='").append(region).append('\'');
        sb.append(", isCamera=").append(isCamera);
        sb.append(", edges=").append(edges);
        sb.append('}');
        return sb.toString();
    }
}