package iot.empiaurhouse.chiron.repositories;

import iot.empiaurhouse.chiron.model.Prescription;
import org.springframework.data.repository.CrudRepository;

public interface PrescriptionRepository extends CrudRepository<Prescription, Long> {
}
