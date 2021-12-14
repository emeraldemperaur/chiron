package iot.empiaurhouse.chiron.jpaservices;

import iot.empiaurhouse.chiron.model.Patient;
import iot.empiaurhouse.chiron.repositories.DiagnosisLevelRepository;
import iot.empiaurhouse.chiron.repositories.DiagnosisRepository;
import iot.empiaurhouse.chiron.repositories.PatientRepository;
import iot.empiaurhouse.chiron.services.PatientService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Profile("SpringDataJPA")
public class PatientJPAService implements PatientService {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
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
        return (Patient) patientRepository.findByInsuranceVendorID(insuranceVendorID);
    }

    @Override
    public Patient findByInsuranceVendor(String insuranceVendor) {
        return (Patient) patientRepository.findByInsuranceVendor(insuranceVendor);
    }

    @Override
    public List<Patient> findAllByLastNameLike(String lastName) {
        return patientRepository.findAllByLastNameLike(lastName);
    }

    @Override
    public List<Patient> findAllByFirstNameLike(String firstName) {
        return patientRepository.findAllByFirstNameLike(firstName);
    }

    @Override
    public List<Patient> findAllByInsuranceVendor(String insuranceVendor) {
        return patientRepository.findByInsuranceVendor(insuranceVendor);
    }

    @Override
    public List<Patient> findAllByInsuranceVendorID(String insuranceVendorID) {
        return patientRepository.findByInsuranceVendorID(insuranceVendorID);
    }

    @Override
    public List<Patient> findAllByInsuranceVendorLike(String insuranceVendor) {
        return patientRepository.findAllByInsuranceVendorLike(insuranceVendor);
    }

    @Override
    public List<Patient> findAllByInsuranceVendorIDLike(String insuranceVendorID) {
        return patientRepository.findAllByInsuranceVendorIDLike(insuranceVendorID);
    }

    @Override
    public List<Patient> findAllByBloodGroupLike(String bloodGroup) {
        return patientRepository.findAllByBloodGroupLike(bloodGroup);
    }

    @Override
    public List<Patient> findAllByBirthDate(String birthDateText) {
        LocalDate birthDate = LocalDate.parse(birthDateText, formatter);
        return patientRepository.findAllByBirthDateLike(birthDate);
    }

    @Override
    public List<Patient> findAllByBirthDateBefore(String birthDateText) {
        LocalDate birthDate = LocalDate.parse(birthDateText, formatter);
        return patientRepository.findAllByBirthDateBefore(birthDate);
    }

    @Override
    public List<Patient> findAllByBirthDateAfter(String birthDateText) {
        LocalDate birthDate = LocalDate.parse(birthDateText, formatter);
        return patientRepository.findAllByBirthDateAfter(birthDate);
    }

    @Override
    public List<Patient> findAllByBirthDateBetween(String birthDateText, String birthDate2Text) {
        LocalDate birthDate = LocalDate.parse(birthDateText, formatter);
        LocalDate birthDate2 = LocalDate.parse(birthDate2Text, formatter);
        return patientRepository.findAllByBirthDateBetween(birthDate, birthDate2);
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
