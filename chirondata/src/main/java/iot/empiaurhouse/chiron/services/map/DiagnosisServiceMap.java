package iot.empiaurhouse.chiron.services.map;

import iot.empiaurhouse.chiron.model.Diagnosis;
import iot.empiaurhouse.chiron.model.DiagnosisLevel;
import iot.empiaurhouse.chiron.model.Patient;
import iot.empiaurhouse.chiron.services.DiagnosisService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Profile({"default","HashMapService"})
public class DiagnosisServiceMap extends AbstractMapService<Diagnosis, Long> implements DiagnosisService {

    @Override
    public Set<Diagnosis> findAll() {
        return super.findAll();
    }

    @Override
    public Diagnosis findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Diagnosis save(Diagnosis object) {
        return super.save(object);
    }

    @Override
    public void delete(Diagnosis object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Set<Diagnosis> findByPatient(Patient patient) {

        return this.findAll()
                .stream()
                .filter(diagnosis -> diagnosis.getPatient().getLastName().equalsIgnoreCase(patient.getLastName()))
                .collect(Collectors.toCollection(HashSet::new));

    }

    @Override
    public Set<Diagnosis> findByDiagnosisLevel(DiagnosisLevel diagnosisLevel) {

        return this.findAll()
                .stream()
                .filter(diagnosis -> diagnosis.getDiagnosisLevel().getDiagnosisLevelName().equalsIgnoreCase(diagnosisLevel.getDiagnosisLevelName()))
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public Set<Diagnosis> findByDiagnosisLevelName(DiagnosisLevel diagnosisLevelName) {
        return this.findAll()
                .stream()
                .filter(diagnosis -> diagnosis.getDiagnosisLevel().getDiagnosisLevelName()
                        .equalsIgnoreCase(diagnosisLevelName.getDiagnosisLevelName()))
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public Diagnosis findByVisitDate(LocalDate visitDate) {
        return this.findAll()
                .stream()
                .filter(diagnosis -> diagnosis.getVisitDate().toString().equalsIgnoreCase(visitDate.toString()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Set<Diagnosis> findSetByVisitDate(LocalDate visitDate) {
        return this.findAll()
                .stream()
                .filter(diagnosis -> diagnosis.getVisitDate().toString().equalsIgnoreCase(visitDate.toString()))
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public List<Diagnosis> findAllByDiagnosisSynopsisLike(String diagnosisSynopsis) {
        return null;
    }

    @Override
    public List<Diagnosis> findAllByDiagnosisLevelLike(DiagnosisLevel diagnosisLevel) {
        return null;
    }

    @Override
    public List<Diagnosis> findAllByVisitDateLike(String visitDate) {
        return null;
    }

    @Override
    public List<Diagnosis> findAllByVisitDateBefore(String visitDate) {
        return null;
    }

    @Override
    public List<Diagnosis> findAllByVisitDateAfter(String visitDate) {
        return null;
    }

    @Override
    public List<Diagnosis> findAllByVisitDateBetween(String visitDate, String visitDate2) {
        return null;
    }
}
