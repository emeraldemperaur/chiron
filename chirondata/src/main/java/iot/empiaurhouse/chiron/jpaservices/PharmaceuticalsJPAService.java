package iot.empiaurhouse.chiron.jpaservices;

import iot.empiaurhouse.chiron.model.Pharmaceuticals;
import iot.empiaurhouse.chiron.repositories.PharmaceuticalsRepository;
import iot.empiaurhouse.chiron.services.PharmaceuticalsService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Profile("SpringDataJPA")
public class PharmaceuticalsJPAService implements PharmaceuticalsService {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
    private final PharmaceuticalsRepository pharmaceuticalsRepository;


    public PharmaceuticalsJPAService(PharmaceuticalsRepository pharmaceuticalsRepository) {
        this.pharmaceuticalsRepository = pharmaceuticalsRepository;
    }

    @Override
    public Pharmaceuticals findByBrandName(String prescriptionName) {
        return pharmaceuticalsRepository.findByBrandName(prescriptionName);
    }

    @Override
    public Pharmaceuticals findByGenericName(String genericName) {
        return pharmaceuticalsRepository.findByGenericName(genericName);
    }

    @Override
    public Pharmaceuticals findByManufactureDate(LocalDate manufactureDate) {
        return pharmaceuticalsRepository.findByManufactureDate(manufactureDate);
    }

    @Override
    public Pharmaceuticals findByExpiryDate(LocalDate expiryDate) {
        return pharmaceuticalsRepository.findByExpiryDate(expiryDate);
    }

    @Override
    public Set<Pharmaceuticals> findSetByManufactureDate(LocalDate manufactureDate) {
        return pharmaceuticalsRepository.findSetByManufactureDate(manufactureDate);
    }

    @Override
    public Set<Pharmaceuticals> findSetByExpiryDate(LocalDate expiryDate) {
        return pharmaceuticalsRepository.findSetByExpiryDate(expiryDate);
    }

    @Override
    public List<Pharmaceuticals> findAllByBrandNameLike(String brandName) {
        return pharmaceuticalsRepository.findAllByBrandNameLike(brandName);
    }

    @Override
    public List<Pharmaceuticals> findAllByGenericNameLike(String genericName) {
        return pharmaceuticalsRepository.findAllByGenericNameLike(genericName);
    }

    @Override
    public List<Pharmaceuticals> findAllByChemicalNameLike(String chemicalName) {
        return pharmaceuticalsRepository.findAllByChemicalNameLike(chemicalName);
    }

    @Override
    public List<Pharmaceuticals> findAllByBatchNumberLike(String batchNumber) {
        return pharmaceuticalsRepository.findAllByBatchNumberLike(batchNumber);
    }

    @Override
    public List<Pharmaceuticals> findAllByApprovalNumberLike(String approvalNumber) {
        return pharmaceuticalsRepository.findAllByApprovalNumberLike(approvalNumber);
    }

    @Override
    public List<Pharmaceuticals> findAllByManufacturerNameLike(String manufacturerName) {
        return pharmaceuticalsRepository.findAllByManufacturerNameLike(manufacturerName);
    }

    @Override
    public List<Pharmaceuticals> findAllByManufactureDateLike(String manufactureDateText) {

        LocalDate manufactureDate = LocalDate.parse(manufactureDateText, formatter);
        return pharmaceuticalsRepository.findAllByManufactureDateLike(manufactureDate);
    }

    @Override
    public List<Pharmaceuticals> findAllByManufactureDateBefore(String manufactureDateText) {
        LocalDate manufactureDate = LocalDate.parse(manufactureDateText, formatter);
        return pharmaceuticalsRepository.findAllByManufactureDateBefore(manufactureDate);
    }

    @Override
    public List<Pharmaceuticals> findAllByManufactureDateAfter(String manufactureDateText) {
        LocalDate manufactureDate = LocalDate.parse(manufactureDateText, formatter);
        return pharmaceuticalsRepository.findAllByManufactureDateAfter(manufactureDate);
    }

    @Override
    public List<Pharmaceuticals> findAllByExpiryDateLike(String expiryDateText) {
        LocalDate expiryDate = LocalDate.parse(expiryDateText, formatter);
        return pharmaceuticalsRepository.findAllByExpiryDateLike(expiryDate);
    }

    @Override
    public List<Pharmaceuticals> findAllByExpiryDateBefore(String expiryDateText) {
        LocalDate expiryDate = LocalDate.parse(expiryDateText, formatter);
        return pharmaceuticalsRepository.findAllByExpiryDateBefore(expiryDate);
    }

    @Override
    public List<Pharmaceuticals> findAllByExpiryDateAfter(String expiryDateText) {
        LocalDate expiryDate = LocalDate.parse(expiryDateText, formatter);
        return pharmaceuticalsRepository.findAllByExpiryDateAfter(expiryDate);
    }

    @Override
    public List<Pharmaceuticals> findAllByManufactureDateBetween(String manufactureDateText, String manufactureDate2Text) {
        LocalDate manufactureDate = LocalDate.parse(manufactureDateText, formatter);
        LocalDate manufactureDate2 = LocalDate.parse(manufactureDate2Text, formatter);
        return pharmaceuticalsRepository.findAllByManufactureDateBetween(manufactureDate, manufactureDate2);
    }

    @Override
    public List<Pharmaceuticals> findAllByExpiryDateBetween(String expiryDateText, String expiryDate2Text) {
        LocalDate expiryDate = LocalDate.parse(expiryDateText, formatter);
        LocalDate expiryDate2 = LocalDate.parse(expiryDate2Text, formatter);
        return pharmaceuticalsRepository.findAllByExpiryDateBetween(expiryDate, expiryDate2);
    }

    @Override
    public Set<Pharmaceuticals> findAll() {
        Set<Pharmaceuticals> pharmaceuticals = new HashSet<>();
        pharmaceuticalsRepository.findAll().forEach(pharmaceuticals::add);

        return pharmaceuticals;
    }



    @Override
    public Pharmaceuticals findById(Long aLong) {
        return pharmaceuticalsRepository.findById(aLong).orElse(null);
    }

    @Override
    public Pharmaceuticals save(Pharmaceuticals object) {
        return pharmaceuticalsRepository.save(object);
    }

    @Override
    public void delete(Pharmaceuticals object) {
        pharmaceuticalsRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        pharmaceuticalsRepository.deleteById(aLong);
    }
}
