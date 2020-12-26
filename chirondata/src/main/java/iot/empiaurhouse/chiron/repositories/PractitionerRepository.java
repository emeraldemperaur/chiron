package iot.empiaurhouse.chiron.repositories;

import iot.empiaurhouse.chiron.model.Practitioner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PractitionerRepository extends CrudRepository<Practitioner, Long> {

    Practitioner findByPractitionerID(String practitionerID);

    Practitioner findByLastName(String lastName);

    Practitioner findByFirstName(String firstName);

    List<Practitioner> findAllByLastNameLike(String lastName);

    List<Practitioner> findAllByFirstNameLike(String firstName);

    List<Practitioner> findAllByPractitionerIDLike(String practitionerID);

}
