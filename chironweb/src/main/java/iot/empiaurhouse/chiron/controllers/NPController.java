package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.model.NursePractitioner;
import iot.empiaurhouse.chiron.services.NPService;
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
public class NPController {

    private final NPService npService;
    public static final String NURSE_PRACTITIONER_EDITOR_VIEW = "nursepractitioners/nursepractitionerseditor";


    public NPController(NPService npService) {
        this.npService = npService;
    }

    @RequestMapping({"/nursepractitioners", "/nursepractitioners/index","/nursepractitioners/index.html"})
    public String listNursePractitioners(Model npModel){
        npModel.addAttribute("nursepractitioners", npService.findAll());
        npModel.addAttribute("nursepractitioner", new NursePractitioner());

        return "nursepractitioners/index";
    }

    @PostMapping("/nursepractitioners/create")
    public String addNewNursePractitionerRecord(@Valid NursePractitioner nursePractitioner, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return NURSE_PRACTITIONER_EDITOR_VIEW;
        }else {
            NursePractitioner stagedNursePractitioner = npService.save(nursePractitioner);
            return "redirect:/nursepractitioners/info/" + stagedNursePractitioner.getId();
        }
    }

    @GetMapping({"/nursepractitioners/inform","nursepractitioners/info/{nursepractitionerId}"})
    public ModelAndView renderNursePractitionerInfo(@PathVariable("nursepractitionerId") Long nursepractitionerId){
        ModelAndView nursepractitionerMV = new ModelAndView("nursepractitioners/nursepractitionersinformation");
        nursepractitionerMV.addObject(npService.findById(nursepractitionerId));

        return nursepractitionerMV;
    }

    @GetMapping("nursepractitioners/edit/{Id}")
    public String initNursePractitionerEditorForm(@PathVariable Long Id, Model nursepractitionerModel){
        nursepractitionerModel.addAttribute(npService.findById(Id));
        return NURSE_PRACTITIONER_EDITOR_VIEW;
    }

    @PostMapping("nursepractitioners/edit/{Id}")
    public String submitNursePractitionerEditorForm(@Valid NursePractitioner nursePractitioner, BindingResult result, @PathVariable Long Id){
        if(result.hasErrors()){
            return NURSE_PRACTITIONER_EDITOR_VIEW;
        } else {
            nursePractitioner.setId(Id);
            NursePractitioner stagedNursePractitioner = npService.save(nursePractitioner);
            return "redirect:/nursepractitioners/info/" + stagedNursePractitioner.getId();
        }
    }


    @GetMapping("/nursepractitioners/delete/{Id}")
    public String deleteNursePractitionerRecordById(@PathVariable String Id){
        npService.deleteById(Long.valueOf(Id));
        return "redirect:/nursepractitioners/index";
    }


}
