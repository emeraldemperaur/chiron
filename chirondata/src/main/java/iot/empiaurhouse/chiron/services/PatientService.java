package iot.empiaurhouse.chiron.services;

import iot.empiaurhouse.chiron.model.Patient;

public interface PatientService extends CrudService<Patient,Long> {

    Patient findByLastName(String lastName);
    Patient findByFirstName(String firstName);
    Patient findByInsuranceVendorID(String insuranceVendorID);
    Patient findByInsuranceVendor(String insuranceVendor);


}
