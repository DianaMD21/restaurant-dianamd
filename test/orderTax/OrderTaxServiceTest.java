package orderTax;

import static org.junit.Assert.assertEquals;

import entities.OrderTax;
import enums.IocServices;
import exceptions.services.EntityNotFoundException;
import exceptions.services.IdNullPointerException;
import exceptions.services.NullEntityException;
import ioc.Ioc;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import services.implementation.OrderTaxServiceImpl;
import util.fixtures.OrderTaxFixtures;
import util.fixtures.OrderTaxServiceFixtures;

public class OrderTaxServiceTest {
  private OrderTaxServiceImpl orderTaxService;

  @Before
  public void setup() {
    orderTaxService = Ioc.getInstance().get(IocServices.ORDER_TAX_SERVICE_INSTANCE);
  }

  @Test(expected = IdNullPointerException.class)
  public void findById_ShouldThrowIdNullPointerException() {
    orderTaxService.findById(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException() {
    orderTaxService.findById(10L);
  }

  @Test
  public void findById_ShouldReturnOptionalOfOrderTax() {
    var orderTax = OrderTaxServiceFixtures.buildOrderTax();
    var newOrderTax = orderTaxService.insert(orderTax);
    assertEquals(newOrderTax, orderTaxService.findById(orderTax.getId()).get());
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var orderTax = OrderTaxServiceFixtures.buildOrderTax();
    var newOrderTax = orderTaxService.insert(orderTax);
    orderTaxService.delete(newOrderTax.getId());
    orderTaxService.findById(newOrderTax.getId());
  }

  @Test(expected = IdNullPointerException.class)
  public void update_ShouldThrowIdNullPointerException() {
    var orderTax = new OrderTax();
    orderTaxService.update(orderTax);
  }

  @Test(expected = NullEntityException.class)
  public void update_ShouldThrowNullEntityException() {
    orderTaxService.update(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void update_ShouldThrowEntityNotFoundException() {
    var orderTaxExample = new OrderTax();
    orderTaxExample.setId(OrderTaxFixtures.FAKEID);
    var orderTax = OrderTaxServiceFixtures.buildOrderTax(orderTaxExample);
    orderTaxService.update(orderTax);
  }

  @Test
  public void update_ShouldReturnUpdatedObject() {
    var orderTax = OrderTaxServiceFixtures.buildOrderTax();
    var newOrderTax = orderTaxService.insert(orderTax);
    var updatedOrderTaxExample = new OrderTax();
    updatedOrderTaxExample.setOrder(null);
    var updatedOrderTax = OrderTaxServiceFixtures.buildOrderTax(updatedOrderTaxExample);
    updatedOrderTax.setId(orderTax.getId());
    assertEquals(newOrderTax, orderTaxService.update(updatedOrderTax));
  }

  @Test
  public void insert_ShouldReturnOrderTax() {
    var orderTax = OrderTaxServiceFixtures.buildOrderTax();
    assertEquals(orderTax, orderTaxService.insert(orderTax));
  }

  @Test(expected = IdNullPointerException.class)
  public void delete_ShouldThrowIdNullPointerException() {
    var orderTax = new OrderTax();
    orderTaxService.delete(orderTax.getId());
  }

  @Test
  public void delete_ShouldReturnDeletedValue() {
    var orderTax = OrderTaxServiceFixtures.buildOrderTax();
    var newOrderTax = orderTaxService.insert(orderTax);
    assertEquals(newOrderTax, orderTaxService.delete(orderTax.getId()));
  }

  @Test
  public void findAll_ShouldReturnListOfOrderTaxs() {
    var orderTaxs = OrderTaxServiceFixtures.buildOrderTaxs(3);
    orderTaxs.stream().forEach(orderTaxService::insert);
    assertEquals(orderTaxs, orderTaxService.findAll());
  }
}
