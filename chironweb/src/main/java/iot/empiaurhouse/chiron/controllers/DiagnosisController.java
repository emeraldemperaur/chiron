package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.model.Diagnosis;
import iot.empiaurhouse.chiron.model.DiagnosisLevel;
import iot.empiaurhouse.chiron.model.Patient;
import iot.empiaurhouse.chiron.services.DiagnosisLevelService;
import iot.empiaurhouse.chiron.services.DiagnosisService;
import iot.empiaurhouse.chiron.services.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RequestMapping("/patients/{patientId}")
@Controller
public class DiagnosisController {

    private final DiagnosisService diagnosisService;
    private final PatientService patientService;
    private final DiagnosisLevelService diagnosisLevelService;
    private static final String DIAGNOSIS_EDITOR = "patients/diagnosiseditor";


    public DiagnosisController(DiagnosisService diagnosisService, PatientService patientService, DiagnosisLevelService diagnosisLevelService) {
        this.diagnosisService = diagnosisService;
        this.patientService = patientService;
        this.diagnosisLevelService = diagnosisLevelService;
    }

    @ModelAttribute("levels")
    public Collection<DiagnosisLevel> fetchDiagnosisLevels(){
        return diagnosisLevelService.findAll();
    }

    @ModelAttribute("patient")
    public Patient findPatient(@PathVariable("patientId") Long patientId){
        return patientService.findById(patientId);
    }

    @InitBinder("patient")
    public void initPatientObjectBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");
    }

    @GetMapping("/diagnosis/create")
    public String initDiagnosisEditor(Patient patient, Model diagnosisModel){
        Diagnosis stagedDiagnosis = new Diagnosis();
        patient.getDiagnoses().add(stagedDiagnosis);
        stagedDiagnosis.setPatient(patient);
        diagnosisModel.addAttribute("diagnosis", stagedDiagnosis);
        diagnosisModel.addAttribute("editorButton", "CREATE");
        diagnosisModel.addAttribute("editorTitle", "Add New Diagnosis Record");
        return DIAGNOSIS_EDITOR;
    }

    @PostMapping("/diagnosis/create")
    public String postDiagnosisData(Patient patient, @Valid Diagnosis diagnosis, BindingResult bindingResult,
                                    ModelMap map){

        if(StringUtils.hasLength(diagnosis.getDiagnosisSynopsis()) && diagnosis.isNew() && patient.getDiagnosis(diagnosis.getDiagnosisSynopsis(), true) != null){
            bindingResult.rejectValue("name", "duplicate", "already exists");
        }
        patient.getDiagnoses().add(diagnosis);
        if (bindingResult.hasErrors()){
            map.put("diagnosis", diagnosis);
            return DIAGNOSIS_EDITOR;
        }else {
            diagnosis.setPatient(patient);
            diagnosisService.save(diagnosis);
            return "redirect:/patients/info/" + patient.getId() + "#diagnoseswrapper";
        }

    }

    @GetMapping("/diagnosis/{diagnosisId}/edit")
    public String initDiagnosisUpdateEditor(@PathVariable Long diagnosisId, Model diagnosisModel) {
        Diagnosis focusDiagnosis = diagnosisService.findById(diagnosisId);
        diagnosisModel.addAttribute("diagnosis", focusDiagnosis);
        diagnosisModel.addAttribute("editorButton", "UPDATE");
        diagnosisModel.addAttribute("editorTitle", "Edit "+ focusDiagnosis.getDiagnosisSynopsis() +
                " Diagnosis Record for " + getFocusDiagnosisPatient(diagnosisId));
        return DIAGNOSIS_EDITOR;
    }


    @PostMapping("/diagnosis/{diagnosisId}/edit")
    public String updateDiagnosisData(@Valid Diagnosis diagnosis, BindingResult bindingResult, Patient patient,
                                      Model diagnosesModel){
        if (bindingResult.hasErrors()){
            diagnosis.setPatient(patient);
            diagnosesModel.addAttribute("diagnosis", diagnosis);
            return DIAGNOSIS_EDITOR;
        }else {
            diagnosis.setPatient(patient);
            diagnosisService.save(diagnosis);
            return "redirect:/patients/info/" + patient.getId() + "#diagnoseswrapper";
        }
    }

    @GetMapping("/diagnosis/{diagnosisId}/delete")
    public String deleteDiagnosisRecordById(@PathVariable String diagnosisId, Patient patient){
        diagnosisService.deleteById(Long.valueOf(diagnosisId));
        return "redirect:/patients/info/" + patient.getId() + "#diagnoseswrapper";
    }


    private String getFocusDiagnosisPatient(Long diagnosisId) {
        Diagnosis focusDiagnosis = diagnosisService.findById(diagnosisId);
        if (focusDiagnosis.getPatient() != null) {
            Patient focusPatient = focusDiagnosis.getPatient();
            return focusPatient.getFirstName() + " " + focusPatient.getLastName();
        }
        else {
                return "Null Patient - Fetch Error!";
            }
        }




}
