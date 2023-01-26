package com.diana.restaurant.services.orderDetail;

import com.diana.restaurant.entities.OrderDetail;
import com.diana.restaurant.enums.IocServices;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.exceptions.services.IdNullPointerException;
import com.diana.restaurant.exceptions.services.NullEntityException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.services.implementation.OrderDetailServiceImpl;
import com.diana.restaurant.util.fixtures.entities.OrderDetailFixtures;
import com.diana.restaurant.util.fixtures.entities.OrderDetailServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderDetailServiceTest {
  private OrderDetailServiceImpl orderDetailService;

  @BeforeEach
  public void setup() {
    orderDetailService = Ioc.getInstance().get(IocServices.ORDER_DETAIL_SERVICE_INSTANCE);
  }

  @Test()
  public void findById_ShouldThrowIdNullPointerException() {
    Assertions.assertThrows(IdNullPointerException.class, () -> orderDetailService.findById(null));
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException() {
    Assertions.assertThrows(EntityNotFoundException.class, () -> orderDetailService.findById(10L));
  }

  @Test
  public void findById_ShouldReturnOptionalOfOrderDetail() {
    var orderDetail = OrderDetailServiceFixtures.buildOrderDetail();
    var newOrderDetail = orderDetailService.add(orderDetail);
    Assertions.assertEquals(newOrderDetail, orderDetailService.findById(orderDetail.getId()).get());
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var orderDetail = OrderDetailServiceFixtures.buildOrderDetail();
    var newOrderDetail = orderDetailService.add(orderDetail);
    orderDetailService.delete(newOrderDetail.getId());
    Assertions.assertThrows(
        EntityNotFoundException.class, () -> orderDetailService.findById(newOrderDetail.getId()));
  }

  @Test()
  public void update_ShouldThrowIdNullPointerException() {
    var orderDetail = new OrderDetail();
    Assertions.assertThrows(
        IdNullPointerException.class, () -> orderDetailService.update(orderDetail));
  }

  @Test()
  public void update_ShouldThrowNullEntityException() {
    Assertions.assertThrows(NullEntityException.class, () -> orderDetailService.update(null));
  }

  @Test()
  public void update_ShouldThrowEntityNotFoundException() {
    var orderDetailExample = new OrderDetail();
    orderDetailExample.setId(OrderDetailFixtures.FAKEID);
    var orderDetail = OrderDetailServiceFixtures.buildOrderDetail(orderDetailExample);
    Assertions.assertThrows(
        EntityNotFoundException.class, () -> orderDetailService.update(orderDetail));
  }

  @Test
  public void update_ShouldReturnUpdatedOrderDetail() {
    var orderDetail = OrderDetailServiceFixtures.buildOrderDetail();
    var newOrderDetail = orderDetailService.add(orderDetail);
    var updatedOrderDetailExample = new OrderDetail();
    updatedOrderDetailExample.setQuantity(500.6);
    var updatedOrderDetail = OrderDetailServiceFixtures.buildOrderDetail(updatedOrderDetailExample);
    updatedOrderDetail.setId(orderDetail.getId());
    Assertions.assertEquals(newOrderDetail, orderDetailService.update(updatedOrderDetail));
  }

  @Test
  public void add_ShouldAddOrderDetail() {
    var orderDetail = OrderDetailServiceFixtures.buildOrderDetail();
    Assertions.assertEquals(orderDetail, orderDetailService.add(orderDetail));
  }

  @Test()
  public void delete_ShouldThrowIdNullPointerException() {
    var orderDetail = new OrderDetail();
    Assertions.assertThrows(
        IdNullPointerException.class, () -> orderDetailService.delete(orderDetail.getId()));
  }

  @Test
  public void delete_ShouldDeleteOrderDetail() {
    var orderDetail = OrderDetailServiceFixtures.buildOrderDetail();
    var newOrderDetail = orderDetailService.add(orderDetail);
    Assertions.assertEquals(newOrderDetail, orderDetailService.delete(orderDetail.getId()));
  }

  @Test
  public void getAll_ShouldReturnListOfOrderDetails() {
    var orderDetails = OrderDetailServiceFixtures.buildOrderDetails(3);
    orderDetails.stream().forEach(orderDetailService::add);
    Assertions.assertEquals(orderDetails, orderDetailService.getAll());
  }
}
