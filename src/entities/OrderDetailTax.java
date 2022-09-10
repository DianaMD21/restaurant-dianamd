package entities;

public class OrderDetailTax extends BaseEntity<Long>{
    private Tax tax;
    private OrderDetailProduct orderDetailProduct;
    private String taxName;
    private Double taxPercentage;
    private OrderDetail orderDetail;

    public OrderDetailTax() {
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public OrderDetailProduct getOrderDetailProduct() {
        return orderDetailProduct;
    }

    public void setOrderDetailProduct(OrderDetailProduct orderDetailProduct) {
        this.orderDetailProduct = orderDetailProduct;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public Double getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(Double taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }
}
