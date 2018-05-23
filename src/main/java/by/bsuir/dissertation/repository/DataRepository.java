package by.bsuir.dissertation.repository;

import by.bsuir.dissertation.entity.neuroph.Data;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataRepository extends MongoRepository<Data, String> {
}
