package com.diana.restaurant.services.orderDetail;

import static org.junit.Assert.assertEquals;

import com.diana.restaurant.entities.OrderDetail;
import com.diana.restaurant.enums.IocServices;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.exceptions.services.IdNullPointerException;
import com.diana.restaurant.exceptions.services.NullEntityException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.services.implementation.OrderDetailServiceImpl;
import com.diana.restaurant.util.fixtures.OrderDetailFixtures;
import com.diana.restaurant.util.fixtures.OrderDetailServiceFixtures;
import org.junit.Before;
import org.junit.Test;

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
  public void update_ShouldReturnUpdatedOrderDetail() {
    var orderDetail = OrderDetailServiceFixtures.buildOrderDetail();
    var newOrderDetail = orderDetailService.insert(orderDetail);
    var updatedOrderDetailExample = new OrderDetail();
    updatedOrderDetailExample.setQuantity(500.6);
    var updatedOrderDetail = OrderDetailServiceFixtures.buildOrderDetail(updatedOrderDetailExample);
    updatedOrderDetail.setId(orderDetail.getId());
    assertEquals(newOrderDetail, orderDetailService.update(updatedOrderDetail));
  }

  @Test
  public void insert_ShouldInsertOrderDetail() {
    var orderDetail = OrderDetailServiceFixtures.buildOrderDetail();
    assertEquals(orderDetail, orderDetailService.insert(orderDetail));
  }

  @Test(expected = IdNullPointerException.class)
  public void delete_ShouldThrowIdNullPointerException() {
    var orderDetail = new OrderDetail();
    orderDetailService.delete(orderDetail.getId());
  }

  @Test
  public void delete_ShouldDeleteOrderDetail() {
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
