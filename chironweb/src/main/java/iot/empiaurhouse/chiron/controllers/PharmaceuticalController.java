package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.services.PharmaceuticalsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/pharmaceuticals")
@Controller
public class PharmaceuticalController {

    private final PharmaceuticalsService pharmaceuticalsService;

    public PharmaceuticalController(PharmaceuticalsService pharmaceuticalsService) {
        this.pharmaceuticalsService = pharmaceuticalsService;
    }

    @RequestMapping({"","/", "/index","/index.html"})
    public String listPharmaceuticals(Model pharmaceuticalsModel){

        pharmaceuticalsModel.addAttribute("pharmaceuticals", pharmaceuticalsService.findAll());
        return "pharmaceuticals/index";
    }

}
