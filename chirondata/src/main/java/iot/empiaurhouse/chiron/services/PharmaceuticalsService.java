package iot.empiaurhouse.chiron.services;

import iot.empiaurhouse.chiron.model.Pharmaceuticals;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface PharmaceuticalsService extends CrudService<Pharmaceuticals,Long> {


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
    List<Pharmaceuticals> findAllByManufactureDateLike(String manufactureDate);
    List<Pharmaceuticals> findAllByManufactureDateBefore(String manufactureDate);
    List<Pharmaceuticals> findAllByManufactureDateAfter(String manufactureDate);
    List<Pharmaceuticals> findAllByExpiryDateLike(String expiryDate);
    List<Pharmaceuticals> findAllByExpiryDateBefore(String expiryDate);
    List<Pharmaceuticals> findAllByExpiryDateAfter(String expiryDate);
    List<Pharmaceuticals> findAllByManufactureDateBetween(String manufactureDate, String manufactureDate2);
    List<Pharmaceuticals> findAllByExpiryDateBetween(String expiryDate, String expiryDate2);





}
