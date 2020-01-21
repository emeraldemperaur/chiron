package iot.empiaurhouse.chiron.services.map;

import iot.empiaurhouse.chiron.model.Diagnosis;
import iot.empiaurhouse.chiron.model.Patient;
import iot.empiaurhouse.chiron.model.Visit;
import iot.empiaurhouse.chiron.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
@Profile({"default","HashMapService"})
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {


    @Override
    public Visit findByVisitDate(LocalDate visitDate) {
        return this.findAll()
                .stream()
                .filter(visit -> visit.getVisitDate().toString().equalsIgnoreCase(visit.toString()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Set<Visit> findSetByVisitDiagnosis(Diagnosis visitDiagnosis) {
        return null;
    }

    @Override
    public Set<Visit> findSetByVisitingPatient(Patient visitingPatient) {
        return null;
    }

    @Override
    public Visit findByVisitingPatient(Patient visitingPatient) {
        return this.findAll()
                .stream()
                .filter(visit -> visit.getVisitingPatient().getLastName().equalsIgnoreCase(visitingPatient.getLastName()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Visit findByVisitDiagnosis(Diagnosis visitDiagnosis) {
        return this.findAll()
                .stream()
                .filter(visit -> visit.getVisitDiagnosis().getDiagnosisDetails().equalsIgnoreCase(visitDiagnosis.getDiagnosisDetails()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Visit findByHostPractitioner(String hostPractitioner) {
        return this.findAll()
                .stream()
                .filter(visit -> visit.getHostPractitioner().equalsIgnoreCase(hostPractitioner))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Visit visit) {
        super.delete(visit);
    }

    @Override
    public Visit save(Visit visit) {
        if (visit.getVisitDiagnosis() == null || visit.getVisitDiagnosis().getPatient() == null ||
        visit.getVisitDiagnosis().getId() == null || visit.getVisitDiagnosis().getPatient().getId() == null){
            throw new RuntimeException("Invalid Visit");
        }
        return super.save(visit);
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }
}
