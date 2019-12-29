package iot.empiaurhouse.chiron.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Diagnosis extends BaseEntity {

    private Patient patient;
    private DiagnosisLevel diagnosisLevel;
    private String diagnosisDetails;
    private LocalDate visitDate;
    private Set<Prescription> prescriptions = new HashSet<>();

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public DiagnosisLevel getDiagnosisLevel() {
        return diagnosisLevel;
    }

    public void setDiagnosisLevel(DiagnosisLevel diagnosisLevel) {
        this.diagnosisLevel = diagnosisLevel;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public Set<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(Set<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }


    public String getDiagnosisDetails() {
        return diagnosisDetails;
    }

    public void setDiagnosisDetails(String diagnosisDetails) {
        this.diagnosisDetails = diagnosisDetails;
    }


}
