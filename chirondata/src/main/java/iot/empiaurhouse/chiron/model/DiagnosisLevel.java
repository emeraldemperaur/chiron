package iot.empiaurhouse.chiron.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "levels")
public class DiagnosisLevel extends BaseEntity {

    public DiagnosisLevel(Long Id, String diagnosisLevelName) {
        super(Id);
        this.diagnosisLevelName = diagnosisLevelName;
    }

    @Column(name = "level_name")
    private String diagnosisLevelName;


    public String getDiagnosisLevelName() {
        return diagnosisLevelName;
    }

    public void setDiagnosisLevelName(String diagnosisLevelName) {
        this.diagnosisLevelName = diagnosisLevelName;
    }

    @Override
    public String toString() {
        return diagnosisLevelName;
    }
}
