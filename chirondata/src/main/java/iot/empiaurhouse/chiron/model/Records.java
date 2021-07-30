package iot.empiaurhouse.chiron.model;

public class Records {

    private String recordName;
    private String recordType;
    private Integer recordCount;
    private Integer recordID;


    public Records() {
    }


    public void setRecordID(Integer recordID){
        this.recordID = recordID;
    }

    public void setRecordCount(Integer recordCount){
        this.recordCount = recordCount;
    }

    public void setRecordType(String recordType){
        this.recordType = recordType;
    }

    public void setRecordName(String recordName){
        this.recordName = recordName;
    }

    public String getRecordName(){
        return recordName;
    }

    public String getRecordType(){
        return recordType;
    }

    public Integer getRecordCount(){
        return recordCount;
    }

    public Integer getRecordID(){
        return recordID;
    }

}
