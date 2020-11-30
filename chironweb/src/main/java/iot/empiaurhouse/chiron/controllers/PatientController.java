package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.model.*;
import iot.empiaurhouse.chiron.services.DiagnosisService;
import iot.empiaurhouse.chiron.services.PatientService;
import iot.empiaurhouse.chiron.services.VisitService;
import iot.empiaurhouse.chiron.util.RxTypes;
import iot.empiaurhouse.chiron.util.VisitTimer;
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
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@RequestMapping("/patients")
@Controller
public class PatientController {

    private static final String VISIT_EDITOR = "patients/visit/visiteditor";
    private final PatientService patientService;
    private final DiagnosisService diagnosisService;
    private final VisitService visitService;
    public static final String PATIENT_EDITOR_VIEW = "patients/patienteditor";
    public static final String PATIENT_INFO = "patients/patientinformation";
    private Patient imgfocusPatient;

    public PatientController(PatientService patientService, DiagnosisService diagnosisService, VisitService visitService) {
        this.patientService = patientService;
        this.diagnosisService = diagnosisService;
        this.visitService = visitService;
    }

    @GetMapping({"","/", "/index","/index.html"})
    public String listPatients(Model patientModel){
        Set<Patient> allPatients = patientService.findAll();
        int patientCount = allPatients.size();
        patientModel.addAttribute("patients", allPatients);
        patientModel.addAttribute("patient", new Patient());
        patientModel.addAttribute("patientCount", patientCount);

        return "patients/index";
    }

    @GetMapping({"/find"})
    public String findPatients(Model findPatientsModel){
        findPatientsModel.addAttribute("patient", new Patient());
        return "patients/find";
    }


    @GetMapping({"/inform","/info/{patientId}"})
    public ModelAndView renderPatientInfo(@PathVariable("patientId") Long patientId){
        ModelAndView patientMV = new ModelAndView("patients/patientinformation");
        RxTypes rxType = new RxTypes();
        VisitTimer visitTimes = new VisitTimer();
        Visit newVisit = new Visit();
        Prescription newPrescription = new Prescription();
        rxType.initRxTypes();
        List<String> rxTypes = rxType.getRxTypes();
        Patient focusPatient = patientService.findById(patientId);
        Set<Diagnosis> foundDiagnoses = focusPatient.getDiagnoses();
        patientMV.addObject(focusPatient);
        patientMV.addObject("rxTypes", rxTypes);
        patientMV.addObject("foundDiagnoses", foundDiagnoses);
        patientMV.addObject("timeSlots", visitTimes.getTimeSlots());
        patientMV.addObject("newVisit", newVisit);
        patientMV.addObject("newPrescription", newPrescription);
        return patientMV;
    }

    @GetMapping("/info")
    public String testInfoUI(){

        return "patients/patientinformation";
    }

    @GetMapping("/findlastname")
    public String findByLastNameForm(Patient patient, BindingResult result, Model model){
        // permits empty GET request to return all patient records records in DB
        if (patient.getLastName() == null) {
            patient.setLastName(""); // empty string signifies broadest possible search
        }
        // find patient by last name
        List<Patient> results = patientService.findAllByLastNameLike("%"+ patient.getLastName() + "%");
        int resultsCount = results.size();

        if (results.isEmpty()) {
            // if no patient found
            result.rejectValue("lastName", "notFound", "patient not found");
            return "patients/find";
        } else if (results.size() == 1) {
            // if 1 patient found
            patient = results.get(0);
            return "redirect:/patients/info/" + patient.getId();
        } else {
            // multiple patients found
            model.addAttribute("patientLNRecords", results);
            model.addAttribute("resultsCount", resultsCount);
            return "patients/patientsfound";
        }
    }

    @GetMapping("/findfirstname")
    public String findByFirstNameForm(Patient patient, BindingResult result, Model model){
        // permits empty GET request to return all patient records records in DB
        if (patient.getFirstName() == null) {
            patient.setFirstName(""); // empty string signifies broadest possible search
        }
        // find patient by first name
        List<Patient> results = patientService.findAllByFirstNameLike("%"+ patient.getFirstName() + "%");
        int resultsCount = results.size();

        if (results.isEmpty()) {
            // if no patient found
            result.rejectValue("firstName", "notFound", "patient not found");
            return "patients/find";
        } else if (results.size() == 1) {
            // if 1 patient found
            patient = results.get(0);
            return "redirect:/patients/info/" + patient.getId();
        } else {
            // multiple patients found
            model.addAttribute("patientFNRecords", results);
            model.addAttribute("resultsCount", resultsCount);
            return "patients/patientsfound";
        }
    }


    @PostMapping("/create")
    public String addNewPatientRecord(@Valid Patient patient, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return PATIENT_EDITOR_VIEW;
        }else {
            Patient stagedPatient = patientService.save(patient);
            return "redirect:/patients/info/" + stagedPatient.getId();
        }
    }


    @GetMapping("/delete/{Id}")
    public String deletePatientRecordById(@PathVariable String Id){
        patientService.deleteById(Long.valueOf(Id));
        return "redirect:/patients";
    }


    @GetMapping("/edit/{Id}")
    public String initPatientEditorForm(@PathVariable Long Id, Model patientModel){
        patientModel.addAttribute(patientService.findById(Id));
        BloodGroup[] bloodGroups = BloodGroup.values();
        patientModel.addAttribute(bloodGroups);
        return PATIENT_EDITOR_VIEW;
    }

    @PostMapping("/edit/{Id}")
    public String submitPatientEditorForm(@Valid Patient patient, BindingResult result, @PathVariable Long Id,
                                          @RequestParam("patientImgFile") MultipartFile file) throws IOException{
        if(result.hasErrors()){
            return PATIENT_EDITOR_VIEW;
        } else {
            patient.setId(Id);
            patient.setImage(patient.getImage());
            Patient imgPatient = savePatientImageFile(Id, file);
            imgPatient.setFirstName(patient.getFirstName());
            imgPatient.setLastName(patient.getLastName());
            imgPatient.setBirthDate(patient.getBirthDate());
            imgPatient.setInsuranceVendorID(patient.getInsuranceVendorID());
            imgPatient.setInsuranceVendor(patient.getInsuranceVendor());
            imgPatient.setCity(patient.getCity());
            imgPatient.setAddress(patient.getAddress());
            imgPatient.setPhoneNumber(patient.getPhoneNumber());
            imgPatient.setBloodGroup(patient.getBloodGroup());
            imgPatient.setDiagnoses(patient.getDiagnoses());
            imgPatient.setImage(imgfocusPatient.getImage());
            Patient stagedPatient = patientService.save(imgPatient);
            return "redirect:/patients/info/" + stagedPatient.getId();
        }
    }

    @PostMapping("/{id}/img")
    public String handleImagePost(@PathVariable String id, @RequestParam("patientImgFile") MultipartFile file) throws IOException{

        savePatientImageFile(Long.valueOf(id), file);

        return "redirect:/patients/info/" + id;
    }


    @GetMapping("/{id}/getimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        Patient focusPatient = patientService.findById(Long.valueOf(id));
        if (focusPatient.getImage() != null) {
            byte[] byteArray = new byte[focusPatient.getImage().length];
            int i = 0;

            for (Byte wrappedByte : focusPatient.getImage()){
                byteArray[i++] = wrappedByte; //auto unboxing
            }
            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
            System.out.println(focusPatient.getFullName() +  " profile image found");

        }
        else {
            System.out.println(focusPatient.getFullName() +  " profile image is null");
        }
    }

    @Transactional
    public Patient savePatientImageFile(Long patientId, MultipartFile file) {
        try {
            imgfocusPatient = patientService.findById(patientId);
            System.out.println(imgfocusPatient.getFullName() + " profile image Save initialized...");
            Byte[] byteObjects = new Byte[file.getBytes().length];
            int i = 0;

            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            imgfocusPatient.setImage(byteObjects);
            // Patient stagedPatient = patientService.save(focusPatient);
            log.debug("Successfully uploaded a Multipart File for: " + imgfocusPatient.getFullName());
            System.out.println("Successfully uploaded  Multipart File for: " + imgfocusPatient.getFullName());
            return imgfocusPatient;

        }catch (IOException e) {
            log.error("Error occurred", e);
            e.printStackTrace();
        }

        return imgfocusPatient;
    }



    @GetMapping("/diagnosis/{diagnosisId}/visit/{visitId}/delete")
    public String deleteVisit(@PathVariable String visitId, @PathVariable String diagnosisId){
        Diagnosis focusDiagnosis = diagnosisService.findById(Long.valueOf(diagnosisId));
        Long focusPatientId = focusDiagnosis.getPatient().getId();
        System.out.println("dId: " + diagnosisId);
        System.out.println("vId: " + visitId);
        Optional<Visit> foundVisitsO = focusDiagnosis.getVisits().stream().filter(visit1 -> visit1.getId().equals(Long.valueOf(visitId))).findFirst();
        if (foundVisitsO.isPresent()){
            Set<Visit> foundVisits = focusDiagnosis.getVisits();
            Optional <Visit> focusVisit = foundVisits.stream().filter(visit -> visit.getId().equals(Long.valueOf(visitId))).findFirst();
            focusVisit.ifPresent(foundVisits::remove);
            System.out.println("Successfully deleted Visit for " + focusVisit.get().getVisitingPatient().getFullName()  + " w/ Diagnosis ID: " + diagnosisId);

        }
        // visitService.deleteById(Long.valueOf(visitId));

        return "redirect:/patients/info/" + focusPatientId + "#diagnoseswrapper";
    }




    @PostMapping("/{patientId}/diagnosis/{diagnosisId}/visit/new")
    public String postVisitLogEntry(@Valid Diagnosis diagnosisVisit, @Valid Visit newVisit, BindingResult bindingResult, @PathVariable String diagnosisId,
                                    @PathVariable String patientId){
        if(bindingResult.hasErrors()){
            return PATIENT_EDITOR_VIEW;
        }else {
            // Diagnosis stagedDiagnosis = diagnosisService.findById(Long.valueOf(diagnosisId));
            // diagnosisVisit.getVisits().add(newVisit);
            diagnosisVisit.getVisits().add(newVisit);
            newVisit.setVisitDiagnosis(diagnosisVisit);
            newVisit.setVisitingPatient(diagnosisVisit.getPatient());
            visitService.save(newVisit);
            // visitService.save(visit);
            // System.out.println("Successfully saved New Visit Log for " + visit.getVisitingPatient()  + "w/ Diagnosis ID: " + diagnosisId);
            return "redirect:/patients/info/" + patientId + "#diagnoseswrapper";
        }
    }




}
