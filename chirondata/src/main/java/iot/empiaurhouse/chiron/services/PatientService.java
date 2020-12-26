package iot.empiaurhouse.chiron.services;

import iot.empiaurhouse.chiron.model.Patient;

import java.util.List;

public interface PatientService extends CrudService<Patient,Long> {

    Patient findByLastName(String lastName);
    Patient findByFirstName(String firstName);
    Patient findByInsuranceVendorID(String insuranceVendorID);
    Patient findByInsuranceVendor(String insuranceVendor);
    List<Patient> findAllByLastNameLike(String lastName);
    List<Patient> findAllByFirstNameLike(String firstName);
    List<Patient> findAllByInsuranceVendor(String insuranceVendor);
    List<Patient> findAllByInsuranceVendorID(String insuranceVendorID);
    List<Patient> findAllByInsuranceVendorLike(String insuranceVendor);

    List<Patient> findAllByInsuranceVendorIDLike(String insuranceVendorID);

    List<Patient> findAllByBirthDate(String birthDate);

    List<Patient> findAllByBirthDateBefore(String birthDate);

    List<Patient> findAllByBirthDateAfter(String birthDate);

    List<Patient> findAllByBirthDateBetween(String birthDate, String birthDate2);





}
