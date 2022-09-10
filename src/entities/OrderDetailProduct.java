package entities;

import enums.MeasureUnitEnum;

import java.util.List;

public class OrderDetailProduct extends BaseEntity<Long>{
    private FinalProduct finalProduct;
    private String name;
    private Double price;
    private MeasureUnitEnum measureUnit;
    private List<OrderDetailTax> orderDetailTaxes;
    private OrderDetail orderDetail;

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

    public void setMeasureUnit(MeasureUnitEnum measureUnit) {
        this.measureUnit = measureUnit;
    }

    public List<OrderDetailTax> getOrderDetailTaxes() {
        return orderDetailTaxes;
    }

    public void setOrderDetailTaxes(List<OrderDetailTax> orderDetailTaxes) {
        this.orderDetailTaxes = orderDetailTaxes;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }
}
