package iot.empiaurhouse.chiron.util;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class RxTypes {

    List<String> RxTypeList;

    public void initRxTypes(){
        if (RxTypeList == null) {
            RxTypeList = new ArrayList<>();
            RxTypeList.add("Pill(s)");
            RxTypeList.add("Tablet(s)");
            RxTypeList.add("Capsule(s)");
            RxTypeList.add("Teaspoon(s)");
            RxTypeList.add("Drop(s)");
            RxTypeList.add("Bag(s)");
        }
    }


    public List<String> getRxTypes(){
        return RxTypeList;
    }

}
