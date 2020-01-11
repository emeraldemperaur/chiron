package iot.empiaurhouse.chiron.repositories;

import iot.empiaurhouse.chiron.model.Pharmaceuticals;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Set;

public interface PharmaceuticalsRepository extends CrudRepository<Pharmaceuticals, Long> {

    Pharmaceuticals findByBrandName(String prescriptionName);
    Pharmaceuticals findByGenericName(String genericName);
    Pharmaceuticals findByManufactureDate(LocalDate manufactureDate);
    Pharmaceuticals findByExpiryDate(LocalDate expiryDate);
    Set<Pharmaceuticals> findSetByManufactureDate(LocalDate manufactureDate);
    Set<Pharmaceuticals> findSetByExpiryDate(LocalDate expiryDate);



}
