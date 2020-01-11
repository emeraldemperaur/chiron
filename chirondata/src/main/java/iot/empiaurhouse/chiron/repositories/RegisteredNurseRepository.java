package iot.empiaurhouse.chiron.repositories;

import iot.empiaurhouse.chiron.model.RegisteredNurse;
import org.springframework.data.repository.CrudRepository;

public interface RegisteredNurseRepository extends CrudRepository<RegisteredNurse, Long> {

    RegisteredNurse findByPractitionerID(String practitionerID);

    RegisteredNurse findByLastName(String lastName);

    RegisteredNurse findByFirstName(String firstName);

}
