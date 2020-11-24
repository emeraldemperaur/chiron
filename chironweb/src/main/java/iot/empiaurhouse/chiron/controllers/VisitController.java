package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.model.Visit;
import iot.empiaurhouse.chiron.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@RequestMapping("/visits")
@Controller
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @RequestMapping({"","/", "/index","/index.html"})
    public String listPatients(Model visitsModel){

        Set<Visit> allVisits = visitService.findAll();
        int visitsCount = allVisits.size();
        visitsModel.addAttribute("visits", allVisits);
        visitsModel.addAttribute("visitsCount", visitsCount);
        return "visits/index";
    }


}
