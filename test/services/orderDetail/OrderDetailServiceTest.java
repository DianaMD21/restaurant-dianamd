package services.orderDetail;

import static org.junit.Assert.assertEquals;

import entities.OrderDetail;
import enums.IocServices;
import exceptions.services.EntityNotFoundException;
import exceptions.services.IdNullPointerException;
import exceptions.services.NullEntityException;
import ioc.Ioc;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import services.implementation.OrderDetailServiceImpl;
import util.fixtures.OrderDetailFixtures;
import util.fixtures.OrderDetailServiceFixtures;

public class OrderDetailServiceTest {
  private OrderDetailServiceImpl orderDetailService;

  @Before
  public void setup() {
    orderDetailService = Ioc.getInstance().get(IocServices.ORDER_DETAIL_SERVICE_INSTANCE);
  }

  @Test(expected = IdNullPointerException.class)
  public void findById_ShouldThrowIdNullPointerException() {
    orderDetailService.findById(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException() {
    orderDetailService.findById(10L);
  }

  @Test
  public void findById_ShouldReturnOptionalOfOrderDetail() {
    var orderDetail = OrderDetailServiceFixtures.buildOrderDetail();
    var newOrderDetail = orderDetailService.insert(orderDetail);
    assertEquals(newOrderDetail, orderDetailService.findById(orderDetail.getId()).get());
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var orderDetail = OrderDetailServiceFixtures.buildOrderDetail();
    var newOrderDetail = orderDetailService.insert(orderDetail);
    orderDetailService.delete(newOrderDetail.getId());
    orderDetailService.findById(newOrderDetail.getId());
  }

  @Test(expected = IdNullPointerException.class)
  public void update_ShouldThrowIdNullPointerException() {
    var orderDetail = new OrderDetail();
    orderDetailService.update(orderDetail);
  }

  @Test(expected = NullEntityException.class)
  public void update_ShouldThrowNullEntityException() {
    orderDetailService.update(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void update_ShouldThrowEntityNotFoundException() {
    var orderDetailExample = new OrderDetail();
    orderDetailExample.setId(OrderDetailFixtures.FAKEID);
    var orderDetail = OrderDetailServiceFixtures.buildOrderDetail(orderDetailExample);
    orderDetailService.update(orderDetail);
  }

  @Test
  public void update_ShouldReturnUpdatedObject() {
    var orderDetail = OrderDetailServiceFixtures.buildOrderDetail();
    var newOrderDetail = orderDetailService.insert(orderDetail);
    var updatedOrderDetailExample = new OrderDetail();
    updatedOrderDetailExample.setQuantity(500.6);
    var updatedOrderDetail = OrderDetailServiceFixtures.buildOrderDetail(updatedOrderDetailExample);
    updatedOrderDetail.setId(orderDetail.getId());
    assertEquals(newOrderDetail, orderDetailService.update(updatedOrderDetail));
  }

  @Test
  public void insert_ShouldReturnOrderDetail() {
    var orderDetail = OrderDetailServiceFixtures.buildOrderDetail();
    assertEquals(orderDetail, orderDetailService.insert(orderDetail));
  }

  @Test(expected = IdNullPointerException.class)
  public void delete_ShouldThrowIdNullPointerException() {
    var orderDetail = new OrderDetail();
    orderDetailService.delete(orderDetail.getId());
  }

  @Test
  public void delete_ShouldReturnDeletedValue() {
    var orderDetail = OrderDetailServiceFixtures.buildOrderDetail();
    var newOrderDetail = orderDetailService.insert(orderDetail);
    assertEquals(newOrderDetail, orderDetailService.delete(orderDetail.getId()));
  }

  @Test
  public void findAll_ShouldReturnListOfOrderDetails() {
    var orderDetails = OrderDetailServiceFixtures.buildOrderDetails(3);
    orderDetails.stream().forEach(orderDetailService::insert);
    assertEquals(orderDetails, orderDetailService.findAll());
  }
}
