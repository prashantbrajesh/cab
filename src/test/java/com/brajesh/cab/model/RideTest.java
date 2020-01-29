package com.brajesh.cab.model;

import lombok.val;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Created by braj on 16/01/20.
 */
public class RideTest {
    @Test
    public void setId() throws Exception {

        final Stream<Character> numbers = "123167357".chars().mapToObj(i->(char)i);
        final int chunkSize = 3;
        int pos = "123167357".length()/chunkSize;
        final AtomicInteger counter = new AtomicInteger();

        final Collection<List<Character>> result = numbers
                .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / chunkSize))
                .values();
        for (List<Character> l: result
             ) {
            Collections.sort(l);
        }
        String s = "";
        List<List<Character>> list = new ArrayList<>(result);


        double n=25-1;
        for (int i = 1; i <=pos ; i++) {
            double p = n/Math.pow(chunkSize,pos-i);
            double r = n%Math.pow(chunkSize,pos-i);
//            BigDecimal b = new BigDecimal("jh");

                    Integer.toHexString(4);
            n=r;
            System.out.println((int)p);
            s+=list.get(i-1).get((int)p);
        }



        System.out.println(result);
        System.out.println(s);

}
}