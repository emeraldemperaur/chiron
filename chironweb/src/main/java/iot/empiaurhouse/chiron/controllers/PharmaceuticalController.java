package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.model.Pharmaceuticals;
import iot.empiaurhouse.chiron.model.Prescription;
import iot.empiaurhouse.chiron.services.DiagnosisService;
import iot.empiaurhouse.chiron.services.PharmaceuticalsService;
import iot.empiaurhouse.chiron.services.PrescriptionService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
@RequestMapping("/pharmaceuticals")
@Controller
public class PharmaceuticalController {

    private final PharmaceuticalsService pharmaceuticalsService;
    private final DiagnosisService diagnosisService;
    private final PrescriptionService prescriptionService;
    public static final String Rx_EDITOR_VIEW = "pharmaceuticals/rxeditor";


    public PharmaceuticalController(PharmaceuticalsService pharmaceuticalsService, DiagnosisService diagnosisService, PrescriptionService prescriptionService) {
        this.pharmaceuticalsService = pharmaceuticalsService;
        this.diagnosisService = diagnosisService;
        this.prescriptionService = prescriptionService;
    }

    @RequestMapping({"","/", "/index","/index.html"})
    public String listPharmaceuticals(Model pharmaceuticalsModel){

        Set<Pharmaceuticals> allRx = pharmaceuticalsService.findAll();
        int n = allRx.size();
        List<Integer> RxCounter = new ArrayList<Integer>(n);
        for (Pharmaceuticals r : allRx){
            Integer stockCount = r.getInStock();
            RxCounter.add(stockCount);
        }
        int sumCount = RxCounter.stream().mapToInt(Integer::intValue).sum();
        pharmaceuticalsModel.addAttribute("pharmaceuticals", allRx);
        pharmaceuticalsModel.addAttribute("pharmaceutical", new Pharmaceuticals());
        pharmaceuticalsModel.addAttribute("totalStock", sumCount);

        return "pharmaceuticals/index";
    }

    @PostMapping("/create")
    public String addNewPharmaceuticalsRecord(@Valid Pharmaceuticals pharmaceutical, BindingResult bindingResult){
         if(bindingResult.hasErrors()){
             return Rx_EDITOR_VIEW;
         }else {
            Pharmaceuticals stagedRx = pharmaceuticalsService.save(pharmaceutical);
            return "redirect:/pharmaceuticals/info/" + stagedRx.getId();
         }
    }

    @GetMapping({"/inform","/info/{RxId}"})
    public ModelAndView renderPharmaceuticalsInfo(@PathVariable("RxId") Long RxId){
        ModelAndView pharmaceuticalMV = new ModelAndView("pharmaceuticals/rxinformation");
        Pharmaceuticals focusPharmaceutical = pharmaceuticalsService.findById(RxId);
        String pharmaceuticalBrandName = focusPharmaceutical.getBrandName();
        String pharmaceuticalGenericName = focusPharmaceutical.getGenericName();
        Set<Prescription> foundPrescriptions = prescriptionService.findAll();
        Set<Prescription> relatedPrescriptions = foundPrescriptions.stream().filter(prescription ->
                (prescription.getPrescriptionName().contains(pharmaceuticalBrandName) && prescription.getPrescriptionName().contains(pharmaceuticalGenericName)) ||
                        (prescription.getPrescriptionName().contains(focusPharmaceutical.getManufacturerName())) || prescription.getPrescriptionName().contains(focusPharmaceutical.getGenericName())).collect(Collectors.toSet());
        int prescriptionsCount = relatedPrescriptions.size();
        pharmaceuticalMV.addObject(focusPharmaceutical);
        pharmaceuticalMV.addObject("relatedPrescriptions", relatedPrescriptions);
        pharmaceuticalMV.addObject("prescriptionsCount", prescriptionsCount);


        return pharmaceuticalMV;
    }

    @GetMapping("/edit/{Id}")
    public String initPharmaceuticalEditorForm(@PathVariable Long Id, Model pharmaceuticalModel){
        pharmaceuticalModel.addAttribute(pharmaceuticalsService.findById(Id));
        return Rx_EDITOR_VIEW;
    }

    @PostMapping("/edit/{Id}")
    public String submitPharmaceuticalEditorForm(@Valid Pharmaceuticals pharmaceutical, BindingResult result, @PathVariable Long Id,
                                                 @RequestParam("pharmaceuticalImgFile") MultipartFile file){
        if(result.hasErrors()){
            return Rx_EDITOR_VIEW;
        } else {
            pharmaceutical.setId(Id);
            pharmaceutical.setImage(pharmaceutical.getImage());
            Pharmaceuticals imgPharmaceuticals = saveNursePractitionerImageFile(Id, file);
            imgPharmaceuticals.setBrandName(pharmaceutical.getBrandName());
            imgPharmaceuticals.setGenericName(pharmaceutical.getGenericName());
            imgPharmaceuticals.setChemicalName(pharmaceutical.getChemicalName());
            imgPharmaceuticals.setManufacturerName(pharmaceutical.getManufacturerName());
            imgPharmaceuticals.setManufactureDate(pharmaceutical.getManufactureDate());
            imgPharmaceuticals.setInStock(pharmaceutical.getInStock());
            imgPharmaceuticals.setExpiryDate(pharmaceutical.getExpiryDate());
            imgPharmaceuticals.setBatchNumber(pharmaceutical.getBatchNumber());
            imgPharmaceuticals.setApprovalNumber(pharmaceutical.getApprovalNumber());
            Pharmaceuticals stagedRx = pharmaceuticalsService.save(imgPharmaceuticals);
            return "redirect:/pharmaceuticals/info/" + stagedRx.getId();
        }
    }


    @GetMapping("/delete/{Id}")
    public String deletePharmaceuticalsRecordById(@PathVariable String Id){
        pharmaceuticalsService.deleteById(Long.valueOf(Id));
        return "redirect:/pharmaceuticals";
    }

    @PostMapping("/{id}/img")
    public String handleImagePost(@PathVariable String id, @RequestParam("pharmaceuticalImgFile") MultipartFile file){

        saveNursePractitionerImageFile(Long.valueOf(id), file);

        return "redirect:/nursepractitioners/info/" + id;
    }

    @GetMapping("/{id}/getimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        Pharmaceuticals focusPharmaceuticals = pharmaceuticalsService.findById(Long.valueOf(id));
        if (focusPharmaceuticals.getImage() != null) {
            byte[] byteArray = new byte[focusPharmaceuticals.getImage().length];
            int i = 0;

            for (Byte wrappedByte : focusPharmaceuticals.getImage()){
                byteArray[i++] = wrappedByte; //auto unboxing
            }
            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
            System.out.println(focusPharmaceuticals.getBrandName() +  " profile image found");

        }
        else {
            System.out.println(focusPharmaceuticals.getBrandName() +  " profile image is null");
        }
    }


    @Transactional
    public Pharmaceuticals saveNursePractitionerImageFile(Long pharmaceuticalId, MultipartFile file) {
        try {
            Pharmaceuticals focusPharmaceutical = pharmaceuticalsService.findById(pharmaceuticalId);
            System.out.println(focusPharmaceutical.getBrandName() + " profile image Save initialized...");
            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            focusPharmaceutical.setImage(byteObjects);
            // Pharmaceuticals stagedNursePractitioner = pharmaceuticalsService.save(focusNursePractitioner);
            log.debug("Successfully uploaded a Multipart File for: " + focusPharmaceutical.getBrandName());
            System.out.println("Successfully uploaded  Multipart File for: " + focusPharmaceutical.getBrandName());
            return focusPharmaceutical;

        }catch (IOException e) {
            log.error("Error occurred", e);
            e.printStackTrace();
        }
        return null;
    }



}
