package iot.empiaurhouse.chiron.jpaservices;

import iot.empiaurhouse.chiron.model.Pharmaceuticals;
import iot.empiaurhouse.chiron.repositories.PharmaceuticalsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PharmaceuticalsJPAServiceTest {

    @Mock
    PharmaceuticalsRepository pharmaceuticalsRepository;
    @InjectMocks
    PharmaceuticalsJPAService pharmaceuticalsJPAService;
    Pharmaceuticals testPharmaceuticals;
    Pharmaceuticals mockPharmaceuticals;
    final Long testID1 = 1L;

    @BeforeEach
    void setUp() {
        testPharmaceuticals = new Pharmaceuticals();
        mockPharmaceuticals = new Pharmaceuticals();
        testPharmaceuticals.setGenericName("GenericTestDrug");
        testPharmaceuticals.setBrandName("TestDrug-TM");
        testPharmaceuticals.setChemicalName("TestDrug-CH");
        testPharmaceuticals.setBatchNumber("1");
        testPharmaceuticals.setManufacturerName("TestDrugCorp");
        testPharmaceuticals.setManufactureDate(LocalDate.now());
        testPharmaceuticals.setExpiryDate(LocalDate.now().plusDays(1L));
    }

    @Test
    void findByBrandName() {
        when(pharmaceuticalsRepository.findByBrandName(anyString())).thenReturn(testPharmaceuticals);
        Pharmaceuticals foundDrug = pharmaceuticalsJPAService.findByBrandName("TestDrug-TM");
        assertEquals(testPharmaceuticals.getBrandName(),foundDrug.getBrandName(),"pharmaceuticalsRepository.findByBrandName() " +
                "failed...Test Drug not found!");
        System.out.println("Test Drug: " + foundDrug.getBrandName() + " was found!");
        verify(pharmaceuticalsRepository).findByBrandName(anyString());
    }

    @Test
    void findByGenericName() {
        when(pharmaceuticalsRepository.findByGenericName(anyString())).thenReturn(testPharmaceuticals);
        Pharmaceuticals foundDrug = pharmaceuticalsJPAService.findByGenericName("GenericTestDrug");
        assertEquals(testPharmaceuticals.getGenericName(),foundDrug.getGenericName(),"pharmaceuticalsRepository.findByBrandName() " +
                "failed...Test Drug not found!");
        System.out.println("Test Drug: " + foundDrug.getGenericName() + " was found!");
        verify(pharmaceuticalsRepository).findByGenericName(anyString());
    }

    @Test
    void findByManufactureDate() {
        when(pharmaceuticalsRepository.findByManufactureDate(any())).thenReturn(testPharmaceuticals);
        Pharmaceuticals foundDrug = pharmaceuticalsJPAService.findByManufactureDate(LocalDate.now());
        assertEquals(testPharmaceuticals.getManufactureDate(),foundDrug.getManufactureDate(),"pharmaceuticalsRepository.findByManufactureDate() " +
                "failed...Test Drug not found!");
        System.out.println("Test Drug made on: " + foundDrug.getManufactureDate() + " was found!");
        verify(pharmaceuticalsRepository).findByManufactureDate(any());
    }

    @Test
    void findByExpiryDate() {
        when(pharmaceuticalsRepository.findByExpiryDate(any())).thenReturn(testPharmaceuticals);
        Pharmaceuticals foundDrug = pharmaceuticalsJPAService.findByExpiryDate(LocalDate.now().plusDays(1L));
        assertEquals(testPharmaceuticals.getExpiryDate(),foundDrug.getExpiryDate(),"pharmaceuticalsRepository.findByExpiryDate() " +
                "failed...Test Drug not found!");
        System.out.println("Test Drug which expires on: " + foundDrug.getExpiryDate() + " was found!");
        verify(pharmaceuticalsRepository).findByExpiryDate(any());
    }

    @Test
    void findAll() {
        Set<Pharmaceuticals> pharmaceuticalsHashSet = new HashSet<>();
        pharmaceuticalsHashSet.add(testPharmaceuticals);
        pharmaceuticalsHashSet.add(mockPharmaceuticals);
        when(pharmaceuticalsRepository.findAll()).thenReturn(pharmaceuticalsHashSet);
        Set<Pharmaceuticals> pharmaceuticals = pharmaceuticalsJPAService.findAll();
        assertNotNull(pharmaceuticals);
        assertEquals(2,pharmaceuticals.size());
        System.out.println("All test Pharmaceuticals were found: " + pharmaceuticals.toString());
    }

    @Test
    void findById() {
        when(pharmaceuticalsRepository.findById(anyLong())).thenReturn(Optional.of(testPharmaceuticals));
        Pharmaceuticals foundDrug = pharmaceuticalsJPAService.findById(1L);
        assertNotNull(foundDrug);
    }

    @Test
    void save() {
        Pharmaceuticals newPharmaceuticals = new Pharmaceuticals();
        when(pharmaceuticalsRepository.save(any())).thenReturn(testPharmaceuticals);
        Pharmaceuticals storedDrug = pharmaceuticalsJPAService.save(newPharmaceuticals);
        assertNotNull(storedDrug);
        System.out.println(storedDrug.getBrandName());
        verify(pharmaceuticalsRepository).save(any());
    }

    @Test
    void delete() {
        pharmaceuticalsJPAService.delete(mockPharmaceuticals);
        verify(pharmaceuticalsRepository).delete(any());
    }

    @Test
    void deleteById() {
        pharmaceuticalsJPAService.deleteById(testID1);
        verify(pharmaceuticalsRepository).deleteById(anyLong());

    }
}