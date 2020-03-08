package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.model.Patient;
import iot.empiaurhouse.chiron.services.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static iot.empiaurhouse.chiron.controllers.PatientController.PATIENT_EDITOR_VIEW;

@RequestMapping("/edit")
@Controller
public class EditController {
    private final PatientService patientService;

    public EditController(PatientService patientService) {
        this.patientService = patientService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/patient")
    public String editPatients(){
        return PATIENT_EDITOR_VIEW;
    }

    @GetMapping("/patient/{Id}")
    public String initPatientEditorForm(@PathVariable Long Id, Model patientModel){
        patientModel.addAttribute(patientService.findById(Id));
        return PATIENT_EDITOR_VIEW;
    }


    @PostMapping("/patient/{Id}")
    public String submitPatientEditorForm(@Valid Patient patient, BindingResult result, @PathVariable Long Id){
        if(result.hasErrors()){
            return PATIENT_EDITOR_VIEW;
        } else {
            patient.setId(Id);
            Patient stagedPatient = patientService.save(patient);
            return "redirect:/patients/info/" + stagedPatient.getId();
        }
    }


}
