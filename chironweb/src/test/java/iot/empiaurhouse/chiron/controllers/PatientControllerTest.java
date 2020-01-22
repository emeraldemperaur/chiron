package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.model.Patient;
import iot.empiaurhouse.chiron.services.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PatientControllerTest {

    @Mock
    PatientService patientService;
    @InjectMocks
    PatientController patientController;
    Set<Patient> patients;
    MockMvc mockMvcEnv;

    @BeforeEach
    void setUp() {
        patients = new HashSet<>();
        Patient testPatient = new Patient();
        Patient mockPatient = new Patient();
        patients.add(testPatient);
        patients.add(mockPatient);
        mockMvcEnv = MockMvcBuilders
                .standaloneSetup(patientController)
                .build();

    }

    @Test
    void listPatients() throws Exception {
        when(patientService.findAll()).thenReturn(patients);
        mockMvcEnv.perform(MockMvcRequestBuilders.get("/patients"))
                .andExpect(status().isOk())
                .andExpect(view().name("patients/index"))
                .andExpect(model().attribute("patients", hasSize(2)));
    }

    @Test
    void findPatients() throws Exception {
        mockMvcEnv.perform(MockMvcRequestBuilders.get("/patients/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("patients/find"));
    }
}