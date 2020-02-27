package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.model.Patient;
import iot.empiaurhouse.chiron.services.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/vendorfinder")
@Controller
public class PatientFinderController {

    private final PatientService patientService;

    public PatientFinderController(PatientService patientService) {
        this.patientService = patientService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }






    @GetMapping("/insurancevendor")
    public String findByInsuranceVendorForm(Patient patient, BindingResult result, Model model){
        // permits empty GET request to return all patient records records in DB
        if (patient.getInsuranceVendor() == null) {
            patient.setInsuranceVendor(""); // empty string signifies broadest possible search
        }
        // find patient by first name
        List<Patient> results = patientService.findAllByInsuranceVendor("%"+ patient.getInsuranceVendor() + "%");

        if (results.isEmpty()) {
            // if no patient found
            result.rejectValue("insuranceVendor", "notFound", "patient not found");
            return "patients/find";
        } else if (results.size() == 1) {
            // if 1 patient found
            patient = results.get(0);
            return "redirect:/patients/info/" + patient.getId();
        } else {
            // multiple patients found
            model.addAttribute("patientVRecords", results);
            return "patients/patientsfound";
        }
    }

    @GetMapping("/insurancevendorid")
    public String findByInsuranceVendorIDForm(Patient patient, BindingResult result, Model model){
        // permits empty GET request to return all patient records records in DB
        if (patient.getInsuranceVendorID() == null) {
            patient.setInsuranceVendorID(""); // empty string signifies broadest possible search
        }
        // find patient by first name
        List<Patient> results = patientService.findAllByInsuranceVendorID("%"+ patient.getInsuranceVendorID() + "%");

        if (results.isEmpty()) {
            // if no patient found
            result.rejectValue("insuranceVendorID", "notFound", "patient not found");
            return "patients/find";
        } else if (results.size() == 1) {
            // if 1 patient found
            patient = results.get(0);
            return "redirect:/patients/info/" + patient.getId();
        } else {
            // multiple patients found
            model.addAttribute("patientIDRecords", results);
            return "patients/patientsfound";
        }
    }


    @GetMapping("/test")
    public String testInfoUI(){

        return "patients/patientinformation";
    }



}
