package iot.empiaurhouse.chiron.services.map;

import iot.empiaurhouse.chiron.model.Doctor;
import iot.empiaurhouse.chiron.services.DoctorService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class DoctorServiceMap extends AbstractMapService<Doctor, Long> implements DoctorService {

    @Override
    public Set<Doctor> findAll() {
        return super.findAll();
    }

    @Override
    public Doctor findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Doctor save(Doctor object) {
        return super.save(object);
    }

    @Override
    public void delete(Doctor object) {
        super.delete(object);

    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }


    @Override
    public Doctor findByLastName(String lastName) {
        return null;
    }

    @Override
    public Doctor findByFirstName(String firstName) {
        return null;
    }

    @Override
    public Doctor findByPractitionerID(String practitionerID) {
        return null;
    }
}
