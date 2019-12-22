package iot.empiaurhouse.chiron.services;

import iot.empiaurhouse.chiron.model.Diagnosis;
import iot.empiaurhouse.chiron.model.Prescription;

import java.time.LocalDate;
import java.util.Set;

public interface PrescriptionService extends CrudService<Prescription,Long> {

    Prescription findByPrescriptionName(String prescriptionName);
    Prescription findByPrescriber(String prescribedBy);
    Prescription findByPrescriptionDate(LocalDate prescriptionDate);
    Prescription findByDiagnosis(Diagnosis diagnosis);
    Set<Prescription> findSetByDiagnosis(Diagnosis diagnosis);


}
