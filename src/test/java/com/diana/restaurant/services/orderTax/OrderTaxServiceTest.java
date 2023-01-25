package com.diana.restaurant.services.orderTax;

import com.diana.restaurant.entities.OrderTax;
import com.diana.restaurant.enums.IocServices;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.exceptions.services.IdNullPointerException;
import com.diana.restaurant.exceptions.services.NullEntityException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.services.implementation.OrderTaxServiceImpl;
import com.diana.restaurant.util.fixtures.entities.OrderTaxFixtures;
import com.diana.restaurant.util.fixtures.services.OrderTaxServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderTaxServiceTest {
  private OrderTaxServiceImpl orderTaxService;

  @BeforeEach
  public void setup() {
    orderTaxService = Ioc.getInstance().get(IocServices.ORDER_TAX_SERVICE_INSTANCE);
  }

  @Test()
  public void findById_ShouldThrowIdNullPointerException() {
    Assertions.assertThrows(IdNullPointerException.class, () -> orderTaxService.findById(null));
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException() {
    Assertions.assertThrows(EntityNotFoundException.class, () -> orderTaxService.findById(10L));
  }

  @Test
  public void findById_ShouldReturnOptionalOfOrderTax() {
    var orderTax = OrderTaxServiceFixtures.buildOrderTax();
    var newOrderTax = orderTaxService.insert(orderTax);
    Assertions.assertEquals(newOrderTax, orderTaxService.findById(orderTax.getId()).get());
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var orderTax = OrderTaxServiceFixtures.buildOrderTax();
    var newOrderTax = orderTaxService.insert(orderTax);
    orderTaxService.delete(newOrderTax.getId());
    Assertions.assertThrows(
        EntityNotFoundException.class, () -> orderTaxService.findById(newOrderTax.getId()));
  }

  @Test()
  public void update_ShouldThrowIdNullPointerException() {
    var orderTax = new OrderTax();
    Assertions.assertThrows(IdNullPointerException.class, () -> orderTaxService.update(orderTax));
  }

  @Test()
  public void update_ShouldThrowNullEntityException() {
    Assertions.assertThrows(NullEntityException.class, () -> orderTaxService.update(null));
  }

  @Test()
  public void update_ShouldThrowEntityNotFoundException() {
    var orderTaxExample = new OrderTax();
    orderTaxExample.setId(OrderTaxFixtures.FAKEID);
    var orderTax = OrderTaxServiceFixtures.buildOrderTax(orderTaxExample);
    Assertions.assertThrows(EntityNotFoundException.class, () -> orderTaxService.update(orderTax));
  }

  @Test
  public void update_ShouldReturnUpdatedOrderTax() {
    var orderTax = OrderTaxServiceFixtures.buildOrderTax();
    var newOrderTax = orderTaxService.insert(orderTax);
    var updatedOrderTaxExample = new OrderTax();
    updatedOrderTaxExample.setOrder(null);
    var updatedOrderTax = OrderTaxServiceFixtures.buildOrderTax(updatedOrderTaxExample);
    updatedOrderTax.setId(orderTax.getId());
    Assertions.assertEquals(newOrderTax, orderTaxService.update(updatedOrderTax));
  }

  @Test
  public void insert_ShouldInsertOrderTax() {
    var orderTax = OrderTaxServiceFixtures.buildOrderTax();
    Assertions.assertEquals(orderTax, orderTaxService.insert(orderTax));
  }

  @Test()
  public void delete_ShouldThrowIdNullPointerException() {
    var orderTax = new OrderTax();
    Assertions.assertThrows(
        IdNullPointerException.class, () -> orderTaxService.delete(orderTax.getId()));
  }

  @Test
  public void delete_ShouldDeleteOrderTax() {
    var orderTax = OrderTaxServiceFixtures.buildOrderTax();
    var newOrderTax = orderTaxService.insert(orderTax);
    Assertions.assertEquals(newOrderTax, orderTaxService.delete(orderTax.getId()));
  }

  @Test
  public void findAll_ShouldReturnListOfOrderTaxs() {
    var orderTaxs = OrderTaxServiceFixtures.buildOrderTaxs(3);
    orderTaxs.stream().forEach(orderTaxService::insert);
    Assertions.assertEquals(orderTaxs, orderTaxService.findAll());
  }
}
