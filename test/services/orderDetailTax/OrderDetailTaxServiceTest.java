package services.orderDetailTax;

import static org.junit.Assert.assertEquals;

import entities.OrderDetailTax;
import enums.IocServices;
import exceptions.services.EntityNotFoundException;
import exceptions.services.IdNullPointerException;
import exceptions.services.NullEntityException;
import ioc.Ioc;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import services.implementation.OrderDetailTaxServiceImpl;
import util.fixtures.OrderDetailServiceFixtures;
import util.fixtures.OrderDetailTaxFixtures;
import util.fixtures.OrderDetailTaxServiceFixtures;

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
  public void update_ShouldReturnUpdatedObject() {
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
  public void insert_ShouldReturnOrderDetailTax() {
    var orderDetailTax = OrderDetailTaxServiceFixtures.buildOrderDetailTax();
    assertEquals(orderDetailTax, orderDetailTaxService.insert(orderDetailTax));
  }

  @Test(expected = IdNullPointerException.class)
  public void delete_ShouldThrowIdNullPointerException() {
    var orderDetailTax = new OrderDetailTax();
    orderDetailTaxService.delete(orderDetailTax.getId());
  }

  @Test
  public void delete_ShouldReturnDeletedValue() {
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
