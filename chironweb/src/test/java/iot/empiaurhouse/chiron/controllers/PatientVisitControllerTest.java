package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.model.Diagnosis;
import iot.empiaurhouse.chiron.model.DiagnosisLevel;
import iot.empiaurhouse.chiron.model.Patient;
import iot.empiaurhouse.chiron.model.Visit;
import iot.empiaurhouse.chiron.services.DiagnosisService;
import iot.empiaurhouse.chiron.services.VisitService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class PatientVisitControllerTest {

    private static final String VISIT_LOG_EDITOR = "patients/visit/visiteditor";
    private static final String REDIRECT_PATIENT_INFO = "redirect:/patients/info/";
    private static final String VISIT_LOG_ENTRY = "This is the test Log entry for testing purposes only";

    @Mock
    DiagnosisService diagnosisService;
    @Mock
    VisitService visitService;
    @InjectMocks
    PatientVisitController patientVisitController;
    private Diagnosis mockDiagnosis;
    private Patient testPatient;
    private DiagnosisLevel nullDiagnosisLevel = new DiagnosisLevel();
    private Set<Visit> visitTestSet = new HashSet<>();
    private MockMvc mockMvcEnv;
    private URI visitsUri;
    private final Map<String, String> uriObjects = new HashMap<>();
    private final UriTemplate visitsUriTemplate = new UriTemplate("/patients/{patientId}/diagnosis/{diagnosisId}/visit/add");
    private Long patientId;


    @BeforeEach
    void setUp() {
        Long diagnosisId = 1L;
        patientId = 1L;
        mockDiagnosis = new Diagnosis();
        testPatient = new Patient();
        testPatient.setFirstName("John");
        testPatient.setLastName("Doe");
        testPatient.setId(patientId);
        mockDiagnosis.setId(diagnosisId);
        mockDiagnosis.setPatient(testPatient);
        mockDiagnosis.setVisits(visitTestSet);
        mockDiagnosis.setVisitDate(LocalDate.now().minusDays(30));
        mockDiagnosis.setDiagnosisLevel(nullDiagnosisLevel);
        mockDiagnosis.setDiagnosisDetails("Positive Test Check Only");
        when(diagnosisService.findById(anyLong())).thenReturn(mockDiagnosis);
        uriObjects.clear();
        uriObjects.put("patientId", patientId.toString());
        uriObjects.put("diagnosisId", diagnosisId.toString());
        visitsUri = visitsUriTemplate.expand(uriObjects);
        mockMvcEnv = MockMvcBuilders.standaloneSetup(patientVisitController).build();

    }


    @Test
    void initVisitLog() throws Exception {
        mockMvcEnv.perform(get(visitsUri))
                .andExpect(status().isOk())
                .andExpect(view().name(VISIT_LOG_EDITOR));
    }

    @Test
    void postVisitLogEntry() throws Exception {
        mockMvcEnv.perform(post(visitsUri)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("visitDate", "2020-11-11")
                    .param("visitDescription", VISIT_LOG_ENTRY)
                    .param("visitTime", "12:00 PM")
                    .param("hostPractitioner", "Dr. Seuss"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(REDIRECT_PATIENT_INFO + patientId.toString() + "#diagnoseswrapper"))
                .andExpect(model().attributeExists("visit"));
    }

}