package iot.empiaurhouse.chiron.services.map;

import iot.empiaurhouse.chiron.model.Doctor;
import iot.empiaurhouse.chiron.model.Speciality;
import iot.empiaurhouse.chiron.services.DoctorService;
import iot.empiaurhouse.chiron.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Profile({"default","HashMapService"})
public class DoctorServiceMap extends AbstractMapService<Doctor, Long> implements DoctorService {

    private final SpecialityService specialityService;

    public DoctorServiceMap(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

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

        if (object.getSpecialities().size() > 0){
            object.getSpecialities().forEach(speciality -> {
                if (speciality.getId() == null){
                    Speciality savedSpeciality = specialityService.save(speciality);
                    speciality.setId(savedSpeciality.getId());
                }
            });
        }
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
        return this.findAll()
                .stream()
                .filter(doctor -> doctor.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Doctor findByFirstName(String firstName) {
        return this.findAll()
                .stream()
                .filter(doctor -> doctor.getFirstName().equalsIgnoreCase(firstName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Doctor findByPractitionerID(String practitionerID) {
        return this.findAll()
                .stream()
                .filter(doctor -> doctor.getPractitionerID().equalsIgnoreCase(practitionerID))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Doctor> findAllByLastNameLike(String lastName) {
        return null;
    }

    @Override
    public List<Doctor> findAllByFirstNameLike(String firstName) {
        return null;
    }

    @Override
    public List<Doctor> findAllByPractitionerIDLike(String practitionerID) {
        return null;
    }
}
