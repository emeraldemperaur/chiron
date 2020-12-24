package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.model.Diagnosis;
import iot.empiaurhouse.chiron.model.Patient;
import iot.empiaurhouse.chiron.model.Prescription;
import iot.empiaurhouse.chiron.model.Visit;
import iot.empiaurhouse.chiron.services.DiagnosisService;
import iot.empiaurhouse.chiron.services.PatientService;
import iot.empiaurhouse.chiron.services.PrescriptionService;
import iot.empiaurhouse.chiron.services.VisitService;
import iot.empiaurhouse.chiron.util.VisitTimer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

import static iot.empiaurhouse.chiron.controllers.PatientPrescriptionController.PRESCRIPTION_EDITOR;
import static iot.empiaurhouse.chiron.controllers.PatientVisitController.VISIT_EDITOR;

@Controller
public class IndexController {

    public static final String PATIENT_EDITOR_VIEW = "patients/patienteditor";
    public static final String CHIRON_ERROR = "/error";
    private final PatientService patientService;
    private final PrescriptionService prescriptionService;
    private final DiagnosisService diagnosisService;
    private final VisitService visitService;
    private final Throwable cex;


    public IndexController(PatientService patientService, PrescriptionService prescriptionService, DiagnosisService diagnosisService, VisitService visitService) {
        this.patientService = patientService;
        this.prescriptionService = prescriptionService;
        this.diagnosisService = diagnosisService;
        this.visitService = visitService;
        cex = null;
    }


    @RequestMapping({"","/", "index", "index.html"})
    public String index(Model indexModel){

        String pattern = "E, dd MMM yyyy zzzz";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        indexModel.addAttribute("date", date);
        indexModel.addAttribute("patient", new Patient());
        return "index";
    }

    @PostMapping("/patients/{patientId}/prescription/add")
    public String submitNewPrescription(@Valid Prescription prescription, BindingResult bindingResult, @RequestParam Long diagnosis,
                                        @PathVariable String patientId){
        if (bindingResult.hasErrors()){
            return CHIRON_ERROR;
        }
        else {
            Diagnosis focusDiagnosis = diagnosisService.findById(diagnosis);
            prescription.setDiagnosis(focusDiagnosis);
            prescription.setPatient(focusDiagnosis.getPatient());
            prescriptionService.save(prescription);
            System.out.println("Successfully saved New Prescription for " + prescription.getPatient().getShortName() + " w/ Diagnosis ID: " + diagnosis.toString());
            return "redirect:/patients/info/" + patientId + "#diagnoseswrapper";
        }
    }


    @PostMapping("/patients/{patientId}/visit/add")
    public String submitNewVisit(@Valid Visit visit, BindingResult bindingResult, @RequestParam Long diagnosis,
                                 @PathVariable String patientId){
        if (bindingResult.hasErrors()){
            return CHIRON_ERROR;
        }
        else {
            Diagnosis focusDiagnosis = diagnosisService.findById(diagnosis);
            visit.setVisitDiagnosis(focusDiagnosis);
            visit.setVisitingPatient(focusDiagnosis.getPatient());
            visitService.save(visit);
            System.out.println("Successfully saved New Visit Log for " + visit.getVisitingPatient().getShortName() + " w/ Diagnosis ID: " + diagnosis.toString());
            return "redirect:/patients/info/" + patientId + "#diagnoseswrapper";
        }
    }

    @RequestMapping({"/chironerror", "/shit", "/ohfuck"})
    public String errorIndex(Model indexModel){

        String pattern = "E, dd MMM yyyy zzzz";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        indexModel.addAttribute("date", date);
        indexModel.addAttribute("error_info", cex);
        return "error";
    }

    @GetMapping("/patients/diagnosis/prescription")
    public String renderPrescriptionEditor(){
        return PRESCRIPTION_EDITOR;
    }

    @GetMapping("/patients/diagnosis/visit")
    public String renderVisitEditor(Model visitModel){
        VisitTimer visitTimes = new VisitTimer();
        visitModel.addAttribute("timeSlots", visitTimes.getTimeSlots());
        return VISIT_EDITOR;
    }

    @PostMapping("/create")
    public String addNewPatientRecord(@Valid Patient patient, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return PATIENT_EDITOR_VIEW;
        }else {
            Patient stagedPatient = patientService.save(patient);
            return "redirect:/patients/info/" + stagedPatient.getId();
        }
    }


    @ExceptionHandler({NullPointerException.class, NoSuchElementException.class, RuntimeException.class})
    public ModelAndView handleError(NullPointerException exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }


}
