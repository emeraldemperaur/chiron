package iot.empiaurhouse.chiron.services.map;


import iot.empiaurhouse.chiron.model.Pharmaceuticals;
import iot.empiaurhouse.chiron.services.PharmaceuticalsService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Profile({"default","HashMapService"})
public class PharmaceuticalsServiceMap extends AbstractMapService<Pharmaceuticals, Long> implements PharmaceuticalsService {

    @Override
    public Set<Pharmaceuticals> findAll() {
        return super.findAll();
    }

    @Override
    public Pharmaceuticals findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Pharmaceuticals save(Pharmaceuticals object) {
        return super.save(object);
    }

    @Override
    public void delete(Pharmaceuticals object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Pharmaceuticals findByBrandName(String prescriptionName) {
        return this.findAll()
                .stream()
                .filter(pharmaceuticals -> pharmaceuticals.getBrandName().equalsIgnoreCase(prescriptionName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Pharmaceuticals findByGenericName(String genericName) {
        return this.findAll()
                .stream()
                .filter(pharmaceuticals -> pharmaceuticals.getGenericName().equalsIgnoreCase(genericName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Pharmaceuticals findByManufactureDate(LocalDate manufactureDate) {
        return this.findAll()
                .stream()
                .filter(pharmaceuticals -> pharmaceuticals.getManufactureDate().toString().equalsIgnoreCase(manufactureDate.toString()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Pharmaceuticals findByExpiryDate(LocalDate expiryDate) {
        return this.findAll()
                .stream()
                .filter(pharmaceuticals -> pharmaceuticals.getExpiryDate().toString().equalsIgnoreCase(expiryDate.toString()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Set<Pharmaceuticals> findSetByManufactureDate(LocalDate manufactureDate) {
        return this.findAll()
                .stream()
                .filter(pharmaceuticals -> pharmaceuticals.getManufactureDate().toString().equalsIgnoreCase(manufactureDate.toString()))
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public Set<Pharmaceuticals> findSetByExpiryDate(LocalDate expiryDate) {
        return this.findAll()
                .stream()
                .filter(pharmaceuticals -> pharmaceuticals.getExpiryDate().toString().equalsIgnoreCase(expiryDate.toString()))
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public List<Pharmaceuticals> findAllByBrandNameLike(String brandName) {
        return null;
    }

    @Override
    public List<Pharmaceuticals> findAllByGenericNameLike(String genericName) {
        return null;
    }

    @Override
    public List<Pharmaceuticals> findAllByChemicalNameLike(String chemicalName) {
        return null;
    }

    @Override
    public List<Pharmaceuticals> findAllByBatchNumberLike(String batchNumber) {
        return null;
    }

    @Override
    public List<Pharmaceuticals> findAllByApprovalNumberLike(String approvalNumber) {
        return null;
    }

    @Override
    public List<Pharmaceuticals> findAllByManufacturerNameLike(String manufacturerName) {
        return null;
    }

    @Override
    public List<Pharmaceuticals> findAllByManufactureDateLike(String manufactureDate) {
        return null;
    }

    @Override
    public List<Pharmaceuticals> findAllByManufactureDateBefore(String manufactureDate) {
        return null;
    }

    @Override
    public List<Pharmaceuticals> findAllByManufactureDateAfter(String manufactureDate) {
        return null;
    }

    @Override
    public List<Pharmaceuticals> findAllByExpiryDateLike(String expiryDate) {
        return null;
    }

    @Override
    public List<Pharmaceuticals> findAllByExpiryDateBefore(String expiryDate) {
        return null;
    }

    @Override
    public List<Pharmaceuticals> findAllByExpiryDateAfter(String expiryDate) {
        return null;
    }

    @Override
    public List<Pharmaceuticals> findAllByManufactureDateBetween(String manufactureDate, String manufactureDate2) {
        return null;
    }

    @Override
    public List<Pharmaceuticals> findAllByExpiryDateBetween(String expiryDate, String expiryDate2) {
        return null;
    }
}
