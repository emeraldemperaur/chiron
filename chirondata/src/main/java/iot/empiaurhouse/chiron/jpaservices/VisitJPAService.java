package iot.empiaurhouse.chiron.jpaservices;

import iot.empiaurhouse.chiron.model.Diagnosis;
import iot.empiaurhouse.chiron.model.Patient;
import iot.empiaurhouse.chiron.model.Visit;
import iot.empiaurhouse.chiron.repositories.VisitRepository;
import iot.empiaurhouse.chiron.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
@Profile("SpringDataJPA")
public class VisitJPAService implements VisitService {

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
