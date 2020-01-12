package com.brajesh.cab.service;

import com.brajesh.cab.exception.RecordNotFoundException;
import com.brajesh.cab.model.Driver;
import com.brajesh.cab.repository.DriverRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DriverService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
     
    @Autowired
    DriverRepository repository;

     
    public List<Driver> getAllDrivers()
    {
        List<Driver> driverList = repository.findAll();
         
        if(driverList.size() > 0) {
            return driverList;
        } else {
            return new ArrayList<>();
        }
    }

     
    public Driver getDriverById(Long id) throws RecordNotFoundException
    {

        Driver driver = repository.findOne(id);
         
        if(driver!=null) {
            return driver;
        } else {
            throw new RecordNotFoundException("No Driver record exist for given id");
        }
    }

    @Transactional
    public Driver createOrUpdateDriver(Driver entity) throws RecordNotFoundException
    {

        Driver driver = repository.findOne(entity.getId());


         
        if(driver!=null)
        {
            logger.info("updating entity");
            driver.setUpdated_at(new Date());
            driver.setActive(entity.isActive());

            return repository.save(driver);
             

        } else {
            logger.info("saving entity");
            entity = repository.save(entity);
             
            return entity;
        }
    }
     
    public void deleteDriverById(Long id) throws RecordNotFoundException
    {
        Driver driver = repository.findOne(id);
         
        if(driver!=null)
        {
            logger.info("deleting entity {}",id);
            repository.delete(id);
        } else {
            throw new RecordNotFoundException("No Driver record exist for given id");
        }
    }

    public void deleteAll()
    {
        repository.deleteAll();

    }
}