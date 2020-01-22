package iot.empiaurhouse.chiron.model;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class PractitionerBaseJPA extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "practitioner_id")
    private String practitionerID;

    @Column(name = "contact_info")
    private String contactInfo;

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

    public String getPractitionerID() {
        return practitionerID;
    }

    public void setPractitionerID(String practitionerID) {
        this.practitionerID = practitionerID;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "PractitionerBaseJPA{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", practitionerID='" + practitionerID + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                '}';
    }
}
