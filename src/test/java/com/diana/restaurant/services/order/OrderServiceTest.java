package com.diana.restaurant.services.order;

import static org.junit.Assert.assertEquals;

import com.diana.restaurant.entities.Order;
import com.diana.restaurant.enums.IocServices;
import com.diana.restaurant.enums.StatusEnum;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.exceptions.services.IdNullPointerException;
import com.diana.restaurant.exceptions.services.NullEntityException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.services.implementation.OrderServiceImpl;
import com.diana.restaurant.util.fixtures.OrderFixtures;
import com.diana.restaurant.util.fixtures.OrderServiceFixtures;
import org.junit.Before;
import org.junit.Test;

public class OrderServiceTest {
  private OrderServiceImpl orderService;

  @Before
  public void setup() {
    orderService = Ioc.getInstance().get(IocServices.ORDER_SERVICE_INSTANCE);
  }

  @Test(expected = IdNullPointerException.class)
  public void findById_ShouldThrowIdNullPointerException() {
    orderService.findById(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException() {
    orderService.findById(10L);
  }

  @Test
  public void findById_ShouldReturnOptionalOfOrder() {
    var order = OrderServiceFixtures.buildOrder();
    var newOrder = orderService.insert(order);
    assertEquals(newOrder, orderService.findById(order.getId()).get());
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var order = OrderServiceFixtures.buildOrder();
    var newOrder = orderService.insert(order);
    orderService.delete(newOrder.getId());
    orderService.findById(newOrder.getId());
  }

  @Test(expected = IdNullPointerException.class)
  public void update_ShouldThrowIdNullPointerException() {
    var order = new Order();
    orderService.update(order);
  }

  @Test(expected = NullEntityException.class)
  public void update_ShouldThrowNullEntityException() {
    orderService.update(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void update_ShouldThrowEntityNotFoundException() {
    var orderExample = new Order();
    orderExample.setId(OrderFixtures.FAKEID);
    var order = OrderServiceFixtures.buildOrder(orderExample);
    orderService.update(order);
  }

  @Test
  public void update_ShouldReturnUpdatedObject() {
    var order = OrderServiceFixtures.buildOrder();
    var newOrder = orderService.insert(order);
    var updatedOrderExample = new Order();
    updatedOrderExample.setStatus(StatusEnum.INACTIVE);
    updatedOrderExample.setTotal(500.6);
    var updatedOrder = OrderServiceFixtures.buildOrder(updatedOrderExample);
    updatedOrder.setId(order.getId());
    assertEquals(newOrder, orderService.update(updatedOrder));
  }

  @Test
  public void insert_ShouldReturnOrder() {
    var order = OrderServiceFixtures.buildOrder();
    assertEquals(order, orderService.insert(order));
  }

  @Test(expected = IdNullPointerException.class)
  public void delete_ShouldThrowIdNullPointerException() {
    var order = new Order();
    orderService.delete(order.getId());
  }

  @Test
  public void delete_ShouldReturnDeletedValue() {
    var order = OrderServiceFixtures.buildOrder();
    var newOrder = orderService.insert(order);
    assertEquals(newOrder, orderService.delete(order.getId()));
  }

  @Test
  public void findAll_ShouldReturnListOfOrders() {
    var orders = OrderServiceFixtures.buildOrders(3);
    orders.stream().forEach(orderService::insert);
    assertEquals(orders, orderService.findAll());
  }
}
