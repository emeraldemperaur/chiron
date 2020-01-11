package iot.empiaurhouse.chiron.repositories;

import iot.empiaurhouse.chiron.model.Diagnosis;
import iot.empiaurhouse.chiron.model.Patient;
import iot.empiaurhouse.chiron.model.Visit;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Set;

public interface VisitRepository extends CrudRepository<Visit, Long> {

    Visit findByVisitDate(LocalDate visitDate);
    Set<Visit> findSetByVisitDiagnosis(Diagnosis visitDiagnosis);
    Set<Visit> findSetByVisitingPatient(Patient visitingPatient);
    Visit findByVisitingPatient(Patient visitingPatient);
    Visit findByVisitDiagnosis(Diagnosis visitDiagnosis);
    Visit findByHostPractitioner(String hostPractitioner);



}
