package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.services.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }


    @GetMapping({"/find"})
    public String findPatients(){

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




}
