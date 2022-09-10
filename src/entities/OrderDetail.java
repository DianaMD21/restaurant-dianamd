package entities;

import java.util.List;

public class OrderDetail extends BaseEntity<Long>{
    private Product product;
    private Double quantity;
    private Double price;
    private List<TaxOrderDetail> taxesOrderDetail;

    public OrderDetail() {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<TaxOrderDetail> getTaxesOrderDetail() {
        return taxesOrderDetail;
    }

    public void setTaxesOrderDetail(List<TaxOrderDetail> taxesOrderDetail) {
        this.taxesOrderDetail = taxesOrderDetail;
    }
}

