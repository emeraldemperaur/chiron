package iot.empiaurhouse.chiron.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {

    @Column(name = "visit_date")
    private LocalDate visitDate;
    @Column(name = "visit_time")
    private java.lang.String visitTime;
    @Column(name = "visit_description")
    private java.lang.String visitDescription;
    @ManyToOne
    @JoinColumn(name = "visitingdatient_id")
    private Patient visitingPatient;
    @ManyToOne
    @JoinColumn(name = "visitdiagnosis_id")
    private Diagnosis visitDiagnosis;
    @Column(name = "hostpractitioner")
    private String hostPractitioner;


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
}
