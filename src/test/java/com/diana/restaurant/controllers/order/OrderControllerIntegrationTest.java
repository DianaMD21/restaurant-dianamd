package com.diana.restaurant.controllers.order;

import com.diana.restaurant.controllers.OrderController;
import com.diana.restaurant.services.implementation.OrderServiceImpl;
import com.diana.restaurant.services.interfaces.OrderService;
import com.diana.restaurant.util.fixtures.services.OrderServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class OrderControllerIntegrationTest {
  private OrderController orderController;
  private OrderService orderServiceSpy;

  @BeforeEach
  public void setUp() {
    OrderService orderService = new OrderServiceImpl();
    orderServiceSpy = Mockito.spy(orderService);
    orderController = new OrderController(orderServiceSpy);
  }

  @Test
  public void findById_ShouldReturnFoundOrder() {
    var order = OrderServiceFixtures.buildOrder();
    orderController.add(order);
    var orderFound = orderController.findById(order.getId());
    Assertions.assertEquals(orderFound, order);
    Mockito.verify(orderServiceSpy, Mockito.times(1)).findById(Mockito.eq(order.getId()));
  }

  @Test
  public void getAll_ShouldReturnAllOrders() {
    var orders = OrderServiceFixtures.buildOrders(3);
    orders.stream().forEach(orderController::add);
    var ordersFound = orderController.getAll();
    Assertions.assertEquals(orders, ordersFound);
  }

  @Test
  public void add_ShouldAddOrder() {
    var order = OrderServiceFixtures.buildOrder();
    var orderAdded = orderController.add(order);
    Assertions.assertEquals(orderAdded, order);
    Mockito.verify(orderServiceSpy, Mockito.times(1)).add(Mockito.eq(order));
  }

  @Test
  public void delete_ShouldDeleteOrder() {
    var orders = OrderServiceFixtures.buildOrders(3);
    orders.stream().forEach(orderController::add);
    var deletedOrder = orderController.deleteById(orders.get(0).getId());
    var deletedOrderToVerify = OrderServiceFixtures.buildOrder(orders.get(0));
    Assertions.assertEquals(deletedOrder, orders.get(0));
    Mockito.verify(orderServiceSpy, Mockito.times(1))
        .delete(Mockito.eq(deletedOrderToVerify.getId()));
  }

  @Test
  public void update_ShouldUpdateOrder() {
    var orderToUpdate = OrderServiceFixtures.buildOrder();
    orderController.add(orderToUpdate);
    orderToUpdate.setTotal(345.3);
    var orderToTestVerify = OrderServiceFixtures.buildOrder(orderToUpdate);
    var updatedOrder = orderController.update(orderToUpdate);
    Assertions.assertEquals(updatedOrder, orderToUpdate);
    Mockito.verify(orderServiceSpy, Mockito.times(1)).update(Mockito.eq(orderToTestVerify));
  }
}
