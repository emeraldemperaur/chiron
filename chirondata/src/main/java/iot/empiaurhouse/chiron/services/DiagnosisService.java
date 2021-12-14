package iot.empiaurhouse.chiron.services;

import iot.empiaurhouse.chiron.model.Diagnosis;
import iot.empiaurhouse.chiron.model.DiagnosisLevel;
import iot.empiaurhouse.chiron.model.Patient;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface DiagnosisService extends CrudService<Diagnosis,Long> {


    Set<Diagnosis> findByPatient(Patient patient);
    Set<Diagnosis> findByDiagnosisLevel(DiagnosisLevel diagnosisLevel);
    Set<Diagnosis> findByDiagnosisLevelName(DiagnosisLevel diagnosisLevelName);
    Diagnosis findByVisitDate(LocalDate visitDate);
    Set<Diagnosis> findSetByVisitDate(LocalDate visitDate);
    List<Diagnosis> findAllByDiagnosisSynopsisLike(String diagnosisSynopsis);
    List<Diagnosis> findAllByDiagnosisDetailsLike(String diagnosisDetails);
    List<Diagnosis> findAllByDiagnosisLevelLike(String diagnosisLevel);
    List<Diagnosis> findAllByVisitDateLike(String visitDate);
    List<Diagnosis> findAllByVisitDateBefore(String visitDate);
    List<Diagnosis> findAllByVisitDateAfter(String visitDate);
    List<Diagnosis> findAllByVisitDateBetween(String visitDate, String visitDate2);
    List<Diagnosis> findAllByPatientLike(Patient patient);




}
