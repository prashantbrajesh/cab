package com.brajesh.cab.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by braj on 13/01/20.
 */

@Data
@Entity
public class Car {

    @Id
    Long id;

    private String type;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "driver_id")
    private Driver driver;
}
