package entities;

import java.util.List;

public class OrderDetail extends BaseEntity<Long>{
    private OrderDetailProduct orderDetailProduct;
    private Double quantity;
    private Double price;
    private List<OrderDetailTax> orderDetailTaxes;
    private Chef chef;

    public OrderDetail() {
    }

    public OrderDetailProduct getOrderDetailProduct() {
        return orderDetailProduct;
    }

    public void setOrderDetailProduct(OrderDetailProduct orderDetailProduct) {
        this.orderDetailProduct = orderDetailProduct;
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

    public List<OrderDetailTax> getOrderDetailTaxes() {
        return orderDetailTaxes;
    }

    public void setOrderDetailTaxes(List<OrderDetailTax> orderDetailTaxes) {
        this.orderDetailTaxes = orderDetailTaxes;
    }

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }
}

