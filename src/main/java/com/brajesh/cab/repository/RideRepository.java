package com.brajesh.cab.repository;

import com.brajesh.cab.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {

    @Query(value = "SELECT r.id, r.started_at,r.end_at FROM ride as r left join ride_rider rr " +
            "ON r.id=rr.rider_id where r.id= ?1",
            nativeQuery = true)
    List<Ride> findRidesByRiderId(Long id);
}
