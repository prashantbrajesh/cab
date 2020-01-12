package com.brajesh.cab.repository;

import com.brajesh.cab.model.Ride;
import com.brajesh.cab.model.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {



}
