package iot.empiaurhouse.chiron.jpaservices;

import iot.empiaurhouse.chiron.model.Doctor;
import iot.empiaurhouse.chiron.repositories.DoctorRepository;
import iot.empiaurhouse.chiron.services.DoctorService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("SpringDataJPA")
public class DoctorJPAService implements DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorJPAService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }


    @Override
    public Doctor findByLastName(String lastName) {
        return doctorRepository.findByLastName(lastName);
    }

    @Override
    public Doctor findByFirstName(String firstName) {
        return doctorRepository.findByFirstName(firstName);
    }

    @Override
    public Doctor findByPractitionerID(String practitionerID) {
        return doctorRepository.findByPractitionerID(practitionerID);
    }

    @Override
    public Set<Doctor> findAll() {
        Set<Doctor> doctors = new HashSet<>();
        doctorRepository.findAll().forEach(doctors::add);

        return doctors;
    }

    @Override
    public Doctor findById(Long aLong) {
        return doctorRepository.findById(aLong).orElse(null);
    }

    @Override
    public Doctor save(Doctor object) {
        return doctorRepository.save(object);
    }

    @Override
    public void delete(Doctor object) {
        doctorRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        doctorRepository.deleteById(aLong);
    }
}
