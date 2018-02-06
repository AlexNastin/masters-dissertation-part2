package by.bsuir.dissertation.repository;

import by.bsuir.dissertation.entity.result.ResultData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResultDataRepository extends MongoRepository<ResultData, String> {
}
