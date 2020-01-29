package com.brajesh.cab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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


    @OneToMany(mappedBy = "driver",cascade = CascadeType.PERSIST)
    private List<Car> cars = new ArrayList<>();


    public void addRide(Ride ride){
        this.rides.add(ride);
    }


}
