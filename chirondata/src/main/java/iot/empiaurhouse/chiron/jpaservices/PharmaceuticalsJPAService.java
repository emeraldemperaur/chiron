package iot.empiaurhouse.chiron.jpaservices;

import iot.empiaurhouse.chiron.model.Pharmaceuticals;
import iot.empiaurhouse.chiron.repositories.PharmaceuticalsRepository;
import iot.empiaurhouse.chiron.services.PharmaceuticalsService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
@Profile("SpringDataJPA")
public class PharmaceuticalsJPAService implements PharmaceuticalsService {

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
