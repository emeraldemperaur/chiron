package iot.empiaurhouse.chiron.util;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class VisitTimer {

    public List<String> getTimeSlots(){
        List<String> timeSlotList = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            timeSlotList.add("0" + i + ":00");
            timeSlotList.add("0" + i + ":15");
            timeSlotList.add("0" + i + ":30");
            timeSlotList.add("0" + i + ":45");
        }
        for (int t = 10; t < 24; t++){
            timeSlotList.add(t + ":00");
            timeSlotList.add(t + ":15");
            timeSlotList.add(t + ":30");
            timeSlotList.add(t + ":45");
        }
        return timeSlotList;
    }



}
