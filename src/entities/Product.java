package entities;

import enums.MeasureUnit;
import java.util.ArrayList;
import java.util.List;

public class Product extends BaseEntity<Long>{
    private String name;
    private Double price;
    private MeasureUnit measureUnit;
    private List<FinalProductProduct> finalProductProducts;
    private List<Tax> taxes;

    public Product() {
        this.taxes=new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public MeasureUnit getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(MeasureUnit measureUnit) {
        this.measureUnit = measureUnit;
    }

    public List<FinalProductProduct> getFinalProductProducts() {
        return finalProductProducts;
    }

    public void setFinalProductProducts(List<FinalProductProduct> finalProductProducts) {
        this.finalProductProducts = finalProductProducts;
    }

    public List<Tax> getTaxes() {
        return taxes;
    }

    public void setTaxes(List<Tax> taxes) {
        this.taxes = taxes;
    }
}
