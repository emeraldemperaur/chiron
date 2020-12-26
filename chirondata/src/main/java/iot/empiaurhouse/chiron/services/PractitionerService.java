package iot.empiaurhouse.chiron.services;

import iot.empiaurhouse.chiron.model.Practitioner;

import java.util.List;


public interface PractitionerService extends CrudService<Practitioner,Long> {


    Practitioner findByPractitionerID(String practitionerID);
    Practitioner findByLastName(String lastName);
    Practitioner findByFirstName(String firstName);
    List<Practitioner> findAllByLastNameLike(String lastName);
    List<Practitioner> findAllByFirstNameLike(String firstName);
    List<Practitioner> findAllByPractitionerIDLike(String practitionerID);

}
