package com.brajesh.cab.service;

import com.brajesh.cab.model.Ride;
import com.brajesh.cab.exception.RecordNotFoundException;
import com.brajesh.cab.repository.RideRepository;
import com.brajesh.cab.repository.RideRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RideService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
     
    @Autowired
    RideRepository repository;

     
    public List<Ride> getAllRides()
    {
        List<Ride> rideList = repository.findAll();
         
        if(rideList.size() > 0) {
            return rideList;
        } else {
            return new ArrayList<>();
        }
    }

     
    public Ride getRideById(Long id) throws RecordNotFoundException
    {

        Ride ride = repository.findOne(id);
         
        if(ride!=null) {
            return ride;
        } else {
            throw new RecordNotFoundException("No Ride record exist for given id");
        }
    }

    @Transactional
    public Ride createOrUpdateRide(Ride entity) throws RecordNotFoundException
    {

        Ride ride=null;

        if(entity.getId()!=null)
             ride = repository.findOne(entity.getId());
         
        if(ride!=null)
        {
            entity.getDriver().addRide(ride);
            entity.getRider().addRide(ride);
            logger.info("updating entity");
            ride.setType(entity.getType());
            ride.setUpdated_at(new Date());

            return repository.save(ride);
             

        } else {
            logger.info("saving entity");
            entity.getDriver().addRide(entity);
            entity.getRider().addRide(entity);
            entity = repository.save(entity);
             
            return entity;
        }
    }
     
    public void deleteRideById(Long id) throws RecordNotFoundException
    {
        Ride ride = repository.findOne(id);
         
        if(ride!=null)
        {
            logger.info("deleting entity {}",id);
            repository.delete(id);
        } else {
            throw new RecordNotFoundException("No Ride record exist for given id");
        }
    }

    public void deleteAll()
    {
        repository.deleteAll();

    }
}