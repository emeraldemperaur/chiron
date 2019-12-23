package iot.empiaurhouse.chiron.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/pharmaceuticals")
@Controller
public class PharmaceuticalController {

    @RequestMapping({"","/", "/index","/index.html"})
    public String listPharmaceuticals(){
        return "pharmaceuticals/index";
    }

}
