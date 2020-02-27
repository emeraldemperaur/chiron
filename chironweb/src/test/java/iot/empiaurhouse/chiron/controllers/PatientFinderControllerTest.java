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

import java.util.ArrayList;
import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class PatientFinderControllerTest {

    @Mock
    PatientService patientService;
    @InjectMocks
    PatientController patientController;
    MockMvc mockMvcEnv;
    Patient testPatient;
    Patient mockPatient;
    ArrayList<Patient> patients = new ArrayList<>();


    @BeforeEach
    void setUp() {
        testPatient = new Patient();
        mockPatient = new Patient();
        testPatient.setId(1L);
        mockPatient.setId(2L);
        patients.add(testPatient);
        patients.add(mockPatient);
        mockMvcEnv = MockMvcBuilders
                .standaloneSetup(patientController)
                .build();
    }

    @Test
    void findManyByParamForm() throws Exception {
        when(patientService.findAllByLastNameLike(anyString()))
                .thenReturn(patients);
        mockMvcEnv.perform(get("/patients/findlastname"))
                .andExpect(status().isOk())
                .andExpect(view().name("patients/patientsfound"))
                .andExpect(model().attribute("patientLNRecords", hasSize(2)));
    }

    @Test
    void findOneByParamForm() throws Exception {
        when(patientService.findAllByLastNameLike(anyString())).thenReturn(Collections.singletonList(testPatient));
        mockMvcEnv.perform(get("/patients/findlastname"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/patients/info/1"));
    }

    @Test
    void findAllByEmptyForm() throws Exception {
        when(patientService.findAllByLastNameLike(anyString()))
                .thenReturn(patients);
        mockMvcEnv.perform(get("/patients/findlastname")
                .param("lastName", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("patients/patientsfound"))
                .andExpect(model().attribute("patientLNRecords", hasSize(2)));

    }


}