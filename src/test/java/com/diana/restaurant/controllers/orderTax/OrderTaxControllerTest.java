package com.diana.restaurant.controllers.orderTax;

import com.diana.restaurant.controllers.OrderTaxController;
import com.diana.restaurant.services.interfaces.OrderTaxService;
import com.diana.restaurant.util.fixtures.services.OrderTaxServiceFixtures;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class OrderTaxControllerTest {
  private OrderTaxController orderTaxController;
  private OrderTaxService orderTaxServiceMock;

  @BeforeEach
  public void setup() {
    orderTaxServiceMock = Mockito.mock(OrderTaxService.class);
    orderTaxController = new OrderTaxController(orderTaxServiceMock);
  }

  @Test
  public void getAll_ShouldReturnListOfOrderTaxs() {
    var orderTaxs = OrderTaxServiceFixtures.buildOrderTaxs(3);
    Mockito.when(orderTaxServiceMock.getAll()).thenReturn(orderTaxs);
    var orderTaxsReturned = orderTaxController.getAll();
    Assertions.assertEquals(orderTaxs, orderTaxsReturned);
    Mockito.verify(orderTaxServiceMock, Mockito.times(1)).getAll();
  }

  @Test
  public void add_ShouldAddOrderTax() {
    var orderTax = OrderTaxServiceFixtures.buildOrderTax();
    var expectedOrderTax = OrderTaxServiceFixtures.buildOrderTax(1L);
    Mockito.when(orderTaxServiceMock.add(Mockito.eq(orderTax))).thenReturn(expectedOrderTax);
    var orderTaxAdded = orderTaxController.add(orderTax);
    Assertions.assertEquals(expectedOrderTax, orderTaxAdded);
    Mockito.verify(orderTaxServiceMock, Mockito.times(1)).add(Mockito.eq(orderTax));
  }

  @Test()
  public void delete_ShouldDeleteOrderTax() {
    var orderTax = OrderTaxServiceFixtures.buildOrderTax(1L);
    Mockito.when(orderTaxServiceMock.delete(orderTax.getId())).thenReturn(orderTax);
    var orderTaxDeleted = orderTaxController.deleteById(orderTax.getId());
    Assertions.assertEquals(orderTax, orderTaxDeleted);
    var orderTaxToTestVerify = OrderTaxServiceFixtures.buildOrderTax(orderTax);
    Mockito.verify(orderTaxServiceMock, Mockito.times(1))
        .delete(Mockito.eq(orderTaxToTestVerify.getId()));
  }

  @Test
  public void findById_ShouldReturnFoundOrderTax() {
    var orderTax = OrderTaxServiceFixtures.buildOrderTax(1L);
    Mockito.when(orderTaxServiceMock.findById(orderTax.getId())).thenReturn(Optional.of(orderTax));
    var orderTaxFound = orderTaxController.findById(orderTax.getId());
    Assertions.assertEquals(orderTax, orderTaxFound);
    var orderTaxToTestVerify = OrderTaxServiceFixtures.buildOrderTax(orderTax);
    Mockito.verify(orderTaxServiceMock, Mockito.times(1))
        .findById(Mockito.eq(orderTaxToTestVerify.getId()));
  }

  @Test
  public void update_ShouldUpdateOrderTax() {
    var orderTaxToUpdate = OrderTaxServiceFixtures.buildOrderTax(1L);
    orderTaxToUpdate.setTaxName("Testing OrderTax fake");
    Mockito.when(orderTaxServiceMock.update(orderTaxToUpdate)).thenReturn(orderTaxToUpdate);
    var orderTaxToTestVerify = OrderTaxServiceFixtures.buildOrderTax(orderTaxToUpdate);
    var orderTaxUpdated = orderTaxController.update(orderTaxToUpdate);
    Assertions.assertEquals(orderTaxToUpdate, orderTaxUpdated);
    Mockito.verify(orderTaxServiceMock, Mockito.times(1)).update(Mockito.eq(orderTaxToTestVerify));
  }
}
