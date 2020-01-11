package iot.empiaurhouse.chiron.repositories;

import iot.empiaurhouse.chiron.model.Doctor;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {

    Doctor findByPractitionerID(String practitionerID);

    Doctor findByLastName(String lastName);

    Doctor findByFirstName(String firstName);

}
