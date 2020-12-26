package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.model.*;
import iot.empiaurhouse.chiron.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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


    @GetMapping("/patients")
    public @ResponseBody Set<Patient> listPatients(){
        return patientService.findAll();
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
    public @ResponseBody Set<Practitioner> listPractitioners(){
        return practitionerService.findAll();
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
    public @ResponseBody Set<Doctor> listDoctors(){
        return doctorService.findAll();
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
    public @ResponseBody Set<NursePractitioner> listNursePractitioners(){
        return npService.findAll();
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
    public @ResponseBody Set<RegisteredNurse> listRegisteredNurses(){
        return rnService.findAll();
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
    public @ResponseBody Set<Pharmaceuticals> listPharmaceuticals(){
        return pharmaceuticalsService.findAll();
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
    public @ResponseBody Set<Diagnosis> listDiagnoses(){
        return diagnosisService.findAll();
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


    @GetMapping("/prescriptions")
    public @ResponseBody Set<Prescription> listPrescriptions(){
        return prescriptionService.findAll();
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

    @GetMapping("/visits")
    public @ResponseBody Set<Visit> listVisits(){
        return visitService.findAll();
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


}
