package iot.empiaurhouse.chiron.model;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
public class PharmaceuticalsBaseJPA extends BaseEntity {

    @Column(name = "brand_name")
    private String brandName;
    @Column(name = "generic_name")
    private String genericName;
    @Column(name = "chemical_name")
    private String chemicalName;
    @Column(name = "manufacturer_name")
    private String manufacturerName;
    @Column(name = "batch_number")
    private String batchNumber;
    @Column(name = "approval_number")
    private String approvalNumber;
    @Column(name = "manufacture_date")
    private LocalDate manufactureDate;
    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Lob
    @Column(name = "image")
    private Byte[] image;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getChemicalName() {
        return chemicalName;
    }

    public void setChemicalName(String chemicalName) {
        this.chemicalName = chemicalName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(LocalDate manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "PharmaceuticalsBaseJPA{" +
                "brandName='" + brandName + '\'' +
                ", genericName='" + genericName + '\'' +
                ", chemicalName='" + chemicalName + '\'' +
                ", manufacturerName='" + manufacturerName + '\'' +
                ", batchNumber='" + batchNumber + '\'' +
                ", approvalNumber='" + approvalNumber + '\'' +
                ", manufactureDate=" + manufactureDate +
                ", expiryDate=" + expiryDate +
                '}';
    }
}
