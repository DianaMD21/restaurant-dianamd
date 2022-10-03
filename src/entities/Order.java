package entities;

import enums.OrderStatusEnum;
import java.util.List;

public class Order extends BaseEntity<Long> {
  private Client client;
  private Cashier cashier;
  private Waiter waiter;
  private List<OrderDetail> orderDetails;
  private Double total;
  private OrderStatusEnum orderStatusEnum;
  private List<OrderTax> orderTaxes;

  public Order() {}

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public Cashier getCashier() {
    return cashier;
  }

  public void setCashier(Cashier cashier) {
    this.cashier = cashier;
  }

  public Waiter getWaiter() {
    return waiter;
  }

  public void setWaiter(Waiter waiter) {
    this.waiter = waiter;
  }

  public List<OrderDetail> getOrderDetails() {
    return orderDetails;
  }

  public void setOrderDetails(List<OrderDetail> orderDetails) {
    this.orderDetails = orderDetails;
  }

  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public OrderStatusEnum getOrderStatusEnum() {
    return orderStatusEnum;
  }

  public void setOrderStatusEnum(OrderStatusEnum orderStatusEnum) {
    this.orderStatusEnum = orderStatusEnum;
  }

  public List<OrderTax> getOrderTaxes() {
    return orderTaxes;
  }

  public void setOrderTaxes(List<OrderTax> orderTaxes) {
    this.orderTaxes = orderTaxes;
  }
}
