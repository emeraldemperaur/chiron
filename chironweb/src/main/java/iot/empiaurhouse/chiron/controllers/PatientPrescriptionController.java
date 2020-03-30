package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.model.Diagnosis;
import iot.empiaurhouse.chiron.model.Prescription;
import iot.empiaurhouse.chiron.services.DiagnosisService;
import iot.empiaurhouse.chiron.services.PrescriptionService;
import iot.empiaurhouse.chiron.util.RxTypes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.util.Map;

@Controller
public class PatientPrescriptionController {

    private final PrescriptionService prescriptionService;
    private final DiagnosisService diagnosisService;
    static final String PRESCRIPTION_EDITOR = "patients/prescription/prescriptioneditor";
    private Diagnosis stagedDiagnosis;



    public PatientPrescriptionController(PrescriptionService prescriptionService, DiagnosisService diagnosisService) {
        this.prescriptionService = prescriptionService;
        this.diagnosisService = diagnosisService;
    }

    @InitBinder
    public void dataBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");
        webDataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) throws IllegalArgumentException{
                setValue(LocalDate.parse(text));
            }
        });
    }

    @ModelAttribute("prescription")
    public Prescription loadDiagnosisWithPrescription(@PathVariable("diagnosisId") Long diagnosisId, Map<String, Object> objectMap){
        stagedDiagnosis = diagnosisService.findById(diagnosisId);
        objectMap.put("diagnosis", stagedDiagnosis);
        Prescription stagedPrescription = new Prescription();
        stagedDiagnosis.getPrescriptions().add(stagedPrescription);
        stagedPrescription.setDiagnosis(stagedDiagnosis);
        stagedPrescription.setPatient(stagedDiagnosis.getPatient());
        return stagedPrescription;
    }


    @GetMapping("/patients/*/diagnosis/{diagnosisId}/prescription/add")
    public String initPrescriptionScript(@PathVariable("diagnosisId") Long diagnosisId, Model RxModel,
                                         Map<String, Object> objectMap){
        RxTypes rxTypes = new RxTypes();
        rxTypes.initRxTypes();
        RxModel.addAttribute("diagnosis", stagedDiagnosis);
        RxModel.addAttribute("rxTypes", rxTypes.getRxTypes());
        return PRESCRIPTION_EDITOR;
    }


    @PostMapping("/patients/{patientId}/diagnosis/{diagnosisId}/prescription/add")
    public String postPrescriptionScript(@Valid Prescription prescription, BindingResult bindingResult, @PathVariable String diagnosisId,
                                    @PathVariable String patientId){
        if(bindingResult.hasErrors()){
            return PRESCRIPTION_EDITOR;
        }else {
            prescriptionService.save(prescription);
            System.out.println("Successfully saved New Prescription for " + prescription.getPatient()  + "w/ Diagnosis ID: " + diagnosisId);
            return "redirect:/patients/info/" + patientId + "#diagnoseswrapper";
        }
    }


}
