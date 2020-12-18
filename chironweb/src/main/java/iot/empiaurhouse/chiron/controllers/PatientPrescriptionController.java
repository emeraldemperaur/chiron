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
import java.util.Optional;

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
        RxModel.addAttribute("editorButton", "CREATE");
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


    @GetMapping("/patients/{patientId}/diagnosis/{diagnosisId}/prescription/{prescriptionId}/edit")
    public String initPrescriptionUpdateEditor(@PathVariable Long patientId, @PathVariable Long diagnosisId,
                                            @PathVariable Long prescriptionId, Model prescriptionModel) {
        Prescription focusPrescription = prescriptionService.findById(prescriptionId);
        Diagnosis focusDiagnosis = diagnosisService.findById(diagnosisId);
        RxTypes rxTypes = new RxTypes();
        rxTypes.initRxTypes();
        prescriptionModel.addAttribute("prescription", focusPrescription);
        prescriptionModel.addAttribute("diagnosis", focusDiagnosis);
        prescriptionModel.addAttribute("rxTypes", rxTypes.getRxTypes());
        prescriptionModel.addAttribute("editorButton", "UPDATE");
        prescriptionModel.addAttribute("editorTitle", "Edit "+ focusPrescription.getPrescriptionName() +
                " Prescription Record for " + focusPrescription.getPatient().getFullName());
        return PRESCRIPTION_EDITOR;
    }



    @PostMapping("/patients/{patientId}/diagnosis/{diagnosisId}/prescription/{prescriptionId}/edit")
    public String updatePrescriptionData(@Valid Prescription prescription, BindingResult bindingResult, @PathVariable String diagnosisId,
                                      @PathVariable String patientId, @PathVariable String prescriptionId){
        if (bindingResult.hasErrors()){
            return PRESCRIPTION_EDITOR;
        }else {
            //patient.getDiagnoses().add(diagnosis);
            prescription.setId(Long.valueOf(prescriptionId));
            Diagnosis focusDiagnosis = diagnosisService.findById(Long.valueOf(diagnosisId));
            Optional<Prescription> foundPrescription = focusDiagnosis.getPrescriptions().stream().filter(prescription1 -> prescription.getId().equals(Long.valueOf(prescriptionId))).findFirst();
            if (prescription.getId().equals(Long.valueOf(prescriptionId))){
                if (foundPrescription.isPresent()){
                    Prescription editedPrescription = foundPrescription.get();
                    editedPrescription.setPatient(prescription.getPatient());
                    editedPrescription.setDiagnosis(prescription.getDiagnosis());
                    editedPrescription.setPrescriptionName(prescription.getPrescriptionName());
                    editedPrescription.setPrescriptionDosageRegimen(prescription.getPrescriptionDosageRegimen());
                    editedPrescription.setPrescribedDosageAmount(prescription.getPrescribedDosageAmount());
                    editedPrescription.setPrescribedDosageType(prescription.getPrescribedDosageType());
                    editedPrescription.setPrescriptionDate(prescription.getPrescriptionDate());
                    editedPrescription.setPrescribedDuration(prescription.getPrescribedDuration());
                    editedPrescription.setPrescriptionPractitionerID(prescription.getPrescriptionPractitionerID());
                    editedPrescription.setPrescribedBy(prescription.getPrescribedBy());
                    focusDiagnosis.getPrescriptions().removeIf(prescription1 -> prescription.getId().equals(Long.valueOf(prescriptionId)));
                    focusDiagnosis.getPrescriptions().add(editedPrescription);
                    diagnosisService.save(focusDiagnosis);

                }
            }
            System.out.println("Successfully modified Prescription for " + prescription.getPatient().getFullName()  + " w/ Diagnosis ID: " + diagnosisId);
            return "redirect:/patients/info/" + patientId + "#diagnoseswrapper";
        }
    }



}
