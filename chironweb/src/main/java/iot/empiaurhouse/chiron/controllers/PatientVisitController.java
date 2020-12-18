package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.model.Diagnosis;
import iot.empiaurhouse.chiron.model.Visit;
import iot.empiaurhouse.chiron.services.DiagnosisService;
import iot.empiaurhouse.chiron.services.VisitService;
import iot.empiaurhouse.chiron.util.RxTypes;
import iot.empiaurhouse.chiron.util.VisitTimer;
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
public class PatientVisitController {
    private final DiagnosisService diagnosisService;
    private final VisitService visitService;
    static final String VISIT_EDITOR = "patients/visit/visiteditor";
    private Diagnosis stagedDiagnosis;



    public PatientVisitController(DiagnosisService diagnosisService, VisitService visitService) {
        this.diagnosisService = diagnosisService;
        this.visitService = visitService;
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


    @ModelAttribute("visit")
    public Visit loadDiagnosisWithVisitObject(@PathVariable("diagnosisId") Long diagnosisId, Map<String, Object> modelMap){
        stagedDiagnosis = diagnosisService.findById(diagnosisId);
        modelMap.put("diagnosis", stagedDiagnosis);
        Visit stagedVisit = new Visit();
        stagedDiagnosis.getVisits().add(stagedVisit);
        stagedVisit.setVisitDiagnosis(stagedDiagnosis);
        stagedVisit.setVisitingPatient(stagedDiagnosis.getPatient());
        return stagedVisit;
    }

    @GetMapping("/patients/*/diagnosis/{diagnosisId}/visit/add")
    public String initVisitLog(@PathVariable("diagnosisId") Long diagnosisId, Model visitModel, Map<String, Object> objectMap){
        VisitTimer visitTimes = new VisitTimer();
        RxTypes rxTypes = new RxTypes();
        rxTypes.initRxTypes();
        visitModel.addAttribute("timeSlots", visitTimes.getTimeSlots());
        visitModel.addAttribute("rxTypes", rxTypes.getRxTypes());
        visitModel.addAttribute("diagnosis", stagedDiagnosis);
        visitModel.addAttribute("editorButton", "CREATE");
        return VISIT_EDITOR;
    }

    @PostMapping("/patients/{patientId}/diagnosis/{diagnosisId}/visit/add")
    public String postVisitLogEntry(@Valid Visit visit, BindingResult bindingResult, @PathVariable String diagnosisId,
                                    @PathVariable String patientId){
        if(bindingResult.hasErrors()){
            return VISIT_EDITOR;
        }else {
            visitService.save(visit);
            System.out.println("Successfully saved New Visit Log for " + visit.getVisitingPatient()  + "w/ Diagnosis ID: " + diagnosisId);
            return "redirect:/patients/info/" + patientId + "#diagnoseswrapper";
        }
    }



    @GetMapping("/patients/{patientId}/diagnosis/{diagnosisId}/visit/{visitId}/edit")
    public String initVisitUpdateEditor(@PathVariable Long patientId, @PathVariable Long diagnosisId,
                                               @PathVariable Long visitId, Model visitModel) {
        Visit focusVisit = visitService.findById(visitId);
        VisitTimer visitTimes = new VisitTimer();
        RxTypes rxTypes = new RxTypes();
        rxTypes.initRxTypes();
        Diagnosis focusDiagnosis = diagnosisService.findById(diagnosisId);
        visitModel.addAttribute("visit", focusVisit);
        visitModel.addAttribute("timeSlots", visitTimes.getTimeSlots());
        visitModel.addAttribute("diagnosis", focusDiagnosis);
        visitModel.addAttribute("editorButton", "UPDATE");
        visitModel.addAttribute("editorTitle", "Edit Diagnosis("+ focusDiagnosis.getDiagnosisSynopsis() +
                ") Visit Record for " + focusVisit.getVisitingPatient().getFullName());
        return VISIT_EDITOR;
    }



    @PostMapping("/patients/{patientId}/diagnosis/{diagnosisId}/visit/{visitId}/edit")
    public String updateVisitData(@Valid Visit visit, BindingResult bindingResult, @PathVariable String diagnosisId,
                                         @PathVariable String patientId, @PathVariable String visitId){
        if (bindingResult.hasErrors()){
            return VISIT_EDITOR;
        }else {
            //patient.getDiagnoses().add(diagnosis);
            visit.setId(Long.valueOf(visitId));
            Diagnosis focusDiagnosis = diagnosisService.findById(Long.valueOf(diagnosisId));
            Optional<Visit> foundVisit = focusDiagnosis.getVisits().stream().filter(visit1 -> visit.getId().equals(Long.valueOf(visitId))).findFirst();
            if (visit.getId().equals(Long.valueOf(visitId))){
                if (foundVisit.isPresent()){
                    Visit editedVisit = foundVisit.get();
                    editedVisit.setVisitingPatient(visit.getVisitingPatient());
                    editedVisit.setVisitDiagnosis(visit.getVisitDiagnosis());
                    editedVisit.setVisitTime(visit.getVisitTime());
                    editedVisit.setVisitDescription(visit.getVisitDescription());
                    editedVisit.setVisitDate(visit.getVisitDate());
                    editedVisit.setHostPractitionerID(visit.getHostPractitionerID());
                    editedVisit.setHostPractitioner(visit.getHostPractitioner());
                    focusDiagnosis.getVisits().removeIf(visit1 -> visit.getId().equals(Long.valueOf(visitId)));
                    focusDiagnosis.getVisits().add(editedVisit);
                    diagnosisService.save(focusDiagnosis);

                }
            }
            System.out.println("Successfully modified Visit for " + visit.getVisitingPatient().getFullName()  + " w/ Diagnosis ID: " + diagnosisId);
            return "redirect:/patients/info/" + patientId + "#diagnoseswrapper";
        }
    }




}
