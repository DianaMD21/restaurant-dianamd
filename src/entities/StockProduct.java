package entities;

import enums.MeasureUnitEnum;

public class StockProduct extends BaseEntity<Long>{
    private Product product;
    private Double quantity;

    public StockProduct() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
