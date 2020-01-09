package iot.empiaurhouse.chiron.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "levels")
public class DiagnosisLevel extends BaseEntity {

    @Column(name = "level_name")
    private String diagnosisLevelName;

    public String getDiagnosisLevelName() {
        return diagnosisLevelName;
    }

    public void setDiagnosisLevelName(String diagnosisLevelName) {
        this.diagnosisLevelName = diagnosisLevelName;
    }
}
