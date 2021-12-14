package iot.empiaurhouse.chiron.services.map;

import iot.empiaurhouse.chiron.model.Diagnosis;
import iot.empiaurhouse.chiron.model.Patient;
import iot.empiaurhouse.chiron.services.DiagnosisLevelService;
import iot.empiaurhouse.chiron.services.DiagnosisService;
import iot.empiaurhouse.chiron.services.PatientService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Profile({"default","HashMapService"})
public class PatientServiceMap extends AbstractMapService<Patient, Long> implements PatientService {

    private final DiagnosisLevelService diagnosisLevelService;
    private final DiagnosisService diagnosisService;

    public PatientServiceMap(DiagnosisLevelService diagnosisLevelService, DiagnosisService diagnosisService) {
        this.diagnosisLevelService = diagnosisLevelService;
        this.diagnosisService = diagnosisService;
    }

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

        if (object != null){
            if (object.getDiagnoses() != null){
                object.getDiagnoses().forEach(diagnosis -> {
                    if (diagnosis.getDiagnosisLevel() != null){
                        if (diagnosis.getDiagnosisLevel().getId() == null){
                            object.setImage(object.getImage());
                            diagnosis.setDiagnosisLevel(diagnosisLevelService.save(diagnosis.getDiagnosisLevel()));

                        }

                    }else {
                        throw new RuntimeException("Diagnosis Level is required");
                    }

                    if (diagnosis.getId() == null){
                        Diagnosis savedDiagnosis = diagnosisService.save(diagnosis);
                        diagnosis.setId(savedDiagnosis.getId());

                    }



                });

            }

            return super.save(object);

        }else {

            return null;
        }

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
        return this.findAll()
                .stream()
                .filter(patient -> patient.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Patient findByFirstName(String firstName) {
        return this.findAll()
                .stream()
                .filter(patient -> patient.getFirstName().equalsIgnoreCase(firstName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Patient findByInsuranceVendorID(String insuranceVendorID) {
        return this.findAll()
                .stream()
                .filter(patient -> patient.getInsuranceVendorID().equalsIgnoreCase(insuranceVendorID))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Patient findByInsuranceVendor(String insuranceVendor) {
        return this.findAll()
                .stream()
                .filter(patient -> patient.getInsuranceVendor().equalsIgnoreCase(insuranceVendor))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Patient> findAllByLastNameLike(String lastName) {
        return null;
    }

    @Override
    public List<Patient> findAllByFirstNameLike(String firstName) {
        return null;
    }

    @Override
    public List<Patient> findAllByInsuranceVendor(String insuranceVendor) {
        return null;
    }

    @Override
    public List<Patient> findAllByInsuranceVendorID(String insuranceVendorID) {
        return null;
    }

    @Override
    public List<Patient> findAllByInsuranceVendorLike(String insuranceVendor) {
        return null;
    }

    @Override
    public List<Patient> findAllByInsuranceVendorIDLike(String insuranceVendorID) {
        return null;
    }

    @Override
    public List<Patient> findAllByBloodGroupLike(String insuranceVendorID) {
        return null;
    }

    @Override
    public List<Patient> findAllByBirthDate(String birthDate) {
        return null;
    }

    @Override
    public List<Patient> findAllByBirthDateBefore(String birthDate) {
        return null;
    }

    @Override
    public List<Patient> findAllByBirthDateAfter(String birthDate) {
        return null;
    }

    @Override
    public List<Patient> findAllByBirthDateBetween(String birthDate, String birthDate2) {
        return null;
    }


}
