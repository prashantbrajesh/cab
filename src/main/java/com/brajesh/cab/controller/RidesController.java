package com.brajesh.cab.controller;

import com.brajesh.cab.model.Ride;
import com.brajesh.cab.service.RideService;
import com.brajesh.cab.exception.RecordNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rides")
public class RidesController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RideService service;

    @GetMapping
    public ResponseEntity<List<Ride>> getAllRides() {
        List<Ride> list = service.getAllRides();
        logger.info("baba");
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ride> getRideById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        Ride entity = service.getRideById(id);

        return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
    }


    @RequestMapping( method = { RequestMethod.PUT, RequestMethod.POST })
    public ResponseEntity<Ride> createOrUpdateRide(@RequestBody Ride event)
            throws RecordNotFoundException {
        Ride updated=null;
        try {
           updated = service.createOrUpdateRide(event);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.CREATED);

        }

        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.CREATED);
    }

    @DeleteMapping()
    public HttpStatus deleteAll()
            throws RecordNotFoundException {
        service.deleteAll();
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteRideById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteRideById(id);
        return HttpStatus.FORBIDDEN;
    }

}
