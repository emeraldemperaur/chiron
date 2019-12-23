package iot.empiaurhouse.chiron.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PractitionerController {

    @RequestMapping({"/practitioners", "/practitioners/index","/practitioners/index.html"})
    public String listPractitioners(){
        return "practitioners/index";
    }

}
