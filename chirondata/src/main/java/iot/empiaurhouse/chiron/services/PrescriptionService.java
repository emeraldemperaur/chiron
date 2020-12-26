package iot.empiaurhouse.chiron.services;

import iot.empiaurhouse.chiron.model.Diagnosis;
import iot.empiaurhouse.chiron.model.Patient;
import iot.empiaurhouse.chiron.model.Prescription;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface PrescriptionService extends CrudService<Prescription,Long> {

    Prescription findByPrescriptionName(String prescriptionName);
    Prescription findByPrescriber(String prescribedBy);
    Prescription findByPrescriptionDate(LocalDate prescriptionDate);
    Prescription findByDiagnosis(Diagnosis diagnosis);
    Set<Prescription> findSetByDiagnosis(Diagnosis diagnosis);
    List<Prescription> findAllByPrescriptionNameLike(String prescriptionName);
    List<Prescription> findAllByPrescribedByLike(String prescribedBy);
    List<Prescription> findAllByPrescriptionPractitionerIDLike(String prescriptionPractitionerID);
    List<Prescription> findAllByPrescriptionDateLike(String prescriptionDate);
    List<Prescription> findAllByPrescriptionDateBefore(String prescriptionDate);
    List<Prescription> findAllByPrescriptionDateAfter(String prescriptionDate);
    List<Prescription> findAllByPrescriptionDateBetween(String prescriptionDate, String prescriptionDate2);
    List<Prescription> findAllByPatientLike(Patient patient);



}
