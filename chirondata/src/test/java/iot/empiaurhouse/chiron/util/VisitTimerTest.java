package iot.empiaurhouse.chiron.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VisitTimerTest {
    private VisitTimer visitTimer;


    @BeforeEach
    void setUp() {
        visitTimer = new VisitTimer();
    }

    @Test
    void getTimeSlots() {
        List<String> fetchedSlots = visitTimer.getTimeSlots();
        System.out.println(fetchedSlots);
        assertEquals(96, fetchedSlots.size());
    }

    @Test
    void getRxTypes(){
        RxTypes rxTypes = new RxTypes();
        rxTypes.initRxTypes();
        List<String> typeList =  rxTypes.getRxTypes();
        System.out.println(typeList);
    }

}