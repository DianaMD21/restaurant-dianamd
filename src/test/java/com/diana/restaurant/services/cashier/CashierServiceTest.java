package com.diana.restaurant.services.cashier;

import com.diana.restaurant.entities.Cashier;
import com.diana.restaurant.enums.IocServices;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.exceptions.services.IdNullPointerException;
import com.diana.restaurant.exceptions.services.NullEntityException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.services.implementation.CashierServiceImpl;
import com.diana.restaurant.util.fixtures.entities.UserFixtures;
import com.diana.restaurant.util.fixtures.services.CashierServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CashierServiceTest {
  private CashierServiceImpl cashierService;

  @BeforeEach
  public void setup() {
    cashierService = Ioc.getInstance().get(IocServices.CASHIER_SERVICE_INSTANCE);
  }

  @Test()
  public void findById_ShouldThrowIdNullPointerException() {
    Assertions.assertThrows(IdNullPointerException.class, () -> cashierService.findById(null));
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException() {
    Assertions.assertThrows(EntityNotFoundException.class, () -> cashierService.findById(10L));
  }

  @Test
  public void findById_ShouldReturnOptionalOfCashier() {
    var cashier = CashierServiceFixtures.buildCashier();
    var newCashier = cashierService.insert(cashier);
    Assertions.assertEquals(newCashier, cashierService.findById(cashier.getId()).get());
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var cashier = CashierServiceFixtures.buildCashier();
    var newCashier = cashierService.insert(cashier);
    cashierService.delete(newCashier.getId());
    Assertions.assertThrows(
        EntityNotFoundException.class, () -> cashierService.findById(newCashier.getId()));
  }

  @Test()
  public void update_ShouldThrowIdNullPointerException() {
    var cashier = new Cashier();
    Assertions.assertThrows(IdNullPointerException.class, () -> cashierService.update(cashier));
  }

  @Test()
  public void update_ShouldThrowNullEntityException() {
    Assertions.assertThrows(NullEntityException.class, () -> cashierService.update(null));
  }

  @Test()
  public void update_ShouldThrowEntityNotFoundException() {
    var cashierExample = new Cashier();
    cashierExample.setId(UserFixtures.FAKEID);
    var cashier = CashierServiceFixtures.buildCashier(cashierExample);
    Assertions.assertThrows(EntityNotFoundException.class, () -> cashierService.update(cashier));
  }

  @Test
  public void update_ShouldReturnUpdatedCashier() {
    var cashier = CashierServiceFixtures.buildCashier();
    var updatedCashierExample = new Cashier();
    updatedCashierExample.setName("Diana");
    updatedCashierExample.setUsername("dxmd");
    var newCashier = cashierService.insert(cashier);
    var updatedCashier = CashierServiceFixtures.buildCashier(updatedCashierExample);
    updatedCashier.setId(cashier.getId());
    Assertions.assertEquals(newCashier, cashierService.update(updatedCashier));
  }

  @Test
  public void insert_ShouldInsertCashier() {
    var cashier = CashierServiceFixtures.buildCashier();
    Assertions.assertEquals(cashier, cashierService.insert(cashier));
  }

  @Test()
  public void delete_ShouldThrowIdNullPointerException() {
    var cashier = new Cashier();
    Assertions.assertThrows(
        IdNullPointerException.class, () -> cashierService.delete(cashier.getId()));
  }

  @Test
  public void delete_ShouldDeleteCashier() {
    var cashier = CashierServiceFixtures.buildCashier();
    var newCashier = cashierService.insert(cashier);
    Assertions.assertEquals(newCashier, cashierService.delete(cashier.getId()));
  }

  @Test
  public void findAll_ShouldReturnListOfCashiers() {
    var cashiers = CashierServiceFixtures.buildCashiers(3);
    cashiers.stream().forEach(cashierService::insert);
    Assertions.assertEquals(cashiers, cashierService.findAll());
  }
}
