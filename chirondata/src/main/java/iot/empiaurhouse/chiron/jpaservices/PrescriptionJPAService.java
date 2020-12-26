package iot.empiaurhouse.chiron.jpaservices;

import iot.empiaurhouse.chiron.model.Diagnosis;
import iot.empiaurhouse.chiron.model.Patient;
import iot.empiaurhouse.chiron.model.Prescription;
import iot.empiaurhouse.chiron.repositories.PrescriptionRepository;
import iot.empiaurhouse.chiron.services.PrescriptionService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Profile("SpringDataJPA")
public class PrescriptionJPAService implements PrescriptionService {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionJPAService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    @Override
    public Prescription findByPrescriptionName(String prescriptionName) {
        return prescriptionRepository.findByPrescriptionName(prescriptionName);
    }

    @Override
    public Prescription findByPrescriber(String prescribedBy) {
        return prescriptionRepository.findByPrescribedBy(prescribedBy);
    }

    @Override
    public Prescription findByPrescriptionDate(LocalDate prescriptionDate) {
        return prescriptionRepository.findByPrescriptionDate(prescriptionDate);
    }

    @Override
    public Prescription findByDiagnosis(Diagnosis diagnosis) {
        return prescriptionRepository.findByDiagnosis(diagnosis);
    }

    @Override
    public Set<Prescription> findSetByDiagnosis(Diagnosis diagnosis) {
        return prescriptionRepository.findSetByDiagnosis(diagnosis);
    }

    @Override
    public List<Prescription> findAllByPrescriptionNameLike(String prescriptionName) {
        return prescriptionRepository.findAllByPrescriptionNameLike(prescriptionName);
    }

    @Override
    public List<Prescription> findAllByPrescribedByLike(String prescribedBy) {
        return prescriptionRepository.findAllByPrescribedByLike(prescribedBy);
    }

    @Override
    public List<Prescription> findAllByPrescriptionPractitionerIDLike(String prescriptionPractitionerID) {
        return prescriptionRepository.findAllByPrescriptionPractitionerIDLike(prescriptionPractitionerID);
    }

    @Override
    public List<Prescription> findAllByPrescriptionDateLike(String prescriptionDateText) {
        LocalDate prescriptionDate = LocalDate.parse(prescriptionDateText, formatter);
        return prescriptionRepository.findAllByPrescriptionDateLike(prescriptionDate);
    }

    @Override
    public List<Prescription> findAllByPrescriptionDateBefore(String prescriptionDateText) {
        LocalDate prescriptionDate = LocalDate.parse(prescriptionDateText, formatter);
        return prescriptionRepository.findAllByPrescriptionDateBefore(prescriptionDate);
    }

    @Override
    public List<Prescription> findAllByPrescriptionDateAfter(String prescriptionDateText) {
        LocalDate prescriptionDate = LocalDate.parse(prescriptionDateText, formatter);
        return prescriptionRepository.findAllByPrescriptionDateAfter(prescriptionDate);
    }

    @Override
    public List<Prescription> findAllByPrescriptionDateBetween(String prescriptionDateText, String prescriptionDate2Text) {
        LocalDate prescriptionDate = LocalDate.parse(prescriptionDateText, formatter);
        LocalDate prescriptionDate2 = LocalDate.parse(prescriptionDate2Text, formatter);
        return prescriptionRepository.findAllByPrescriptionDateBetween(prescriptionDate, prescriptionDate2);
    }

    @Override
    public List<Prescription> findAllByPatientLike(Patient patient) {
        return prescriptionRepository.findAllByPatientLike(patient);
    }

    @Override
    public Set<Prescription> findAll() {
        Set<Prescription> prescriptions = new HashSet<>();
        prescriptionRepository.findAll().forEach(prescriptions::add);

        return prescriptions;
    }

    @Override
    public Prescription findById(Long aLong) {
        return prescriptionRepository.findById(aLong).orElse(null);
    }

    @Override
    public Prescription save(Prescription object) {
        return prescriptionRepository.save(object);
    }

    @Override
    public void delete(Prescription object) {
        prescriptionRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        prescriptionRepository.deleteById(aLong);
    }
}
