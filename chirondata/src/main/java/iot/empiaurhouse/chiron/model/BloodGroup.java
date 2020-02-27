package iot.empiaurhouse.chiron.model;

public enum  BloodGroup {
    APLUS("A+"),
    AMINUS("A-"),
    BPLUS ("B+"),
    BMINUS ("B-"),
    OPLUS ("O+"),
    OMINUS ("O-"),
    ABPLUS ("AB+"),
    ABMINUS ("AB-");

    private final String bGroup;

    BloodGroup(String bG) {
        bGroup = bG;
    }

    public String toString() {
        return this.bGroup;
    }


}
