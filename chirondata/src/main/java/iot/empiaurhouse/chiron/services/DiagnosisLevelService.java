package iot.empiaurhouse.chiron.services;

import iot.empiaurhouse.chiron.model.DiagnosisLevel;

import java.util.Set;

public interface DiagnosisLevelService {

    DiagnosisLevel findById(Long id);
    DiagnosisLevel save(DiagnosisLevel diagnosisLevel);
    Set<DiagnosisLevel> findAll();

}
