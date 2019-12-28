package iot.empiaurhouse.chiron.bootstrap;

import iot.empiaurhouse.chiron.model.*;
import iot.empiaurhouse.chiron.services.*;
import iot.empiaurhouse.chiron.services.map.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Component
public class ChironDataInitializer implements CommandLineRunner {

    private final DoctorService doctorService;
    private final PatientService patientService;
    private final NPService npService;
    private final RNService rnService;
    private final PractitionerService practitionerService;
    private final PharmaceuticalsService pharmaceuticalsService;



    public ChironDataInitializer() {
        doctorService = new DoctorServiceMap();
        patientService = new PatientServiceMap();
        npService = new NPServiceMap();
        rnService = new RNServiceMap();
        practitionerService = new PractitionerServiceMap();
        pharmaceuticalsService = new PharmaceuticalsServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {

        Doctor chironDoctorA = new Doctor();
        chironDoctorA.setId(1L);
        chironDoctorA.setFirstName("Phil");
        chironDoctorA.setLastName("McGraw");
        chironDoctorA.setPractitionerID("PHILM9087");
        chironDoctorA.setContactInfo("+1(565) 555-9087");
        doctorService.save(chironDoctorA);

        Doctor chironDoctorB = new Doctor();
        chironDoctorB.setId(2L);
        chironDoctorB.setFirstName("Andre");
        chironDoctorB.setLastName("Young");
        chironDoctorB.setPractitionerID("ANDRE9967");
        chironDoctorB.setContactInfo("+1(544) 877-9967");
        doctorService.save(chironDoctorB);

        System.out.println("Loaded Doctors bootstrap data...");


        Practitioner chironPractitionerA = new Practitioner();
        chironPractitionerA.setId(1L);
        chironPractitionerA.setFirstName("Eli");
        chironPractitionerA.setLastName("Remington");
        chironPractitionerA.setPractitionerID("ELIREM8765");
        chironPractitionerA.setContactInfo("+1(523) 777-8765");
        practitionerService.save(chironPractitionerA);


        Practitioner chironPractitionerB = new Practitioner();
        chironPractitionerB.setId(2L);
        chironPractitionerB.setFirstName("Peter");
        chironPractitionerB.setLastName("Thelonious");
        chironPractitionerB.setPractitionerID("PETER15432");
        chironPractitionerB.setContactInfo("+1(523) 111-5432");
        practitionerService.save(chironPractitionerB);


        System.out.println("Loaded Practitioners bootstrap data...");


        NursePractitioner nursePractitionerA = new NursePractitioner();
        nursePractitionerA.setFirstName("Jenny");
        nursePractitionerA.setLastName("Smith");
        nursePractitionerA.setPractitionerID("JS2213247");
        nursePractitionerA.setContactInfo("+1(403) 221-3247");
        nursePractitionerA.setId(1L);
        npService.save(nursePractitionerA);

        NursePractitioner nursePractitionerB = new NursePractitioner();
        nursePractitionerB.setFirstName("Penny");
        nursePractitionerB.setLastName("Robinson");
        nursePractitionerB.setPractitionerID("PRLIS112");
        nursePractitionerB.setContactInfo("+1(403) 221-3112");
        nursePractitionerB.setId(2L);
        npService.save(nursePractitionerB);


        System.out.println("Loaded Nurse Practitioners bootstrap data...");


        RegisteredNurse registeredNurseA = new RegisteredNurse();
        registeredNurseA.setFirstName("Lucy");
        registeredNurseA.setLastName("Dee");
        registeredNurseA.setPractitionerID("LD2216787");
        registeredNurseA.setContactInfo("+1(403) 221-6787");
        registeredNurseA.setId(1L);
        rnService.save(registeredNurseA);

        RegisteredNurse registeredNurseB = new RegisteredNurse();
        registeredNurseB.setFirstName("Penny");
        registeredNurseB.setLastName("Robinson");
        registeredNurseB.setPractitionerID("PRLIS112");
        registeredNurseB.setContactInfo("+1(403) 221-3112");
        registeredNurseB.setId(2L);
        rnService.save(registeredNurseB);

        System.out.println("Loaded Registered Nurses bootstrap data...");



        Patient chironPatientA = new Patient();
        chironPatientA.setId(1L);
        chironPatientA.setFirstName("John");
        chironPatientA.setLastName("Doe");
        chironPatientA.setInsuranceVendor("Blue Cross");
        chironPatientA.setInsuranceVendorID("BC765111809");


        Patient chironPatientB = new Patient();
        chironPatientB.setId(2L);
        chironPatientB.setFirstName("John");
        chironPatientB.setLastName("Smith");
        chironPatientB.setInsuranceVendor("Red Cross");
        chironPatientB.setInsuranceVendorID("RC911214509");


        Diagnosis holderDiagnosisA = new Diagnosis();
        holderDiagnosisA.setDiagnosisDetails("Common Flu");
        holderDiagnosisA.setPatient(chironPatientA);
        holderDiagnosisA.setVisitDate(LocalDate.now());
        holderDiagnosisA.setId(1L);


        Diagnosis holderDiagnosisB = new Diagnosis();
        holderDiagnosisB.setDiagnosisDetails("Malaria");
        holderDiagnosisB.setPatient(chironPatientA);
        holderDiagnosisB.setVisitDate(LocalDate.now());
        holderDiagnosisB.setId(2L);

        Prescription holderPrescriptionA = new Prescription();
        holderPrescriptionA.setId(1L);
        holderPrescriptionA.setBrandName("Penicillin");
        holderPrescriptionA.setBatchNumber("546PNC");
        holderPrescriptionA.setPrescribedBy("Dr. Phil");
        holderPrescriptionA.setDiagnosis(holderDiagnosisA);
        holderPrescriptionA.setPrescribedDuration("30");
        holderPrescriptionA.setPatient(chironPatientA);
        holderPrescriptionA.setPrescriptionDate(LocalDate.now());

        Prescription holderPrescriptionB = new Prescription();
        holderPrescriptionB.setId(2L);
        holderPrescriptionB.setBrandName("Tylenol");
        holderPrescriptionB.setBatchNumber("546TYL");
        holderPrescriptionB.setPrescribedBy("Dr. Dre");
        holderPrescriptionB.setDiagnosis(holderDiagnosisB);
        holderPrescriptionB.setPrescribedDuration("30");
        holderPrescriptionB.setPatient(chironPatientA);
        holderPrescriptionB.setPrescriptionDate(LocalDate.now());




        Set<Diagnosis> diagnosisSet = new HashSet<Diagnosis>();
        diagnosisSet.add(holderDiagnosisA);
        diagnosisSet.add(holderDiagnosisB);

        Set<Prescription> prescriptionSet = new HashSet<Prescription>();
        prescriptionSet.add(holderPrescriptionA);
        prescriptionSet.add(holderPrescriptionB);

        chironPatientA.setDiagnoses(diagnosisSet);
        chironPatientB.setDiagnoses(diagnosisSet);
        holderDiagnosisA.setPrescriptions(prescriptionSet);
        holderDiagnosisB.setPrescriptions(prescriptionSet);

        patientService.save(chironPatientA);
        patientService.save(chironPatientB);

        System.out.println("Loaded Patients bootstrap data...");


        Pharmaceuticals holderPharmaceuticalsA = new Pharmaceuticals();
        holderPharmaceuticalsA.setId(1L);
        holderPharmaceuticalsA.setBrandName("Weed");
        holderPharmaceuticalsA.setChemicalName("THC");
        holderPharmaceuticalsA.setApprovalNumber("CBC-111-556");
        holderPharmaceuticalsA.setExpiryDate(LocalDate.MAX);
        holderPharmaceuticalsA.setManufacturerName("BC Delivers");
        holderPharmaceuticalsA.setManufactureDate(LocalDate.MIN);
        pharmaceuticalsService.save(holderPharmaceuticalsA);

        Pharmaceuticals holderPharmaceuticalsB = new Pharmaceuticals();
        holderPharmaceuticalsB.setId(2L);
        holderPharmaceuticalsB.setBrandName("Mushrooms");
        holderPharmaceuticalsB.setGenericName("Magic Mushrooms");
        holderPharmaceuticalsB.setChemicalName("Psilocybin Mushroom");
        holderPharmaceuticalsB.setApprovalNumber("MMM-111-556");
        holderPharmaceuticalsB.setExpiryDate(LocalDate.MAX);
        holderPharmaceuticalsB.setManufacturerName("BC Delivers");
        holderPharmaceuticalsB.setManufactureDate(LocalDate.MIN);
        pharmaceuticalsService.save(holderPharmaceuticalsB);


        Pharmaceuticals holderPharmaceuticalsC = new Pharmaceuticals();
        holderPharmaceuticalsC.setId(3L);
        holderPharmaceuticalsC.setBrandName("Tylenol");
        holderPharmaceuticalsC.setGenericName("Acetaminophen");
        holderPharmaceuticalsC.setChemicalName("Acetaminophen");
        holderPharmaceuticalsC.setApprovalNumber("ACE-232-556");
        holderPharmaceuticalsC.setExpiryDate(LocalDate.MAX);
        holderPharmaceuticalsC.setManufacturerName("BC Delivers");
        holderPharmaceuticalsC.setManufactureDate(LocalDate.MIN);
        pharmaceuticalsService.save(holderPharmaceuticalsC);

        System.out.println("Loaded Pharmaceuticals bootstrap data...");



    }
}
