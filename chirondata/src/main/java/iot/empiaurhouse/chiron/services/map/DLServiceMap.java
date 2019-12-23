package iot.empiaurhouse.chiron.services.map;

import iot.empiaurhouse.chiron.model.DiagnosisLevel;
import iot.empiaurhouse.chiron.services.CrudService;

import java.util.Set;

public class DLServiceMap extends AbstractMapService<DiagnosisLevel, Long> implements CrudService<DiagnosisLevel, Long> {

    @Override
    public Set<DiagnosisLevel> findAll() {
        return super.findAll();
    }

    @Override
    public DiagnosisLevel findById(Long id) {
        return super.findById(id);
    }

    @Override
    public DiagnosisLevel save(DiagnosisLevel object) {
        return super.save(object.getId(),object);
    }

    @Override
    public void delete(DiagnosisLevel object) {
        super.delete(object);

    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
