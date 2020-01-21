package iot.empiaurhouse.chiron.services.map;

import iot.empiaurhouse.chiron.model.Diagnosis;
import iot.empiaurhouse.chiron.model.DiagnosisLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DiagnosisServiceMapTest {

    DiagnosisServiceMap diagnosisServiceMap;
    DiagnosisLevel testDiagnosisLevel;
    final Long testID1 = 1L;


    @BeforeEach
    void setUp(){
        diagnosisServiceMap = new DiagnosisServiceMap();
        Diagnosis testDiagnosis = new Diagnosis();
        testDiagnosisLevel = new DiagnosisLevel();
        testDiagnosisLevel.setDiagnosisLevelName("Test");
        Diagnosis mockDiagnosis = new Diagnosis();
        testDiagnosis.setDiagnosisLevel(testDiagnosisLevel);
        mockDiagnosis.setDiagnosisLevel(testDiagnosisLevel);
        testDiagnosis.setId(testID1);
        diagnosisServiceMap.save(testDiagnosis);
        diagnosisServiceMap.save(mockDiagnosis);
    }

    @Test
    void findAll() {
        Set<Diagnosis> diagnosisSet = diagnosisServiceMap.findAll();
        assertEquals(2, diagnosisSet.size());


    }

    @Test
    void findById() {
        Diagnosis foundDiagnosis = diagnosisServiceMap.findById(testID1);
        assertEquals(testID1, foundDiagnosis.getId());

    }

    @Test
    void saveSetID() {
        Diagnosis knownDiagnosis = new Diagnosis();
        knownDiagnosis.setId(3L);
        Diagnosis returnedDiagnosis = diagnosisServiceMap.save(knownDiagnosis);
        assertEquals(3L,returnedDiagnosis.getId(),"Diagnosis Hash Map Service Save (Set ID) failed!");
    }

    @Test
    void saveAutoID(){
        Diagnosis unknownDiagnosis = new Diagnosis();
        Diagnosis returnedDiagnosis = diagnosisServiceMap.save(unknownDiagnosis);
        assertNotNull(returnedDiagnosis,"Diagnosis Hash Map Service Save (Auto ID) Failed!...No Diagnosis found!");
        assertNotNull(returnedDiagnosis.getId(),"Diagnosis Hash Map Service Save (Auto ID) failed...No ID found!");

    }

    @Test
    void delete() {
        diagnosisServiceMap.delete(diagnosisServiceMap.findById(testID1));
        assertEquals(1, diagnosisServiceMap.findAll().size(),"Diagnosis Hash Map Service Delete failed!");

    }

    @Test
    void deleteById() {
        diagnosisServiceMap.deleteById(testID1);
        assertEquals(1, diagnosisServiceMap.findAll().size(),"Diagnosis Hash Map Service Delete failed!");

    }


     @Test
    void findByDiagnosisLevel() {
        Set<Diagnosis> diagnoses = diagnosisServiceMap.findByDiagnosisLevel(testDiagnosisLevel);
        System.out.println(diagnoses.toString());
        assertNotNull(diagnoses);

    }

}