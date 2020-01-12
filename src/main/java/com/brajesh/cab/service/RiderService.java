package com.brajesh.cab.service;

import com.brajesh.cab.exception.RecordNotFoundException;
import com.brajesh.cab.model.Driver;
import com.brajesh.cab.model.Ride;
import com.brajesh.cab.model.Rider;
import com.brajesh.cab.repository.DriverRepository;
import com.brajesh.cab.repository.RideRepository;
import com.brajesh.cab.repository.RiderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RiderService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
     
    @Autowired
    RiderRepository repository;

    @Autowired
    RideRepository rideRepository;

    @Autowired
    DriverRepository driverRepository;

     
    public List<Rider> getAllRiders()
    {
        List<Rider> riderList = repository.findAll();
         
        if(riderList.size() > 0) {
            return riderList;
        } else {
            return new ArrayList<>();
        }
    }


    public List<Ride> getAllRides(Long id) throws  RecordNotFoundException
    {
        List<Ride> rideList = rideRepository.findRidesByRiderId(id);

        if(rideList.size() > 0) {
            return rideList;
        } else {
            throw new RecordNotFoundException("No Rider record exist for given id");
        }
    }

    public Driver assignNearestDriver(Long id) throws RecordNotFoundException
    {
        List<Driver> driverList = driverRepository.findByIsBooked(false);

        Rider rider = getRiderById(id);

        double min[] = {Double.MAX_VALUE};
        final Driver[] driver = {null};

        driverList.stream().forEach(d->
                {
                    double dist = Math.sqrt(Math.pow(d.getLocation().getX()-rider.getLocation().getX(),2)+
                            Math.pow(d.getLocation().getY()-rider.getLocation().getY(),2));
                    if(dist<min[0]){
                        min[0]=dist;
                        driver[0] =d;
                    }
                }
        );
        driver[0].setBooked(true);
        driverRepository.save(driver[0]);

        return driver[0];
    }

     
    public Rider getRiderById(Long id) throws RecordNotFoundException
    {

        Rider rider = repository.findOne(id);
         
        if(rider!=null) {
            return rider;
        } else {
            throw new RecordNotFoundException("No Rider record exist for given id");
        }
    }

    @Transactional
    public Rider createOrUpdateRider(Rider entity) throws RecordNotFoundException
    {

        Rider rider=null;

        if(entity.getId()!=null)
             rider = repository.findOne(entity.getId());
         
        if(rider!=null)
        {
            entity.setLocation(entity.getLocation());
            entity.setUpdated_at(new Date());
            logger.info("updating entity");


            return repository.save(rider);
             

        } else {
            logger.info("saving entity");

            entity.setUpdated_at(new Date());
            entity = repository.save(entity);
             
            return entity;
        }
    }
     
    public void deleteRiderById(Long id) throws RecordNotFoundException
    {
        Rider rider = repository.findOne(id);
         
        if(rider!=null)
        {
            logger.info("deleting entity {}",id);
            repository.delete(id);
        } else {
            throw new RecordNotFoundException("No Rider record exist for given id");
        }
    }

    public void deleteAll()
    {
        repository.deleteAll();

    }
}