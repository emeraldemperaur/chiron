package iot.empiaurhouse.chiron.jpaservices;

import iot.empiaurhouse.chiron.model.Doctor;
import iot.empiaurhouse.chiron.model.Speciality;
import iot.empiaurhouse.chiron.repositories.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DoctorJPAServiceTest {

    @Mock
    DoctorRepository doctorRepository;
    @InjectMocks
    DoctorJPAService doctorJPAService;

    Doctor testDoctor;
    Doctor mockDoctor;
    Speciality mockSpeciality;
    final Long testID1 = 1L;


    @BeforeEach
    void setUp() {
        testDoctor = new Doctor();
        mockDoctor = new Doctor();
        mockSpeciality = new Speciality();
        mockSpeciality.setSpecialityDescription("Test");
        testDoctor.setFirstName("Phil");
        testDoctor.setLastName("McGraw");
        testDoctor.setContactInfo("7782680948");
        testDoctor.setPractitionerID("TestID");
        testDoctor.getSpecialities().add(mockSpeciality);
        mockDoctor.setPractitionerID("TEST1234");
        mockDoctor.setFirstName("John");
        mockDoctor.setLastName("Murphy");
        mockDoctor.getSpecialities().add(mockSpeciality);
    }

    @Test
    void findByLastName() {
        when(doctorRepository.findByLastName(anyString())).thenReturn(testDoctor);
        Doctor foundDoctor = doctorJPAService.findByLastName("McGraw");
        assertEquals(testDoctor.getLastName(),foundDoctor.getLastName(),"doctorJPAService.findByLastName() " +
                "failed...Test Doctor not found!");
        System.out.println("Test Doctor: " + foundDoctor.getLastName() + " " + foundDoctor.getFirstName() + " was found!");
        verify(doctorRepository).findByLastName(anyString());
    }

    @Test
    void findByFirstName() {
        when(doctorRepository.findByFirstName(anyString())).thenReturn(testDoctor);
        Doctor foundDoctor = doctorJPAService.findByFirstName("Phil");
        assertEquals(testDoctor.getFirstName(),foundDoctor.getFirstName(),"doctorJPAService.findByFirstName() " +
                "failed...Test Doctor not found!");
        System.out.println("Test Doctor: " + foundDoctor.getFirstName() + " " + foundDoctor.getLastName() + " was found!");
        verify(doctorRepository).findByFirstName(anyString());
    }

    @Test
    void findByPractitionerID() {
        when(doctorRepository.findByPractitionerID(anyString())).thenReturn(testDoctor);
        Doctor foundDoctor = doctorJPAService.findByPractitionerID("TEST1234");
        assertEquals(testDoctor.getPractitionerID(),foundDoctor.getPractitionerID(),
                "doctorJPAService.findByPractitionerID() " +
                        "failed...Test Doctor not found!");
        System.out.println("Test Doctor found with PractitionerID: " + foundDoctor.getPractitionerID() + "!");
        verify(doctorRepository).findByPractitionerID(anyString());
    }

    @Test
    void findAll() {
        Set<Doctor> returnedPatientSet = new HashSet<>();
        returnedPatientSet.add(testDoctor);
        returnedPatientSet.add(mockDoctor);
        when(doctorRepository.findAll()).thenReturn(returnedPatientSet);
        Set<Doctor> doctors = doctorJPAService.findAll();
        assertNotNull(doctors);
        assertEquals(2,doctors.size());
        System.out.println("All test Doctors were found: " + doctors.toString());
    }

    @Test
    void findById() {
        when(doctorRepository.findById(anyLong())).thenReturn(Optional.of(testDoctor));
        Doctor foundDoctor = doctorJPAService.findById(1L);
        assertNotNull(foundDoctor);
    }

    @Test
    void save() {
        Doctor newDoctor = new Doctor();
        when(doctorRepository.save(any())).thenReturn(testDoctor);
        Doctor storedDoctor = doctorJPAService.save(newDoctor);
        assertNotNull(storedDoctor);
        System.out.println(storedDoctor.getLastName());
        verify(doctorRepository).save(any());
    }

    @Test
    void delete() {
        doctorJPAService.delete(mockDoctor);
        verify(doctorRepository).delete(any());
    }

    @Test
    void deleteById() {
        doctorJPAService.deleteById(testID1);
        verify(doctorRepository).deleteById(anyLong());
    }



}