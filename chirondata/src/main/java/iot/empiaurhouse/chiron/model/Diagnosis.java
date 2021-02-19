package iot.empiaurhouse.chiron.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "diagnoses")
public class Diagnosis extends BaseEntity {


    public Diagnosis(Long id, Patient patient, DiagnosisLevel diagnosisLevel,
                     String diagnosisSynopsis, LocalDate visitDate, Set<Visit> visits) {
        super(id);
        this.patient = patient;
        this.diagnosisLevel = diagnosisLevel;
        this.diagnosisSynopsis = diagnosisSynopsis;
        this.visitDate = visitDate;

        if(visits == null || visits.size() > 0){
            this.visits = visits;
        }
    }


    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonIgnore
    private Patient patient;

    @Column(name = "patient_full_name")
    private String patientFullName;
    @ManyToOne
    @JoinColumn(name = "level_id")
    private DiagnosisLevel diagnosisLevel;

    @Column(name = "diagnosis_details", length = 500000)
    private String diagnosisDetails;

    @Column(name = "diagnosis_synopsis")
    private String diagnosisSynopsis;

    @Column(name = "visit_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate visitDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diagnosis")
    private Set<Prescription> prescriptions = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "visitDiagnosis")
    private Set<Visit> visits = new HashSet<>();

    public Patient getPatient() {
        return patient;
    }


    public String getPatientFullName() {
        return patientFullName;
    }


    public void setPatient(Patient patient) {
        this.patient = patient;
        this.patientFullName = patient.getFullName();
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
                " diagnosisLevel=" + diagnosisLevel +
                ", diagnosisDetails='" + diagnosisDetails + '\'' +
                ", diagnosisSynopsis='" + diagnosisSynopsis + '\'' +
                ", visitDate=" + visitDate +
                '}';
    }
}
