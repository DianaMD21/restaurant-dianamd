package com.diana.restaurant.controllers.order;

import com.diana.restaurant.controllers.OrderController;
import com.diana.restaurant.services.interfaces.OrderService;
import com.diana.restaurant.util.fixtures.services.OrderServiceFixtures;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class OrderControllerTest {
  private OrderController orderController;
  private OrderService orderServiceMock;

  @BeforeEach
  public void setup() {
    orderServiceMock = Mockito.mock(OrderService.class);
    orderController = new OrderController(orderServiceMock);
  }

  @Test
  public void getAll_ShouldReturnListOfOrders() {
    var orders = OrderServiceFixtures.buildOrders(3);
    Mockito.when(orderServiceMock.getAll()).thenReturn(orders);
    var ordersReturned = orderController.getAll();
    Assertions.assertEquals(orders, ordersReturned);
    Mockito.verify(orderServiceMock, Mockito.times(1)).getAll();
  }

  @Test
  public void add_ShouldAddOrder() {
    var order = OrderServiceFixtures.buildOrder();
    var expectedOrder = OrderServiceFixtures.buildOrder(1L);
    Mockito.when(orderServiceMock.add(Mockito.eq(order))).thenReturn(expectedOrder);
    var orderAdded = orderController.add(order);
    Assertions.assertEquals(expectedOrder, orderAdded);
    Mockito.verify(orderServiceMock, Mockito.times(1)).add(Mockito.eq(order));
  }

  @Test()
  public void delete_ShouldDeleteOrder() {
    var order = OrderServiceFixtures.buildOrder(1L);
    Mockito.when(orderServiceMock.delete(order.getId())).thenReturn(order);
    var orderDeleted = orderController.deleteById(order.getId());
    Assertions.assertEquals(order, orderDeleted);
    var orderToTestVerify = OrderServiceFixtures.buildOrder(order);
    Mockito.verify(orderServiceMock, Mockito.times(1))
        .delete(Mockito.eq(orderToTestVerify.getId()));
  }

  @Test
  public void findById_ShouldReturnFoundOrder() {
    var order = OrderServiceFixtures.buildOrder(1L);
    Mockito.when(orderServiceMock.findById(order.getId())).thenReturn(Optional.of(order));
    var orderFound = orderController.findById(order.getId());
    Assertions.assertEquals(order, orderFound);
    var orderToTestVerify = OrderServiceFixtures.buildOrder(order);
    Mockito.verify(orderServiceMock, Mockito.times(1))
        .findById(Mockito.eq(orderToTestVerify.getId()));
  }

  @Test
  public void update_ShouldUpdateOrder() {
    var orderToUpdate = OrderServiceFixtures.buildOrder(1L);
    orderToUpdate.setTotal(34534.3);
    Mockito.when(orderServiceMock.update(orderToUpdate)).thenReturn(orderToUpdate);
    var orderToTestVerify = OrderServiceFixtures.buildOrder(orderToUpdate);
    var orderUpdated = orderController.update(orderToUpdate);
    Assertions.assertEquals(orderToUpdate, orderUpdated);
    Mockito.verify(orderServiceMock, Mockito.times(1)).update(Mockito.eq(orderToTestVerify));
  }
}
