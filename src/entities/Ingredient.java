package entities;

import enums.MeasureUnit;

public class Ingredient extends BaseEntity<Long>{
    private Product product;
    private MeasureUnit measureUnit;
    private Double amount;

    public Ingredient() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public MeasureUnit getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(MeasureUnit measureUnit) {
        this.measureUnit = measureUnit;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
