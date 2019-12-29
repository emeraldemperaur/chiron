package iot.empiaurhouse.chiron.services.map;

import iot.empiaurhouse.chiron.model.Diagnosis;
import iot.empiaurhouse.chiron.model.DiagnosisLevel;
import iot.empiaurhouse.chiron.model.Patient;
import iot.empiaurhouse.chiron.services.DiagnosisService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
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
        return null;
    }

    @Override
    public Set<Diagnosis> findByDiagnosisLevel(DiagnosisLevel diagnosisLevel) {
        return null;
    }

    @Override
    public Set<Diagnosis> findByDiagnosisLevelName(DiagnosisLevel diagnosisLevelName) {
        return null;
    }

    @Override
    public Diagnosis findByVisitDate(LocalDate visitDate) {
        return null;
    }

    @Override
    public Set<Diagnosis> findSetByVisitDate(LocalDate visitDate) {
        return null;
    }
}
