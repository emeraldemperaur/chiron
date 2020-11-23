package iot.empiaurhouse.chiron.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "pharmaceuticals")
public class Pharmaceuticals extends PharmaceuticalsBaseJPA {

    @Column(name = "in_stock")
    private Integer inStock = 0;

    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }
}
