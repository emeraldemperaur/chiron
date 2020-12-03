package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.model.Doctor;
import iot.empiaurhouse.chiron.model.Prescription;
import iot.empiaurhouse.chiron.model.Visit;
import iot.empiaurhouse.chiron.services.DoctorService;
import iot.empiaurhouse.chiron.services.PrescriptionService;
import iot.empiaurhouse.chiron.services.VisitService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping("/doctors")
@Controller
public class DoctorController {

    private final DoctorService doctorService;
    private final PrescriptionService prescriptionService;
    private final VisitService visitService;
    public static final String DOCTORS_EDITOR_VIEW = "doctors/doctorseditor";


    public DoctorController(DoctorService doctorService, PrescriptionService prescriptionService, VisitService visitService) {
        this.doctorService = doctorService;
        this.prescriptionService = prescriptionService;
        this.visitService = visitService;
    }


    @RequestMapping({"","/", "/index","/index.html"})
    public String listDoctorPractitioners(Model doctorModel)
    {
        Set<Doctor> allDoctors = doctorService.findAll();
        int doctorCount = allDoctors.size();
        doctorModel.addAttribute("doctors", allDoctors);
        doctorModel.addAttribute("doctor", new Doctor());
        doctorModel.addAttribute("doctorCount", doctorCount);

        return "doctors/index";
    }

    @PostMapping("/create")
    public String addNewDoctorRecord(@Valid Doctor doctor, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return DOCTORS_EDITOR_VIEW;
        }else {
            Doctor stagedDoctor = doctorService.save(doctor);
            return "redirect:/doctors/info/" + stagedDoctor.getId();
        }
    }

    @GetMapping({"/inform","/info/{doctorId}"})
    public ModelAndView renderDoctorInfo(@PathVariable("doctorId") Long doctorId){
        ModelAndView doctorMV = new ModelAndView("doctors/doctorsinformation");
        Doctor foundDoctor = doctorService.findById(doctorId);
        String doctorID = foundDoctor.getPractitionerID();
        String doctorName = foundDoctor.getFullName();
        Set<Prescription> foundPrescriptions = prescriptionService.findAll();
        Set<Visit> foundVisits = visitService.findAll();
        Set<Prescription> doctorPrescriptions = foundPrescriptions.stream().filter(prescription ->
                prescription.getPrescribedBy().contains(doctorName) && prescription.getPrescriptionPractitionerID().contains(doctorID)).collect(Collectors.toSet());
        Set<Visit> doctorVisits = foundVisits.stream().filter(visit
                -> visit.getHostPractitioner().contains(doctorName) && visit.getHostPractitionerID().contains(doctorID)).collect(Collectors.toSet());
        int prescriptionsCount = doctorPrescriptions.size();
        int visitsCount = doctorVisits.size();
        doctorMV.addObject(foundDoctor);
        doctorMV.addObject("doctorPrescriptions", doctorPrescriptions);
        doctorMV.addObject("doctorVisits", doctorVisits);
        doctorMV.addObject("prescriptionsCount", prescriptionsCount);
        doctorMV.addObject("visitsCount", visitsCount);

        return doctorMV;
    }

    @GetMapping("/edit/{Id}")
    public String initDoctorEditorForm(@PathVariable Long Id, Model doctorModel){
        doctorModel.addAttribute(doctorService.findById(Id));
        return DOCTORS_EDITOR_VIEW;
    }

    @PostMapping("/edit/{Id}")
    public String submitDoctorEditorForm(@Valid Doctor doctor, BindingResult result, @PathVariable Long Id,
                                         @RequestParam("doctorImgFile") MultipartFile file) throws IOException{
        if(result.hasErrors()){
            return DOCTORS_EDITOR_VIEW;
        } else {
            doctor.setId(Id);
            doctor.setImage(doctor.getImage());
            Doctor imgDoctor = saveDoctorImageFile(Id, file);
            imgDoctor.setFirstName(doctor.getFirstName());
            imgDoctor.setLastName(doctor.getLastName());
            imgDoctor.setPractitionerID(doctor.getPractitionerID());
            imgDoctor.setContactInfo(doctor.getContactInfo());
            imgDoctor.setEmailInfo(doctor.getEmailInfo());
            imgDoctor.setSpecialities(doctor.getSpecialities());
            Doctor stagedDoctor = doctorService.save(imgDoctor);
            // stagedDoctor.setImage(imgDoctor.getImage());
            return "redirect:/doctors/info/" + stagedDoctor.getId();
        }
    }


    @GetMapping("/delete/{Id}")
    public String deleteDoctorRecordById(@PathVariable String Id){
        doctorService.deleteById(Long.valueOf(Id));
        return "redirect:/doctors";
    }



    @PostMapping("/{id}/img")
    public String handleImagePost(@PathVariable String id, @RequestParam("doctorImgFile") MultipartFile file){

        saveDoctorImageFile(Long.valueOf(id), file);

        return "redirect:/doctors/info/" + id;
    }

    @GetMapping("/{id}/getimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        Doctor focusDoctor = doctorService.findById(Long.valueOf(id));
        if (focusDoctor.getImage() != null) {
            byte[] byteArray = new byte[focusDoctor.getImage().length];
            int i = 0;

            for (Byte wrappedByte : focusDoctor.getImage()){
                byteArray[i++] = wrappedByte; //auto unboxing
            }
            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
            System.out.println(focusDoctor.getFirstName() + ' ' + focusDoctor.getLastName() +  " profile image found");

        }
        else {
            System.out.println(focusDoctor.getFirstName() + ' ' + focusDoctor.getLastName() +  " profile image is null");
        }
    }


    @Transactional
    public Doctor saveDoctorImageFile(Long doctorId, MultipartFile file) {
        try {
            Doctor focusDoctor = doctorService.findById(doctorId);
            System.out.println(focusDoctor.getFirstName() + ' ' + focusDoctor.getLastName() + " profile image Save initialized...");
            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            focusDoctor.setImage(byteObjects);
            // Doctor stagedDoctor = doctorService.save(focusDoctor);
            log.debug("Successfully uploaded a Multipart File for: " + focusDoctor.getDelimitedFullName());
            System.out.println("Successfully uploaded  Multipart File for: " + focusDoctor.getDelimitedFullName());
            return focusDoctor;


        }catch (IOException e) {
            log.error("Error occurred", e);
            e.printStackTrace();
        }

        return null;
    }



}
