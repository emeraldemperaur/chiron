package iot.empiaurhouse.chiron.services.map;

import iot.empiaurhouse.chiron.model.Doctor;
import iot.empiaurhouse.chiron.model.Speciality;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DoctorServiceMapTest {
    DoctorServiceMap doctorServiceMap;
    final Long testID1 = 1L;


    @BeforeEach
    void setUp() {
        doctorServiceMap = new DoctorServiceMap(new SpecialityMapService());
        Doctor testDoctor = new Doctor();
        Doctor mockDoctor = new Doctor();
        Speciality mockSpecialities = new Speciality();
        testDoctor.setId(testID1);
        testDoctor.getSpecialities().add(mockSpecialities);
        mockDoctor.setId(2L);
        mockDoctor.setFirstName("Peter");
        mockDoctor.setLastName("King");
        mockDoctor.setPractitionerID("Test-ID");
        mockDoctor.setContactInfo("Test1234");
        mockDoctor.getSpecialities().add(mockSpecialities);
        doctorServiceMap.save(testDoctor);
        doctorServiceMap.save(mockDoctor);
    }

    @Test
    void findAll() {
        Set<Doctor> doctorSet = doctorServiceMap.findAll();
        assertEquals(2, doctorSet.size());
    }

    @Test
    void findById() {
        Doctor foundDoctor = doctorServiceMap.findById(testID1);
        assertEquals(testID1, foundDoctor.getId());
    }

    @Test
    void save() {
        Doctor unknownDoctor = new Doctor();
        Doctor returnedDoctor = doctorServiceMap.save(unknownDoctor);
        assertNotNull(returnedDoctor,"Doctor Hash Map Service Save (Auto ID) Failed!...No Doc found!");
        assertNotNull(returnedDoctor.getId(),"Doctor Hash Map Service Save (Auto ID) failed...No ID found!");
    }

    @Test
    void delete() {
        doctorServiceMap.delete(doctorServiceMap.findById(testID1));
        assertEquals(1, doctorServiceMap.findAll().size(),"Doctor Hash Map Service Delete failed!");

    }

    @Test
    void deleteById() {
        doctorServiceMap.deleteById(testID1);
        assertEquals(1, doctorServiceMap.findAll().size(),"Doctor Hash Map Service Delete failed!");

    }


}