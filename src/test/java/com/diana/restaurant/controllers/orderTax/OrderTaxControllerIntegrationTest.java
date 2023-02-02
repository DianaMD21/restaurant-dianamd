package com.diana.restaurant.controllers.orderTax;

import com.diana.restaurant.controllers.OrderTaxController;
import com.diana.restaurant.services.implementation.OrderTaxServiceImpl;
import com.diana.restaurant.services.interfaces.OrderTaxService;
import com.diana.restaurant.util.fixtures.services.OrderTaxServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class OrderTaxControllerIntegrationTest {
  private OrderTaxController orderTaxController;
  private OrderTaxService orderTaxServiceSpy;

  @BeforeEach
  public void setUp() {
    OrderTaxService orderTaxService = new OrderTaxServiceImpl();
    orderTaxServiceSpy = Mockito.spy(orderTaxService);
    orderTaxController = new OrderTaxController(orderTaxServiceSpy);
  }

  @Test
  public void findById_ShouldReturnFoundOrderTax() {
    var orderTax = OrderTaxServiceFixtures.buildOrderTax();
    orderTaxController.add(orderTax);
    var orderTaxFound = orderTaxController.findById(orderTax.getId());
    Assertions.assertEquals(orderTaxFound, orderTax);
    Mockito.verify(orderTaxServiceSpy, Mockito.times(1)).findById(Mockito.eq(orderTax.getId()));
  }

  @Test
  public void getAll_ShouldReturnAllOrderTaxes() {
    var orderTaxes = OrderTaxServiceFixtures.buildOrderTaxes(3);
    orderTaxes.stream().forEach(orderTaxController::add);
    var orderTaxesFound = orderTaxController.getAll();
    Assertions.assertEquals(orderTaxes, orderTaxesFound);
  }

  @Test
  public void add_ShouldAddOrderTax() {
    var orderTax = OrderTaxServiceFixtures.buildOrderTax();
    var orderTaxAdded = orderTaxController.add(orderTax);
    Assertions.assertEquals(orderTaxAdded, orderTax);
    Mockito.verify(orderTaxServiceSpy, Mockito.times(1)).add(Mockito.eq(orderTax));
  }

  @Test
  public void delete_ShouldDeleteOrderTax() {
    var orderTaxes = OrderTaxServiceFixtures.buildOrderTaxes(3);
    orderTaxes.stream().forEach(orderTaxController::add);
    var deletedOrderTax = orderTaxController.deleteById(orderTaxes.get(0).getId());
    var deletedOrderTaxToVerify = OrderTaxServiceFixtures.buildOrderTax(orderTaxes.get(0));
    Assertions.assertEquals(deletedOrderTax, orderTaxes.get(0));
    Mockito.verify(orderTaxServiceSpy, Mockito.times(1))
        .delete(Mockito.eq(deletedOrderTaxToVerify.getId()));
  }

  @Test
  public void update_ShouldUpdateOrderTax() {
    var orderTaxToUpdate = OrderTaxServiceFixtures.buildOrderTax();
    orderTaxController.add(orderTaxToUpdate);
    orderTaxToUpdate.setTaxName("Testing OrderTax fake");
    var orderTaxToTestVerify = OrderTaxServiceFixtures.buildOrderTax(orderTaxToUpdate);
    var updatedOrderTax = orderTaxController.update(orderTaxToUpdate);
    Assertions.assertEquals(updatedOrderTax, orderTaxToUpdate);
    Mockito.verify(orderTaxServiceSpy, Mockito.times(1)).update(Mockito.eq(orderTaxToTestVerify));
  }
}
