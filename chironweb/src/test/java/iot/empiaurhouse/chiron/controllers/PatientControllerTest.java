package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.model.Patient;
import iot.empiaurhouse.chiron.services.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class PatientControllerTest {

    @Mock
    PatientService patientService;
    @InjectMocks
    PatientController patientController;
    Set<Patient> patients;
    MockMvc mockMvcEnv;
    Patient testPatient;
    Patient mockPatient;

    @BeforeEach
    void setUp() {
        patients = new HashSet<>();
        testPatient = new Patient();
        mockPatient = new Patient();
        testPatient.setId(1L);
        mockPatient.setId(2L);
        testPatient.setFirstName("John");
        testPatient.setLastName("Doe");
        mockPatient.setFirstName("Jane");
        mockPatient.setLastName("Smith");
        mockPatient.setBloodGroup("B+");
        testPatient.setBloodGroup("O-");
        patients.add(testPatient);
        patients.add(mockPatient);
        mockMvcEnv = MockMvcBuilders
                .standaloneSetup(patientController)
                .build();

    }

    @Test
    void listPatients() throws Exception {
        when(patientService.findAll()).thenReturn(patients);
        mockMvcEnv.perform(get("/patients"))
                .andExpect(status().isOk())
                .andExpect(view().name("patients/index"))
                .andExpect(model().attribute("patients", hasSize(2)));
    }

    @Test
    void findPatients() throws Exception {
        mockMvcEnv.perform(get("/patients/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("patients/find"))
                .andExpect(model().attributeExists("patient"));
        verifyNoInteractions(patientService);
    }


    @Test
    void renderPatient() throws Exception{

        when(patientService.findById(anyLong())).thenReturn(testPatient);
        mockMvcEnv.perform(get("/patients/info/1"))
                .andExpect(view().name("patients/patientinformation"))
                .andExpect(model().attribute("patient", hasProperty("id", is(1L))));

                log.debug("Found test patient: " + testPatient.getFirstName() +
                        " w/ ID (Long): " + testPatient.getId());


    }



}