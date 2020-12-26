package iot.empiaurhouse.chiron.services;

import iot.empiaurhouse.chiron.model.RegisteredNurse;

import java.util.List;


public interface RNService extends CrudService<RegisteredNurse,Long> {

    RegisteredNurse findByLastName(String lastName);
    RegisteredNurse findByFirstName(String firstName);
    RegisteredNurse findByPractitionerID(String practitionerID);
    List<RegisteredNurse> findAllByLastNameLike(String lastName);
    List<RegisteredNurse> findAllByFirstNameLike(String firstName);
    List<RegisteredNurse> findAllByPractitionerIDLike(String practitionerID);


}
