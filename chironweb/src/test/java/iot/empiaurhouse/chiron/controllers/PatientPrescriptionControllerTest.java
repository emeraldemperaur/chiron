package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.model.Diagnosis;
import iot.empiaurhouse.chiron.model.DiagnosisLevel;
import iot.empiaurhouse.chiron.model.Patient;
import iot.empiaurhouse.chiron.model.Prescription;
import iot.empiaurhouse.chiron.services.DiagnosisService;
import iot.empiaurhouse.chiron.services.PrescriptionService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class PatientPrescriptionControllerTest {

    private static final String PRESCRIPTION_SCRIPT_EDITOR = "patients/prescription/prescriptioneditor";
    private static final String REDIRECT_PATIENT_INFO = "redirect:/patients/info/";
    private URI prescriptionUri;
    private final Map<String, String> uriObjects = new HashMap<>();
    private final UriTemplate prescriptionUriTemplate = new UriTemplate("/patients/{patientId}/diagnosis/{diagnosisId}/prescription/add");

    @Mock
    PrescriptionService prescriptionService;
    @Mock
    DiagnosisService diagnosisService;
    @InjectMocks
    PatientPrescriptionController patientPrescriptionController;
    Set<Prescription> mockPrescriptions;
    Prescription testPrescription;
    Prescription genericPrescription;
    Prescription nullPrescription;
    MockMvc mockMvcEnv;
    private Long patientId;
    private DiagnosisLevel nullDiagnosisLevel;
    private Diagnosis mockDiagnosis;


    @BeforeEach
    void setUp() {
        mockPrescriptions = new HashSet<>();
        testPrescription = new Prescription();
        genericPrescription = new Prescription();
        nullPrescription = new Prescription();
        testPrescription.setPrescriptionName("Test Drug");
        testPrescription.setBrandName("Test Drug");
        genericPrescription.setPrescriptionName("Generic Drug");
        nullPrescription.setPrescriptionName("Null Prescription");
        mockPrescriptions.add(testPrescription);
        mockPrescriptions.add(genericPrescription);
        mockPrescriptions.add(nullPrescription);
        Long diagnosisId = 1L;
        patientId = 1L;
        mockDiagnosis = new Diagnosis();
        Patient testPatient = new Patient();
        testPatient.setFirstName("John");
        testPatient.setLastName("Doe");
        testPatient.setId(patientId);
        mockDiagnosis.setId(diagnosisId);
        mockDiagnosis.setPatient(testPatient);
        mockDiagnosis.setPrescriptions(mockPrescriptions);
        mockDiagnosis.setVisitDate(LocalDate.now().minusDays(30));
        mockDiagnosis.setDiagnosisLevel(nullDiagnosisLevel);
        mockDiagnosis.setDiagnosisDetails("Positive Test Check Only");
        uriObjects.clear();
        uriObjects.put("patientId", patientId.toString());
        uriObjects.put("diagnosisId", diagnosisId.toString());
        prescriptionUri = prescriptionUriTemplate.expand(uriObjects);
        mockMvcEnv = MockMvcBuilders.standaloneSetup(patientPrescriptionController).build();

    }


    @Test
    void prescriptionServiceFetch() throws Exception{
        when(prescriptionService.findAll()).thenReturn(mockPrescriptions);
        Set<Prescription> foundPrescriptions = prescriptionService.findAll();
        System.out.println(foundPrescriptions);
        assertEquals(mockPrescriptions.size(), foundPrescriptions.size());
    }

    @Test
    void initPrescriptionScript() throws Exception {
        when(diagnosisService.findById(anyLong())).thenReturn(mockDiagnosis);
        mockMvcEnv.perform(get(prescriptionUri))
                .andExpect(status().isOk())
                .andExpect(view().name(PRESCRIPTION_SCRIPT_EDITOR));
    }

    @Test
    void postPrescriptionScript() throws Exception {
        when(diagnosisService.findById(anyLong())).thenReturn(mockDiagnosis);
        mockMvcEnv.perform(post(prescriptionUri)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("prescriptionName", "2020-11-11")
                .param("prescriptionDosageRegimen", "Per Hour")
                .param("prescribedDosageAmount", "2")
                .param("prescribedDuration", "3 Months")
                .param("prescribedBy", "Dr. Seuss")
                .param("prescriptionDate", LocalDate.now().toString())
                .param("prescribedDosageType", "Pill"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(REDIRECT_PATIENT_INFO + patientId.toString() + "#diagnoseswrapper"))
                .andExpect(model().attributeExists("prescription"));
    }
}