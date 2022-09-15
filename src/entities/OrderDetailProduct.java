package entities;

import enums.MeasureUnitEnum;

public class OrderDetailProduct extends BaseEntity<Long>{
    private FinalProduct finalProduct;
    private String productName;
    private Double productPrice;
    private MeasureUnitEnum productMeasureUnit;

    public OrderDetailProduct() {
    }

    public FinalProduct getFinalProduct() {
        return finalProduct;
    }

    public void setFinalProduct(FinalProduct finalProduct) {
        this.finalProduct = finalProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public MeasureUnitEnum getProductMeasureUnit() {
        return productMeasureUnit;
    }

    public void setProductMeasureUnit(MeasureUnitEnum productMeasureUnit) {
        this.productMeasureUnit = productMeasureUnit;
    }
}
