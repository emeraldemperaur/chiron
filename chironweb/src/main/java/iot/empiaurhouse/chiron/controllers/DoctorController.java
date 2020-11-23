package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.model.Doctor;
import iot.empiaurhouse.chiron.services.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class DoctorController {

    private final DoctorService doctorService;
    public static final String DOCTORS_EDITOR_VIEW = "doctors/doctorseditor";


    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }


    @RequestMapping({"/doctors", "/doctors/index","/doctors/index.html"})
    public String listDoctorPractitioners(Model doctorModel)
    {
        doctorModel.addAttribute("doctors", doctorService.findAll());
        doctorModel.addAttribute("doctor", new Doctor());

        return "doctors/index";
    }

    @PostMapping("/doctors/create")
    public String addNewDoctorRecord(@Valid Doctor doctor, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return DOCTORS_EDITOR_VIEW;
        }else {
            Doctor stagedDoctor = doctorService.save(doctor);
            return "redirect:/doctors/info/" + stagedDoctor.getId();
        }
    }

    @GetMapping({"/doctors/inform","doctors/info/{doctorId}"})
    public ModelAndView renderDoctorInfo(@PathVariable("doctorId") Long doctorId){
        ModelAndView doctorMV = new ModelAndView("doctors/doctorsinformation");
        doctorMV.addObject(doctorService.findById(doctorId));

        return doctorMV;
    }

    @GetMapping("doctors/edit/{Id}")
    public String initDoctorEditorForm(@PathVariable Long Id, Model doctorModel){
        doctorModel.addAttribute(doctorService.findById(Id));
        return DOCTORS_EDITOR_VIEW;
    }

    @PostMapping("doctors/edit/{Id}")
    public String submitDoctorEditorForm(@Valid Doctor doctor, BindingResult result, @PathVariable Long Id){
        if(result.hasErrors()){
            return DOCTORS_EDITOR_VIEW;
        } else {
            doctor.setId(Id);
            Doctor stagedDoctor = doctorService.save(doctor);
            return "redirect:/doctors/info/" + stagedDoctor.getId();
        }
    }


    @GetMapping("/doctors/delete/{Id}")
    public String deleteDoctorRecordById(@PathVariable String Id){
        doctorService.deleteById(Long.valueOf(Id));
        return "redirect:/doctors/index";
    }

}
