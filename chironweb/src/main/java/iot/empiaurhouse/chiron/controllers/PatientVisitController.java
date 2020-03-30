package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.model.Diagnosis;
import iot.empiaurhouse.chiron.model.Patient;
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






    @GetMapping("/patients/{patientId}/diagnosis/{diagnosisId}/visit/{visitId}/delete")
    public String deleteDiagnosisRecordById(@PathVariable String diagnosisId, @PathVariable String visitId,
                                            Patient patient){
        visitService.deleteById(Long.valueOf(visitId));
        return "redirect:/patients/info/" + patient.getId() + "#diagnoseswrapper";
    }


}
