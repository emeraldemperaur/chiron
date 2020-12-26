package iot.empiaurhouse.chiron.services;

import iot.empiaurhouse.chiron.model.Doctor;

import java.util.List;


public interface DoctorService extends CrudService<Doctor,Long> {


    Doctor findByLastName(String lastName);
    Doctor findByFirstName(String firstName);
    Doctor findByPractitionerID(String practitionerID);
    List<Doctor> findAllByLastNameLike(String lastName);
    List<Doctor> findAllByFirstNameLike(String firstName);
    List<Doctor> findAllByPractitionerIDLike(String practitionerID);


}
