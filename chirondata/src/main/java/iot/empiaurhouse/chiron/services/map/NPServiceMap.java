package iot.empiaurhouse.chiron.services.map;

import iot.empiaurhouse.chiron.model.NursePractitioner;
import iot.empiaurhouse.chiron.services.CrudService;

import java.util.Set;

public class NPServiceMap extends AbstractMapService<NursePractitioner, Long> implements CrudService<NursePractitioner, Long> {

    @Override
    public Set<NursePractitioner> findAll() {
        return super.findAll();
    }

    @Override
    public NursePractitioner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public NursePractitioner save(NursePractitioner object) {
        return super.save(object.getId(), object);
    }

    @Override
    public void delete(NursePractitioner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
