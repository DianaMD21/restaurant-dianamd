package com.diana.restaurant.controllers.cashier;

import com.diana.restaurant.controllers.CashierController;
import com.diana.restaurant.services.interfaces.CashierService;
import com.diana.restaurant.util.fixtures.services.CashierServiceFixtures;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CashierControllerTest {
  private CashierController cashierController;
  private CashierService cashierServiceMock;

  @BeforeEach
  public void setup() {
    cashierServiceMock = Mockito.mock(CashierService.class);
    cashierController = new CashierController(cashierServiceMock);
  }

  @Test
  public void getAll_ShouldReturnListOfCashiers() {
    var cashiers = CashierServiceFixtures.buildCashiers(3);
    Mockito.when(cashierServiceMock.getAll()).thenReturn(cashiers);
    var cashiersReturned = cashierController.getAll();
    Assertions.assertEquals(cashiers, cashiersReturned);
    Mockito.verify(cashierServiceMock, Mockito.times(1)).getAll();
  }

  @Test
  public void add_ShouldAddCashier() {
    var cashier = CashierServiceFixtures.buildCashier();
    var expectedCashier = CashierServiceFixtures.buildCashier(1L);
    Mockito.when(cashierServiceMock.add(Mockito.eq(cashier))).thenReturn(expectedCashier);
    var cashierAdded = cashierController.add(cashier);
    Assertions.assertEquals(expectedCashier, cashierAdded);
    Mockito.verify(cashierServiceMock, Mockito.times(1)).add(Mockito.eq(cashier));
  }

  @Test()
  public void delete_ShouldDeleteCashier() {
    var cashier = CashierServiceFixtures.buildCashier(1L);
    Mockito.when(cashierServiceMock.delete(cashier.getId())).thenReturn(cashier);
    var cashierDeleted = cashierController.deleteById(cashier.getId());
    Assertions.assertEquals(cashier, cashierDeleted);
    var cashierToTestVerify = CashierServiceFixtures.buildCashier(cashier);
    Mockito.verify(cashierServiceMock, Mockito.times(1))
        .delete(Mockito.eq(cashierToTestVerify.getId()));
  }

  @Test
  public void findById_ShouldReturnFoundCashier() {
    var cashier = CashierServiceFixtures.buildCashier(1L);
    Mockito.when(cashierServiceMock.findById(cashier.getId())).thenReturn(Optional.of(cashier));
    var cashierFound = cashierController.findById(cashier.getId());
    Assertions.assertEquals(cashier, cashierFound);
    var cashierToTestVerify = CashierServiceFixtures.buildCashier(cashier);
    Mockito.verify(cashierServiceMock, Mockito.times(1))
        .findById(Mockito.eq(cashierToTestVerify.getId()));
  }

  @Test
  public void update_ShouldUpdateCashier() {
    var cashierToUpdate = CashierServiceFixtures.buildCashier(1L);
    cashierToUpdate.setName("Diana");
    Mockito.when(cashierServiceMock.update(cashierToUpdate)).thenReturn(cashierToUpdate);
    var cashierToTestVerify = CashierServiceFixtures.buildCashier(cashierToUpdate);
    var cashierUpdated = cashierController.update(cashierToUpdate);
    Assertions.assertEquals(cashierToUpdate, cashierUpdated);
    Mockito.verify(cashierServiceMock, Mockito.times(1)).update(Mockito.eq(cashierToTestVerify));
  }
}
