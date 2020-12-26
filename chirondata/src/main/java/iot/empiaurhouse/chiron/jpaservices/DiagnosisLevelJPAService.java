package iot.empiaurhouse.chiron.jpaservices;

import iot.empiaurhouse.chiron.model.DiagnosisLevel;
import iot.empiaurhouse.chiron.repositories.DiagnosisLevelRepository;
import iot.empiaurhouse.chiron.services.DiagnosisLevelService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("SpringDataJPA")
public class DiagnosisLevelJPAService implements DiagnosisLevelService {

    private final DiagnosisLevelRepository diagnosisLevelRepository;

    public DiagnosisLevelJPAService(DiagnosisLevelRepository diagnosisLevelRepository) {
        this.diagnosisLevelRepository = diagnosisLevelRepository;
    }


    @Override
    public Set<DiagnosisLevel> findAll() {
        Set<DiagnosisLevel> diagnosisLevels = new HashSet<>();
        diagnosisLevelRepository.findAll().forEach(diagnosisLevels::add);

        return diagnosisLevels;
    }

    @Override
    public DiagnosisLevel findById(Long aLong) {
        return diagnosisLevelRepository.findById(aLong).orElse(null);
    }

    @Override
    public DiagnosisLevel save(DiagnosisLevel object) {
        return diagnosisLevelRepository.save(object);
    }

    @Override
    public void delete(DiagnosisLevel object) {
        diagnosisLevelRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        diagnosisLevelRepository.deleteById(aLong);
    }

    @Override
    public DiagnosisLevel findByDiagnosisLevelNameLike(String diagnosisLevelName) {
        return diagnosisLevelRepository.findByDiagnosisLevelNameLike(diagnosisLevelName);
    }
}
