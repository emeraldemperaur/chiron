package iot.empiaurhouse.chiron.services;

import iot.empiaurhouse.chiron.model.Practitioner;

import java.util.Set;

public interface PractitionerService {

    Practitioner findById(Long id);
    Practitioner save(Practitioner practitioner);
    Set<Practitioner> findAll();

}
