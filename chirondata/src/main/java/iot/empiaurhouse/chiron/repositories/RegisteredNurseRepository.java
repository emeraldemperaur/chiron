package iot.empiaurhouse.chiron.repositories;

import iot.empiaurhouse.chiron.model.RegisteredNurse;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RegisteredNurseRepository extends CrudRepository<RegisteredNurse, Long> {

    RegisteredNurse findByPractitionerID(String practitionerID);

    RegisteredNurse findByLastName(String lastName);

    RegisteredNurse findByFirstName(String firstName);

    List<RegisteredNurse> findAllByLastNameLike(String lastName);

    List<RegisteredNurse> findAllByFirstNameLike(String firstName);

    List<RegisteredNurse> findAllByPractitionerIDLike(String practitionerID);


}
