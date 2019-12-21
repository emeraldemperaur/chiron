package iot.empiaurhouse.chiron.services;

import iot.empiaurhouse.chiron.model.Diagnosis;

import java.util.Set;

public interface DiagnosisService {

    Diagnosis findById(Long id);
    Diagnosis save(Diagnosis diagnosis);
    Set<Diagnosis> findAll();


}
