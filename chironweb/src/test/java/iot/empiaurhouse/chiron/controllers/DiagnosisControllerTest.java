package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.model.Diagnosis;
import iot.empiaurhouse.chiron.model.DiagnosisLevel;
import iot.empiaurhouse.chiron.model.Patient;
import iot.empiaurhouse.chiron.services.DiagnosisLevelService;
import iot.empiaurhouse.chiron.services.DiagnosisService;
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
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class DiagnosisControllerTest {

    MockMvc mockMvcEnv;
    @Mock
    PatientService patientService;
    @Mock
    DiagnosisService diagnosisService;
    @Mock
    DiagnosisLevelService diagnosisLevelService;
    @InjectMocks
    DiagnosisController diagnosisController;

    private Patient testPatient;
    private Diagnosis mockDiagnosis;
    private DiagnosisLevel testDiagnosisLevel;
    private DiagnosisLevel mockDiagnosisLevel;
    private Set<DiagnosisLevel> diagnosisLevels;

    @BeforeEach
    void setUp() {
        testPatient = new Patient();
        testDiagnosisLevel = new DiagnosisLevel();
        mockDiagnosisLevel = new DiagnosisLevel();
        mockDiagnosis = new Diagnosis();
        mockDiagnosis.setId(1L);
        mockDiagnosis.setDiagnosisSynopsis("Test Flu");
        mockDiagnosis.setDiagnosisDetails("Not COVID-19");
        mockDiagnosis.setVisitDate(LocalDate.now().plusDays(6L));
        testDiagnosisLevel.setDiagnosisLevelName("Emergency Test");
        mockDiagnosisLevel.setDiagnosisLevelName("Critical Test");
        testPatient.setId(1L);
        testPatient.setFirstName("John");
        testPatient.setLastName("Doe");
        diagnosisLevels = new HashSet<>();
        diagnosisLevels.add(testDiagnosisLevel);
        diagnosisLevels.add(mockDiagnosisLevel);
        mockMvcEnv = MockMvcBuilders.standaloneSetup(diagnosisController)
                .build();
    }

    @Test
    void initDiagnosisEditor() throws Exception {
        when(patientService.findById(anyLong())).thenReturn(testPatient);
        when(diagnosisLevelService.findAll()).thenReturn(diagnosisLevels);
        mockMvcEnv.perform(get("/patients/1/diagnosis/create"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("patient"))
                .andExpect(model().attributeExists("editorTitle"))
                .andExpect(model().attributeExists("editorButton"))
                .andExpect(model().attributeExists("diagnosis"))
                .andExpect(view().name("patients/diagnosiseditor"));

    }

    @Test
    void initDiagnosisUpdateEditor() throws Exception {
        when(patientService.findById(anyLong())).thenReturn(testPatient);
        when(diagnosisLevelService.findAll()).thenReturn(diagnosisLevels);
        when(diagnosisService.findById(anyLong())).thenReturn(mockDiagnosis);
        mockMvcEnv.perform(get("/patients/1/diagnosis/1/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("patient"))
                .andExpect(model().attributeExists("editorTitle"))
                .andExpect(model().attributeExists("editorButton"))
                .andExpect(model().attributeExists("diagnosis"))
                .andExpect(view().name("patients/diagnosiseditor"));
    }


    @Test
    void  postNewDiagnosisData() throws Exception {
        when(patientService.findById(anyLong())).thenReturn(testPatient);
        when(diagnosisLevelService.findAll()).thenReturn(diagnosisLevels);
        mockMvcEnv.perform(post("/patients/1/diagnosis/create"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/patients/info/1#diagnoseswrapper"));
        verify(diagnosisService).save(any());
    }

    @Test
    void updateDiagnosisData() throws Exception {
        when(patientService.findById(anyLong())).thenReturn(testPatient);
        when(diagnosisLevelService.findAll()).thenReturn(diagnosisLevels);
        mockMvcEnv.perform(post("/patients/1/diagnosis/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/patients/info/1#diagnoseswrapper"));
        verify(diagnosisService).save(any());
    }



    @Test
    void fetchDiagnosisLevels() throws Exception{
        when(diagnosisLevelService.findAll()).thenReturn(diagnosisLevels);
        Set<DiagnosisLevel> diagnosisLevelsFound = (Set<DiagnosisLevel>) diagnosisController.fetchDiagnosisLevels();
        assertEquals(2, diagnosisLevelsFound.size());
    }

    @Test
    void findPatientRecord() throws Exception{
        when(patientService.findById(anyLong())).thenReturn(testPatient);
        Patient foundPatient = diagnosisController.findPatient(1L);
        assertEquals(testPatient.getLastName(), foundPatient.getLastName());
    }


}