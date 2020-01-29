package com.brajesh.cab.controller;

import com.brajesh.cab.exception.RecordNotFoundException;
import com.brajesh.cab.model.Driver;
import com.brajesh.cab.service.DriverService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {
    @Autowired
    DriverService service;



    @GetMapping
    public ResponseEntity<List<Driver>> getAllDrivers() {
        List<Driver> list = service.getAllDrivers();

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }


    @RequestMapping( method = { RequestMethod.PUT, RequestMethod.POST })
    public ResponseEntity<Driver> createOrUpdateDriver(@RequestBody Driver Driver)
            throws RecordNotFoundException {
        Driver updated = service.createOrUpdateDriver(Driver);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping()
    public HttpStatus deleteAll()
            throws RecordNotFoundException {
        service.deleteAll();
        return HttpStatus.OK;
    }


}
