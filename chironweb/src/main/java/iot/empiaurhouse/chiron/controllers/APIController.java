package iot.empiaurhouse.chiron.controllers;


import com.google.gson.Gson;
import iot.empiaurhouse.chiron.model.*;
import iot.empiaurhouse.chiron.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequestMapping("/api")
@Controller
public class APIController {
    private final PatientService patientService;
    private final PractitionerService practitionerService;
    private final DoctorService doctorService;
    private final NPService npService;
    private final RNService rnService;
    private final DiagnosisService diagnosisService;
    private final PrescriptionService prescriptionService;
    private final VisitService visitService;
    private final PharmaceuticalsService pharmaceuticalsService;
    //private final Gson gson = new Gson();


    public APIController(PatientService patientService, PractitionerService practitionerService, DoctorService doctorService,
                         NPService npService, RNService rnService, DiagnosisService diagnosisService,
                         PrescriptionService prescriptionService, VisitService visitService,
                         PharmaceuticalsService pharmaceuticalsService) {
        this.patientService = patientService;
        this.practitionerService = practitionerService;
        this.doctorService = doctorService;
        this.npService = npService;
        this.rnService = rnService;
        this.diagnosisService = diagnosisService;
        this.prescriptionService = prescriptionService;
        this.visitService = visitService;
        this.pharmaceuticalsService = pharmaceuticalsService;
    }

    @GetMapping("/whatsup")
    public @ResponseBody String pingServer(){
        List<PingObject> objectResponse = new ArrayList<>();
        objectResponse.add(new PingObject());

        Gson gsonObject = new Gson();
        return gsonObject.toJson(objectResponse);
    }

    @GetMapping("/patients")
    public @ResponseBody List<Patient> listPatients(){
        Set<Patient> patientSet = patientService.findAll();
        return new ArrayList<>(patientSet);
    }

    @GetMapping("/patients/l/{lastName}")
    public @ResponseBody List<Patient> listPatientByLastName(@PathVariable("lastName") String lastName){

        return patientService.findAllByLastNameLike(lastName);
    }

    @GetMapping("/patients/f/{firstName}")
    public @ResponseBody List<Patient> listPatientByFirstName(@PathVariable("firstName") String firstName){
        return patientService.findAllByFirstNameLike(firstName);
    }

    @GetMapping("/patients/insurancevendor/{insuranceVendor}")
    public @ResponseBody List<Patient> listPatientByInsuranceVendor(@PathVariable("insuranceVendor") String insuranceVendor){
        return patientService.findAllByInsuranceVendorLike(insuranceVendor);
    }

    @GetMapping("/patients/insurancevendorid/{insuranceVendorID}")
    public @ResponseBody List<Patient> listPatientByInsuranceVendorID(@PathVariable("insuranceVendorID") String insuranceVendorID){
        return patientService.findAllByInsuranceVendorIDLike(insuranceVendorID);
    }

    @GetMapping("/patients/birthdate/on/{birthDate}")
    public @ResponseBody List<Patient> listPatientByBirthDate(@PathVariable("birthDate") String birthDate){
        return patientService.findAllByBirthDate(birthDate);
    }

    @GetMapping("/patients/birthdate/before/{birthDate}")
    public @ResponseBody List<Patient> listPatientByBirthDateBefore(@PathVariable("birthDate") String birthDate){
        return patientService.findAllByBirthDateBefore(birthDate);
    }

    @GetMapping("/patients/birthdate/after/{birthDate}")
    public @ResponseBody List<Patient> listPatientByBirthDateAfter(@PathVariable("birthDate") String birthDate){
        return patientService.findAllByBirthDateAfter(birthDate);
    }

    @GetMapping("/patients/birthdate/between/{birthDate}/{birthDate2}")
    public @ResponseBody List<Patient> listPatientByBirthDateBetween(@PathVariable("birthDate") String birthDate,
                                                                     @PathVariable("birthDate2") String birthDate2){
        return patientService.findAllByBirthDateBetween(birthDate, birthDate2);
    }

    @GetMapping("/practitioners")
    public @ResponseBody List<Practitioner> listPractitioners(){
        return new ArrayList<>(practitionerService.findAll());
    }

    @GetMapping("/practitioners/l/{lastName}")
    public @ResponseBody List<Practitioner> listPractitionerByLastName(@PathVariable("lastName") String lastName){
        return practitionerService.findAllByLastNameLike(lastName);
    }

    @GetMapping("/practitioners/f/{firstName}")
    public @ResponseBody List<Practitioner> listPractitionerByFirstName(@PathVariable("firstName") String firstName){
        return practitionerService.findAllByFirstNameLike(firstName);
    }

    @GetMapping("/practitioners/id/{practitionerId}")
    public @ResponseBody List<Practitioner> listPractitionerByPID(@PathVariable("practitionerId") String practitionerId){
        return practitionerService.findAllByPractitionerIDLike(practitionerId);
    }

    @GetMapping("/doctors")
    public @ResponseBody List<Doctor> listDoctors(){
        return new ArrayList<>(doctorService.findAll());
    }

    @GetMapping("/doctors/l/{lastName}")
    public @ResponseBody List<Doctor> listDoctorByLastName(@PathVariable("lastName") String lastName){
        return doctorService.findAllByLastNameLike(lastName);
    }

    @GetMapping("/doctors/f/{firstName}")
    public @ResponseBody List<Doctor> listDoctorByFirstName(@PathVariable("firstName") String firstName){
        return doctorService.findAllByFirstNameLike(firstName);
    }

    @GetMapping("/doctors/id/{practitionerId}")
    public @ResponseBody List<Doctor> listDoctorByPID(@PathVariable("practitionerId") String practitionerId){
        return doctorService.findAllByPractitionerIDLike(practitionerId);
    }

    @GetMapping("/nursepractitioners")
    public @ResponseBody List<NursePractitioner> listNursePractitioners(){
        return new ArrayList<NursePractitioner>(npService.findAll());
    }

    @GetMapping("/nursepractitioners/l/{lastName}")
    public @ResponseBody List<NursePractitioner> listNursePractitionerByLastName(@PathVariable("lastName") String lastName){
        return npService.findAllByLastNameLike(lastName);
    }

    @GetMapping("/nursepractitioners/f/{firstName}")
    public @ResponseBody List<NursePractitioner> listNursePractitionerByFirstName(@PathVariable("firstName") String firstName){
        return npService.findAllByFirstNameLike(firstName);
    }

    @GetMapping("/nursepractitioners/id/{practitionerId}")
    public @ResponseBody List<NursePractitioner> listNursePractitionerByPID(@PathVariable("practitionerId") String practitionerId){
        return npService.findAllByPractitionerIDLike(practitionerId);
    }

    @GetMapping("/registerednurses")
    public @ResponseBody List<RegisteredNurse> listRegisteredNurses(){
        return new ArrayList<>(rnService.findAll());
    }

    @GetMapping("/registerednurses/l/{lastName}")
    public @ResponseBody List<RegisteredNurse> listRegisteredNurseByLastName(@PathVariable("lastName") String lastName){
        return rnService.findAllByLastNameLike(lastName);
    }

    @GetMapping("/registerednurses/f/{firstName}")
    public @ResponseBody List<RegisteredNurse> listRegisteredNurseByFirstName(@PathVariable("firstName") String firstName){
        return rnService.findAllByFirstNameLike(firstName);
    }

    @GetMapping("/registerednurses/id/{practitionerId}")
    public @ResponseBody List<RegisteredNurse> listRegisteredNurseByPID(@PathVariable("practitionerId") String practitionerId){
        return rnService.findAllByPractitionerIDLike(practitionerId);
    }

    @GetMapping("/pharmaceuticals")
    public @ResponseBody List<Pharmaceuticals> listPharmaceuticals(){
        return new ArrayList<>(pharmaceuticalsService.findAll());
    }

    @GetMapping("/pharmaceuticals/brand/{brandName}")
    public @ResponseBody List<Pharmaceuticals> listBrandNamePharmaceuticals(@PathVariable("brandName") String brandName){
        return pharmaceuticalsService.findAllByBrandNameLike(brandName);
    }

    @GetMapping("/pharmaceuticals/generic/{genericName}")
    public @ResponseBody List<Pharmaceuticals> listGenericNamePharmaceuticals(@PathVariable("genericName") String genericName){
        return pharmaceuticalsService.findAllByGenericNameLike(genericName);
    }

    @GetMapping("/pharmaceuticals/chemical/{chemicalName}")
    public @ResponseBody List<Pharmaceuticals> listChemicalNamePharmaceuticals(@PathVariable("chemicalName") String chemicalName){
        return pharmaceuticalsService.findAllByChemicalNameLike(chemicalName);
    }

    @GetMapping("/pharmaceuticals/manufacturer/{manufacturerName}")
    public @ResponseBody List<Pharmaceuticals> listManufacturerNamePharmaceuticals(@PathVariable("manufacturerName") String manufacturerName){
        return pharmaceuticalsService.findAllByManufacturerNameLike(manufacturerName);
    }

    @GetMapping("/pharmaceuticals/manufacturedate/{manufactureDate}")
    public @ResponseBody List<Pharmaceuticals> listManufactureDatePharmaceuticals(@PathVariable("manufactureDate") String manufactureDate){

        return pharmaceuticalsService.findAllByManufactureDateLike(manufactureDate);
    }

    @GetMapping("/pharmaceuticals/manufacturedbefore/{manufactureDate}")
    public @ResponseBody List<Pharmaceuticals> listManufactureDateBeforePharmaceuticals(@PathVariable("manufactureDate") String manufactureDate){
        return pharmaceuticalsService.findAllByManufactureDateBefore(manufactureDate);
    }

    @GetMapping("/pharmaceuticals/manufacturedafter/{manufactureDate}")
    public @ResponseBody List<Pharmaceuticals> listManufactureDateAfterPharmaceuticals(@PathVariable("manufactureDate") String manufactureDate){
        return pharmaceuticalsService.findAllByManufactureDateAfter(manufactureDate);
    }

    @GetMapping("/pharmaceuticals/manufacturedbetween/{manufactureDate}/{manufactureDate2}")
    public @ResponseBody List<Pharmaceuticals> listManufactureDateBetweenPharmaceuticals(@PathVariable("manufactureDate") String manufactureDate,
                                                                                         @PathVariable("manufactureDate2") String manufactureDate2){
        return pharmaceuticalsService.findAllByManufactureDateBetween(manufactureDate, manufactureDate2);
    }

    @GetMapping("/pharmaceuticals/expirydate/{expiryDate}")
    public @ResponseBody List<Pharmaceuticals> listExpiryDatePharmaceuticals(@PathVariable("expiryDate") String expiryDate){
        return pharmaceuticalsService.findAllByExpiryDateLike(expiryDate);
    }

    @GetMapping("/pharmaceuticals/expiredbefore/{expiryDate}")
    public @ResponseBody List<Pharmaceuticals> listExpiryDateBeforePharmaceuticals(@PathVariable("expiryDate") String expiryDate){
        return pharmaceuticalsService.findAllByExpiryDateBefore(expiryDate);
    }

    @GetMapping("/pharmaceuticals/expiredafter/{expiryDate}")
    public @ResponseBody List<Pharmaceuticals> listExpiryDateAfterPharmaceuticals(@PathVariable("expiryDate") String expiryDate){
        return pharmaceuticalsService.findAllByExpiryDateAfter(expiryDate);
    }

    @GetMapping("/pharmaceuticals/expiredbetween/{expiryDate}/{expiryDate2}")
    public @ResponseBody List<Pharmaceuticals> listExpiredBetweenPharmaceuticals(@PathVariable("expiryDate") String expiryDate,
                                                                                 @PathVariable("expiryDate2") String expiryDate2){
        return pharmaceuticalsService.findAllByExpiryDateBetween(expiryDate, expiryDate2);
    }

    @GetMapping("/diagnoses")
    public @ResponseBody List<Diagnosis> listDiagnoses(){
        Set<Diagnosis> diagnosesSet = diagnosisService.findAll();
        return new ArrayList<>(diagnosesSet);
    }

    @GetMapping("/diagnoses/synopsis/{diagnosisSynopsis}")
    public @ResponseBody List<Diagnosis> listDiagnoses(@PathVariable("diagnosisSynopsis") String diagnosisSynopsis){
        return diagnosisService.findAllByDiagnosisSynopsisLike(diagnosisSynopsis);
    }

    @GetMapping("/diagnoses/on/{visitDate}")
    public @ResponseBody List<Diagnosis> listDiagnosesOnVisitDate(@PathVariable("visitDate") String visitDate){
        return diagnosisService.findAllByVisitDateLike(visitDate);
    }

    @GetMapping("/diagnoses/before/{visitDate}")
    public @ResponseBody List<Diagnosis> listDiagnosesBeforeVisitDate(@PathVariable("visitDate") String visitDate){
        return diagnosisService.findAllByVisitDateBefore(visitDate);
    }

    @GetMapping("/diagnoses/after/{visitDate}")
    public @ResponseBody List<Diagnosis> listDiagnosesAfterVisitDate(@PathVariable("visitDate") String visitDate){
        return diagnosisService.findAllByVisitDateAfter(visitDate);
    }

    @GetMapping("/diagnoses/between/{visitDate}/{visitDate2}")
    public @ResponseBody List<Diagnosis> listDiagnosesBetweenVisitDate(@PathVariable("visitDate") String visitDate, @PathVariable("visitDate2") String visitDate2){
        return diagnosisService.findAllByVisitDateBetween(visitDate, visitDate2);
    }

    @GetMapping("/diagnoses/insurancevendorid/{insuranceVendorID}")
    public @ResponseBody List<Diagnosis> listDiagnosesByInsuranceVendorID(@PathVariable("insuranceVendorID") String insuranceVendorID){
        List<Patient> focusPatients = patientService.findAllByInsuranceVendorID(insuranceVendorID);
        boolean focusPatientNotFound = focusPatients.isEmpty();
        if (!focusPatientNotFound){

            return diagnosisService.findAllByPatientLike(focusPatients.get(0));
        }
        else {

            return new ArrayList<>();
        }
    }

    @GetMapping("/diagnoses/diagnosislevel/{diagnosisLevelName}")
    public @ResponseBody List<Diagnosis> listDiagnosesByDiagnosisLevelName(@PathVariable("diagnosisLevelName") String diagnosisLevelName){
        return diagnosisService.findAllByDiagnosisLevelLike(diagnosisLevelName);
    }

    @GetMapping("/prescriptions")
    public @ResponseBody List<Prescription> listPrescriptions(){
        return new ArrayList<>(prescriptionService.findAll());
    }

    @GetMapping("/prescriptions/name/{prescriptionName}")
    public @ResponseBody List<Prescription> listPrescriptionsByName(@PathVariable("prescriptionName") String prescriptionName){
        return prescriptionService.findAllByPrescriptionNameLike(prescriptionName);
    }

    @GetMapping("/prescriptions/prescriber/{prescribedBy}")
    public @ResponseBody List<Prescription> listPrescriptionsByPrescriber(@PathVariable("prescribedBy") String prescribedBy){
        return prescriptionService.findAllByPrescribedByLike(prescribedBy);
    }

    @GetMapping("/prescriptions/prescriberid/{prescriptionPractitionerID}")
    public @ResponseBody List<Prescription> listPrescriptionsByPrescriberID(@PathVariable("prescriptionPractitionerID") String prescriptionPractitionerID){
        return prescriptionService.findAllByPrescriptionPractitionerIDLike(prescriptionPractitionerID);
    }

    @GetMapping("/prescriptions/on/{prescriptionDate}")
    public @ResponseBody List<Prescription> listPrescriptionsOnPrescriptionDate(@PathVariable("prescriptionDate") String prescriptionDate){
        return prescriptionService.findAllByPrescriptionDateLike(prescriptionDate);
    }

    @GetMapping("/prescriptions/before/{prescriptionDate}")
    public @ResponseBody List<Prescription> listPrescriptionsBeforePrescriptionDate(@PathVariable("prescriptionDate") String prescriptionDate){
        return prescriptionService.findAllByPrescriptionDateBefore(prescriptionDate);
    }

    @GetMapping("/prescriptions/after/{prescriptionDate}")
    public @ResponseBody List<Prescription> listPrescriptionsAfterPrescriptionDate(@PathVariable("prescriptionDate") String prescriptionDate){
        return prescriptionService.findAllByPrescriptionDateAfter(prescriptionDate);
    }

    @GetMapping("/prescriptions/between/{prescriptionDate}/{prescriptionDate2}")
    public @ResponseBody List<Prescription> listPrescriptionsBetweenPrescriptionDate(@PathVariable("prescriptionDate") String prescriptionDate, @PathVariable("prescriptionDate2") String prescriptionDate2){
        return prescriptionService.findAllByPrescriptionDateBetween(prescriptionDate, prescriptionDate2);
    }

    @GetMapping("/prescriptions/insurancevendorid/{insuranceVendorID}")
    public @ResponseBody List<Prescription> listPrescriptionsByInsuranceVendorID(@PathVariable("insuranceVendorID") String insuranceVendorID){
        List<Patient> focusPatients = patientService.findAllByInsuranceVendorID(insuranceVendorID);
        boolean focusPatientNotFound = focusPatients.isEmpty();
        if (!focusPatientNotFound){
            return prescriptionService.findAllByPatientLike(focusPatients.get(0));
        }
        else {
            return new ArrayList<>();
        }
    }

    @GetMapping("/visits")
    public @ResponseBody List<Visit> listVisits(){
        return new ArrayList<>(visitService.findAll());
    }

    @GetMapping("/visits/host/{hostPractitioner}")
    public @ResponseBody List<Visit> listVisitsByHostPractitioner(@PathVariable("hostPractitioner") String hostPractitioner){
        return visitService.findAllByHostPractitionerLike(hostPractitioner);
    }

    @GetMapping("/visits/hostid/{hostPractitionerID}")
    public @ResponseBody List<Visit> listVisitsByHostPractitionerID(@PathVariable("hostPractitionerID") String hostPractitionerID){
        return visitService.findAllByHostPractitionerIDLike(hostPractitionerID);
    }

    @GetMapping("/visits/time/{visitTime}")
    public @ResponseBody List<Visit> listVisitsByTime(@PathVariable("visitTime") String visitTime){
        Gson gsonObject = new Gson();
        return visitService.findAllByVisitTimeLike(visitTime);
    }

    @GetMapping("/visits/description/{visitDescription}")
    public @ResponseBody List<Visit> listVisitsByVisitDescription(@PathVariable("visitDescription") String visitDescription){
        return visitService.findAllByVisitDescriptionContains(visitDescription);
    }

    @GetMapping("/visits/on/{visitDate}")
    public @ResponseBody List<Visit> listVisitsOnVisitDate(@PathVariable("visitDate") String visitDate){
        return visitService.findAllByVisitDateLike(visitDate);
    }

    @GetMapping("/visits/before/{visitDate}")
    public @ResponseBody List<Visit> listVisitsBeforeVisitDate(@PathVariable("visitDate") String visitDate){
        return visitService.findAllByVisitDateBefore(visitDate);
    }

    @GetMapping("/visits/after/{visitDate}")
    public @ResponseBody List<Visit> listVisitsAfterVisitDate(@PathVariable("visitDate") String visitDate){
        return visitService.findAllByVisitDateAfter(visitDate);
    }

    @GetMapping("/visits/between/{visitDate}/{visitDate2}")
    public @ResponseBody List<Visit> listVisitsBetweenVisitDate(@PathVariable("visitDate") String visitDate, @PathVariable("visitDate2") String visitDate2){

        return visitService.findAllByVisitDateBetween(visitDate, visitDate2);
    }

    @GetMapping("/visits/insurancevendorid/{insuranceVendorID}")
    public @ResponseBody List<Visit> listVisitsByInsuranceVendorID(@PathVariable("insuranceVendorID") String insuranceVendorID){
        List<Patient> focusPatients = patientService.findAllByInsuranceVendorID(insuranceVendorID);
        boolean focusPatientNotFound = focusPatients.isEmpty();
        if (!focusPatientNotFound){
            return visitService.findAllByVisitingPatientLike(focusPatients.get(0));
        }
        else {
            return new ArrayList<>();
        }
    }


    @PostMapping("/patient")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient newPatient){
        Patient storedPatient = patientService.save(newPatient);
        System.out.println("Successfully posted patient record: " + newPatient.getFullName());
        return ResponseEntity.created(URI
                .create(String.format("/patient/%s", newPatient.getFirstName()))).body(storedPatient);

    }

    @PostMapping("/deletepatient")
    public ResponseEntity<Patient> deletePatient(@RequestBody Patient stagedPatient){
        Long pUID = stagedPatient.getId();
        patientService.deleteById(pUID);
        System.out.println("Successfully deleted patient record: " + stagedPatient.getFullName());
        return ResponseEntity.created(URI
                .create(String.format("/patient/%s", stagedPatient.getFirstName()))).body(stagedPatient);

    }


    @PostMapping("/diagnosis")
    public ResponseEntity<Diagnosis> addDiagnosis(@RequestBody Diagnosis newDiagnosis){
        Diagnosis storedDiagnosis = diagnosisService.save(newDiagnosis);
        System.out.println("Successfully posted diagnosis record: " + newDiagnosis.getDiagnosisSynopsis());
        return ResponseEntity.created(URI
                .create(String.format("/diagnosis/%s", newDiagnosis.getDiagnosisSynopsis()))).body(storedDiagnosis);
    }

    @PostMapping("/deletediagnosis")
    public ResponseEntity<Diagnosis> deleteDiagnosis(@RequestBody Diagnosis stagedDiagnosis){
        Long pUID = stagedDiagnosis.getId();
        System.out.println("Successfully deleted diagnosis record: " + stagedDiagnosis.getDiagnosisSynopsis());
        diagnosisService.deleteById(pUID);
        return ResponseEntity.created(URI
                .create(String.format("/diagnosis/%s", stagedDiagnosis.getDiagnosisSynopsis()))).body(stagedDiagnosis);
    }

    @PostMapping("/practitioner")
    public ResponseEntity<Practitioner> addPractitioner(@RequestBody Practitioner newPractitioner){
        Practitioner storedPractitioner = practitionerService.save(newPractitioner);
        System.out.println("Successfully posted practitioner record: " + newPractitioner.getFullName());
        return ResponseEntity.created(URI
                .create(String.format("/practitioner/%s", newPractitioner.getFullName()))).body(storedPractitioner);

    }

    @PostMapping("/deletepractitioner")
    public ResponseEntity<Practitioner> deletePractitioner(@RequestBody Practitioner stagedPractitioner){
        Long pUID = stagedPractitioner.getId();
        System.out.println("Successfully deleted practitioner record: " + stagedPractitioner.getFullName());
        practitionerService.deleteById(pUID);
        return ResponseEntity.created(URI
                .create(String.format("/practitioner/%s", stagedPractitioner.getFullName()))).body(stagedPractitioner);
    }

    @PostMapping("/doctor")
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor newDoctor){
        Doctor storedDoctor = doctorService.save(newDoctor);
        System.out.println("Successfully posted doctor record: " + newDoctor.getFullName());
        return ResponseEntity.created(URI
                .create(String.format("/doctor/%s", newDoctor.getFullName()))).body(storedDoctor);
    }

    @PostMapping("/deletedoctor")
    public ResponseEntity<Doctor> deleteDoctor(@RequestBody Doctor stagedDoctor){
        Long pUID = stagedDoctor.getId();
        System.out.println("Successfully deleted doctor record: " + stagedDoctor.getFullName());
        doctorService.deleteById(pUID);
        return ResponseEntity.created(URI
                .create(String.format("/doctor/%s", stagedDoctor.getFullName()))).body(stagedDoctor);
    }

    @PostMapping("/registerednurse")
    public ResponseEntity<RegisteredNurse> addRNurse(@RequestBody RegisteredNurse newRegisteredNurse){
        RegisteredNurse storedRegisteredNurse = rnService.save(newRegisteredNurse);
        System.out.println("Successfully posted registerednurse record: " + newRegisteredNurse.getFullName());
        return ResponseEntity.created(URI
                .create(String.format("/registerednurse/%s", newRegisteredNurse.getFullName()))).body(storedRegisteredNurse);
    }

    @PostMapping("/deleteregisterednurse")
    public ResponseEntity<RegisteredNurse> deleteRNurse(@RequestBody RegisteredNurse stagedRegisteredNurse){
        Long pUID = stagedRegisteredNurse.getId();
        System.out.println("Successfully deleted" + stagedRegisteredNurse.getFullName());
        rnService.deleteById(pUID);
        return ResponseEntity.created(URI
                .create(String.format("/registerednurse/%s", stagedRegisteredNurse.getFullName()))).body(stagedRegisteredNurse);

    }

    @PostMapping("/nursepractitioner")
    public ResponseEntity<NursePractitioner> addNPractitioner(@RequestBody NursePractitioner newNursePractitioner){
        NursePractitioner storedNursePractitioner = npService.save(newNursePractitioner);
        System.out.println("Successfully posted" + newNursePractitioner.getFullName());
        return ResponseEntity.created(URI
                .create(String.format("/nursepractitioner/%s", newNursePractitioner.getFullName()))).body(storedNursePractitioner);

    }

    @PostMapping("/deletenursepractitioner")
    public ResponseEntity<NursePractitioner> deleteNPractitioner(@RequestBody NursePractitioner stagedNursePractitioner){
        Long pUID = stagedNursePractitioner.getId();
        System.out.println("Successfully deleted" + stagedNursePractitioner.getFullName());
        npService.deleteById(pUID);
        return ResponseEntity.created(URI
                .create(String.format("/nursepractitioner/%s", stagedNursePractitioner.getFullName()))).body(stagedNursePractitioner);

    }

    @PostMapping("/pharmaceutical")
    public ResponseEntity<Pharmaceuticals> addPharmaceutical(@RequestBody Pharmaceuticals newPharmaceutical){
        Pharmaceuticals storedPharmaceuticals = pharmaceuticalsService.save(newPharmaceutical);
        System.out.println("Successfully posted" + newPharmaceutical.getBrandName());
        return ResponseEntity.created(URI
                .create(String.format("/pharmaceutical/%s", newPharmaceutical.getBrandName()))).body(storedPharmaceuticals);


    }

    @PostMapping("/deletepharmaceutical")
    public ResponseEntity<Pharmaceuticals> deletePharmaceutical(@RequestBody Pharmaceuticals stagedPharmaceutical){
        Long pUID = stagedPharmaceutical.getId();
        System.out.println("Successfully deleted" + stagedPharmaceutical.getBrandName());
        pharmaceuticalsService.deleteById(pUID);
        return ResponseEntity.created(URI
                .create(String.format("/pharmaceutical/%s", stagedPharmaceutical.getBrandName()))).body(stagedPharmaceutical);

    }



    @GetMapping("/records")
    public @ResponseBody
    String listRecordsInsight(){
        List<Records> dbRecordsList = new ArrayList<>();
        Records patientRecords = new Records();
        patientRecords.setRecordName("Patient");
        patientRecords.setRecordType("Records");
        patientRecords.setRecordCount(patientService.findAll().size());
        patientRecords.setRecordID(1);

        Records diagnosisRecords = new Records();
        diagnosisRecords.setRecordName("Diagnosis");
        diagnosisRecords.setRecordType("Files");
        diagnosisRecords.setRecordCount(diagnosisService.findAll().size());
        diagnosisRecords.setRecordID(2);

        Records prescriptionRecords = new Records();
        prescriptionRecords.setRecordName("Prescription");
        prescriptionRecords.setRecordType("Entries");
        prescriptionRecords.setRecordCount(prescriptionService.findAll().size());
        prescriptionRecords.setRecordID(3);

        Records visitRecords = new Records();
        visitRecords.setRecordName("Visit");
        visitRecords.setRecordType("Logs");
        visitRecords.setRecordCount(visitService.findAll().size());
        visitRecords.setRecordID(4);

        Records practitionerRecords = new Records();
        practitionerRecords.setRecordName("Practitioner");
        practitionerRecords.setRecordType("Records");
        practitionerRecords.setRecordCount(practitionerService.findAll().size());
        practitionerRecords.setRecordID(5);

        Records doctorRecords = new Records();
        doctorRecords.setRecordName("Doctor");
        doctorRecords.setRecordType("Records");
        doctorRecords.setRecordCount(doctorService.findAll().size());
        doctorRecords.setRecordID(6);

        Records rNRecords = new Records();
        rNRecords.setRecordName("Registered Nurse");
        rNRecords.setRecordType("Records");
        rNRecords.setRecordCount(rnService.findAll().size());
        rNRecords.setRecordID(7);

        Records nPRecords = new Records();
        nPRecords.setRecordName("Nurse Practitioner");
        nPRecords.setRecordType("Records");
        nPRecords.setRecordCount(npService.findAll().size());
        nPRecords.setRecordID(8);


        Records pharmaceuticalsRecords = new Records();
        pharmaceuticalsRecords.setRecordName("Pharmaceutical");
        pharmaceuticalsRecords.setRecordType("Records");
        pharmaceuticalsRecords.setRecordCount(pharmaceuticalsService.findAll().size());
        pharmaceuticalsRecords.setRecordID(9);

        dbRecordsList.add(patientRecords);
        dbRecordsList.add(diagnosisRecords);
        dbRecordsList.add(prescriptionRecords);
        dbRecordsList.add(visitRecords);
        dbRecordsList.add(practitionerRecords);
        dbRecordsList.add(doctorRecords);
        dbRecordsList.add(rNRecords);
        dbRecordsList.add(nPRecords);
        dbRecordsList.add(pharmaceuticalsRecords);
        Gson gsonObject = new Gson();
        return gsonObject.toJson(dbRecordsList);
    }



}
