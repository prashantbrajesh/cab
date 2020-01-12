package com.brajesh.cab.repository;

import com.brajesh.cab.model.Driver;
import com.brajesh.cab.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    List<Driver> findByMobile(String mobile);

    List<Driver> findByIsActive(Boolean isActive);
    List<Driver> findByIsBooked(Boolean isBooked);
}
