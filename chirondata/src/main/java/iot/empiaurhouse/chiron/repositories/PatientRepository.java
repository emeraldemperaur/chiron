package iot.empiaurhouse.chiron.repositories;

import iot.empiaurhouse.chiron.model.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Long> {

    Patient findByLastName(String lastName);

    Patient findByFirstName(String firstName);

    Patient findByInsuranceVendorID(String insuranceVendorID);

    Patient findByInsuranceVendor(String insuranceVendor);

}
