package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/visits")
@Controller
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @RequestMapping({"","/", "/index","/index.html"})
    public String listPatients(Model visitsModel){


        visitsModel.addAttribute("visits", visitService.findAll());
        return "visits/index";
    }


}
