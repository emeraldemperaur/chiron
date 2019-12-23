package iot.empiaurhouse.chiron.services.map;

import iot.empiaurhouse.chiron.model.Prescription;
import iot.empiaurhouse.chiron.services.CrudService;

import java.util.Set;

public class PrescriptionServiceMap extends AbstractMapService<Prescription, Long> implements CrudService<Prescription, Long> {

    @Override
    public Set<Prescription> findAll() {
        return super.findAll();
    }

    @Override
    public Prescription findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Prescription save(Prescription object) {
        return super.save(object.getId(), object);
    }

    @Override
    public void delete(Prescription object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
