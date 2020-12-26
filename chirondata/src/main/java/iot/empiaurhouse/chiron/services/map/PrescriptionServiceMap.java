package iot.empiaurhouse.chiron.services.map;

import iot.empiaurhouse.chiron.model.Diagnosis;
import iot.empiaurhouse.chiron.model.Prescription;
import iot.empiaurhouse.chiron.services.PrescriptionService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Profile({"default","HashMapService"})
public class PrescriptionServiceMap extends AbstractMapService<Prescription, Long> implements PrescriptionService {

    @Override
    public Set<Prescription> findAll() {
        return super.findAll();
    }

    @Override
    public Prescription findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Prescription save(Prescription object) {
        return super.save(object);
    }

    @Override
    public void delete(Prescription object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Prescription findByPrescriptionName(String prescriptionName) {
        return this.findAll()
                .stream()
                .filter(prescription -> prescription.getPrescriptionName().equalsIgnoreCase(prescriptionName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Prescription findByPrescriber(String prescribedBy) {
        return this.findAll()
                .stream()
                .filter(prescription -> prescription.getPrescribedBy().equalsIgnoreCase(prescribedBy))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Prescription findByPrescriptionDate(LocalDate prescriptionDate) {
        return this.findAll()
                .stream()
                .filter(prescription -> prescription.getPrescriptionDate().toString().equalsIgnoreCase(prescriptionDate.toString()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Prescription findByDiagnosis(Diagnosis diagnosis) {
        return this.findAll()
                .stream()
                .filter(prescription -> prescription.getDiagnosis().getDiagnosisDetails().equalsIgnoreCase(diagnosis.getDiagnosisDetails()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Set<Prescription> findSetByDiagnosis(Diagnosis diagnosis) {
        return this.findAll()
                .stream()
                .filter(prescription -> prescription.getDiagnosis().getDiagnosisLevel().getDiagnosisLevelName().equalsIgnoreCase(diagnosis.getDiagnosisLevel().getDiagnosisLevelName()))
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public List<Prescription> findAllByPrescriptionNameLike(String prescriptionName) {
        return null;
    }

    @Override
    public List<Prescription> findAllByPrescribedByLike(String prescribedBy) {
        return null;
    }

    @Override
    public List<Prescription> findAllByPrescriptionPractitionerIDLike(String prescriptionPractitionerID) {
        return null;
    }

    @Override
    public List<Prescription> findAllByPrescriptionDateLike(String prescriptionDate) {
        return null;
    }

    @Override
    public List<Prescription> findAllByPrescriptionDateBefore(String prescriptionDate) {
        return null;
    }

    @Override
    public List<Prescription> findAllByPrescriptionDateAfter(String prescriptionDate) {
        return null;
    }

    @Override
    public List<Prescription> findAllByPrescriptionDateBetween(String prescriptionDate, String prescriptionDate2) {
        return null;
    }
}
