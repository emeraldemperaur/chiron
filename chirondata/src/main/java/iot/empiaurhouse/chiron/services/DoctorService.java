package iot.empiaurhouse.chiron.services;

import iot.empiaurhouse.chiron.model.Doctor;

import java.util.Set;

public interface DoctorService {

    Doctor findById(Long id);
    Doctor save(Doctor doctor);
    Set<Doctor> findAll();

}
