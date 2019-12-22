package iot.empiaurhouse.chiron.services;

import iot.empiaurhouse.chiron.model.Practitioner;


public interface PractitionerService extends CrudService<Practitioner,Long> {


    Practitioner findByPractitionerID(String practitionerID);


}