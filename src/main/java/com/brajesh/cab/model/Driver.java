package com.brajesh.cab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Driver extends LoginUser implements Serializable {



    private boolean isBooked;
    @JsonIgnore
    @OneToMany(mappedBy = "driver", orphanRemoval = true)
    private List<Ride> rides= new ArrayList<>();


    public void addRide(Ride ride){
        this.rides.add(ride);
    }


}
