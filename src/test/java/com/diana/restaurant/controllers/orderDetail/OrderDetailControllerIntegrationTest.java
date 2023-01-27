package com.diana.restaurant.controllers.orderDetail;

import com.diana.restaurant.controllers.OrderDetailController;
import com.diana.restaurant.services.implementation.OrderDetailServiceImpl;
import com.diana.restaurant.services.interfaces.OrderDetailService;
import com.diana.restaurant.util.fixtures.services.OrderDetailServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class OrderDetailControllerIntegrationTest {
  private OrderDetailController orderDetailController;
  private OrderDetailService orderDetailServiceSpy;

  @BeforeEach
  public void setUp() {
    OrderDetailService orderDetailService = new OrderDetailServiceImpl();
    orderDetailServiceSpy = Mockito.spy(orderDetailService);
    orderDetailController = new OrderDetailController(orderDetailServiceSpy);
  }

  @Test
  public void findById_ShouldReturnFoundOrderDetail() {
    var orderDetail = OrderDetailServiceFixtures.buildOrderDetail();
    orderDetailController.add(orderDetail);
    var orderDetailFound = orderDetailController.findById(orderDetail.getId());
    Assertions.assertEquals(orderDetailFound, orderDetail);
    Mockito.verify(orderDetailServiceSpy, Mockito.times(1))
        .findById(Mockito.eq(orderDetail.getId()));
  }

  @Test
  public void getAll_ShouldReturnAllOrderDetails() {
    var orderDetails = OrderDetailServiceFixtures.buildOrderDetails(3);
    orderDetails.stream().forEach(orderDetailController::add);
    var orderDetailsFound = orderDetailController.getAll();
    Assertions.assertEquals(orderDetails, orderDetailsFound);
  }

  @Test
  public void add_ShouldAddOrderDetail() {
    var orderDetail = OrderDetailServiceFixtures.buildOrderDetail();
    var orderDetailAdded = orderDetailController.add(orderDetail);
    Assertions.assertEquals(orderDetailAdded, orderDetail);
    Mockito.verify(orderDetailServiceSpy, Mockito.times(1)).add(Mockito.eq(orderDetail));
  }

  @Test
  public void delete_ShouldDeleteOrderDetail() {
    var orderDetails = OrderDetailServiceFixtures.buildOrderDetails(3);
    orderDetails.stream().forEach(orderDetailController::add);
    var deletedOrderDetail = orderDetailController.deleteById(orderDetails.get(0).getId());
    var deletedOrderDetailToVerify =
        OrderDetailServiceFixtures.buildOrderDetail(orderDetails.get(0));
    Assertions.assertEquals(deletedOrderDetail, orderDetails.get(0));
    Mockito.verify(orderDetailServiceSpy, Mockito.times(1))
        .delete(Mockito.eq(deletedOrderDetailToVerify.getId()));
  }

  @Test
  public void update_ShouldUpdateOrderDetail() {
    var orderDetailToUpdate = OrderDetailServiceFixtures.buildOrderDetail();
    orderDetailController.add(orderDetailToUpdate);
    orderDetailToUpdate.setQuantity(435.3);
    var orderDetailToTestVerify = OrderDetailServiceFixtures.buildOrderDetail(orderDetailToUpdate);
    var updatedOrderDetail = orderDetailController.update(orderDetailToUpdate);
    Assertions.assertEquals(updatedOrderDetail, orderDetailToUpdate);
    Mockito.verify(orderDetailServiceSpy, Mockito.times(1))
        .update(Mockito.eq(orderDetailToTestVerify));
  }
}
