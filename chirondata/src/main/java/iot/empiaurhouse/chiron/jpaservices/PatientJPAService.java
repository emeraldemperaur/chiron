package iot.empiaurhouse.chiron.jpaservices;

import iot.empiaurhouse.chiron.model.Patient;
import iot.empiaurhouse.chiron.repositories.DiagnosisLevelRepository;
import iot.empiaurhouse.chiron.repositories.DiagnosisRepository;
import iot.empiaurhouse.chiron.repositories.PatientRepository;
import iot.empiaurhouse.chiron.services.PatientService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("SpringDataJPA")
public class PatientJPAService implements PatientService {

    private final PatientRepository patientRepository;
    private final DiagnosisRepository diagnosisRepository;
    private final DiagnosisLevelRepository diagnosisLevelRepository;

    public PatientJPAService(PatientRepository patientRepository, DiagnosisRepository diagnosisRepository,
                             DiagnosisLevelRepository diagnosisLevelRepository) {
        this.patientRepository = patientRepository;
        this.diagnosisRepository = diagnosisRepository;
        this.diagnosisLevelRepository = diagnosisLevelRepository;
    }


    @Override
    public Patient findByLastName(String lastName) {
        return patientRepository.findByLastName(lastName);
    }

    @Override
    public Patient findByFirstName(String firstName) {
        return patientRepository.findByFirstName(firstName);
    }

    @Override
    public Patient findByInsuranceVendorID(String insuranceVendorID) {
        return patientRepository.findByInsuranceVendorID(insuranceVendorID);
    }

    @Override
    public Patient findByInsuranceVendor(String insuranceVendor) {
        return patientRepository.findByInsuranceVendor(insuranceVendor);
    }

    @Override
    public Set<Patient> findAll() {

        Set<Patient> patients = new HashSet<>();
        patientRepository.findAll().forEach(patients::add);

        return patients;
    }

    @Override
    public Patient findById(Long aLong) {
        return patientRepository.findById(aLong).orElse(null);
    }

    @Override
    public Patient save(Patient object) {
        return patientRepository.save(object);
    }

    @Override
    public void delete(Patient object) {
        patientRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        patientRepository.deleteById(aLong);
    }
}
