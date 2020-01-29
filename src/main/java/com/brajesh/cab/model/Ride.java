package com.brajesh.cab.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ride {

    public Ride(String type) {
        this.type = type;
    }

    @Id

    @GeneratedValue
    private Long id;
    private String type;
    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "ride_rider",
            joinColumns =
                    { @JoinColumn(name = "ride_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "rider_id", referencedColumnName = "id") })
    private Rider rider;
    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "ride_driver",
            joinColumns =
                    { @JoinColumn(name = "ride_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "driver_id", referencedColumnName = "id") })
    private Driver driver;

    @Embedded
    private EmbadedTest embadedTest;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date started_at;
    @JsonIgnore
    private Date updated_at= new Date();
    private boolean isActive;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(nullable = true)
    private Date end_at;


}
