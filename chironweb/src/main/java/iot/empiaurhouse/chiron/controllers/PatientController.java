package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.model.Patient;
import iot.empiaurhouse.chiron.services.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/patients")
@Controller
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping({"","/", "/index","/index.html"})
    public String listPatients(Model patientModel){

        patientModel.addAttribute("patients", patientService.findAll());

        return "patients/index";
    }

    @GetMapping({"/find"})
    public String findPatients(Model findPatientsModel){
        findPatientsModel.addAttribute("patient", new Patient());
        return "patients/find";
    }


    @GetMapping({"/inform","/info/{patientId}"})
    public ModelAndView renderPatientInfo(@PathVariable("patientId") Long patientId){
        ModelAndView patientMV = new ModelAndView("patients/patientinformation");
        patientMV.addObject(patientService.findById(patientId));

        return patientMV;
    }

    @GetMapping("/info")
    public String testInfoUI(){

        return "patients/patientinformation";
    }

    @GetMapping("/findlastname")
    public String findByLastNameForm(Patient patient, BindingResult result, Model model){
        // permits empty GET request to return all patient records records in DB
        if (patient.getLastName() == null) {
            patient.setLastName(""); // empty string signifies broadest possible search
        }
        // find patient by last name
        List<Patient> results = patientService.findAllByLastNameLike("%"+ patient.getLastName() + "%");

        if (results.isEmpty()) {
            // if no patient found
            result.rejectValue("lastName", "notFound", "patient not found");
            return "patients/find";
        } else if (results.size() == 1) {
            // if 1 patient found
            patient = results.get(0);
            return "redirect:/patients/info/" + patient.getId();
        } else {
            // multiple patients found
            model.addAttribute("patientLNRecords", results);
            return "patients/patientsfound";
        }
    }

    @GetMapping("/findfirstname")
    public String findByFirstNameForm(Patient patient, BindingResult result, Model model){
        // permits empty GET request to return all patient records records in DB
        if (patient.getFirstName() == null) {
            patient.setFirstName(""); // empty string signifies broadest possible search
        }
        // find patient by first name
        List<Patient> results = patientService.findAllByFirstNameLike("%"+ patient.getFirstName() + "%");

        if (results.isEmpty()) {
            // if no patient found
            result.rejectValue("firstName", "notFound", "patient not found");
            return "patients/find";
        } else if (results.size() == 1) {
            // if 1 patient found
            patient = results.get(0);
            return "redirect:/patients/info/" + patient.getId();
        } else {
            // multiple patients found
            model.addAttribute("patientFNRecords", results);
            return "patients/patientsfound";
        }
    }






}
