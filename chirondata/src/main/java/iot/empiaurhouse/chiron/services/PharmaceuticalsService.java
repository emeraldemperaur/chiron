package iot.empiaurhouse.chiron.services;

import iot.empiaurhouse.chiron.model.Pharmaceuticals;

import java.util.Set;

public interface PharmaceuticalsService {

    Pharmaceuticals findById(Long id);
    Pharmaceuticals save(Pharmaceuticals pharmaceuticals);
    Set<Pharmaceuticals> findAll();


}
