package iot.empiaurhouse.chiron.repositories;

import iot.empiaurhouse.chiron.model.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PatientRepository extends CrudRepository<Patient, Long> {

    Patient findByLastName(String lastName);

    Patient findByFirstName(String firstName);

    Patient findByInsuranceVendorID(String insuranceVendorID);

    Patient findByInsuranceVendor(String insuranceVendor);

    List<Patient> findAllByLastNameLike(String lastName);

    List<Patient> findAllByFirstNameLike(String firstName);

    List<Patient> findAllByInsuranceVendor(String insuranceVendor);

    List<Patient> findAllByInsuranceVendorID(String insuranceVendorID);

}
