package com.brajesh.cab.controller;

import com.brajesh.cab.model.Driver;
import com.brajesh.cab.model.Ride;
import com.brajesh.cab.model.Rider;
import com.brajesh.cab.service.RiderService;
import com.brajesh.cab.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rider")
public class RiderController {
    @Autowired
    RiderService service;

    @GetMapping
    public ResponseEntity<List<Rider>> getAllRiders() {
        List<Rider> list = service.getAllRiders();

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/rider/{id}")
    public ResponseEntity<List<Ride>> getAllRides(@PathVariable Long id)
            throws RecordNotFoundException
    {
        List<Ride> list = service.getAllRides(id);

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/rider/bookCab/{id}")
    public ResponseEntity<Driver> assignNearestDeiver(@PathVariable Long id)
    throws RecordNotFoundException {
        Driver driver = service.assignNearestDriver(id);

        return new ResponseEntity<>(driver, new HttpHeaders(), HttpStatus.OK);
    }




    @RequestMapping( method = { RequestMethod.PUT, RequestMethod.POST })
    public ResponseEntity<Rider> createOrUpdateRider(@RequestBody Rider Rider)
            throws RecordNotFoundException {
        Rider updated = service.createOrUpdateRider(Rider);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping()
    public HttpStatus deleteAll()
            throws RecordNotFoundException {
        service.deleteAll();
        return HttpStatus.OK;
    }


}
