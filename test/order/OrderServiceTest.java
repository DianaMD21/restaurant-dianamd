package order;

import static org.junit.Assert.assertEquals;

import entities.Order;
import enums.IocServices;
import enums.StatusEnum;
import exceptions.services.EntityNotFoundException;
import exceptions.services.IdNullPointerException;
import exceptions.services.NullEntityException;
import ioc.Ioc;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import services.implementation.OrderServiceImpl;
import util.fixtures.OrderFixtures;
import util.fixtures.OrderServiceFixtures;

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
    assertEquals(newOrder, orderService.findById(order.getId()));
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var order = OrderServiceFixtures.buildOrder();
    var newOrder = orderService.insert(order).get();
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
  public void insert_ShouldReturnOptionalOfOrder() {
    var order = OrderServiceFixtures.buildOrder();
    assertEquals(Optional.of(order), orderService.insert(order));
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
