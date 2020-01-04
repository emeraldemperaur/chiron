package iot.empiaurhouse.chiron.model;

import java.time.LocalDate;

public class Visit<P> extends BaseEntity {

    private LocalDate visitDate;
    private String visitTime;
    private String visitDescription;
    private Patient visitingPatient;
    private P hostPractitioner;


    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }

    public String getVisitDescription() {
        return visitDescription;
    }

    public void setVisitDescription(String visitDescription) {
        this.visitDescription = visitDescription;
    }

    public Patient getVisitingPatient() {
        return visitingPatient;
    }

    public void setVisitingPatient(Patient visitingPatient) {
        this.visitingPatient = visitingPatient;
    }

    public P getHostPractitioner() {
        return hostPractitioner;
    }

    public void setHostPractitioner(P hostPractitioner) {
        this.hostPractitioner = hostPractitioner;
    }
}
