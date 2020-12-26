package iot.empiaurhouse.chiron.repositories;

import iot.empiaurhouse.chiron.model.Patient;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends CrudRepository<Patient, Long> {

    Patient findByLastName(String lastName);

    Patient findByFirstName(String firstName);

    List<Patient> findByInsuranceVendorID(String insuranceVendorID);

    List<Patient> findByInsuranceVendor(String insuranceVendor);

    List<Patient> findAllByLastNameLike(String lastName);

    List<Patient> findAllByFirstNameLike(String firstName);

    List<Patient> findAllByInsuranceVendorLike(String insuranceVendor);

    List<Patient> findAllByInsuranceVendorIDLike(String insuranceVendorID);

    List<Patient> findAllByBirthDateLike(LocalDate birthDate);

    List<Patient> findAllByBirthDateBefore(LocalDate birthDate);

    List<Patient> findAllByBirthDateAfter(LocalDate birthDate);

    List<Patient> findAllByBirthDateBetween(LocalDate birthDate, LocalDate birthDate2);


}
