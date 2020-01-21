package iot.empiaurhouse.chiron.services.map;

import iot.empiaurhouse.chiron.model.DiagnosisLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DLServiceMapTest {

    DLServiceMap dlServiceMap;
    final Long testID1 = 1L;

    @BeforeEach
    void setUp() {
        dlServiceMap = new DLServiceMap();
        DiagnosisLevel testDiagnosisLevel = new DiagnosisLevel();
        DiagnosisLevel mockDiagnosisLevel = new DiagnosisLevel();
        testDiagnosisLevel.setId(testID1);
        testDiagnosisLevel.setDiagnosisLevelName("Test");
        dlServiceMap.save(testDiagnosisLevel);
        dlServiceMap.save(mockDiagnosisLevel);
    }

    @Test
    void findAll() {
        Set<DiagnosisLevel> diagnosisLevelSet = dlServiceMap.findAll();
        assertEquals(2, diagnosisLevelSet.size());

    }

    @Test
    void findById() {
        DiagnosisLevel foundDiagnosisLevel = dlServiceMap.findById(testID1);
        assertEquals(testID1, foundDiagnosisLevel.getId());
    }

    @Test
    void saveSetID() {
        DiagnosisLevel knownDiagnosisLevel = new DiagnosisLevel();
        knownDiagnosisLevel.setId(3L);
        DiagnosisLevel returnedDiagnosisLevel = dlServiceMap.save(knownDiagnosisLevel);
        assertEquals(3L,returnedDiagnosisLevel.getId(),"Diagnosis Level Hash Map Service Save (Set ID) failed!");
    }

    @Test
    void saveAutoID(){
        DiagnosisLevel unknownDiagnosisLevel = new DiagnosisLevel();
        DiagnosisLevel returnedDiagnosisLevel = dlServiceMap.save(unknownDiagnosisLevel);
        assertNotNull(returnedDiagnosisLevel,"Diagnosis Level Hash Map Service Save (Auto ID) Failed!...No Diagnosis found!");
        assertNotNull(returnedDiagnosisLevel.getId(),"Diagnosis Level Hash Map Service Save (Auto ID) failed...No ID found!");

    }


    @Test
    void delete() {
        dlServiceMap.delete(dlServiceMap.findById(testID1));
        assertEquals(1, dlServiceMap.findAll().size(),"Diagnosis Level Hash Map Service Delete failed!");

    }

    @Test
    void deleteById() {
        dlServiceMap.deleteById(testID1);
        assertEquals(1, dlServiceMap.findAll().size(),"Diagnosis Level Hash Map Service Delete failed!");
    }
}