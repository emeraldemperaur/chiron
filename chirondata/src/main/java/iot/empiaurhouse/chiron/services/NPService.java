package iot.empiaurhouse.chiron.services;

import iot.empiaurhouse.chiron.model.NursePractitioner;

import java.util.List;


public interface NPService extends CrudService<NursePractitioner,Long> {


    NursePractitioner findByLastName(String lastName);
    NursePractitioner findByFirstName(String firstName);
    NursePractitioner findByPractitionerID(String practitionerID);
    List<NursePractitioner> findAllByLastNameLike(String lastName);
    List<NursePractitioner> findAllByFirstNameLike(String firstName);
    List<NursePractitioner> findAllByPractitionerIDLike(String practitionerID);


}
