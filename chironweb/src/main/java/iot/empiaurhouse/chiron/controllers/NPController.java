package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.services.NPService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NPController {

    private final NPService npService;

    public NPController(NPService npService) {
        this.npService = npService;
    }

    @RequestMapping({"/nursepractitioners", "/nursepractitioners/index","/nursepractitioners/index.html"})
    public String listNursePractitioners(Model npModel){
        npModel.addAttribute("nursepractitioners", npService.findAll());
        return "nursepractitioners/index";
    }

}
