package com.diana.restaurant.entities;

public class OrderTax extends DefaultTaxHistory {
  private Order order;

  public OrderTax() {}

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }
}
