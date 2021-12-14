package iot.empiaurhouse.chiron.repositories;

import iot.empiaurhouse.chiron.model.Diagnosis;
import iot.empiaurhouse.chiron.model.DiagnosisLevel;
import iot.empiaurhouse.chiron.model.Patient;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface DiagnosisRepository extends CrudRepository<Diagnosis, Long> {




    Set<Diagnosis> findByPatient(Patient patient);
    //Set<Diagnosis> findByDiagnosisLevel(DiagnosisLevel diagnosisLevel);
    //Set<Diagnosis> findByDiagnosisLevelName(DiagnosisLevel diagnosisLevelName);
    Diagnosis findByVisitDate(LocalDate visitDate);
    Set<Diagnosis> findSetByVisitDate(LocalDate visitDate);
    List<Diagnosis> findAllByDiagnosisSynopsisLike(String diagnosisSynopsis);
    List<Diagnosis> findAllByDiagnosisDetailsContains(String diagnosisDetails);
    List<Diagnosis> findAllByDiagnosisLevelLike(DiagnosisLevel diagnosisLevel);
    List<Diagnosis> findAllByVisitDateLike(LocalDate visitDate);
    List<Diagnosis> findAllByVisitDateBefore(LocalDate visitDate);
    List<Diagnosis> findAllByVisitDateAfter(LocalDate visitDate);
    List<Diagnosis> findAllByVisitDateBetween(LocalDate visitDate, LocalDate visitDate2);
    List<Diagnosis> findAllByPatientLike(Patient patient);





}
