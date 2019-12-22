package iot.empiaurhouse.chiron.services;

import iot.empiaurhouse.chiron.model.Pharmaceuticals;

import java.time.LocalDate;
import java.util.Set;

public interface PharmaceuticalsService extends CrudService<Pharmaceuticals,Long> {


    Pharmaceuticals findByBrandName(String prescriptionName);
    Pharmaceuticals findByGenericName(String genericName);
    Pharmaceuticals findByManufactureDate(LocalDate manufactureDate);
    Pharmaceuticals findByExpiryDate(LocalDate expiryDate);
    Set<Pharmaceuticals> findSetByManufactureDate(LocalDate manufactureDate);
    Set<Pharmaceuticals> findSetByExpiryDate(LocalDate expiryDate);




}
