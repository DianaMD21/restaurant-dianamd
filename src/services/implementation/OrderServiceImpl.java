package services.implementation;

import entities.Order;
import services.interfaces.OrderService;

public class OrderServiceImpl extends BaseServiceImpl<Order, Long> implements OrderService {
  public OrderServiceImpl() {
    super(0L, Order.class, Long.class);
  }
}
