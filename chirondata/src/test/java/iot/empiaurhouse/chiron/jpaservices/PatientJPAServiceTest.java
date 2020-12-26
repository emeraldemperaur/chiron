package iot.empiaurhouse.chiron.jpaservices;

import iot.empiaurhouse.chiron.model.Patient;
import iot.empiaurhouse.chiron.repositories.DiagnosisLevelRepository;
import iot.empiaurhouse.chiron.repositories.DiagnosisRepository;
import iot.empiaurhouse.chiron.repositories.PatientRepository;
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
class PatientJPAServiceTest {

    @Mock
    PatientRepository patientRepository;
    @Mock
    DiagnosisRepository diagnosisRepository;
    @Mock
    DiagnosisLevelRepository diagnosisLevelRepository;
    @InjectMocks
    PatientJPAService patientJPAService;

    Patient testPatient;
    Patient mockPatient;
    final Long testID1 = 1L;


    @BeforeEach
    void setUp() {
        testPatient = new Patient();
        mockPatient = new Patient();
        testPatient.setFirstName("Jaha");
        testPatient.setLastName("Thelonious");
        testPatient.setBirthDate(LocalDate.now());
        testPatient.setInsuranceVendor("TestVendor");
        testPatient.setInsuranceVendorID("TEST1234");
        mockPatient.setFirstName("John");
        mockPatient.setLastName("Murphy");
    }

    @Test
    void findByLastName() {
        when(patientRepository.findByLastName(anyString())).thenReturn(testPatient);
        Patient foundPatient = patientJPAService.findByLastName("Thelonious");
        assertEquals(testPatient.getLastName(),foundPatient.getLastName(),"patientJPAService.findByLastName() " +
                "failed...Test Patient not found!");
        System.out.println("Test Patient: " + foundPatient.getLastName() + " " + foundPatient.getFirstName() + " was found!");
        verify(patientRepository).findByLastName(anyString());
    }

    @Test
    void findByFirstName() {
        when(patientRepository.findByFirstName(anyString())).thenReturn(testPatient);
        Patient foundPatient = patientJPAService.findByFirstName("Jaha");
        assertEquals(testPatient.getFirstName(),foundPatient.getFirstName(),"patientJPAService.findByFirstName() " +
                "failed...Test Patient not found!");
        System.out.println("Test Patient: " + foundPatient.getFirstName() + " " + foundPatient.getLastName() + " was found!");
        verify(patientRepository).findByFirstName(anyString());

    }

  

    @Test
    void findAll() {
        Set<Patient> returnedPatientSet = new HashSet<>();
        returnedPatientSet.add(testPatient);
        returnedPatientSet.add(mockPatient);
        when(patientRepository.findAll()).thenReturn(returnedPatientSet);
        Set<Patient> patients = patientJPAService.findAll();
        assertNotNull(patients);
        assertEquals(2,patients.size());
        System.out.println("All test Patients were found: " + patients.toString());

    }

    @Test
    void findById() {
        when(patientRepository.findById(anyLong())).thenReturn(Optional.of(testPatient));
        Patient foundPatient = patientJPAService.findById(1L);
        assertNotNull(foundPatient);

    }

    @Test
    void save() {
        Patient newPatient = new Patient();
        when(patientRepository.save(any())).thenReturn(testPatient);
        Patient storedPatient = patientJPAService.save(newPatient);
        assertNotNull(storedPatient);
        System.out.println(storedPatient.getLastName());
        verify(patientRepository).save(any());
    }

    @Test
    void delete() {
        patientJPAService.delete(mockPatient);
        verify(patientRepository).delete(any());

    }

    @Test
    void deleteById() {
        patientJPAService.deleteById(testID1);
        verify(patientRepository).deleteById(anyLong());

    }
}