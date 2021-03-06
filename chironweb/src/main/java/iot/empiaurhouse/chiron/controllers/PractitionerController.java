package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.model.*;
import iot.empiaurhouse.chiron.services.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping("/practitioners")
@Controller
public class PractitionerController {

    private final PractitionerService practitionerService;
    private final DoctorService doctorService;
    private final NPService npService;
    private final RNService rnService;
    private final PrescriptionService prescriptionService;
    private final VisitService visitService;
    public static final String PRACTITIONERS_EDITOR_VIEW = "practitioners/practitionerseditor";


    public PractitionerController(PractitionerService practitionerService, DoctorService doctorService, NPService npService, RNService rnService, PrescriptionService prescriptionService, VisitService visitService) {
        this.practitionerService = practitionerService;
        this.doctorService = doctorService;
        this.npService = npService;
        this.rnService = rnService;
        this.prescriptionService = prescriptionService;
        this.visitService = visitService;
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
        Practitioner foundPractitioner = practitionerService.findById(practitionerId);
        String practitionerID = foundPractitioner.getPractitionerID();
        String practitionerName = foundPractitioner.getFullName();
        Set<Prescription> foundPrescriptions = prescriptionService.findAll();
        Set<Visit> foundVisits = visitService.findAll();
        Set<Prescription> practitionerPrescriptions = foundPrescriptions.stream().filter(prescription ->
                prescription.getPrescribedBy().contains(practitionerName) && prescription.getPrescriptionPractitionerID().contains(practitionerID)).collect(Collectors.toSet());
        Set<Visit> practitionerVisits = foundVisits.stream().filter(visit
                -> visit.getHostPractitioner().contains(practitionerName) && visit.getHostPractitionerID().contains(practitionerID)).collect(Collectors.toSet());
        int prescriptionsCount = practitionerPrescriptions.size();
        int visitsCount = practitionerVisits.size();
        practitionerMV.addObject(foundPractitioner);
        practitionerMV.addObject("practitionerPrescriptions", practitionerPrescriptions);
        practitionerMV.addObject("practitionerVisits", practitionerVisits);
        practitionerMV.addObject("prescriptionsCount", prescriptionsCount);
        practitionerMV.addObject("visitsCount", visitsCount);

        return practitionerMV;
    }

    @GetMapping("/edit/{Id}")
    public String initPractitionerEditorForm(@PathVariable Long Id, Model practitionerModel){
        practitionerModel.addAttribute(practitionerService.findById(Id));
        return PRACTITIONERS_EDITOR_VIEW;
    }

    @PostMapping("/edit/{Id}")
    public String submitPractitionerEditorForm(@Valid Practitioner practitioner, BindingResult result, @PathVariable Long Id,
                                               @RequestParam("practitionerImgFile") MultipartFile file) throws IOException{
        if(result.hasErrors()){
            return PRACTITIONERS_EDITOR_VIEW;
        } else {
            practitioner.setId(Id);
            practitioner.setImage(practitioner.getImage());
            Practitioner imgPractitioner = savePractitionerImageFile(Id, file);
            imgPractitioner.setFirstName(practitioner.getFirstName());
            imgPractitioner.setLastName(practitioner.getLastName());
            imgPractitioner.setPractitionerID(practitioner.getPractitionerID());
            imgPractitioner.setContactInfo(practitioner.getContactInfo());
            imgPractitioner.setEmailInfo(practitioner.getEmailInfo());
            Practitioner stagedPractitioner = practitionerService.save(imgPractitioner);
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
    public String deletePatientRecordById(@PathVariable String Id, RedirectAttributes redirectAttributes){
        Practitioner stagedPractitioner = practitionerService.findById(Long.valueOf(Id));
        redirectAttributes.addFlashAttribute("stagedPractitioner", stagedPractitioner.getFullName());
        practitionerService.deleteById(Long.valueOf(Id));
        return "redirect:/practitioners";
    }


    @PostMapping("/{id}/img")
    public String handleImagePost(@PathVariable String id, @RequestParam("practitionerImgFile") MultipartFile file){

        savePractitionerImageFile(Long.valueOf(id), file);

        return "redirect:/practitioners/info/" + id;
    }

    @GetMapping("/{id}/getimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        Practitioner focusPractitioner = practitionerService.findById(Long.valueOf(id));
        if (focusPractitioner.getImage() != null) {
            byte[] byteArray = new byte[focusPractitioner.getImage().length];
            int i = 0;

            for (Byte wrappedByte : focusPractitioner.getImage()){
                byteArray[i++] = wrappedByte; //auto unboxing
            }
            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
            System.out.println(focusPractitioner.getFirstName() + ' ' + focusPractitioner.getLastName() +  " profile image found");

        }
        else {
            System.out.println(focusPractitioner.getFirstName() + ' ' + focusPractitioner.getLastName() +  " profile image is null");
        }
    }


    @Transactional
    public Practitioner savePractitionerImageFile(Long practitionerId, MultipartFile file) {
        try {
            Practitioner focusPractitioner = practitionerService.findById(practitionerId);
            System.out.println(focusPractitioner.getFirstName() + ' ' + focusPractitioner.getLastName() + " profile image Save initialized...");
            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            focusPractitioner.setImage(byteObjects);
            // Practitioner stagedPractitioner = practitionerService.save(focusPractitioner);
            log.debug("Successfully uploaded a Multipart File for: " + focusPractitioner.getDelimitedFullName());
            System.out.println("Successfully uploaded  Multipart File for: " + focusPractitioner.getDelimitedFullName());
            return focusPractitioner;


        }catch (IOException e) {
            log.error("Error occurred", e);
            e.printStackTrace();
        }

        return null;
    }


}
