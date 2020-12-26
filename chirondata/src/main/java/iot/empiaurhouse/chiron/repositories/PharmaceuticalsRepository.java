package iot.empiaurhouse.chiron.repositories;

import iot.empiaurhouse.chiron.model.Pharmaceuticals;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface PharmaceuticalsRepository extends CrudRepository<Pharmaceuticals, Long> {

    Pharmaceuticals findByBrandName(String prescriptionName);
    Pharmaceuticals findByGenericName(String genericName);
    Pharmaceuticals findByManufactureDate(LocalDate manufactureDate);
    Pharmaceuticals findByExpiryDate(LocalDate expiryDate);
    Set<Pharmaceuticals> findSetByManufactureDate(LocalDate manufactureDate);
    Set<Pharmaceuticals> findSetByExpiryDate(LocalDate expiryDate);
    List<Pharmaceuticals> findAllByBrandNameLike(String brandName);
    List<Pharmaceuticals> findAllByGenericNameLike(String genericName);
    List<Pharmaceuticals> findAllByChemicalNameLike(String chemicalName);
    List<Pharmaceuticals> findAllByBatchNumberLike(String batchNumber);
    List<Pharmaceuticals> findAllByApprovalNumberLike(String approvalNumber);
    List<Pharmaceuticals> findAllByManufacturerNameLike(String manufacturerName);
    List<Pharmaceuticals> findAllByManufactureDateLike(LocalDate manufactureDate);
    List<Pharmaceuticals> findAllByManufactureDateBefore(LocalDate manufactureDate);
    List<Pharmaceuticals> findAllByManufactureDateAfter(LocalDate manufactureDate);
    List<Pharmaceuticals> findAllByExpiryDateLike(LocalDate expiryDate);
    List<Pharmaceuticals> findAllByExpiryDateBefore(LocalDate expiryDate);
    List<Pharmaceuticals> findAllByExpiryDateAfter(LocalDate expiryDate);
    List<Pharmaceuticals> findAllByManufactureDateBetween(LocalDate manufactureDate, LocalDate manufactureDate2);
    List<Pharmaceuticals> findAllByExpiryDateBetween(LocalDate expiryDate, LocalDate expiryDate2);



}
