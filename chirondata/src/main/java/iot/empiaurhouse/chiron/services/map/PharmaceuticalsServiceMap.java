package iot.empiaurhouse.chiron.services.map;


import iot.empiaurhouse.chiron.model.Pharmaceuticals;
import iot.empiaurhouse.chiron.services.CrudService;

import java.util.Set;

public class PharmaceuticalsServiceMap extends AbstractMapService<Pharmaceuticals, Long> implements CrudService<Pharmaceuticals, Long> {

    @Override
    public Set<Pharmaceuticals> findAll() {
        return super.findAll();
    }

    @Override
    public Pharmaceuticals findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Pharmaceuticals save(Pharmaceuticals object) {
        return super.save(object.getId(), object);
    }

    @Override
    public void delete(Pharmaceuticals object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
