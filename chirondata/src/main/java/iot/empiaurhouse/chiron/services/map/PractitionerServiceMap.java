package iot.empiaurhouse.chiron.services.map;

import iot.empiaurhouse.chiron.model.Practitioner;
import iot.empiaurhouse.chiron.services.PractitionerService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
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
        return super.save(object.getId(), object);
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
}
