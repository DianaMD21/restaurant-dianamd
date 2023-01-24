package com.diana.restaurant.services.orderDetailTax;

import static org.junit.Assert.assertEquals;

import com.diana.restaurant.entities.OrderDetailTax;
import com.diana.restaurant.enums.IocServices;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.exceptions.services.IdNullPointerException;
import com.diana.restaurant.exceptions.services.NullEntityException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.services.implementation.OrderDetailTaxServiceImpl;
import com.diana.restaurant.util.fixtures.OrderDetailServiceFixtures;
import com.diana.restaurant.util.fixtures.OrderDetailTaxFixtures;
import com.diana.restaurant.util.fixtures.OrderDetailTaxServiceFixtures;
import org.junit.Before;
import org.junit.Test;

public class OrderDetailTaxServiceTest {
  private OrderDetailTaxServiceImpl orderDetailTaxService;

  @Before
  public void setup() {
    orderDetailTaxService = Ioc.getInstance().get(IocServices.ORDER_DETAIL_TAX_SERVICE_INSTANCE);
  }

  @Test(expected = IdNullPointerException.class)
  public void findById_ShouldThrowIdNullPointerException() {
    orderDetailTaxService.findById(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException() {
    orderDetailTaxService.findById(10L);
  }

  @Test
  public void findById_ShouldReturnOptionalOfOrderDetailTax() {
    var orderDetailTax = OrderDetailTaxServiceFixtures.buildOrderDetailTax();
    var newOrderDetailTax = orderDetailTaxService.insert(orderDetailTax);
    assertEquals(newOrderDetailTax, orderDetailTaxService.findById(orderDetailTax.getId()).get());
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var orderDetailTax = OrderDetailTaxServiceFixtures.buildOrderDetailTax();
    var newOrderDetailTax = orderDetailTaxService.insert(orderDetailTax);
    orderDetailTaxService.delete(newOrderDetailTax.getId());
    orderDetailTaxService.findById(newOrderDetailTax.getId());
  }

  @Test(expected = IdNullPointerException.class)
  public void update_ShouldThrowIdNullPointerException() {
    var orderDetailTax = new OrderDetailTax();
    orderDetailTaxService.update(orderDetailTax);
  }

  @Test(expected = NullEntityException.class)
  public void update_ShouldThrowNullEntityException() {
    orderDetailTaxService.update(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void update_ShouldThrowEntityNotFoundException() {
    var orderDetailTaxExample = new OrderDetailTax();
    orderDetailTaxExample.setId(OrderDetailTaxFixtures.FAKEID);
    var orderDetailTax = OrderDetailTaxServiceFixtures.buildOrderDetailTax(orderDetailTaxExample);
    orderDetailTaxService.update(orderDetailTax);
  }

  @Test
  public void update_ShouldReturnUpdatedOrderDetailTax() {
    var orderDetailTax = OrderDetailTaxServiceFixtures.buildOrderDetailTax();
    var newOrderDetailTax = orderDetailTaxService.insert(orderDetailTax);
    var updatedOrderDetailTaxExample = new OrderDetailTax();
    var newOrderDetail = OrderDetailServiceFixtures.buildOrderDetail();
    updatedOrderDetailTaxExample.setOrderDetail(newOrderDetail);
    var updatedOrderDetailTax =
        OrderDetailTaxServiceFixtures.buildOrderDetailTax(updatedOrderDetailTaxExample);
    updatedOrderDetailTax.setId(orderDetailTax.getId());
    assertEquals(newOrderDetailTax, orderDetailTaxService.update(updatedOrderDetailTax));
  }

  @Test
  public void insert_ShouldInsertOrderDetailTax() {
    var orderDetailTax = OrderDetailTaxServiceFixtures.buildOrderDetailTax();
    assertEquals(orderDetailTax, orderDetailTaxService.insert(orderDetailTax));
  }

  @Test(expected = IdNullPointerException.class)
  public void delete_ShouldThrowIdNullPointerException() {
    var orderDetailTax = new OrderDetailTax();
    orderDetailTaxService.delete(orderDetailTax.getId());
  }

  @Test
  public void delete_ShouldDeleteOrderDetailTax() {
    var orderDetailTax = OrderDetailTaxServiceFixtures.buildOrderDetailTax();
    var newOrderDetailTax = orderDetailTaxService.insert(orderDetailTax);
    assertEquals(newOrderDetailTax, orderDetailTaxService.delete(orderDetailTax.getId()));
  }

  @Test
  public void findAll_ShouldReturnListOfOrderDetailTaxs() {
    var orderDetailTaxs = OrderDetailTaxServiceFixtures.buildOrderDetailTaxs(3);
    orderDetailTaxs.stream().forEach(orderDetailTaxService::insert);
    assertEquals(orderDetailTaxs, orderDetailTaxService.findAll());
  }
}
