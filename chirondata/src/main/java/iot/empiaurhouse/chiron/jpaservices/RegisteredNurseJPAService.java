package iot.empiaurhouse.chiron.jpaservices;

import iot.empiaurhouse.chiron.model.RegisteredNurse;
import iot.empiaurhouse.chiron.repositories.RegisteredNurseRepository;
import iot.empiaurhouse.chiron.services.RNService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("SpringDataJPA")
public class RegisteredNurseJPAService implements RNService {

    private final RegisteredNurseRepository registeredNurseRepository;

    public RegisteredNurseJPAService(RegisteredNurseRepository registeredNurseRepository) {
        this.registeredNurseRepository = registeredNurseRepository;
    }

    @Override
    public RegisteredNurse findByLastName(String lastName) {
        return registeredNurseRepository.findByLastName(lastName);
    }

    @Override
    public RegisteredNurse findByFirstName(String firstName) {
        return registeredNurseRepository.findByFirstName(firstName);
    }

    @Override
    public RegisteredNurse findByPractitionerID(String practitionerID) {
        return registeredNurseRepository.findByPractitionerID(practitionerID);
    }

    @Override
    public Set<RegisteredNurse> findAll() {
        Set<RegisteredNurse> registeredNurses = new HashSet<>();
        registeredNurseRepository.findAll().forEach(registeredNurses::add);

        return registeredNurses;
    }

    @Override
    public RegisteredNurse findById(Long aLong) {
        return registeredNurseRepository.findById(aLong).orElse(null);
    }

    @Override
    public RegisteredNurse save(RegisteredNurse object) {
        return registeredNurseRepository.save(object);
    }

    @Override
    public void delete(RegisteredNurse object) {
        registeredNurseRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        registeredNurseRepository.deleteById(aLong);
    }
}
