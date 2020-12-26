package iot.empiaurhouse.chiron.repositories;

import iot.empiaurhouse.chiron.model.Doctor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {

    Doctor findByPractitionerID(String practitionerID);

    Doctor findByLastName(String lastName);

    Doctor findByFirstName(String firstName);

    List<Doctor> findAllByLastNameLike(String lastName);

    List<Doctor> findAllByFirstNameLike(String firstName);

    List<Doctor> findAllByPractitionerIDLike(String practitionerID);

}
