package iot.empiaurhouse.chiron.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prescriptions")
public class Prescription extends PharmaceuticalsBaseJPA {

    @ManyToOne
    @JoinColumn(name = "diagnosis_id")
    private Diagnosis diagnosis;
    @Column(name = "prescription_name")
    private String prescriptionName;
    @Column(name = "prescription_dosage_regimen")
    private String prescriptionDosageRegimen;
    @Column(name = "prescribed_dosage_amount")
    private String prescribedDosageAmount;
    @Column(name = "prescribed_dosage_type")
    private String prescribedDosageType;
    @Column(name = "prescribed_duration")
    private String prescribedDuration;
    @Column(name = "prescribed_by")
    private String prescribedBy;
    @Column(name = "prescription_practitioner_id")
    private String prescriptionPractitionerID;
    @Column(name = "prescription_date")
    private LocalDate prescriptionDate;

    @ManyToOne
    @JoinColumn(name = "patient_id")
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

    public String getPrescribedDosageAmount() {
        return prescribedDosageAmount;
    }

    public void setPrescribedDosageAmount(String prescribedDosageAmount) {
        this.prescribedDosageAmount = prescribedDosageAmount;
    }

    public String getPrescribedDosageType() {
        return prescribedDosageType;
    }

    public void setPrescribedDosageType(String prescribedDosageType) {
        this.prescribedDosageType = prescribedDosageType;
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

    public String getPrescriptionPractitionerID() {
        return prescriptionPractitionerID;
    }

    public void setPrescriptionPractitionerID(String prescriptionPractitionerID) {
        this.prescriptionPractitionerID = prescriptionPractitionerID;
    }

    @Override
    public String toString() {
        return prescriptionName;
    }
}
