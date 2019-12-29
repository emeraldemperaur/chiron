package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.services.RNService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RNController {

    private final RNService rnService;

    public RNController(RNService rnService) {
        this.rnService = rnService;
    }

    @RequestMapping({"/registerednurses", "/registerednurses/index","/registerednurses/index.html"})
    public String listRNPractitioners(Model rnModel){

        rnModel.addAttribute("registerednurses", rnService.findAll());
        return "registerednurses/index";
    }
}
