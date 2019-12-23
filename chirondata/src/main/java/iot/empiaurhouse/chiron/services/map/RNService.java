package iot.empiaurhouse.chiron.services.map;

import iot.empiaurhouse.chiron.model.RegisteredNurse;
import iot.empiaurhouse.chiron.services.CrudService;

import java.util.Set;

public class RNService extends AbstractMapService<RegisteredNurse, Long> implements CrudService<RegisteredNurse, Long> {


    @Override
    public Set<RegisteredNurse> findAll() {
        return super.findAll();
    }

    @Override
    public RegisteredNurse findById(Long id) {
        return super.findById(id);
    }

    @Override
    public RegisteredNurse save(RegisteredNurse object) {
        return super.save(object.getId(), object);
    }

    @Override
    public void delete(RegisteredNurse object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
