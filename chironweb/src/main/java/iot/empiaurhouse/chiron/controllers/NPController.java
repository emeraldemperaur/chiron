package iot.empiaurhouse.chiron.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NPController {

    @RequestMapping({"/nursepractitioners", "/nursepractitioners/index","/nursepractitioners/index.html"})
    public String listNursePractitioners(){
        return "nursepractitioners/index";
    }

}