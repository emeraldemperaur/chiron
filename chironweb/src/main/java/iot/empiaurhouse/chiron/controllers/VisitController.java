package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.model.Diagnosis;
import iot.empiaurhouse.chiron.model.DiagnosisLevel;
import iot.empiaurhouse.chiron.model.Patient;
import iot.empiaurhouse.chiron.model.Visit;
import iot.empiaurhouse.chiron.services.DiagnosisLevelService;
import iot.empiaurhouse.chiron.services.DiagnosisService;
import iot.empiaurhouse.chiron.services.PatientService;
import iot.empiaurhouse.chiron.services.VisitService;
import iot.empiaurhouse.chiron.util.VisitTimer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@RequestMapping("/visits")
@Controller
public class VisitController {

    private final VisitService visitService;
    public static final String CHIRON_ERROR = "/error";
    private final PatientService patientService;
    private final DiagnosisLevelService diagnosisLevelService;

    public VisitController(VisitService visitService, PatientService patientService, DiagnosisService diagnosisService, DiagnosisService diagnosisService1, DiagnosisLevelService diagnosisLevelService) {
        this.visitService = visitService;
        this.patientService = patientService;
        this.diagnosisLevelService = diagnosisLevelService;
    }

    @RequestMapping({"","/", "/index","/index.html"})
    public String listVisits(Model visitsModel){
        VisitTimer visitTimes = new VisitTimer();
        Visit newVisit = new Visit();
        DiagnosisLevel medCheckLevel = diagnosisLevelService.findByDiagnosisLevelNameLike("NORMAL");
        Diagnosis medCheck = new Diagnosis();
        medCheck.setVisitDate(LocalDate.now());
        medCheck.setDiagnosisLevel(medCheckLevel);
        String pattern = "MMMM dd, yyyy - h:m a";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        medCheck.setDiagnosisSynopsis("Medical Checkup on " + date);
        medCheck.setDiagnosisDetails("See Visit Log of " + medCheck.getDiagnosisSynopsis() + " for more details");
        newVisit.setVisitDate(LocalDate.now());
        newVisit.setVisitDiagnosis(medCheck);
        Set<Visit> allVisits = visitService.findAll();
        Set<Patient> patients = patientService.findAll();
        int visitsCount = allVisits.size();
        visitsModel.addAttribute("visits", allVisits);
        visitsModel.addAttribute("visitsCount", visitsCount);
        visitsModel.addAttribute("patients", patients);
        visitsModel.addAttribute("newVisit", newVisit);
        visitsModel.addAttribute("timeSlots", visitTimes.getTimeSlots());
        return "visits/index";
    }


    @PostMapping("/patients/visit/new")
    public String submitNewVisit(@RequestParam Long patient){

        return "redirect:/patients/info/" + patient.toString() + "#diagnoseswrapper";

    }


}
