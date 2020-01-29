package com.brajesh.cab.repository;

import com.brajesh.cab.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest(classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class DriverRepositoryTest {


    @Autowired
    DriverRepository driverRepository;

    @Test
    public void findByMobile() {

        driverRepository.findAll();
    }
}