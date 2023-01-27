package com.diana.restaurant.controllers;

import com.diana.restaurant.entities.OrderDetailProduct;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.services.interfaces.OrderDetailProductService;
import java.util.List;

public class OrderDetailProductController {
  private OrderDetailProductService orderDetailProductService;

  public OrderDetailProductController(OrderDetailProductService orderDetailProductService) {
    this.orderDetailProductService = orderDetailProductService;
  }

  public List<OrderDetailProduct> getAll() {
    return this.orderDetailProductService.getAll();
  }

  public OrderDetailProduct findById(Long id) {
    return this.orderDetailProductService
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException(OrderDetailProduct.class, id));
  }

  public OrderDetailProduct add(OrderDetailProduct orderDetailProduct) {
    return this.orderDetailProductService.add(orderDetailProduct);
  }

  public OrderDetailProduct deleteById(Long id) {
    return this.orderDetailProductService.delete(id);
  }

  public OrderDetailProduct update(OrderDetailProduct orderDetailProduct) {
    return this.orderDetailProductService.update(orderDetailProduct);
  }
}
