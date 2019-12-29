package iot.empiaurhouse.chiron.services.map;

import iot.empiaurhouse.chiron.model.RegisteredNurse;
import iot.empiaurhouse.chiron.services.RNService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RNServiceMap extends AbstractMapService<RegisteredNurse, Long> implements RNService {


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

    @Override
    public RegisteredNurse findByLastName(String lastName) {
        return null;
    }

    @Override
    public RegisteredNurse findByFirstName(String firstName) {
        return null;
    }

    @Override
    public RegisteredNurse findByPractitionerID(String practitionerID) {
        return null;
    }
}
