package com.diana.restaurant.controllers;

import com.diana.restaurant.entities.Order;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.services.interfaces.OrderService;
import java.util.List;

public class OrderController {
  private OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  public List<Order> getAll() {
    return this.orderService.getAll();
  }

  public Order findById(Long id) {
    return this.orderService
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException(Order.class, id));
  }

  public Order add(Order order) {
    return this.orderService.add(order);
  }

  public Order deleteById(Long id) {
    return this.orderService.delete(id);
  }

  public Order update(Order order) {
    return this.orderService.update(order);
  }
}
