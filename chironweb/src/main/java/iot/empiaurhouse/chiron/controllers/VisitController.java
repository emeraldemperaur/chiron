package iot.empiaurhouse.chiron.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/visits")
@Controller
public class VisitController {

    @RequestMapping({"","/", "/index","/index.html"})
    public String listPatients(Model visitsModel){


        return "visits/index";
    }


}
