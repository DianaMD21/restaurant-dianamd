package com.diana.restaurant.controllers.orderDetail;

import com.diana.restaurant.controllers.OrderDetailController;
import com.diana.restaurant.services.interfaces.OrderDetailService;
import com.diana.restaurant.util.fixtures.services.OrderDetailServiceFixtures;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class OrderDetailControllerTest {
  private OrderDetailController orderDetailController;
  private OrderDetailService orderDetailServiceMock;

  @BeforeEach
  public void setup() {
    orderDetailServiceMock = Mockito.mock(OrderDetailService.class);
    orderDetailController = new OrderDetailController(orderDetailServiceMock);
  }

  @Test
  public void getAll_ShouldReturnListOfOrderDetails() {
    var orderDetails = OrderDetailServiceFixtures.buildOrderDetails(3);
    Mockito.when(orderDetailServiceMock.getAll()).thenReturn(orderDetails);
    var orderDetailsReturned = orderDetailController.getAll();
    Assertions.assertEquals(orderDetails, orderDetailsReturned);
    Mockito.verify(orderDetailServiceMock, Mockito.times(1)).getAll();
  }

  @Test
  public void add_ShouldAddOrderDetail() {
    var orderDetail = OrderDetailServiceFixtures.buildOrderDetail();
    var expectedOrderDetail = OrderDetailServiceFixtures.buildOrderDetail(1L);
    Mockito.when(orderDetailServiceMock.add(Mockito.eq(orderDetail)))
        .thenReturn(expectedOrderDetail);
    var orderDetailAdded = orderDetailController.add(orderDetail);
    Assertions.assertEquals(expectedOrderDetail, orderDetailAdded);
    Mockito.verify(orderDetailServiceMock, Mockito.times(1)).add(Mockito.eq(orderDetail));
  }

  @Test()
  public void delete_ShouldDeleteOrderDetail() {
    var orderDetail = OrderDetailServiceFixtures.buildOrderDetail(1L);
    Mockito.when(orderDetailServiceMock.delete(orderDetail.getId())).thenReturn(orderDetail);
    var orderDetailDeleted = orderDetailController.deleteById(orderDetail.getId());
    Assertions.assertEquals(orderDetail, orderDetailDeleted);
    var orderDetailToTestVerify = OrderDetailServiceFixtures.buildOrderDetail(orderDetail);
    Mockito.verify(orderDetailServiceMock, Mockito.times(1))
        .delete(Mockito.eq(orderDetailToTestVerify.getId()));
  }

  @Test
  public void findById_ShouldReturnFoundOrderDetail() {
    var orderDetail = OrderDetailServiceFixtures.buildOrderDetail(1L);
    Mockito.when(orderDetailServiceMock.findById(orderDetail.getId()))
        .thenReturn(Optional.of(orderDetail));
    var orderDetailFound = orderDetailController.findById(orderDetail.getId());
    Assertions.assertEquals(orderDetail, orderDetailFound);
    var orderDetailToTestVerify = OrderDetailServiceFixtures.buildOrderDetail(orderDetail);
    Mockito.verify(orderDetailServiceMock, Mockito.times(1))
        .findById(Mockito.eq(orderDetailToTestVerify.getId()));
  }

  @Test
  public void update_ShouldUpdateOrderDetail() {
    var orderDetailToUpdate = OrderDetailServiceFixtures.buildOrderDetail(1L);
    orderDetailToUpdate.setQuantity(234.2);
    Mockito.when(orderDetailServiceMock.update(orderDetailToUpdate))
        .thenReturn(orderDetailToUpdate);
    var orderDetailToTestVerify = OrderDetailServiceFixtures.buildOrderDetail(orderDetailToUpdate);
    var orderDetailUpdated = orderDetailController.update(orderDetailToUpdate);
    Assertions.assertEquals(orderDetailToUpdate, orderDetailUpdated);
    Mockito.verify(orderDetailServiceMock, Mockito.times(1))
        .update(Mockito.eq(orderDetailToTestVerify));
  }
}
