package iot.empiaurhouse.chiron.services.map;

import iot.empiaurhouse.chiron.model.Pharmaceuticals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PharmaceuticalsServiceMapTest {
    PharmaceuticalsServiceMap pharmaceuticalsServiceMap;
    final Long testID1 = 1L;


    @BeforeEach
    void setUp() {
        pharmaceuticalsServiceMap = new PharmaceuticalsServiceMap();
        Pharmaceuticals testPharmaceuticals = new Pharmaceuticals();
        Pharmaceuticals mockPharmaceuticals = new Pharmaceuticals();
        testPharmaceuticals.setId(testID1);
        testPharmaceuticals.setGenericName("Generic-Test");
        testPharmaceuticals.setApprovalNumber("Test");
        testPharmaceuticals.setManufactureDate(LocalDate.now());
        testPharmaceuticals.setExpiryDate(LocalDate.now());
        testPharmaceuticals.setManufacturerName("Test");
        testPharmaceuticals.setBrandName("Test");
        pharmaceuticalsServiceMap.save(testPharmaceuticals);
        pharmaceuticalsServiceMap.save(mockPharmaceuticals);
    }

    @Test
    void findAll() {
        Set<Pharmaceuticals> pharmaceuticalsSet = pharmaceuticalsServiceMap.findAll();
        assertEquals(2, pharmaceuticalsSet.size());
    }

    @Test
    void findById() {
        Pharmaceuticals foundPharmaceuticals = pharmaceuticalsServiceMap.findById(testID1);
        assertEquals(testID1, foundPharmaceuticals.getId());
    }

    @Test
    void save() {
        Pharmaceuticals unknownDrugs = new Pharmaceuticals();
        Pharmaceuticals returnedDrug = pharmaceuticalsServiceMap.save(unknownDrugs);
        assertNotNull(returnedDrug,"Pharmaceuticals Hash Map Service Save (Auto ID) Failed!...No Doc found!");
        assertNotNull(returnedDrug.getId(),"Pharmaceuticals Hash Map Service Save (Auto ID) failed...No ID found!");
    }

    @Test
    void delete() {
        pharmaceuticalsServiceMap.delete(pharmaceuticalsServiceMap.findById(testID1));
        assertEquals(1, pharmaceuticalsServiceMap.findAll().size(),"Pharmaceuticals Hash Map Service Delete failed!");

    }

    @Test
    void deleteById() {
        pharmaceuticalsServiceMap.deleteById(testID1);
        assertEquals(1, pharmaceuticalsServiceMap.findAll().size(),"Pharmaceuticals Hash Map Service Delete failed!");

    }



}