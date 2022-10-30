package cashier;

import static org.junit.Assert.assertEquals;

import entities.Cashier;
import enums.IocServices;
import exceptions.services.EntityNotFoundException;
import exceptions.services.IdNullPointerException;
import exceptions.services.NullEntityException;
import ioc.Ioc;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import services.implementation.CashierServiceImpl;
import util.fixtures.CashierServiceFixtures;

public class CashierServiceTest {
  private CashierServiceImpl cashierService;

  @Before
  public void setup() {
    cashierService = Ioc.getInstance().get(IocServices.CASHIER_SERVICE_INSTANCE);
  }

  @Test(expected = IdNullPointerException.class)
  public void findById_ShouldThrowIdNullPointerException() {
    cashierService.findById(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException() {
    cashierService.findById(10L);
  }

  @Test
  public void findById_ShouldReturnOptionalOfCashier() {
    var cashier = CashierServiceFixtures.buildCashier();
    var newCashier = cashierService.insert(cashier);
    assertEquals(newCashier, cashierService.findById(cashier.getId()));
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException_IfStatusIsDeleted() {
    var cashier = CashierServiceFixtures.buildCashier();
    var newCashier = cashierService.insert(cashier).get();
    cashierService.delete(newCashier.getId());
    cashierService.findById(newCashier.getId());
  }

  @Test(expected = IdNullPointerException.class)
  public void update_ShouldThrowIdNullPointerException() {
    var cashier = new Cashier();
    cashierService.update(cashier);
  }

  @Test(expected = NullEntityException.class)
  public void update_ShouldThrowNullEntityException() {
    cashierService.update(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void update_ShouldThrowEntityNotFoundException() {
    var cashierExample = new Cashier();
    cashierExample.setId(CashierServiceFixtures.FAKEID);
    var cashier = CashierServiceFixtures.buildCashier(cashierExample);
    cashierService.update(cashier);
  }

  @Test
  public void update_ShouldReturnUpdatedObject() {
    var cashier = CashierServiceFixtures.buildCashier();
    var newCashier = cashierService.insert(cashier);
    var updatedCashierExample = new Cashier();
    updatedCashierExample.setName("Diana");
    updatedCashierExample.setUsername("dxmd");
    var updatedCashier = CashierServiceFixtures.buildCashier(updatedCashierExample);
    updatedCashier.setId(cashier.getId());
    assertEquals(newCashier, cashierService.update(updatedCashier));
  }

  @Test
  public void insert_ShouldReturnOptionalOfCashier() {
    var cashier = CashierServiceFixtures.buildCashier();
    assertEquals(Optional.of(cashier), cashierService.insert(cashier));
  }

  @Test(expected = IdNullPointerException.class)
  public void delete_ShouldThrowIdNullPointerException() {
    var cashier = new Cashier();
    cashierService.delete(cashier.getId());
  }

  @Test
  public void delete_ShouldReturnDeletedValue() {
    var cashier = CashierServiceFixtures.buildCashier();
    var newCashier = cashierService.insert(cashier);
    assertEquals(newCashier, cashierService.delete(cashier.getId()));
  }

  @Test
  public void findAll_ShouldReturnListOfCashiers() {
    var cashiers = CashierServiceFixtures.buildCashiers(3);
    cashiers.stream().forEach(cashierService::insert);
    assertEquals(cashiers, cashierService.findAll());
  }
}
