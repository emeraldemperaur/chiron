package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.services.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/patients")
@Controller
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping({"","/", "/index","/index.html"})
    public String listPatients(Model patientModel){

        patientModel.addAttribute("patients", patientService.findAll());

        return "patients/index";
    }


    @RequestMapping({"/find"})
    public String findPatients(){

        return "patients/find";
    }


}
