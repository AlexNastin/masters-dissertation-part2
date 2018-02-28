package by.bsuir.dissertation.web;

import by.bsuir.dissertation.CarTrafficDataProcessing;
import by.bsuir.dissertation.entity.exchange.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "/api/v1/neural-network-service")
public class DataController {

    private CarTrafficDataProcessing carTrafficDataProcessing;

    @Autowired
    public DataController(CarTrafficDataProcessing carTrafficDataProcessing) {
        this.carTrafficDataProcessing = carTrafficDataProcessing;
    }

    @RequestMapping(value = "/location/car/{id}", method = RequestMethod.GET)
    public ResponseEntity getLocation(@PathVariable(value = "id") String id, @RequestParam(value = "time") Long time) {
        // use test id: '7d389e3b-5316-45eb-87bf-87ac72bfcb9c' and date '2018-02-04 11:08:49.601'
        ResponseData responseData = carTrafficDataProcessing.getCoordinates(id, new Date(time));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}