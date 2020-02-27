package iot.empiaurhouse.chiron.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "diagnoses")
public class Diagnosis extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "level_id")
    private DiagnosisLevel diagnosisLevel;

    @Column(name = "diagnosis_details")
    private String diagnosisDetails;

    @Column(name = "diagnosis_synopsis")
    private String diagnosisSynopsis;

    @Column(name = "visit_date")
    private LocalDate visitDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diagnosis")
    private Set<Prescription> prescriptions = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "visitDiagnosis")
    private Set<Visit> visits = new HashSet<>();

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

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }

    public String getDiagnosisSynopsis() {
        return diagnosisSynopsis;
    }

    public void setDiagnosisSynopsis(String diagnosisSynopsis) {
        this.diagnosisSynopsis = diagnosisSynopsis;
    }

    @Override
    public String toString() {
        return "Diagnosis{" +
                "patient=" + patient +
                ", diagnosisLevel=" + diagnosisLevel +
                ", diagnosisDetails='" + diagnosisDetails + '\'' +
                ", diagnosisSynopsis='" + diagnosisSynopsis + '\'' +
                ", visitDate=" + visitDate +
                ", prescriptions=" + prescriptions +
                ", visits=" + visits +
                '}';
    }
}
