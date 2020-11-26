package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.model.RegisteredNurse;
import iot.empiaurhouse.chiron.services.RNService;
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


@Slf4j
@RequestMapping("/registerednurses")
@Controller
public class RNController {

    private final RNService rnService;
    public static final String REGISTERED_NURSE_EDITOR_VIEW = "registerednurses/registerednurseseditor";


    public RNController(RNService rnService) {
        this.rnService = rnService;
    }

    @RequestMapping({"", "/", "/index","/index.html"})
    public String listRNPractitioners(Model rnModel){
        Set<RegisteredNurse> allRegisteredNurses= rnService.findAll();
        int registeredNurseCount = allRegisteredNurses.size();
        rnModel.addAttribute("registerednurses", allRegisteredNurses);
        rnModel.addAttribute("registerednurse", new RegisteredNurse());
        rnModel.addAttribute("registeredNurseCount", registeredNurseCount);

        return "registerednurses/index";
    }

    @PostMapping("/create")
    public String addNewRegisteredNurseRecord(@Valid RegisteredNurse registeredNurse, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return REGISTERED_NURSE_EDITOR_VIEW;
        }else {
            RegisteredNurse stagedRegisteredNurse = rnService.save(registeredNurse);
            return "redirect:/registerednurses/info/" + stagedRegisteredNurse.getId();
        }
    }

    @GetMapping({"/inform","/info/{registerednurseId}"})
    public ModelAndView renderRegisteredNurseInfo(@PathVariable("registerednurseId") Long registerednurseId){
        ModelAndView registerednurseMV = new ModelAndView("registerednurses/registerednursesinformation");
        registerednurseMV.addObject(rnService.findById(registerednurseId));

        return registerednurseMV;
    }

    @GetMapping("/edit/{Id}")
    public String initRegisteredNurseEditorForm(@PathVariable Long Id, Model registerednurseModel){
        registerednurseModel.addAttribute(rnService.findById(Id));
        return REGISTERED_NURSE_EDITOR_VIEW;
    }

    @PostMapping("/edit/{Id}")
    public String submitRegisteredNurseEditorForm(@Valid RegisteredNurse registeredNurse, BindingResult result, @PathVariable Long Id,
                                                  @RequestParam("registeredNurseImgFile") MultipartFile file) throws IOException{
        if(result.hasErrors()){
            return REGISTERED_NURSE_EDITOR_VIEW;
        } else {
            registeredNurse.setId(Id);
            registeredNurse.setImage(registeredNurse.getImage());
            RegisteredNurse imgRegisteredNurse = saveRegisteredNurseImageFile(Id, file);
            imgRegisteredNurse.setFirstName(registeredNurse.getFirstName());
            imgRegisteredNurse.setLastName(registeredNurse.getLastName());
            imgRegisteredNurse.setPractitionerID(registeredNurse.getPractitionerID());
            imgRegisteredNurse.setContactInfo(registeredNurse.getContactInfo());
            imgRegisteredNurse.setEmailInfo(registeredNurse.getEmailInfo());
            RegisteredNurse stagedRegisteredNurse = rnService.save(imgRegisteredNurse);
            return "redirect:/registerednurses/info/" + stagedRegisteredNurse.getId();
        }
    }


    @GetMapping("/delete/{Id}")
    public String deleteRegisteredNurseRecordById(@PathVariable String Id){
        rnService.deleteById(Long.valueOf(Id));
        return "redirect:/registerednurses";
    }


    @PostMapping("/{id}/img")
    public String handleImagePost(@PathVariable String id, @RequestParam("registerednurseImgFile") MultipartFile file){

        saveRegisteredNurseImageFile(Long.valueOf(id), file);

        return "redirect:/nursepractitioners/info/" + id;
    }

    @GetMapping("/{id}/getimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        RegisteredNurse focusRegisteredNurse = rnService.findById(Long.valueOf(id));
        if (focusRegisteredNurse.getImage() != null) {
            byte[] byteArray = new byte[focusRegisteredNurse.getImage().length];
            int i = 0;

            for (Byte wrappedByte : focusRegisteredNurse.getImage()){
                byteArray[i++] = wrappedByte; //auto unboxing
            }
            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
            System.out.println(focusRegisteredNurse.getFirstName() + ' ' + focusRegisteredNurse.getLastName() +  " profile image found");

        }
        else {
            System.out.println(focusRegisteredNurse.getFirstName() + ' ' + focusRegisteredNurse.getLastName() +  " profile image is null");
        }
    }


    @Transactional
    public RegisteredNurse saveRegisteredNurseImageFile(Long registeredNurseId, MultipartFile file) {
        try {
            RegisteredNurse focusRegisteredNurse = rnService.findById(registeredNurseId);
            System.out.println(focusRegisteredNurse.getFirstName() + ' ' + focusRegisteredNurse.getLastName() + " profile image Save initialized...");
            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            focusRegisteredNurse.setImage(byteObjects);
            // RegisteredNurse stagedRegisteredNurse = rnService.save(focusRegisteredNurse);
            log.debug("Successfully uploaded a Multipart File for: " + focusRegisteredNurse.getDelimitedFullName());
            System.out.println("Successfully uploaded  Multipart File for: " + focusRegisteredNurse.getDelimitedFullName());
            return focusRegisteredNurse;

        }catch (IOException e) {
            log.error("Error occurred", e);
            e.printStackTrace();
        }
        return null;
    }




}
