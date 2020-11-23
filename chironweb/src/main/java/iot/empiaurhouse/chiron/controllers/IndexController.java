package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.model.Patient;
import iot.empiaurhouse.chiron.services.PatientService;
import iot.empiaurhouse.chiron.util.VisitTimer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

import static iot.empiaurhouse.chiron.controllers.PatientPrescriptionController.PRESCRIPTION_EDITOR;
import static iot.empiaurhouse.chiron.controllers.PatientVisitController.VISIT_EDITOR;

@Controller
public class IndexController {

    public static final String PATIENT_EDITOR_VIEW = "patients/patienteditor";
    private final PatientService patientService;

    public IndexController(PatientService patientService) {
        this.patientService = patientService;
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

}
