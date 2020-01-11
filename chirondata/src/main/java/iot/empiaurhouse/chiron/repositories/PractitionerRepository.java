package iot.empiaurhouse.chiron.repositories;

import iot.empiaurhouse.chiron.model.Practitioner;
import org.springframework.data.repository.CrudRepository;

public interface PractitionerRepository extends CrudRepository<Practitioner, Long> {

    Practitioner findByPractitionerID(String practitionerID);

    Practitioner findByLastName(String lastName);

    Practitioner findByFirstName(String firstName);

}
