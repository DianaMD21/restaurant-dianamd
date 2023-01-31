package com.diana.restaurant.controllers;

import com.diana.restaurant.entities.OrderDetail;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.services.interfaces.OrderDetailService;
import java.util.List;

public class OrderDetailController {
  private OrderDetailService orderDetailService;

  public OrderDetailController(OrderDetailService orderDetailService) {
    this.orderDetailService = orderDetailService;
  }

  public List<OrderDetail> getAll() {
    return this.orderDetailService.getAll();
  }

  public OrderDetail findById(Long id) {
    return this.orderDetailService
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException(OrderDetail.class, id));
  }

  public OrderDetail add(OrderDetail orderDetail) {
    return this.orderDetailService.add(orderDetail);
  }

  public OrderDetail deleteById(Long id) {
    return this.orderDetailService.delete(id);
  }

  public OrderDetail update(OrderDetail orderDetail) {
    return this.orderDetailService.update(orderDetail);
  }
}
