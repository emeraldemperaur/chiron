package iot.empiaurhouse.chiron.services;

import iot.empiaurhouse.chiron.model.DiagnosisLevel;


public interface DiagnosisLevelService extends CrudService<DiagnosisLevel,Long> {

    DiagnosisLevel findByDiagnosisLevelNameLike(String diagnosisLevelName);



}
