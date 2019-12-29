package iot.empiaurhouse.chiron.services.map;

import iot.empiaurhouse.chiron.model.Patient;
import iot.empiaurhouse.chiron.services.PatientService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PatientServiceMap extends AbstractMapService<Patient, Long> implements PatientService {

    @Override
    public Set<Patient> findAll() {
        return super.findAll();
    }

    @Override
    public Patient findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Patient save(Patient object) {
        return super.save(object);
    }

    @Override
    public void delete(Patient object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Patient findByLastName(String lastName) {
        return null;
    }

    @Override
    public Patient findByFirstName(String firstName) {
        return null;
    }

    @Override
    public Patient findByInsuranceVendorID(String insuranceVendorID) {
        return null;
    }

    @Override
    public Patient findByInsuranceVendor(String insuranceVendor) {
        return null;
    }
}
