package iot.empiaurhouse.chiron.services;

import iot.empiaurhouse.chiron.model.NursePractitioner;

import java.util.Set;

public interface NPService {

    NursePractitioner findById(Long id);
    NursePractitioner save(NursePractitioner nursePractitioner);
    Set<NursePractitioner> findAll();


}
