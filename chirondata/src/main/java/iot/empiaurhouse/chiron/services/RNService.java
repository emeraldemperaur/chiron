package iot.empiaurhouse.chiron.services;

import iot.empiaurhouse.chiron.model.RegisteredNurse;


public interface RNService extends CrudService<RegisteredNurse,Long> {

    RegisteredNurse findByLastName(String lastName);
    RegisteredNurse findByFirstName(String firstName);
    RegisteredNurse findByPractitionerID(String practitionerID);



}
