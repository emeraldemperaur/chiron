package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.services.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }


    @RequestMapping({"/doctors", "/doctors/index","/doctors/index.html"})
    public String listDoctorPractitioners(Model doctorModel)
    {
        doctorModel.addAttribute("doctors", doctorService.findAll());
        return "doctors/index";
    }

}
