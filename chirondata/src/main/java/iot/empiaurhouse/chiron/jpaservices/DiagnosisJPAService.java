package iot.empiaurhouse.chiron.jpaservices;

import iot.empiaurhouse.chiron.model.Diagnosis;
import iot.empiaurhouse.chiron.model.DiagnosisLevel;
import iot.empiaurhouse.chiron.model.Patient;
import iot.empiaurhouse.chiron.repositories.DiagnosisRepository;
import iot.empiaurhouse.chiron.services.DiagnosisService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
@Profile("SpringDataJPA")
public class DiagnosisJPAService implements DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;

    public DiagnosisJPAService(DiagnosisRepository diagnosisRepository) {
        this.diagnosisRepository = diagnosisRepository;
    }

    @Override
    public Set<Diagnosis> findByPatient(Patient patient) {

        return diagnosisRepository.findByPatient(patient);
    }

    @Override
    public Set<Diagnosis> findByDiagnosisLevel(DiagnosisLevel diagnosisLevel) {
        return diagnosisRepository.findByDiagnosisLevel(diagnosisLevel);
    }

    @Override
    public Set<Diagnosis> findByDiagnosisLevelName(DiagnosisLevel diagnosisLevelName) {
        return diagnosisRepository.findByDiagnosisLevelName(diagnosisLevelName);
    }

    @Override
    public Diagnosis findByVisitDate(LocalDate visitDate) {
        return diagnosisRepository.findByVisitDate(visitDate);
    }

    @Override
    public Set<Diagnosis> findSetByVisitDate(LocalDate visitDate) {
        return diagnosisRepository.findSetByVisitDate(visitDate);
    }

    @Override
    public Set<Diagnosis> findAll() {
       Set<Diagnosis> diagnoses = new HashSet<>();
       diagnosisRepository.findAll().forEach(diagnoses::add);

        return diagnoses;
    }

    @Override
    public Diagnosis findById(Long aLong) {
        return diagnosisRepository.findById(aLong).orElse(null);
    }

    @Override
    public Diagnosis save(Diagnosis object) {
        return diagnosisRepository.save(object);
    }

    @Override
    public void delete(Diagnosis object) {
        diagnosisRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        diagnosisRepository.deleteById(aLong);
    }
}
