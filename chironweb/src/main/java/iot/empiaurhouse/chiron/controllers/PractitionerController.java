package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.model.Doctor;
import iot.empiaurhouse.chiron.model.NursePractitioner;
import iot.empiaurhouse.chiron.model.Practitioner;
import iot.empiaurhouse.chiron.model.RegisteredNurse;
import iot.empiaurhouse.chiron.services.DoctorService;
import iot.empiaurhouse.chiron.services.NPService;
import iot.empiaurhouse.chiron.services.PractitionerService;
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
import java.util.Set;

@RequestMapping("/practitioners")
@Controller
public class PractitionerController {

    private final PractitionerService practitionerService;
    private final DoctorService doctorService;
    private final NPService npService;
    private final RNService rnService;
    public static final String PRACTITIONERS_EDITOR_VIEW = "practitioners/practitionerseditor";


    public PractitionerController(PractitionerService practitionerService, DoctorService doctorService, NPService npService, RNService rnService) {
        this.practitionerService = practitionerService;
        this.doctorService = doctorService;
        this.npService = npService;
        this.rnService = rnService;
    }

    @RequestMapping({"", "/", "/index","/index.html"})
    public String listPractitioners(Model practitionerModel){

        Set<Practitioner> allPractitioners = practitionerService.findAll();
        Set<Doctor> allDoctors = doctorService.findAll();
        Set<NursePractitioner> allNursePractitioners = npService.findAll();
        Set<RegisteredNurse> registeredNurses = rnService.findAll();
        int practitioners = allPractitioners.size();
        int doctors = allDoctors.size();
        int nursePractitioners = allNursePractitioners.size();
        int registerednurses = registeredNurses.size();
        int practitionersSum = practitioners + doctors + nursePractitioners + registerednurses;
        practitionerModel.addAttribute("practitioners", allPractitioners);
        practitionerModel.addAttribute("practitioner", new Practitioner());
        practitionerModel.addAttribute("practitionersSum", practitionersSum);
        return "practitioners/index";
    }

    @PostMapping("/create")
    public String addNewPatientRecord(@Valid Practitioner practitioner, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return PRACTITIONERS_EDITOR_VIEW;
        }else {
            Practitioner stagedPractitioner = practitionerService.save(practitioner);
            return "redirect:/practitioners/info/" + stagedPractitioner.getId();
        }
    }


    @GetMapping({"/inform","/info/{practitionerId}"})
    public ModelAndView renderPractitionerInfo(@PathVariable("practitionerId") Long practitionerId){
        ModelAndView practitionerMV = new ModelAndView("practitioners/practitionerinformation");
        practitionerMV.addObject(practitionerService.findById(practitionerId));

        return practitionerMV;
    }

    @GetMapping("/edit/{Id}")
    public String initPractitionerEditorForm(@PathVariable Long Id, Model practitionerModel){
        practitionerModel.addAttribute(practitionerService.findById(Id));
        return PRACTITIONERS_EDITOR_VIEW;
    }

    @PostMapping("/edit/{Id}")
    public String submitPractitionerEditorForm(@Valid Practitioner practitioner, BindingResult result, @PathVariable Long Id){
        if(result.hasErrors()){
            return PRACTITIONERS_EDITOR_VIEW;
        } else {
            practitioner.setId(Id);
            Practitioner stagedPractitioner = practitionerService.save(practitioner);
            return "redirect:/practitioners/info/" + stagedPractitioner.getId();
        }
    }


    @GetMapping("/contact/{Id}")
    public String contactPractitioner(@PathVariable Long Id, Model practitionerContactModel){
        Practitioner foundPractitioner = practitionerService.findById(Id);
        String phone = "'tel: +1 (000) 000-0000'";
        // phone = foundPractitioner.getContactInfo();
        String email = "info@chiron.com";
        // email = foundPractitioner.getEmailInfo();
        if (phone != null){
            practitionerContactModel.addAttribute("contact_info", phone);
        }
        else {
            practitionerContactModel.addAttribute("contact_info", email);
        }

        return "practitioners/practitionerinformation";
    }

    @GetMapping("/delete/{Id}")
    public String deletePatientRecordById(@PathVariable String Id){
        practitionerService.deleteById(Long.valueOf(Id));
        return "redirect:/practitioners";
    }


}
