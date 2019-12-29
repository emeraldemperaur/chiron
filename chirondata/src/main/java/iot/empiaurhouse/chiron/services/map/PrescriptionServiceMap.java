package iot.empiaurhouse.chiron.services.map;

import iot.empiaurhouse.chiron.model.Diagnosis;
import iot.empiaurhouse.chiron.model.Prescription;
import iot.empiaurhouse.chiron.services.PrescriptionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
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
        return null;
    }

    @Override
    public Prescription findByPrescriber(String prescribedBy) {
        return null;
    }

    @Override
    public Prescription findByPrescriptionDate(LocalDate prescriptionDate) {
        return null;
    }

    @Override
    public Prescription findByDiagnosis(Diagnosis diagnosis) {
        return null;
    }

    @Override
    public Set<Prescription> findSetByDiagnosis(Diagnosis diagnosis) {
        return null;
    }
}
