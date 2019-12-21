package iot.empiaurhouse.chiron.services;

import iot.empiaurhouse.chiron.model.Prescription;

import java.util.Set;

public interface PrescriptionService {

    Prescription findById(Long id);
    Prescription save(Prescription prescription);
    Set<Prescription> findAll();

}
