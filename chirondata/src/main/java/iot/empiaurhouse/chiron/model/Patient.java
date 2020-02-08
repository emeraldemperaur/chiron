package iot.empiaurhouse.chiron.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "blood_group")
    private String bloodGroup;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "insurance_vendor")
    private String insuranceVendor;
    @Column(name = "insurance_vendor_id")
    private String insuranceVendorID;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    private Set<Diagnosis> diagnoses = new HashSet<>();

    @Lob
    @Column(name = "image")
    private Byte[] image;


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

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", insuranceVendor='" + insuranceVendor + '\'' +
                ", insuranceVendorID='" + insuranceVendorID + '\'' +
                '}';
    }
}


