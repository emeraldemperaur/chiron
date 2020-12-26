package iot.empiaurhouse.chiron.services.map;

import iot.empiaurhouse.chiron.model.RegisteredNurse;
import iot.empiaurhouse.chiron.services.RNService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Profile({"default","HashMapService"})
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
        return super.save(object);
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
        return this.findAll()
                .stream()
                .filter(registeredNurse -> registeredNurse.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public RegisteredNurse findByFirstName(String firstName) {
        return this.findAll()
                .stream()
                .filter(registeredNurse -> registeredNurse.getFirstName().equalsIgnoreCase(firstName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public RegisteredNurse findByPractitionerID(String practitionerID) {
        return this.findAll()
                .stream()
                .filter(registeredNurse -> registeredNurse.getPractitionerID().equalsIgnoreCase(practitionerID))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<RegisteredNurse> findAllByLastNameLike(String lastName) {
        return null;
    }

    @Override
    public List<RegisteredNurse> findAllByFirstNameLike(String firstName) {
        return null;
    }

    @Override
    public List<RegisteredNurse> findAllByPractitionerIDLike(String practitionerID) {
        return null;
    }
}
