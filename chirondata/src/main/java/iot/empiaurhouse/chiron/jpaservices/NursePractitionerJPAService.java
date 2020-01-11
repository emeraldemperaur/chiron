package iot.empiaurhouse.chiron.jpaservices;

import iot.empiaurhouse.chiron.model.NursePractitioner;
import iot.empiaurhouse.chiron.repositories.NursePractitionerRepository;
import iot.empiaurhouse.chiron.services.NPService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("SpringDataJPA")
public class NursePractitionerJPAService implements NPService {

    private final NursePractitionerRepository nursePractitionerRepository;

    public NursePractitionerJPAService(NursePractitionerRepository nursePractitionerRepository) {
        this.nursePractitionerRepository = nursePractitionerRepository;
    }

    @Override
    public NursePractitioner findByLastName(String lastName) {
        return nursePractitionerRepository.findByLastName(lastName);
    }

    @Override
    public NursePractitioner findByFirstName(String firstName) {
        return nursePractitionerRepository.findByFirstName(firstName);
    }

    @Override
    public NursePractitioner findByPractitionerID(String practitionerID) {
        return nursePractitionerRepository.findByPractitionerID(practitionerID);
    }

    @Override
    public Set<NursePractitioner> findAll() {
        Set<NursePractitioner> nursePractitioners = new HashSet<>();
        nursePractitionerRepository.findAll().forEach(nursePractitioners::add);

        return nursePractitioners;
    }

    @Override
    public NursePractitioner findById(Long aLong) {
        return nursePractitionerRepository.findById(aLong).orElse(null);
    }

    @Override
    public NursePractitioner save(NursePractitioner object) {
        return nursePractitionerRepository.save(object);
    }

    @Override
    public void delete(NursePractitioner object) {
        nursePractitionerRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        nursePractitionerRepository.deleteById(aLong);
    }
}
