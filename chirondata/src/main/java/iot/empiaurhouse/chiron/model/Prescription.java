package iot.empiaurhouse.chiron.model;

import java.time.LocalDate;

public class Prescription extends Pharmaceuticals {

    private Diagnosis diagnosis;
    private String prescriptionName;
    private String prescriptionDosageRegimen;
    private String prescribedDuration;
    private String prescribedBy;
    private LocalDate prescriptionDate;
    private Patient patient;



    public String getPrescriptionName() {
        return prescriptionName;
    }

    public void setPrescriptionName(String prescriptionName) {
        this.prescriptionName = prescriptionName;
    }

    public String getPrescriptionDosageRegimen() {
        return prescriptionDosageRegimen;
    }

    public void setPrescriptionDosageRegimen(String prescriptionDosageRegimen) {
        this.prescriptionDosageRegimen = prescriptionDosageRegimen;
    }

    public String getPrescribedDuration() {
        return prescribedDuration;
    }

    public void setPrescribedDuration(String prescribedDuration) {
        this.prescribedDuration = prescribedDuration;
    }

    public String getPrescribedBy() {
        return prescribedBy;
    }

    public void setPrescribedBy(String prescribedBy) {
        this.prescribedBy = prescribedBy;
    }

    public LocalDate getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(LocalDate prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }


    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
