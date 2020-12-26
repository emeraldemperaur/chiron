package iot.empiaurhouse.chiron.jpaservices;

import iot.empiaurhouse.chiron.model.Diagnosis;
import iot.empiaurhouse.chiron.model.Patient;
import iot.empiaurhouse.chiron.model.Visit;
import iot.empiaurhouse.chiron.repositories.VisitRepository;
import iot.empiaurhouse.chiron.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Profile("SpringDataJPA")
public class VisitJPAService implements VisitService {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
    private final VisitRepository visitRepository;

    public VisitJPAService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Visit findByVisitDate(LocalDate visitDate) {
        return visitRepository.findByVisitDate(visitDate);
    }

    @Override
    public Set<Visit> findSetByVisitDiagnosis(Diagnosis visitDiagnosis) {
        return visitRepository.findSetByVisitDiagnosis(visitDiagnosis);
    }

    @Override
    public Set<Visit> findSetByVisitingPatient(Patient visitingPatient) {
        return visitRepository.findSetByVisitingPatient(visitingPatient);
    }

    @Override
    public Visit findByVisitingPatient(Patient visitingPatient) {
        return visitRepository.findByVisitingPatient(visitingPatient);
    }

    @Override
    public Visit findByVisitDiagnosis(Diagnosis visitDiagnosis) {
        return visitRepository.findByVisitDiagnosis(visitDiagnosis);
    }

    @Override
    public Visit findByHostPractitioner(String hostPractitioner) {
        return visitRepository.findByHostPractitioner(hostPractitioner);
    }

    @Override
    public List<Visit> findAllByHostPractitionerLike(String hostPractitioner) {
        return visitRepository.findAllByHostPractitionerLike(hostPractitioner);
    }

    @Override
    public List<Visit> findAllByHostPractitionerIDLike(String hostPractitionerID) {
        return visitRepository.findAllByHostPractitionerIDLike(hostPractitionerID);
    }

    @Override
    public List<Visit> findAllByVisitTimeLike(String visitTime) {
        return visitRepository.findAllByVisitTimeLike(visitTime);
    }

    @Override
    public List<Visit> findAllByVisitDescriptionContains(String visitDescription) {
        return visitRepository.findAllByVisitDescriptionContains(visitDescription);
    }

    @Override
    public List<Visit> findAllByVisitDateLike(String visitDateText) {
        LocalDate visitDate = LocalDate.parse(visitDateText, formatter);
        return visitRepository.findAllByVisitDateLike(visitDate);
    }

    @Override
    public List<Visit> findAllByVisitDateBefore(String visitDateText) {
        LocalDate visitDate = LocalDate.parse(visitDateText, formatter);
        return visitRepository.findAllByVisitDateBefore(visitDate);
    }

    @Override
    public List<Visit> findAllByVisitDateAfter(String visitDateText) {
        LocalDate visitDate = LocalDate.parse(visitDateText, formatter);
        return visitRepository.findAllByVisitDateAfter(visitDate);
    }

    @Override
    public List<Visit> findAllByVisitDateBetween(String visitDateText, String visitDate2Text) {
        LocalDate visitDate = LocalDate.parse(visitDateText, formatter);
        LocalDate visitDate2 = LocalDate.parse(visitDate2Text, formatter);
        return visitRepository.findAllByVisitDateBetween(visitDate, visitDate2);
    }

    @Override
    public Set<Visit> findAll() {
        Set<Visit> visits = new HashSet<>();
        visitRepository.findAll().forEach(visits::add);

        return visits;
    }

    @Override
    public Visit findById(Long aLong) {
        return visitRepository.findById(aLong).orElse(null);
    }

    @Override
    public Visit save(Visit object) {
        return visitRepository.save(object);
    }

    @Override
    public void delete(Visit object) {
        visitRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        visitRepository.deleteById(aLong);
    }
}
