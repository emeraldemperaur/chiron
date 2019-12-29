package iot.empiaurhouse.chiron.services.map;


import iot.empiaurhouse.chiron.model.Pharmaceuticals;
import iot.empiaurhouse.chiron.services.PharmaceuticalsService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
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
        return super.save(object.getId(), object);
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
        return null;
    }

    @Override
    public Pharmaceuticals findByGenericName(String genericName) {
        return null;
    }

    @Override
    public Pharmaceuticals findByManufactureDate(LocalDate manufactureDate) {
        return null;
    }

    @Override
    public Pharmaceuticals findByExpiryDate(LocalDate expiryDate) {
        return null;
    }

    @Override
    public Set<Pharmaceuticals> findSetByManufactureDate(LocalDate manufactureDate) {
        return null;
    }

    @Override
    public Set<Pharmaceuticals> findSetByExpiryDate(LocalDate expiryDate) {
        return null;
    }
}
