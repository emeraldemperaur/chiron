package iot.empiaurhouse.chiron.services.map;

import iot.empiaurhouse.chiron.model.NursePractitioner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class NPServiceMapTest {
    NPServiceMap npServiceMap;
    final Long testID1 = 1L;


    @BeforeEach
    void setUp() {
        npServiceMap = new NPServiceMap();
        NursePractitioner testNursePractitioner = new NursePractitioner();
        NursePractitioner mockNursePractitioner = new NursePractitioner();
        testNursePractitioner.setId(testID1);
        testNursePractitioner.setFirstName("Gwen");
        testNursePractitioner.setLastName("Dolly");
        testNursePractitioner.setPractitionerID("Test1234");
        npServiceMap.save(testNursePractitioner);
        npServiceMap.save(mockNursePractitioner);
    }

    @Test
    void findAll() {
        Set<NursePractitioner> nursePractitionerSet = npServiceMap.findAll();
        assertEquals(2, nursePractitionerSet.size());
    }

    @Test
    void findById() {
        NursePractitioner foundNP = npServiceMap.findById(testID1);
        assertEquals(testID1, foundNP.getId());
    }

    @Test
    void save() {
        NursePractitioner unknownNP = new NursePractitioner();
        NursePractitioner returnedNP = npServiceMap.save(unknownNP);
        assertNotNull(returnedNP,"NursePractitioner Hash Map Service Save (Auto ID) Failed!...No Doc found!");
        assertNotNull(returnedNP.getId(),"Doctor Hash Map Service Save (Auto ID) failed...No ID found!");
    }

    @Test
    void delete() {
        npServiceMap.delete(npServiceMap.findById(testID1));
        assertEquals(1, npServiceMap.findAll().size(),"NursePractitioner Hash Map Service Delete failed!");

    }

    @Test
    void deleteById() {
        npServiceMap.deleteById(testID1);
        assertEquals(1, npServiceMap.findAll().size(),"NursePractitioner Hash Map Service Delete failed!");

    }
}