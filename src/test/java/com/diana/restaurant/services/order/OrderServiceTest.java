package com.diana.restaurant.services.order;

import com.diana.restaurant.entities.Order;
import com.diana.restaurant.enums.IocServices;
import com.diana.restaurant.enums.StatusEnum;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.exceptions.services.IdNullPointerException;
import com.diana.restaurant.exceptions.services.NullEntityException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.services.implementation.OrderServiceImpl;
import com.diana.restaurant.util.fixtures.entities.OrderFixtures;
import com.diana.restaurant.util.fixtures.services.OrderServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
  private OrderServiceImpl orderService;

  @BeforeEach
  public void setup() {
    orderService = Ioc.getInstance().get(IocServices.ORDER_SERVICE_INSTANCE);
  }

  @Test()
  public void findById_ShouldThrowIdNullPointerException() {
    Assertions.assertThrows(IdNullPointerException.class, () -> orderService.findById(null));
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException() {
    Assertions.assertThrows(EntityNotFoundException.class, () -> orderService.findById(10L));
  }

  @Test
  public void findById_ShouldReturnOptionalOfOrder() {
    var order = OrderServiceFixtures.buildOrder();
    var newOrder = orderService.add(order);
    Assertions.assertEquals(newOrder, orderService.findById(order.getId()).get());
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var order = OrderServiceFixtures.buildOrder();
    var newOrder = orderService.add(order);
    orderService.delete(newOrder.getId());
    Assertions.assertThrows(
        EntityNotFoundException.class, () -> orderService.findById(newOrder.getId()));
  }

  @Test()
  public void update_ShouldThrowIdNullPointerException() {
    var order = new Order();
    Assertions.assertThrows(IdNullPointerException.class, () -> orderService.update(order));
  }

  @Test()
  public void update_ShouldThrowNullEntityException() {
    Assertions.assertThrows(NullEntityException.class, () -> orderService.update(null));
  }

  @Test()
  public void update_ShouldThrowEntityNotFoundException() {
    var orderExample = new Order();
    orderExample.setId(OrderFixtures.FAKEID);
    var order = OrderServiceFixtures.buildOrder(orderExample);
    Assertions.assertThrows(EntityNotFoundException.class, () -> orderService.update(order));
  }

  @Test
  public void update_ShouldReturnUpdatedOrder() {
    var order = OrderServiceFixtures.buildOrder();
    var updatedOrderExample = new Order();
    updatedOrderExample.setStatus(StatusEnum.INACTIVE);
    updatedOrderExample.setTotal(500.6);
    var newOrder = orderService.add(order);
    var updatedOrder = OrderServiceFixtures.buildOrder(updatedOrderExample);
    updatedOrder.setId(order.getId());
    Assertions.assertEquals(newOrder, orderService.update(updatedOrder));
  }

  @Test
  public void add_ShouldAddOrder() {
    var order = OrderServiceFixtures.buildOrder();
    Assertions.assertEquals(order, orderService.add(order));
  }

  @Test()
  public void delete_ShouldThrowIdNullPointerException() {
    var order = new Order();
    Assertions.assertThrows(IdNullPointerException.class, () -> orderService.delete(order.getId()));
  }

  @Test
  public void delete_ShouldDeleteOrder() {
    var order = OrderServiceFixtures.buildOrder();
    var newOrder = orderService.add(order);
    Assertions.assertEquals(newOrder, orderService.delete(order.getId()));
  }

  @Test
  public void getAll_ShouldReturnListOfOrders() {
    var orders = OrderServiceFixtures.buildOrders(3);
    orders.stream().forEach(orderService::add);
    Assertions.assertEquals(orders, orderService.getAll());
  }
}
