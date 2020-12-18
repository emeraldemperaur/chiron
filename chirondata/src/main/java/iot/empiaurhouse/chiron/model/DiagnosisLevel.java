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
    private String diagnosisLevelNameHexCode;


    public String getDiagnosisLevelName() {
        return diagnosisLevelName;
    }

    public void setDiagnosisLevelName(String diagnosisLevelName) {
        this.diagnosisLevelName = diagnosisLevelName;
    }

    public String getDiagnosisLevelHexCode(){
        switch (diagnosisLevelName){
            case "NORMAL":
                System.out.println("Monday");
                diagnosisLevelNameHexCode = "#0000ff";
                break;
            case "STABLE":
                diagnosisLevelNameHexCode = "#FFFF00";
                break;
            case "CRITICAL":
                diagnosisLevelNameHexCode = "#ff8c00";
                break;
            case "INFECTIOUS":
                diagnosisLevelNameHexCode = "#ff0000";
                break;
            case "CONTAGIOUS":
                diagnosisLevelNameHexCode = "#cc0000";
                break;
            case "TERMINAL":
                diagnosisLevelNameHexCode = "#b30000";
                break;
        }
        return diagnosisLevelNameHexCode;
    }

    @Override
    public String toString() {
        return diagnosisLevelName;
    }
}
