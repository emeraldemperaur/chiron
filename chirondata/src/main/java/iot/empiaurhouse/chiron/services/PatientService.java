package iot.empiaurhouse.chiron.services;

import iot.empiaurhouse.chiron.model.Patient;

import java.util.Set;

public interface PatientService {

    Patient findByLastName(String lastName);
    Patient findByFirstName(String firstName);
    Patient findByInsuranceVendorID(String insuranceVendorID);
    Patient findByInsuranceVendor(String insuranceVendor);
    Patient findById(Long id);
    Patient save(Patient patient);
    Set<Patient> findAll();



}
