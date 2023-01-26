package com.diana.restaurant.controllers.cashier;

import com.diana.restaurant.controllers.CashierController;
import com.diana.restaurant.services.implementation.CashierServiceImpl;
import com.diana.restaurant.services.interfaces.CashierService;
import com.diana.restaurant.util.fixtures.services.CashierServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CashierControllerIntegrationTest {
  private CashierController cashierController;
  private CashierService cashierServiceSpy;

  @BeforeEach
  public void setUp() {
    CashierService cashierService = new CashierServiceImpl();
    cashierServiceSpy = Mockito.spy(cashierService);
    cashierController = new CashierController(cashierServiceSpy);
  }

  @Test
  public void findById_ShouldReturnFoundCashier() {
    var cashier = CashierServiceFixtures.buildCashier();
    cashierController.add(cashier);
    var cashierFound = cashierController.findById(cashier.getId());
    Assertions.assertEquals(cashierFound, cashier);
    Mockito.verify(cashierServiceSpy, Mockito.times(1)).findById(Mockito.eq(cashier.getId()));
  }

  @Test
  public void getAll_ShouldReturnAllCashiers() {
    var cashiers = CashierServiceFixtures.buildCashiers(3);
    cashiers.stream().forEach(cashierController::add);
    var cashiersFound = cashierController.getAll();
    Assertions.assertEquals(cashiers, cashiersFound);
  }

  @Test
  public void add_ShouldAddCashier() {
    var cashier = CashierServiceFixtures.buildCashier();
    var cashierAdded = cashierController.add(cashier);
    Assertions.assertEquals(cashierAdded, cashier);
    Mockito.verify(cashierServiceSpy, Mockito.times(1)).add(Mockito.eq(cashier));
  }

  @Test
  public void delete_ShouldDeleteCashier() {
    var cashiers = CashierServiceFixtures.buildCashiers(3);
    cashiers.stream().forEach(cashierController::add);
    var deletedCashier = cashierController.deleteById(cashiers.get(0).getId());
    var deletedCashierToVerify = CashierServiceFixtures.buildCashier(cashiers.get(0));
    Assertions.assertEquals(deletedCashier, cashiers.get(0));
    Mockito.verify(cashierServiceSpy, Mockito.times(1))
        .delete(Mockito.eq(deletedCashierToVerify.getId()));
  }

  @Test
  public void update_ShouldUpdateCashier() {
    var cashierToUpdate = CashierServiceFixtures.buildCashier();
    cashierController.add(cashierToUpdate);
    cashierToUpdate.setName("DIANA MARIA");
    var cashierToTestVerify = CashierServiceFixtures.buildCashier(cashierToUpdate);
    var updatedCashier = cashierController.update(cashierToUpdate);
    Assertions.assertEquals(updatedCashier, cashierToUpdate);
    Mockito.verify(cashierServiceSpy, Mockito.times(1)).update(Mockito.eq(cashierToTestVerify));
  }
}
