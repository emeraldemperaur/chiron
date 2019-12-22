package iot.empiaurhouse.chiron.services;

import iot.empiaurhouse.chiron.model.NursePractitioner;


public interface NPService extends CrudService<NursePractitioner,Long> {


    NursePractitioner findByLastName(String lastName);
    NursePractitioner findByFirstName(String firstName);
    NursePractitioner findByPractitionerID(String practitionerID);


}
