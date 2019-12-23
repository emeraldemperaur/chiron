package iot.empiaurhouse.chiron.services.map;

import iot.empiaurhouse.chiron.model.Diagnosis;
import iot.empiaurhouse.chiron.services.CrudService;

import java.util.Set;

public class DiagnosisServiceMap extends AbstractMapService<Diagnosis, Long> implements CrudService<Diagnosis, Long> {

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
        return super.save(object.getId(), object);
    }

    @Override
    public void delete(Diagnosis object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
