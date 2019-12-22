package iot.empiaurhouse.chiron.services;

import iot.empiaurhouse.chiron.model.Doctor;


public interface DoctorService extends CrudService<Doctor,Long> {


    Doctor findByLastName(String lastName);
    Doctor findByFirstName(String firstName);
    Doctor findByPractitionerID(String practitionerID);


}
