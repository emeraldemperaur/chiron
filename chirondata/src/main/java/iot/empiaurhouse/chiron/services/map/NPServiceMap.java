package iot.empiaurhouse.chiron.services.map;

import iot.empiaurhouse.chiron.model.NursePractitioner;
import iot.empiaurhouse.chiron.services.NPService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class NPServiceMap extends AbstractMapService<NursePractitioner, Long> implements NPService {

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
        return super.save(object);
    }

    @Override
    public void delete(NursePractitioner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public NursePractitioner findByLastName(String lastName) {
        return null;
    }

    @Override
    public NursePractitioner findByFirstName(String firstName) {
        return null;
    }

    @Override
    public NursePractitioner findByPractitionerID(String practitionerID) {
        return null;
    }
}
