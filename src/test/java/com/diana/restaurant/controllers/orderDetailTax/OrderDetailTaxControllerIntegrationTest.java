package com.diana.restaurant.controllers.orderDetailTax;

import com.diana.restaurant.controllers.OrderDetailTaxController;
import com.diana.restaurant.services.implementation.OrderDetailTaxServiceImpl;
import com.diana.restaurant.services.interfaces.OrderDetailTaxService;
import com.diana.restaurant.util.fixtures.services.OrderDetailTaxServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class OrderDetailTaxControllerIntegrationTest {
  private OrderDetailTaxController orderDetailTaxController;
  private OrderDetailTaxService orderDetailTaxServiceSpy;

  @BeforeEach
  public void setUp() {
    OrderDetailTaxService orderDetailTaxService = new OrderDetailTaxServiceImpl();
    orderDetailTaxServiceSpy = Mockito.spy(orderDetailTaxService);
    orderDetailTaxController = new OrderDetailTaxController(orderDetailTaxServiceSpy);
  }

  @Test
  public void findById_ShouldReturnFoundOrderDetailTax() {
    var orderDetailTax = OrderDetailTaxServiceFixtures.buildOrderDetailTax();
    orderDetailTaxController.add(orderDetailTax);
    var orderDetailTaxFound = orderDetailTaxController.findById(orderDetailTax.getId());
    Assertions.assertEquals(orderDetailTaxFound, orderDetailTax);
    Mockito.verify(orderDetailTaxServiceSpy, Mockito.times(1))
        .findById(Mockito.eq(orderDetailTax.getId()));
  }

  @Test
  public void getAll_ShouldReturnAllOrderDetailTaxes() {
    var orderDetailTaxes = OrderDetailTaxServiceFixtures.buildOrderDetailTaxes(3);
    orderDetailTaxes.stream().forEach(orderDetailTaxController::add);
    var orderDetailTaxesFound = orderDetailTaxController.getAll();
    Assertions.assertEquals(orderDetailTaxes, orderDetailTaxesFound);
  }

  @Test
  public void add_ShouldAddOrderDetailTax() {
    var orderDetailTax = OrderDetailTaxServiceFixtures.buildOrderDetailTax();
    var orderDetailTaxAdded = orderDetailTaxController.add(orderDetailTax);
    Assertions.assertEquals(orderDetailTaxAdded, orderDetailTax);
    Mockito.verify(orderDetailTaxServiceSpy, Mockito.times(1)).add(Mockito.eq(orderDetailTax));
  }

  @Test
  public void delete_ShouldDeleteOrderDetailTax() {
    var orderDetailTaxes = OrderDetailTaxServiceFixtures.buildOrderDetailTaxes(3);
    orderDetailTaxes.stream().forEach(orderDetailTaxController::add);
    var deletedOrderDetailTax = orderDetailTaxController.deleteById(orderDetailTaxes.get(0).getId());
    var deletedOrderDetailTaxToVerify =
        OrderDetailTaxServiceFixtures.buildOrderDetailTax(orderDetailTaxes.get(0));
    Assertions.assertEquals(deletedOrderDetailTax, orderDetailTaxes.get(0));
    Mockito.verify(orderDetailTaxServiceSpy, Mockito.times(1))
        .delete(Mockito.eq(deletedOrderDetailTaxToVerify.getId()));
  }

  @Test
  public void update_ShouldUpdateOrderDetailTax() {
    var orderDetailTaxToUpdate = OrderDetailTaxServiceFixtures.buildOrderDetailTax();
    orderDetailTaxController.add(orderDetailTaxToUpdate);
    orderDetailTaxToUpdate.setTaxName("Testing OrderDetailTax fake");
    var orderDetailTaxToTestVerify =
        OrderDetailTaxServiceFixtures.buildOrderDetailTax(orderDetailTaxToUpdate);
    var updatedOrderDetailTax = orderDetailTaxController.update(orderDetailTaxToUpdate);
    Assertions.assertEquals(updatedOrderDetailTax, orderDetailTaxToUpdate);
    Mockito.verify(orderDetailTaxServiceSpy, Mockito.times(1))
        .update(Mockito.eq(orderDetailTaxToTestVerify));
  }
}
