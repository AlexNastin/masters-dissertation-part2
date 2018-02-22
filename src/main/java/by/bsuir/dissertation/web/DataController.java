package by.bsuir.dissertation.web;

import by.bsuir.dissertation.entity.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/neural-network-service")
public class DataController {


    @RequestMapping(value = "/location/car/{id}", method = RequestMethod.GET)
    public ResponseEntity getLocation(@PathVariable(value = "id") String id, @RequestParam Long time) {
        return new ResponseEntity<>(new Car(id, time), HttpStatus.OK);
    }
}