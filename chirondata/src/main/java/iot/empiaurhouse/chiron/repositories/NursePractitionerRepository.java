package iot.empiaurhouse.chiron.repositories;

import iot.empiaurhouse.chiron.model.NursePractitioner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NursePractitionerRepository extends CrudRepository<NursePractitioner, Long> {

    NursePractitioner findByPractitionerID(String practitionerID);

    NursePractitioner findByLastName(String lastName);

    NursePractitioner findByFirstName(String firstName);

    List<NursePractitioner> findAllByLastNameLike(String lastName);

    List<NursePractitioner> findAllByFirstNameLike(String firstName);

    List<NursePractitioner> findAllByPractitionerIDLike(String practitionerID);

}
