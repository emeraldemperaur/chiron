package iot.empiaurhouse.chiron.repositories;

import iot.empiaurhouse.chiron.model.DiagnosisLevel;
import org.springframework.data.repository.CrudRepository;

public interface DiagnosisLevelRepository extends CrudRepository<DiagnosisLevel, Long> {

    DiagnosisLevel findByDiagnosisLevelNameLike(String diagnosisLevelName);
}
