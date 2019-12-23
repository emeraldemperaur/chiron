package iot.empiaurhouse.chiron.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RNController {
    @RequestMapping({"/registerednurses", "/registerednurses/index","/registerednurses/index.html"})
    public String listPractitioners(){
        return "registerednurses/index";
    }
}
