package iot.empiaurhouse.chiron.services;

import iot.empiaurhouse.chiron.model.Diagnosis;
import iot.empiaurhouse.chiron.model.Patient;
import iot.empiaurhouse.chiron.model.Visit;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface VisitService extends CrudService<Visit, Long> {

    Visit findByVisitDate(LocalDate visitDate);
    Set<Visit> findSetByVisitDiagnosis(Diagnosis visitDiagnosis);
    Set<Visit> findSetByVisitingPatient(Patient visitingPatient);
    Visit findByVisitingPatient(Patient visitingPatient);
    Visit findByVisitDiagnosis(Diagnosis visitDiagnosis);
    Visit findByHostPractitioner(String hostPractitioner);
    List<Visit> findAllByHostPractitionerLike(String hostPractitioner);
    List<Visit> findAllByHostPractitionerIDLike(String hostPractitionerID);
    List<Visit> findAllByVisitTimeLike(String visitTime);
    List<Visit> findAllByVisitDescriptionContains(String visitDescription);
    List<Visit> findAllByVisitDateLike(String visitDate);
    List<Visit> findAllByVisitDateBefore(String visitDate);
    List<Visit> findAllByVisitDateAfter(String visitDate);
    List<Visit> findAllByVisitDateBetween(String visitDate, String visitDate2);
    List<Visit> findAllByVisitingPatientLike(Patient patient);



}
