package com.empiaurhouse.chiron.model;

import java.util.Set;

public class Patient {

    private String firstName;
    private String lastName;
    private String insuranceVendor;
    private String insuranceVendorID;
    private Set<Diagnosis> diagnoses;

    public Set<Diagnosis> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(Set<Diagnosis> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getInsuranceVendor() {
        return insuranceVendor;
    }

    public void setInsuranceVendor(String insuranceVendor) {
        this.insuranceVendor = insuranceVendor;
    }

    public String getInsuranceVendorID() {
        return insuranceVendorID;
    }

    public void setInsuranceVendorID(String insuranceVendorID) {
        this.insuranceVendorID = insuranceVendorID;
    }
}
