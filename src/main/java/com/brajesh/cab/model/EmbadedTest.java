package com.brajesh.cab.model;

import lombok.Data;

import javax.persistence.Embeddable;

/**
 * Created by braj on 12/01/20.
 */

@Data
@Embeddable
public class EmbadedTest {
    private int x;
    private int y;
}
