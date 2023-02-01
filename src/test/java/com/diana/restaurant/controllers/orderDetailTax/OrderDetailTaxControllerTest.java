package com.diana.restaurant.controllers.orderDetailTax;

import com.diana.restaurant.controllers.OrderDetailTaxController;
import com.diana.restaurant.services.interfaces.OrderDetailTaxService;
import com.diana.restaurant.util.fixtures.services.OrderDetailTaxServiceFixtures;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class OrderDetailTaxControllerTest {
  private OrderDetailTaxController orderDetailTaxController;
  private OrderDetailTaxService orderDetailTaxServiceMock;

  @BeforeEach
  public void setup() {
    orderDetailTaxServiceMock = Mockito.mock(OrderDetailTaxService.class);
    orderDetailTaxController = new OrderDetailTaxController(orderDetailTaxServiceMock);
  }

  @Test
  public void getAll_ShouldReturnListOfOrderDetailTaxs() {
    var orderDetailTaxs = OrderDetailTaxServiceFixtures.buildOrderDetailTaxs(3);
    Mockito.when(orderDetailTaxServiceMock.getAll()).thenReturn(orderDetailTaxs);
    var orderDetailTaxsReturned = orderDetailTaxController.getAll();
    Assertions.assertEquals(orderDetailTaxs, orderDetailTaxsReturned);
    Mockito.verify(orderDetailTaxServiceMock, Mockito.times(1)).getAll();
  }

  @Test
  public void add_ShouldAddOrderDetailTax() {
    var orderDetailTax = OrderDetailTaxServiceFixtures.buildOrderDetailTax();
    var expectedOrderDetailTax = OrderDetailTaxServiceFixtures.buildOrderDetailTax(1L);
    Mockito.when(orderDetailTaxServiceMock.add(Mockito.eq(orderDetailTax)))
        .thenReturn(expectedOrderDetailTax);
    var orderDetailTaxAdded = orderDetailTaxController.add(orderDetailTax);
    Assertions.assertEquals(expectedOrderDetailTax, orderDetailTaxAdded);
    Mockito.verify(orderDetailTaxServiceMock, Mockito.times(1)).add(Mockito.eq(orderDetailTax));
  }

  @Test()
  public void delete_ShouldDeleteOrderDetailTax() {
    var orderDetailTax = OrderDetailTaxServiceFixtures.buildOrderDetailTax(1L);
    Mockito.when(orderDetailTaxServiceMock.delete(orderDetailTax.getId()))
        .thenReturn(orderDetailTax);
    var orderDetailTaxDeleted = orderDetailTaxController.deleteById(orderDetailTax.getId());
    Assertions.assertEquals(orderDetailTax, orderDetailTaxDeleted);
    var orderDetailTaxToTestVerify =
        OrderDetailTaxServiceFixtures.buildOrderDetailTax(orderDetailTax);
    Mockito.verify(orderDetailTaxServiceMock, Mockito.times(1))
        .delete(Mockito.eq(orderDetailTaxToTestVerify.getId()));
  }

  @Test
  public void findById_ShouldReturnFoundOrderDetailTax() {
    var orderDetailTax = OrderDetailTaxServiceFixtures.buildOrderDetailTax(1L);
    Mockito.when(orderDetailTaxServiceMock.findById(orderDetailTax.getId()))
        .thenReturn(Optional.of(orderDetailTax));
    var orderDetailTaxFound = orderDetailTaxController.findById(orderDetailTax.getId());
    Assertions.assertEquals(orderDetailTax, orderDetailTaxFound);
    var orderDetailTaxToTestVerify =
        OrderDetailTaxServiceFixtures.buildOrderDetailTax(orderDetailTax);
    Mockito.verify(orderDetailTaxServiceMock, Mockito.times(1))
        .findById(Mockito.eq(orderDetailTaxToTestVerify.getId()));
  }

  @Test
  public void update_ShouldUpdateOrderDetailTax() {
    var orderDetailTaxToUpdate = OrderDetailTaxServiceFixtures.buildOrderDetailTax(1L);
    orderDetailTaxToUpdate.setTaxName("Testing OrderDetailTax fake");
    Mockito.when(orderDetailTaxServiceMock.update(orderDetailTaxToUpdate))
        .thenReturn(orderDetailTaxToUpdate);
    var orderDetailTaxToTestVerify =
        OrderDetailTaxServiceFixtures.buildOrderDetailTax(orderDetailTaxToUpdate);
    var orderDetailTaxUpdated = orderDetailTaxController.update(orderDetailTaxToUpdate);
    Assertions.assertEquals(orderDetailTaxToUpdate, orderDetailTaxUpdated);
    Mockito.verify(orderDetailTaxServiceMock, Mockito.times(1))
        .update(Mockito.eq(orderDetailTaxToTestVerify));
  }
}
