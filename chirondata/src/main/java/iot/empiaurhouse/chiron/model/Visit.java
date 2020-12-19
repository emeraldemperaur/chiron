package iot.empiaurhouse.chiron.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {

    @Column(name = "visit_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate visitDate;
    @Column(name = "visit_time")
    private java.lang.String visitTime;
    @Column(name = "visit_description", length = 500000)
    private java.lang.String visitDescription;
    @ManyToOne
    @JoinColumn(name = "visitingpatient_id")
    private Patient visitingPatient;
    @ManyToOne
    @JoinColumn(name = "visitdiagnosis_id")
    private Diagnosis visitDiagnosis;
    @Column(name = "hostpractitioner")
    private String hostPractitioner;
    @Column(name = "hostpractitioner_id")
    private String hostPractitionerID;


    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public java.lang.String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(java.lang.String visitTime) {
        this.visitTime = visitTime;
    }

    public java.lang.String getVisitDescription() {
        return visitDescription;
    }

    public void setVisitDescription(java.lang.String visitDescription) {
        this.visitDescription = visitDescription;
    }

    public Patient getVisitingPatient() {
        return visitingPatient;
    }

    public void setVisitingPatient(Patient visitingPatient) {
        this.visitingPatient = visitingPatient;
    }

    public String getHostPractitioner() {
        return hostPractitioner;
    }

    public void setHostPractitioner(String hostPractitioner) {
        this.hostPractitioner = hostPractitioner;
    }

    public Diagnosis getVisitDiagnosis() {
        return visitDiagnosis;
    }

    public void setVisitDiagnosis(Diagnosis visitDiagnosis) {
        this.visitDiagnosis = visitDiagnosis;
    }

    public String getHostPractitionerID() {
        return hostPractitionerID;
    }

    public void setHostPractitionerID(String hostPractitionerID) {
        this.hostPractitionerID = hostPractitionerID;
    }
}
