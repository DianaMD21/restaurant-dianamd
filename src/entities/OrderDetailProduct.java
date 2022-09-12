package entities;

import enums.MeasureUnitEnum;

public class OrderDetailProduct extends BaseEntity<Long>{
    private FinalProduct finalProduct;
    private String name;
    private Double price;
    private MeasureUnitEnum measureUnit;

    public OrderDetailProduct() {
    }

    public FinalProduct getFinalProduct() {
        return finalProduct;
    }

    public void setFinalProduct(FinalProduct finalProduct) {
        this.finalProduct = finalProduct;
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

    public MeasureUnitEnum getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(MeasureUnitEnum measureUnitEnum) {
        this.measureUnit = measureUnitEnum;
    }
}
