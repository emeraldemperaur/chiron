package iot.empiaurhouse.chiron.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Patient extends BaseEntity {

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String phoneNumber;
    private String insuranceVendor;
    private String insuranceVendorID;
    private LocalDate birthDate;
    private Set<Diagnosis> diagnoses = new HashSet<>();


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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Set<Diagnosis> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(Set<Diagnosis> diagnoses) {
        this.diagnoses = diagnoses;
    }
}
