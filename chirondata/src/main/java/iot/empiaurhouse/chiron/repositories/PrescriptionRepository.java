package iot.empiaurhouse.chiron.repositories;

import iot.empiaurhouse.chiron.model.Diagnosis;
import iot.empiaurhouse.chiron.model.Prescription;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
@Profile("SpringDataJPA")
public interface PrescriptionRepository extends CrudRepository<Prescription, Long> {


    Prescription findByPrescriptionName(String prescriptionName);
    Prescription findByPrescribedBy(String prescribedBy);
    Prescription findByPrescriptionDate(LocalDate prescriptionDate);
    Prescription findByDiagnosis(Diagnosis diagnosis);
    Set<Prescription> findSetByDiagnosis(Diagnosis diagnosis);



}
