package iot.empiaurhouse.chiron.services;

import iot.empiaurhouse.chiron.model.RegisteredNurse;

import java.util.Set;

public interface RNService {

    RegisteredNurse findById(Long id);
    RegisteredNurse save(RegisteredNurse registeredNurse);
    Set<RegisteredNurse> findAll();

}
