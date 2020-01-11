package iot.empiaurhouse.chiron.jpaservices;

import iot.empiaurhouse.chiron.model.Practitioner;
import iot.empiaurhouse.chiron.repositories.PractitionerRepository;
import iot.empiaurhouse.chiron.services.PractitionerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("SpringDataJPA")
public class PractitionerJPAService implements PractitionerService {

    private final PractitionerRepository practitionerRepository;

    public PractitionerJPAService(PractitionerRepository practitionerRepository) {
        this.practitionerRepository = practitionerRepository;
    }

    @Override
    public Practitioner findByPractitionerID(String practitionerID) {
        return practitionerRepository.findByPractitionerID(practitionerID);
    }

    @Override
    public Practitioner findByLastName(String lastName) {
        return practitionerRepository.findByLastName(lastName);
    }

    @Override
    public Practitioner findByFirstName(String firstName) {
        return practitionerRepository.findByFirstName(firstName);
    }

    @Override
    public Set<Practitioner> findAll() {
        Set<Practitioner> practitioners = new HashSet<>();
        practitionerRepository.findAll().forEach(practitioners::add);

        return practitioners;
    }

    @Override
    public Practitioner findById(Long aLong) {
        return practitionerRepository.findById(aLong).orElse(null);
    }

    @Override
    public Practitioner save(Practitioner object) {
        return practitionerRepository.save(object);
    }

    @Override
    public void delete(Practitioner object) {
        practitionerRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        practitionerRepository.deleteById(aLong);
    }
}
