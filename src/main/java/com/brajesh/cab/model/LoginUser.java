package com.brajesh.cab.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by braj on 11/01/20.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public  class LoginUser {
    @Id
    @GeneratedValue()
    private Long id;
    private String userName;
    private String mobile;
    @Email
    private String email;
    private boolean isActive;
    private String password;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date created_at;
    @JsonIgnore
    private Date updated_at = new Date();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="location_id")
    private Location location;

    @Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Location{
        @Id
        @JsonIgnore
        @GeneratedValue()
        private Long id;
        private int x;
        private int y;

    }
}
