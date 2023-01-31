package com.diana.restaurant.services.orderDetailTax;

import com.diana.restaurant.entities.OrderDetailTax;
import com.diana.restaurant.enums.IocServices;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.exceptions.services.IdNullPointerException;
import com.diana.restaurant.exceptions.services.NullEntityException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.services.implementation.OrderDetailTaxServiceImpl;
import com.diana.restaurant.util.fixtures.entities.OrderDetailTaxFixtures;
import com.diana.restaurant.util.fixtures.services.OrderDetailServiceFixtures;
import com.diana.restaurant.util.fixtures.services.OrderDetailTaxServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderDetailTaxServiceTest {
  private OrderDetailTaxServiceImpl orderDetailTaxService;

  @BeforeEach
  public void setup() {
    orderDetailTaxService = Ioc.getInstance().get(IocServices.ORDER_DETAIL_TAX_SERVICE_INSTANCE);
  }

  @Test()
  public void findById_ShouldThrowIdNullPointerException() {
    Assertions.assertThrows(
        IdNullPointerException.class, () -> orderDetailTaxService.findById(null));
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException() {
    Assertions.assertThrows(
        EntityNotFoundException.class, () -> orderDetailTaxService.findById(10L));
  }

  @Test
  public void findById_ShouldReturnOptionalOfOrderDetailTax() {
    var orderDetailTax = OrderDetailTaxServiceFixtures.buildOrderDetailTax();
    var newOrderDetailTax = orderDetailTaxService.add(orderDetailTax);
    Assertions.assertEquals(
        newOrderDetailTax, orderDetailTaxService.findById(orderDetailTax.getId()).get());
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var orderDetailTax = OrderDetailTaxServiceFixtures.buildOrderDetailTax();
    var newOrderDetailTax = orderDetailTaxService.add(orderDetailTax);
    orderDetailTaxService.delete(newOrderDetailTax.getId());
    Assertions.assertThrows(
        EntityNotFoundException.class,
        () -> orderDetailTaxService.findById(newOrderDetailTax.getId()));
  }

  @Test()
  public void update_ShouldThrowIdNullPointerException() {
    var orderDetailTax = new OrderDetailTax();
    Assertions.assertThrows(
        IdNullPointerException.class, () -> orderDetailTaxService.update(orderDetailTax));
  }

  @Test()
  public void update_ShouldThrowNullEntityException() {
    Assertions.assertThrows(NullEntityException.class, () -> orderDetailTaxService.update(null));
  }

  @Test()
  public void update_ShouldThrowEntityNotFoundException() {
    var orderDetailTaxExample = new OrderDetailTax();
    orderDetailTaxExample.setId(OrderDetailTaxFixtures.FAKEID);
    var orderDetailTax = OrderDetailTaxServiceFixtures.buildOrderDetailTax(orderDetailTaxExample);
    Assertions.assertThrows(
        EntityNotFoundException.class, () -> orderDetailTaxService.update(orderDetailTax));
  }

  @Test
  public void update_ShouldReturnUpdatedOrderDetailTax() {
    var orderDetailTax = OrderDetailTaxServiceFixtures.buildOrderDetailTax();
    var newOrderDetailTax = orderDetailTaxService.add(orderDetailTax);
    var updatedOrderDetailTaxExample = new OrderDetailTax();
    var newOrderDetail = OrderDetailServiceFixtures.buildOrderDetail();
    updatedOrderDetailTaxExample.setOrderDetail(newOrderDetail);
    var updatedOrderDetailTax =
        OrderDetailTaxServiceFixtures.buildOrderDetailTax(updatedOrderDetailTaxExample);
    updatedOrderDetailTax.setId(orderDetailTax.getId());
    Assertions.assertEquals(newOrderDetailTax, orderDetailTaxService.update(updatedOrderDetailTax));
  }

  @Test
  public void add_ShouldAddOrderDetailTax() {
    var orderDetailTax = OrderDetailTaxServiceFixtures.buildOrderDetailTax();
    Assertions.assertEquals(orderDetailTax, orderDetailTaxService.add(orderDetailTax));
  }

  @Test()
  public void delete_ShouldThrowIdNullPointerException() {
    var orderDetailTax = new OrderDetailTax();
    Assertions.assertThrows(
        IdNullPointerException.class, () -> orderDetailTaxService.delete(orderDetailTax.getId()));
  }

  @Test
  public void delete_ShouldDeleteOrderDetailTax() {
    var orderDetailTax = OrderDetailTaxServiceFixtures.buildOrderDetailTax();
    var newOrderDetailTax = orderDetailTaxService.add(orderDetailTax);
    Assertions.assertEquals(
        newOrderDetailTax, orderDetailTaxService.delete(orderDetailTax.getId()));
  }

  @Test
  public void getAll_ShouldReturnListOfOrderDetailTaxs() {
    var orderDetailTaxs = OrderDetailTaxServiceFixtures.buildOrderDetailTaxs(3);
    orderDetailTaxs.stream().forEach(orderDetailTaxService::add);
    Assertions.assertEquals(orderDetailTaxs, orderDetailTaxService.getAll());
  }
}
