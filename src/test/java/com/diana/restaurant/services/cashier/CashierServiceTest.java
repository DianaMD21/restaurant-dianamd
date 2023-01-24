package com.diana.restaurant.services.cashier;

import static org.junit.Assert.assertEquals;

import com.diana.restaurant.entities.Cashier;
import com.diana.restaurant.enums.IocServices;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.exceptions.services.IdNullPointerException;
import com.diana.restaurant.exceptions.services.NullEntityException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.services.implementation.CashierServiceImpl;
import com.diana.restaurant.util.fixtures.CashierServiceFixtures;
import com.diana.restaurant.util.fixtures.UserFixtures;
import org.junit.Before;
import org.junit.Test;

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
    assertEquals(newCashier, cashierService.findById(cashier.getId()).get());
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var cashier = CashierServiceFixtures.buildCashier();
    var newCashier = cashierService.insert(cashier);
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
    cashierExample.setId(UserFixtures.FAKEID);
    var cashier = CashierServiceFixtures.buildCashier(cashierExample);
    cashierService.update(cashier);
  }

  @Test
  public void update_ShouldReturnUpdatedCashier() {
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
  public void insert_ShouldInsertCashier() {
    var cashier = CashierServiceFixtures.buildCashier();
    assertEquals(cashier, cashierService.insert(cashier));
  }

  @Test(expected = IdNullPointerException.class)
  public void delete_ShouldThrowIdNullPointerException() {
    var cashier = new Cashier();
    cashierService.delete(cashier.getId());
  }

  @Test
  public void delete_ShouldDeleteCashier() {
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
