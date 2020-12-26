package iot.empiaurhouse.chiron.services.map;

import iot.empiaurhouse.chiron.model.NursePractitioner;
import iot.empiaurhouse.chiron.services.NPService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Profile({"default","HashMapService"})
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
        return this.findAll()
                .stream()
                .filter(nursePractitioner -> nursePractitioner.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public NursePractitioner findByFirstName(String firstName) {
        return this.findAll()
                .stream()
                .filter(nursePractitioner -> nursePractitioner.getFirstName().equalsIgnoreCase(firstName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public NursePractitioner findByPractitionerID(String practitionerID) {
        return this.findAll()
                .stream()
                .filter(nursePractitioner -> nursePractitioner.getPractitionerID().equalsIgnoreCase(practitionerID))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<NursePractitioner> findAllByLastNameLike(String lastName) {
        return null;
    }

    @Override
    public List<NursePractitioner> findAllByFirstNameLike(String firstName) {
        return null;
    }

    @Override
    public List<NursePractitioner> findAllByPractitionerIDLike(String practitionerID) {
        return null;
    }
}
