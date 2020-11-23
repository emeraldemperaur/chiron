package iot.empiaurhouse.chiron.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "specialities")
public class Speciality extends BaseEntity {

    @Column(name = "speciality_description")
    private String specialityDescription;

    public String getSpecialityDescription() {
        return specialityDescription;
    }

    public void setSpecialityDescription(String specialityDescription) {
        this.specialityDescription = specialityDescription;
    }

    @Override
    public String toString() {
        return " " + specialityDescription + " " + "\n";
    }
}
