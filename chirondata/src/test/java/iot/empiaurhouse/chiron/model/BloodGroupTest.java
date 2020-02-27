package iot.empiaurhouse.chiron.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class BloodGroupTest {

    List<BloodGroup> bloodGroups;

    @BeforeEach
    void setUp() {
        bloodGroups = Arrays.asList(BloodGroup.values());
    }

    @Test
    void testToString() {
        for(BloodGroup b : bloodGroups){
            System.out.println("Found BloodGroup: " + b);
        }
        System.out.println("All BloodGroup(s) found: " + bloodGroups);
    }
}