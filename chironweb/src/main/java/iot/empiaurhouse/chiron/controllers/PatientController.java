package iot.empiaurhouse.chiron.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/patients")
@Controller
public class PatientController {

    @RequestMapping({"","/", "/index","/index.html"})
    public String listPatients(){
        return "patients/index";
    }

}
