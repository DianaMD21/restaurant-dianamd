package com.diana.restaurant.services.implementation;

import com.diana.restaurant.entities.Order;
import com.diana.restaurant.services.interfaces.OrderService;

public class OrderServiceImpl extends BaseServiceImpl<Order, Long> implements OrderService {
  public OrderServiceImpl() {
    super(0L, Order.class, Long.class);
  }
}
