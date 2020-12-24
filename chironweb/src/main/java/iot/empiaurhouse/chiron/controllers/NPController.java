package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.model.NursePractitioner;
import iot.empiaurhouse.chiron.model.Prescription;
import iot.empiaurhouse.chiron.model.Visit;
import iot.empiaurhouse.chiron.services.NPService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping("/nursepractitioners")
@Controller
public class NPController {

    private final NPService npService;
    private final PrescriptionService prescriptionService;
    private final VisitService visitService;
    public static final String NURSE_PRACTITIONER_EDITOR_VIEW = "nursepractitioners/nursepractitionerseditor";


    public NPController(NPService npService, PrescriptionService prescriptionService, VisitService visitService) {
        this.npService = npService;
        this.prescriptionService = prescriptionService;
        this.visitService = visitService;
    }

    @RequestMapping({"", "/", "/index","/index.html"})
    public String listNursePractitioners(Model npModel){
        Set<NursePractitioner> allNursePractitioners = npService.findAll();
        int nursePractitionerCount = allNursePractitioners.size();
        npModel.addAttribute("nursepractitioners", allNursePractitioners);
        npModel.addAttribute("nursepractitioner", new NursePractitioner());
        npModel.addAttribute("nursePractitionerCount", nursePractitionerCount);

        return "nursepractitioners/index";
    }

    @PostMapping("/create")
    public String addNewNursePractitionerRecord(@Valid NursePractitioner nursePractitioner, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return NURSE_PRACTITIONER_EDITOR_VIEW;
        }else {
            NursePractitioner stagedNursePractitioner = npService.save(nursePractitioner);
            return "redirect:/nursepractitioners/info/" + stagedNursePractitioner.getId();
        }
    }

    @GetMapping({"/inform","/info/{nursepractitionerId}"})
    public ModelAndView renderNursePractitionerInfo(@PathVariable("nursepractitionerId") Long nursepractitionerId){
        ModelAndView nursepractitionerMV = new ModelAndView("nursepractitioners/nursepractitionersinformation");
        NursePractitioner foundNursePractitioner = npService.findById(nursepractitionerId);
        String nursePractitionerID = foundNursePractitioner.getPractitionerID();
        String nursePractitionerName = foundNursePractitioner.getFullName();
        Set<Prescription> foundPrescriptions = prescriptionService.findAll();
        Set<Visit> foundVisits = visitService.findAll();
        Set<Prescription> nursePractitionerPrescriptions = foundPrescriptions.stream().filter(prescription ->
                prescription.getPrescribedBy().contains(nursePractitionerName) && prescription.getPrescriptionPractitionerID().contains(nursePractitionerID)).collect(Collectors.toSet());
        Set<Visit> nursePractitionerVisits = foundVisits.stream().filter(visit
                -> visit.getHostPractitioner().contains(nursePractitionerName) && visit.getHostPractitionerID().contains(nursePractitionerID)).collect(Collectors.toSet());
        int prescriptionsCount = nursePractitionerPrescriptions.size();
        int visitsCount = nursePractitionerVisits.size();
        nursepractitionerMV.addObject(foundNursePractitioner);
        nursepractitionerMV.addObject("nursePractitionerPrescriptions", nursePractitionerPrescriptions);
        nursepractitionerMV.addObject("nursePractitionerVisits", nursePractitionerVisits);
        nursepractitionerMV.addObject("prescriptionsCount", prescriptionsCount);
        nursepractitionerMV.addObject("visitsCount", visitsCount);

        return nursepractitionerMV;
    }

    @GetMapping("/edit/{Id}")
    public String initNursePractitionerEditorForm(@PathVariable Long Id, Model nursepractitionerModel){
        nursepractitionerModel.addAttribute(npService.findById(Id));
        return NURSE_PRACTITIONER_EDITOR_VIEW;
    }

    @PostMapping("/edit/{Id}")
    public String submitNursePractitionerEditorForm(@Valid NursePractitioner nursePractitioner, BindingResult result, @PathVariable Long Id,
                                                    @RequestParam("nursePractitionerImgFile") MultipartFile file) throws IOException{
        if(result.hasErrors()){
            return NURSE_PRACTITIONER_EDITOR_VIEW;
        } else {
            nursePractitioner.setId(Id);
            nursePractitioner.setImage(nursePractitioner.getImage());
            NursePractitioner imgNursePractitioner = saveNursePractitionerImageFile(Id, file);
            imgNursePractitioner.setFirstName(nursePractitioner.getFirstName());
            imgNursePractitioner.setLastName(nursePractitioner.getLastName());
            imgNursePractitioner.setPractitionerID(nursePractitioner.getPractitionerID());
            imgNursePractitioner.setContactInfo(nursePractitioner.getContactInfo());
            imgNursePractitioner.setEmailInfo(nursePractitioner.getEmailInfo());
            NursePractitioner stagedNursePractitioner = npService.save(imgNursePractitioner);
            return "redirect:/nursepractitioners/info/" + stagedNursePractitioner.getId();
        }
    }


    @GetMapping("/delete/{Id}")
    public String deleteNursePractitionerRecordById(@PathVariable String Id, RedirectAttributes redirectAttributes){
        NursePractitioner stagedNursePractitioner = npService.findById(Long.valueOf(Id));
        redirectAttributes.addFlashAttribute("stagedNursePractitioner", stagedNursePractitioner.getFullName());
        npService.deleteById(Long.valueOf(Id));
        return "redirect:/nursepractitioners";
    }



    @PostMapping("/{id}/img")
    public String handleImagePost(@PathVariable String id, @RequestParam("nursepractitionerImgFile") MultipartFile file){

        saveNursePractitionerImageFile(Long.valueOf(id), file);

        return "redirect:/nursepractitioners/info/" + id;
    }

    @GetMapping("/{id}/getimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        NursePractitioner focusNursePractitioner = npService.findById(Long.valueOf(id));
        if (focusNursePractitioner.getImage() != null) {
            byte[] byteArray = new byte[focusNursePractitioner.getImage().length];
            int i = 0;

            for (Byte wrappedByte : focusNursePractitioner.getImage()){
                byteArray[i++] = wrappedByte; //auto unboxing
            }
            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
            System.out.println(focusNursePractitioner.getFirstName() + ' ' + focusNursePractitioner.getLastName() +  " profile image found");

        }
        else {
            System.out.println(focusNursePractitioner.getFirstName() + ' ' + focusNursePractitioner.getLastName() +  " profile image is null");
        }
    }

    @Transactional
    public NursePractitioner saveNursePractitionerImageFile(Long nursePractitionerId, MultipartFile file) {
        try {
            NursePractitioner focusNursePractitioner = npService.findById(nursePractitionerId);
            System.out.println(focusNursePractitioner.getFirstName() + ' ' + focusNursePractitioner.getLastName() + " profile image Save initialized...");
            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            focusNursePractitioner.setImage(byteObjects);
            // NursePractitioner stagedNursePractitioner = npService.save(focusNursePractitioner);
            log.debug("Successfully uploaded a Multipart File for: " + focusNursePractitioner.getDelimitedFullName());
            System.out.println("Successfully uploaded  Multipart File for: " + focusNursePractitioner.getDelimitedFullName());
            return focusNursePractitioner;

        }catch (IOException e) {
            log.error("Error occurred", e);
            e.printStackTrace();
        }
        return null;
    }




}
