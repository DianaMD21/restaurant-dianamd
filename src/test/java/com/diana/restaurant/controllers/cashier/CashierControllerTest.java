package com.diana.restaurant.controllers.cashier;

import static org.junit.Assert.assertEquals;

import com.diana.restaurant.controllers.CashierController;
import com.diana.restaurant.entities.Cashier;
import com.diana.restaurant.enums.IocControllers;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.util.fixtures.services.CashierServiceFixtures;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CashierControllerTest {
  private CashierController cashierController;

  @Before
  public void setup() {
    cashierController = Ioc.getInstance().get(IocControllers.CASHIER_CONTROLLER);
  }

  @Test
  public void getAll_ShouldReturnListOfCashiers() {
    var cashiers = CashierServiceFixtures.buildCashiers(3);
    cashiers.stream().forEach(cashierController::add);
    assertEquals(cashiers, cashierController.getAll());
  }

  @Test
  public void add_ShouldInsertCashier() {
    var cashier = CashierServiceFixtures.buildCashier();
    Assert.assertEquals(cashier, cashierController.add(cashier));
  }

  @Test(expected = EntityNotFoundException.class)
  public void delete_ShouldDeleteCashier() {
    var cashier = CashierServiceFixtures.buildCashier();
    var newCashier = cashierController.add(cashier);
    cashierController.deleteById(cashier.getId());
    cashierController.findById(newCashier.getId());
  }

  @Test
  public void findById_ShouldReturnFoundCashier() {
    var cashier = CashierServiceFixtures.buildCashier();
    var newCashier = cashierController.add(cashier);
    assertEquals(newCashier, cashierController.findById(cashier.getId()));
  }

  @Test
  public void update_ShouldUpdateCashier() {
    var cashier = CashierServiceFixtures.buildCashier();
    var updatedCashierExample = new Cashier();
    updatedCashierExample.setName("Diana");
    updatedCashierExample.setUsername("dxmd");
    var newCashier = cashierController.add(cashier);
    var updatedCashier = CashierServiceFixtures.buildCashier(updatedCashierExample);
    updatedCashier.setId(cashier.getId());
    assertEquals(newCashier, cashierController.update(updatedCashier));
  }
}
