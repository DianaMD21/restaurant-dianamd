package com.diana.restaurant.controllers.waiter;

import com.diana.restaurant.controllers.WaiterController;
import com.diana.restaurant.services.implementation.WaiterServiceImpl;
import com.diana.restaurant.services.interfaces.WaiterService;
import com.diana.restaurant.util.fixtures.services.WaiterServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class WaiterControllerIntegrationTest {
  private WaiterController waiterController;
  private WaiterService waiterServiceSpy;

  @BeforeEach
  public void setUp() {
    WaiterService waiterService = new WaiterServiceImpl();
    waiterServiceSpy = Mockito.spy(waiterService);
    waiterController = new WaiterController(waiterServiceSpy);
  }

  @Test
  public void findById_ShouldReturnFoundWaiter() {
    var waiter = WaiterServiceFixtures.buildWaiter();
    waiterController.add(waiter);
    var waiterFound = waiterController.findById(waiter.getId());
    Assertions.assertEquals(waiterFound, waiter);
    Mockito.verify(waiterServiceSpy, Mockito.times(1)).findById(Mockito.eq(waiter.getId()));
  }

  @Test
  public void getAll_ShouldReturnAllWaiters() {
    var waiters = WaiterServiceFixtures.buildWaiters(3);
    waiters.stream().forEach(waiterController::add);
    var waitersFound = waiterController.getAll();
    Assertions.assertEquals(waiters, waitersFound);
  }

  @Test
  public void add_ShouldAddWaiter() {
    var waiter = WaiterServiceFixtures.buildWaiter();
    var waiterAdded = waiterController.add(waiter);
    Assertions.assertEquals(waiterAdded, waiter);
    Mockito.verify(waiterServiceSpy, Mockito.times(1)).add(Mockito.eq(waiter));
  }

  @Test
  public void delete_ShouldDeleteWaiter() {
    var waiters = WaiterServiceFixtures.buildWaiters(3);
    waiters.stream().forEach(waiterController::add);
    var deletedWaiter = waiterController.deleteById(waiters.get(0).getId());
    var deletedWaiterToVerify = WaiterServiceFixtures.buildWaiter(waiters.get(0));
    Assertions.assertEquals(deletedWaiter, waiters.get(0));
    Mockito.verify(waiterServiceSpy, Mockito.times(1))
        .delete(Mockito.eq(deletedWaiterToVerify.getId()));
  }

  @Test
  public void update_ShouldUpdateWaiter() {
    var waiterToUpdate = WaiterServiceFixtures.buildWaiter();
    waiterController.add(waiterToUpdate);
    waiterToUpdate.setName("Testing Waiter fake");
    var waiterToTestVerify = WaiterServiceFixtures.buildWaiter(waiterToUpdate);
    var updatedWaiter = waiterController.update(waiterToUpdate);
    Assertions.assertEquals(updatedWaiter, waiterToUpdate);
    Mockito.verify(waiterServiceSpy, Mockito.times(1)).update(Mockito.eq(waiterToTestVerify));
  }
}
