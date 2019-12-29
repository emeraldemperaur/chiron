package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.services.PractitionerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PractitionerController {

    private final PractitionerService practitionerService;

    public PractitionerController(PractitionerService practitionerService) {
        this.practitionerService = practitionerService;
    }

    @RequestMapping({"/practitioners", "/practitioners/index","/practitioners/index.html"})
    public String listPractitioners(Model practitionerModel){

        practitionerModel.addAttribute("practitioners", practitionerService.findAll());
        return "practitioners/index";
    }

}
