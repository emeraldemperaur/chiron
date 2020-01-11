package iot.empiaurhouse.chiron.services.map;

import iot.empiaurhouse.chiron.model.Practitioner;
import iot.empiaurhouse.chiron.services.PractitionerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default","HashMapService"})
public class PractitionerServiceMap extends AbstractMapService<Practitioner, Long> implements PractitionerService {

    @Override
    public Set<Practitioner> findAll() {
        return super.findAll();
    }

    @Override
    public Practitioner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Practitioner save(Practitioner object) {
        return super.save(object);
    }

    @Override
    public void delete(Practitioner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Practitioner findByPractitionerID(String practitionerID) {
        return null;
    }

    @Override
    public Practitioner findByLastName(String lastName) {
        return null;
    }

    @Override
    public Practitioner findByFirstName(String firstName) {
        return null;
    }
}
