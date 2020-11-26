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

import java.time.LocalDate;

import static iot.empiaurhouse.chiron.controllers.PatientController.PATIENT_EDITOR_VIEW;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class EditControllerTest {

    MockMvc mockMvcEnv;
    @Mock
    PatientService patientService;
    @InjectMocks
    PatientController patientController;
    Patient testPatient;

    @BeforeEach
    void setUp() {
        testPatient = new Patient();
        testPatient.setId(1L);
        testPatient.setFirstName("John");
        testPatient.setLastName("Smith");
        testPatient.setBirthDate(LocalDate.now().minusDays(3L));
        mockMvcEnv = MockMvcBuilders
                .standaloneSetup(patientController)
                .build();
    }

    @Test
    void initPatientEditor() throws Exception {
        when(patientService.findById(anyLong())).thenReturn(testPatient);
        mockMvcEnv.perform(get("/patients/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name(PATIENT_EDITOR_VIEW))
                .andExpect(model().attributeExists("patient"));


    }



}