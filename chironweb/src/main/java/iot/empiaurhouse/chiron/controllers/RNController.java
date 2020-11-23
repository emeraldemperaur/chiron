package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.model.RegisteredNurse;
import iot.empiaurhouse.chiron.services.RNService;
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
public class RNController {

    private final RNService rnService;
    public static final String REGISTERED_NURSE_EDITOR_VIEW = "registerednurses/registerednurseseditor";


    public RNController(RNService rnService) {
        this.rnService = rnService;
    }

    @RequestMapping({"/registerednurses", "/registerednurses/index","/registerednurses/index.html"})
    public String listRNPractitioners(Model rnModel){

        rnModel.addAttribute("registerednurses", rnService.findAll());
        rnModel.addAttribute("registerednurse", new RegisteredNurse());

        return "registerednurses/index";
    }

    @PostMapping("/registerednurses/create")
    public String addNewRegisteredNurseRecord(@Valid RegisteredNurse registeredNurse, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return REGISTERED_NURSE_EDITOR_VIEW;
        }else {
            RegisteredNurse stagedRegisteredNurse = rnService.save(registeredNurse);
            return "redirect:/registerednurses/info/" + stagedRegisteredNurse.getId();
        }
    }

    @GetMapping({"/registerednurses/inform","registerednurses/info/{registerednurseId}"})
    public ModelAndView renderRegisteredNurseInfo(@PathVariable("registerednurseId") Long registerednurseId){
        ModelAndView registerednurseMV = new ModelAndView("registerednurses/registerednursesinformation");
        registerednurseMV.addObject(rnService.findById(registerednurseId));

        return registerednurseMV;
    }

    @GetMapping("registerednurses/edit/{Id}")
    public String initRegisteredNurseEditorForm(@PathVariable Long Id, Model registerednurseModel){
        registerednurseModel.addAttribute(rnService.findById(Id));
        return REGISTERED_NURSE_EDITOR_VIEW;
    }

    @PostMapping("registerednurses/edit/{Id}")
    public String submitRegisteredNurseEditorForm(@Valid RegisteredNurse registeredNurse, BindingResult result, @PathVariable Long Id){
        if(result.hasErrors()){
            return REGISTERED_NURSE_EDITOR_VIEW;
        } else {
            registeredNurse.setId(Id);
            RegisteredNurse stagedRegisteredNurse = rnService.save(registeredNurse);
            return "redirect:/registerednurses/info/" + stagedRegisteredNurse.getId();
        }
    }


    @GetMapping("/registerednurses/delete/{Id}")
    public String deleteRegisteredNurseRecordById(@PathVariable String Id){
        rnService.deleteById(Long.valueOf(Id));
        return "redirect:/registerednurses/index";
    }

}
