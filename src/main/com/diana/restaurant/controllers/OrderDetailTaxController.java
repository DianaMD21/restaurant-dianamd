package com.diana.restaurant.controllers;

import com.diana.restaurant.entities.OrderDetailTax;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.services.interfaces.OrderDetailTaxService;
import java.util.List;

public class OrderDetailTaxController {
  private OrderDetailTaxService orderDetailTaxService;

  public OrderDetailTaxController(OrderDetailTaxService orderDetailTaxService) {
    this.orderDetailTaxService = orderDetailTaxService;
  }

  public List<OrderDetailTax> getAll() {
    return this.orderDetailTaxService.getAll();
  }

  public OrderDetailTax findById(Long id) {
    return this.orderDetailTaxService
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException(OrderDetailTax.class, id));
  }

  public OrderDetailTax add(OrderDetailTax orderDetailTax) {
    return this.orderDetailTaxService.add(orderDetailTax);
  }

  public OrderDetailTax deleteById(Long id) {
    return this.orderDetailTaxService.delete(id);
  }

  public OrderDetailTax update(OrderDetailTax orderDetailTax) {
    return this.orderDetailTaxService.update(orderDetailTax);
  }
}
