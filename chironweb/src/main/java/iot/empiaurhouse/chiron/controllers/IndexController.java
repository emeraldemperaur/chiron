package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.util.VisitTimer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

import static iot.empiaurhouse.chiron.controllers.PatientPrescriptionController.PRESCRIPTION_EDITOR;
import static iot.empiaurhouse.chiron.controllers.PatientVisitController.VISIT_EDITOR;

@Controller
public class IndexController {

    @RequestMapping({"","/", "index", "index.html"})
    public String index(Model indexModel){

        String pattern = "E, dd MMM yyyy zzzz";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        indexModel.addAttribute("date", date);

        return "index";
    }

    @GetMapping("/patients/diagnosis/prescription")
    public String renderPrescriptionEditor(){
        return PRESCRIPTION_EDITOR;
    }

    @GetMapping("/patients/diagnosis/visit")
    public String renderVisitEditor(Model visitModel){
        VisitTimer visitTimes = new VisitTimer();
        visitModel.addAttribute("timeSlots", visitTimes.getTimeSlots());
        return VISIT_EDITOR;
    }

}
