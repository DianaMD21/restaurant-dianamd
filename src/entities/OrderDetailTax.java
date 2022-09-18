package entities;

public class OrderDetailTax extends DefaultTaxHistory {
    private OrderDetailProduct orderDetailProduct;
    private OrderDetail orderDetail;

    public OrderDetailTax() {
    }

    public OrderDetailProduct getOrderDetailProduct() {
        return orderDetailProduct;
    }

    public void setOrderDetailProduct(OrderDetailProduct orderDetailProduct) {
        this.orderDetailProduct = orderDetailProduct;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }
}
