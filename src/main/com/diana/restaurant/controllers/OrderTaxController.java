package com.diana.restaurant.controllers;

import com.diana.restaurant.entities.OrderTax;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.services.interfaces.OrderTaxService;
import java.util.List;

public class OrderTaxController {
  private OrderTaxService orderTaxService;

  public OrderTaxController(OrderTaxService orderTaxService) {
    this.orderTaxService = orderTaxService;
  }

  public List<OrderTax> getAll() {
    return this.orderTaxService.getAll();
  }

  public OrderTax findById(Long id) {
    return this.orderTaxService
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException(OrderTax.class, id));
  }

  public OrderTax add(OrderTax orderTax) {
    return this.orderTaxService.add(orderTax);
  }

  public OrderTax deleteById(Long id) {
    return this.orderTaxService.delete(id);
  }

  public OrderTax update(OrderTax orderTax) {
    return this.orderTaxService.update(orderTax);
  }
}
