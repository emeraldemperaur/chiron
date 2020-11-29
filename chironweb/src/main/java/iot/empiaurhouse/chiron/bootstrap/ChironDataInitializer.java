package iot.empiaurhouse.chiron.bootstrap;

import iot.empiaurhouse.chiron.model.*;
import iot.empiaurhouse.chiron.services.*;
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
    private final DiagnosisLevelService diagnosisLevelService;
    private final SpecialityService specialityService;
    private final VisitService visitService;


    public ChironDataInitializer(DoctorService doctorService, PatientService patientService, NPService npService, RNService rnService,
                                 PractitionerService practitionerService, PharmaceuticalsService pharmaceuticalsService, DiagnosisLevelService diagnosisLevelService, SpecialityService specialityService, VisitService visitService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.npService = npService;
        this.rnService = rnService;
        this.practitionerService = practitionerService;
        this.pharmaceuticalsService = pharmaceuticalsService;
        this.diagnosisLevelService = diagnosisLevelService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int dataCount = diagnosisLevelService.findAll().size();
        if (dataCount == 0) {
            loadChironData();
        }

    }

    private void loadChironData() {

        DiagnosisLevel normal = new DiagnosisLevel();
        normal.setDiagnosisLevelName("NORMAL");
        DiagnosisLevel normalDiagnosisLevel = diagnosisLevelService.save(normal);

        DiagnosisLevel stable = new DiagnosisLevel();
        stable.setDiagnosisLevelName("STABLE");
        DiagnosisLevel stableDiagnosisLevel = diagnosisLevelService.save(stable);


        DiagnosisLevel critical = new DiagnosisLevel();
        critical.setDiagnosisLevelName("CRITICAL");
        DiagnosisLevel criticalDiagnosisLevel = diagnosisLevelService.save(critical);


        DiagnosisLevel infectious = new DiagnosisLevel();
        infectious.setDiagnosisLevelName("INFECTIOUS");
        DiagnosisLevel infectiousDiagnosisLevel = diagnosisLevelService.save(infectious);


        DiagnosisLevel contagious = new DiagnosisLevel();
        contagious.setDiagnosisLevelName("CONTAGIOUS");
        DiagnosisLevel contagiousDiagnosisLevel = diagnosisLevelService.save(contagious);


        DiagnosisLevel terminal = new DiagnosisLevel();
        terminal.setDiagnosisLevelName("TERMINAL");
        DiagnosisLevel terminalDiagnosisLevel = diagnosisLevelService.save(terminal);

        System.out.println("Loaded Diagnosis Level(s) bootstrap data...");


        Speciality dentistry = new Speciality();
        dentistry.setSpecialityDescription("Dentistry");
        Speciality dentistrySpeciality = specialityService.save(dentistry);

        Speciality radiology = new Speciality();
        radiology.setSpecialityDescription("Radiology");
        Speciality radiologySpeciality = specialityService.save(radiology);

        Speciality neurology = new Speciality();
        neurology.setSpecialityDescription("Neurology");
        Speciality neurologySpeciality = specialityService.save(neurology);

        Speciality diagnostics = new Speciality();
        diagnostics.setSpecialityDescription("Diagnostics");
        Speciality diagnosticsSpeciality = specialityService.save(diagnostics);

        Speciality immunology = new Speciality();
        immunology.setSpecialityDescription("Immunology");
        Speciality immunologySpeciality = specialityService.save(immunology);

        Speciality surgery = new Speciality();
        surgery.setSpecialityDescription("Surgery");
        Speciality surgerySpeciality = specialityService.save(surgery);

        System.out.println("Loaded Speciality bootstrap data...");


        Doctor chironDoctorA = new Doctor();
        chironDoctorA.setFirstName("Phil");
        chironDoctorA.setLastName("McGraw");
        chironDoctorA.setPractitionerID("PHILM9087");
        chironDoctorA.setContactInfo("+1(565) 555-9087");
        chironDoctorA.getSpecialities().add(immunologySpeciality);
        doctorService.save(chironDoctorA);

        Doctor chironDoctorB = new Doctor();
        chironDoctorB.setFirstName("Andre");
        chironDoctorB.setLastName("Young");
        chironDoctorB.setPractitionerID("ANDRE9967");
        chironDoctorB.setContactInfo("+1(544) 877-9967");
        chironDoctorB.getSpecialities().add(surgerySpeciality);
        doctorService.save(chironDoctorB);

        System.out.println("Loaded Doctors bootstrap data...");


        Practitioner chironPractitionerA = new Practitioner();
        chironPractitionerA.setFirstName("Eli");
        chironPractitionerA.setLastName("Remington");
        chironPractitionerA.setPractitionerID("ELIREM8765");
        chironPractitionerA.setContactInfo("+1(523) 777-8765");
        practitionerService.save(chironPractitionerA);


        Practitioner chironPractitionerB = new Practitioner();
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
        npService.save(nursePractitionerA);

        NursePractitioner nursePractitionerB = new NursePractitioner();
        nursePractitionerB.setFirstName("Penny");
        nursePractitionerB.setLastName("Robinson");
        nursePractitionerB.setPractitionerID("PRLIS112");
        nursePractitionerB.setContactInfo("+1(403) 221-3112");
        npService.save(nursePractitionerB);


        System.out.println("Loaded Nurse Practitioners bootstrap data...");


        RegisteredNurse registeredNurseA = new RegisteredNurse();
        registeredNurseA.setFirstName("Lucy");
        registeredNurseA.setLastName("Dee");
        registeredNurseA.setPractitionerID("LD2216787");
        registeredNurseA.setContactInfo("+1(403) 221-6787");
        rnService.save(registeredNurseA);

        RegisteredNurse registeredNurseB = new RegisteredNurse();
        registeredNurseB.setFirstName("Penny");
        registeredNurseB.setLastName("Robinson");
        registeredNurseB.setPractitionerID("PRLIS112");
        registeredNurseB.setContactInfo("+1(403) 221-3112");
        rnService.save(registeredNurseB);

        System.out.println("Loaded Registered Nurses bootstrap data...");


        Patient chironPatientA = new Patient();
        chironPatientA.setFirstName("John");
        chironPatientA.setLastName("Doe");
        chironPatientA.setInsuranceVendor("Blue Cross");
        chironPatientA.setInsuranceVendorID("BC765111809");
        chironPatientA.setAddress("1 Bunker Drive");
        chironPatientA.setCity("Polis");
        chironPatientA.setBloodGroup("B+");
        chironPatientA.setPhoneNumber("+234 (233) 222-3456");
        chironPatientA.setBirthDate(LocalDate.now());


        Patient chironPatientB = new Patient();
        chironPatientB.setFirstName("John");
        chironPatientB.setLastName("Smith");
        chironPatientB.setInsuranceVendor("Red Cross");
        chironPatientB.setInsuranceVendorID("RC911214509");
        chironPatientB.setAddress("13 Jaha Road");
        chironPatientB.setCity("Arkadia");
        chironPatientB.setBloodGroup("B+");
        chironPatientB.setPhoneNumber("+44 (656) 213-3326");
        chironPatientB.setBirthDate(LocalDate.now());


        Diagnosis holderDiagnosisA = new Diagnosis();
        holderDiagnosisA.setDiagnosisDetails("Common Flu 2020-02-08 23:10:18.482  WARN 8016 --- [l-1 housekeeper] com.zaxxer.hikari.pool.HikariPool  ");
        holderDiagnosisA.setDiagnosisSynopsis("Flu");
        holderDiagnosisA.setPatient(chironPatientA);
        holderDiagnosisA.setVisitDate(LocalDate.now());
        holderDiagnosisA.setDiagnosisLevel(normalDiagnosisLevel);

        Diagnosis mockDiagnosis = new Diagnosis();
        mockDiagnosis.setDiagnosisDetails("Fever 2020-02-08 23:10:18.482  WARN 8016 --- [l-1 housekeeper] com.zaxxer.hikari.pool.HikariPool  ");
        mockDiagnosis.setDiagnosisSynopsis("Fever");
        mockDiagnosis.setPatient(chironPatientA);
        mockDiagnosis.setVisitDate(LocalDate.now().plusDays(1L));
        mockDiagnosis.setDiagnosisLevel(criticalDiagnosisLevel);

        Visit holderVisitA = new Visit();
        holderVisitA.setVisitDiagnosis(holderDiagnosisA);
        holderVisitA.setHostPractitioner("Dr. Dre");
        holderVisitA.setVisitDate(LocalDate.now());
        holderVisitA.setVisitingPatient(chironPatientA);
        holderVisitA.setVisitDescription("Routine Follow Up");
        holderVisitA.setVisitTime("02:00");

        System.out.println("Loaded Diagnosis bootstrap data...");


        Diagnosis holderDiagnosisB = new Diagnosis();
        holderDiagnosisB.setDiagnosisDetails("Malaria 2020-02-08 23:10:18.482  WARN 8016 --- [l-1 housekeeper] com.zaxxer.hikari.pool.HikariPool  ");
        holderDiagnosisB.setDiagnosisSynopsis("Malaria");
        holderDiagnosisB.setPatient(chironPatientA);
        holderDiagnosisB.setVisitDate(LocalDate.now());
        holderDiagnosisB.setDiagnosisLevel(criticalDiagnosisLevel);

        Diagnosis holderDiagnosisC = new Diagnosis();
        holderDiagnosisC.setDiagnosisDetails("Tuberculosis 2020-02-08 23:10:18.482  WARN 8016 --- [l-1 housekeeper] com.zaxxer.hikari.pool.HikariPool  ");
        holderDiagnosisC.setDiagnosisSynopsis("TB");
        holderDiagnosisC.setPatient(chironPatientB);
        holderDiagnosisC.setVisitDate(LocalDate.now());
        holderDiagnosisC.setDiagnosisLevel(contagious);

        Visit holderVisitB = new Visit();
        holderVisitB.setVisitDiagnosis(holderDiagnosisC);
        holderVisitB.setVisitingPatient(chironPatientB);
        holderVisitB.setHostPractitioner("Dr. Riley");
        holderVisitB.setVisitDate(LocalDate.now());
        holderVisitB.setVisitDescription("Emergency Incident\n");
        holderVisitB.setVisitTime("10:30");

        Visit holderVisitC = new Visit();
        holderVisitC.setVisitDiagnosis(mockDiagnosis);
        holderVisitC.setVisitingPatient(chironPatientA);
        holderVisitC.setHostPractitioner("Dr. Seuss");
        holderVisitC.setVisitDate(LocalDate.now().plusDays(1L));
        holderVisitC.setVisitDescription("Fever/Tuberculosis 2020-02-08 23:10:18.482  WARN 8016");
        holderVisitC.setVisitTime("16:00");



        Diagnosis holderDiagnosisD = new Diagnosis();
        holderDiagnosisD.setDiagnosisDetails("Malaria");
        holderDiagnosisD.setDiagnosisSynopsis("Malaria");
        holderDiagnosisD.setPatient(chironPatientB);
        holderDiagnosisD.setVisitDate(LocalDate.now());
        holderDiagnosisD.setDiagnosisLevel(critical);

        System.out.println("Loaded Diagnosis bootstrap data...");


        Prescription holderPrescriptionA = new Prescription();
        holderPrescriptionA.setBrandName("Penicillin");
        holderPrescriptionA.setBatchNumber("546PNC");
        holderPrescriptionA.setPrescribedBy("Dr. Phil");
        holderPrescriptionA.setDiagnosis(holderDiagnosisA);
        holderPrescriptionA.setPrescribedDuration("30 Days");
        holderPrescriptionA.setPatient(chironPatientA);
        holderPrescriptionA.setPrescribedDosageAmount("4");
        holderPrescriptionA.setPrescribedDosageType("tablets");
        holderPrescriptionA.setPrescriptionDosageRegimen("per day");
        holderPrescriptionA.setPrescriptionDate(LocalDate.now());
        holderPrescriptionA.setPrescriptionName(holderPrescriptionA.getBrandName());

        Prescription holderPrescriptionB = new Prescription();
        holderPrescriptionB.setBrandName("Tylenol");
        holderPrescriptionB.setBatchNumber("546TYL");
        holderPrescriptionB.setPrescribedBy("Dr. Dre");
        holderPrescriptionB.setDiagnosis(holderDiagnosisB);
        holderPrescriptionB.setPrescribedDuration("60 Days");
        holderPrescriptionB.setPatient(chironPatientA);
        holderPrescriptionB.setPrescribedDosageAmount("2");
        holderPrescriptionB.setPrescribedDosageType("pills");
        holderPrescriptionB.setPrescriptionDosageRegimen("every 3 hours");
        holderPrescriptionB.setPrescriptionDate(LocalDate.now().plusDays(3L));
        holderPrescriptionB.setPrescriptionName(holderPrescriptionB.getBrandName());

        Prescription holderPrescriptionC = new Prescription();
        holderPrescriptionC.setBrandName("Olbas Oil");
        holderPrescriptionC.setBatchNumber("5O46TYL");
        holderPrescriptionC.setPrescribedBy("Dr. Dre");
        holderPrescriptionC.setDiagnosis(holderDiagnosisA);
        holderPrescriptionC.setPrescribedDuration("90 Days");
        holderPrescriptionC.setPatient(chironPatientA);
        holderPrescriptionC.setPrescribedDosageAmount("2");
        holderPrescriptionC.setPrescribedDosageType("drops");
        holderPrescriptionC.setPrescriptionDosageRegimen("every 6 hours");
        holderPrescriptionC.setPrescriptionDate(LocalDate.now().plusDays(3L));
        holderPrescriptionC.setPrescriptionName(holderPrescriptionC.getBrandName());


        Set<Prescription> prescriptionSet = new HashSet<Prescription>();
        prescriptionSet.add(holderPrescriptionA);
        prescriptionSet.add(holderPrescriptionB);
        prescriptionSet.add(holderPrescriptionC);

        holderDiagnosisA.getPrescriptions().addAll(prescriptionSet);
        holderDiagnosisB.getPrescriptions().addAll(prescriptionSet);


        System.out.println("Loaded Diagnosis-Prescriptions bootstrap data...");


        Set<Diagnosis> diagnosisSet1 = new HashSet<Diagnosis>();
        diagnosisSet1.add(holderDiagnosisA);
        diagnosisSet1.add(holderDiagnosisB);
        diagnosisSet1.add(mockDiagnosis);

        Set<Diagnosis> diagnosisSet2 = new HashSet<Diagnosis>();
        diagnosisSet2.add(holderDiagnosisC);
        diagnosisSet2.add(holderDiagnosisD);


        chironPatientA.getDiagnoses().addAll(diagnosisSet1);
        chironPatientB.getDiagnoses().addAll(diagnosisSet2);


        patientService.save(chironPatientA);
        patientService.save(chironPatientB);
        visitService.save(holderVisitA);
        visitService.save(holderVisitB);
        visitService.save(holderVisitC);

        System.out.println("Loaded Patients bootstrap data...");

        System.out.println("Loaded Visit bootstrap data...");


        Pharmaceuticals holderPharmaceuticalsA = new Pharmaceuticals();
        holderPharmaceuticalsA.setBrandName("Weed");
        holderPharmaceuticalsA.setGenericName("Cannabis");
        holderPharmaceuticalsA.setChemicalName("THC");
        holderPharmaceuticalsA.setApprovalNumber("CBC-111-556");
        holderPharmaceuticalsA.setExpiryDate(LocalDate.now());
        holderPharmaceuticalsA.setInStock(150);
        holderPharmaceuticalsA.setManufacturerName("BC Delivers");
        holderPharmaceuticalsA.setManufactureDate(LocalDate.now());
        pharmaceuticalsService.save(holderPharmaceuticalsA);

        Pharmaceuticals holderPharmaceuticalsB = new Pharmaceuticals();
        holderPharmaceuticalsB.setBrandName("Mushrooms");
        holderPharmaceuticalsB.setGenericName("Magic Mushrooms");
        holderPharmaceuticalsB.setChemicalName("Psilocybin Mushroom");
        holderPharmaceuticalsB.setApprovalNumber("MMM-111-556");
        holderPharmaceuticalsB.setExpiryDate(LocalDate.now());
        holderPharmaceuticalsB.setInStock(1000);
        holderPharmaceuticalsB.setManufacturerName("BC Delivers");
        holderPharmaceuticalsB.setManufactureDate(LocalDate.now());
        pharmaceuticalsService.save(holderPharmaceuticalsB);


        Pharmaceuticals holderPharmaceuticalsC = new Pharmaceuticals();
        holderPharmaceuticalsC.setBrandName("Tylenol");
        holderPharmaceuticalsC.setGenericName("Acetaminophen");
        holderPharmaceuticalsC.setChemicalName("Acetaminophen");
        holderPharmaceuticalsC.setApprovalNumber("ACE-232-556");
        holderPharmaceuticalsC.setExpiryDate(LocalDate.now());
        holderPharmaceuticalsC.setInStock(333);
        holderPharmaceuticalsC.setManufacturerName("BC Delivers");
        holderPharmaceuticalsC.setManufactureDate(LocalDate.now());
        pharmaceuticalsService.save(holderPharmaceuticalsC);

        System.out.println("Loaded Pharmaceuticals bootstrap data...");

    }
}
