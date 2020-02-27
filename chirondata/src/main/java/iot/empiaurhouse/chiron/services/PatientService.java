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





}
