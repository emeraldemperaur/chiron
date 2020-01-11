package iot.empiaurhouse.chiron.repositories;

import iot.empiaurhouse.chiron.model.NursePractitioner;
import org.springframework.data.repository.CrudRepository;

public interface NursePractitionerRepository extends CrudRepository<NursePractitioner, Long> {

    NursePractitioner findByPractitionerID(String practitionerID);

    NursePractitioner findByLastName(String lastName);

    NursePractitioner findByFirstName(String firstName);

}
