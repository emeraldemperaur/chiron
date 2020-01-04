package iot.empiaurhouse.chiron.model;

import java.util.HashSet;
import java.util.Set;

public class Doctor extends Practitioner {

    private Set<Speciality> specialities = new HashSet<>();

    public Set<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Set<Speciality> specialities) {
        this.specialities = specialities;
    }
}
