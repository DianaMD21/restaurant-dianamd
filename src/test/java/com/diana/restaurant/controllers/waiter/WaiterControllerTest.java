package com.diana.restaurant.controllers.waiter;

import com.diana.restaurant.controllers.WaiterController;
import com.diana.restaurant.services.interfaces.WaiterService;
import com.diana.restaurant.util.fixtures.services.WaiterServiceFixtures;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class WaiterControllerTest {
  private WaiterController waiterController;
  private WaiterService waiterServiceMock;

  @BeforeEach
  public void setup() {
    waiterServiceMock = Mockito.mock(WaiterService.class);
    waiterController = new WaiterController(waiterServiceMock);
  }

  @Test
  public void getAll_ShouldReturnListOfWaiters() {
    var waiters = WaiterServiceFixtures.buildWaiters(3);
    Mockito.when(waiterServiceMock.getAll()).thenReturn(waiters);
    var waitersReturned = waiterController.getAll();
    Assertions.assertEquals(waiters, waitersReturned);
    Mockito.verify(waiterServiceMock, Mockito.times(1)).getAll();
  }

  @Test
  public void add_ShouldAddWaiter() {
    var waiter = WaiterServiceFixtures.buildWaiter();
    var expectedWaiter = WaiterServiceFixtures.buildWaiter(1L);
    Mockito.when(waiterServiceMock.add(Mockito.eq(waiter))).thenReturn(expectedWaiter);
    var waiterAdded = waiterController.add(waiter);
    Assertions.assertEquals(expectedWaiter, waiterAdded);
    Mockito.verify(waiterServiceMock, Mockito.times(1)).add(Mockito.eq(waiter));
  }

  @Test()
  public void delete_ShouldDeleteWaiter() {
    var waiter = WaiterServiceFixtures.buildWaiter(1L);
    Mockito.when(waiterServiceMock.delete(waiter.getId())).thenReturn(waiter);
    var waiterDeleted = waiterController.deleteById(waiter.getId());
    Assertions.assertEquals(waiter, waiterDeleted);
    var waiterToTestVerify = WaiterServiceFixtures.buildWaiter(waiter);
    Mockito.verify(waiterServiceMock, Mockito.times(1))
        .delete(Mockito.eq(waiterToTestVerify.getId()));
  }

  @Test
  public void findById_ShouldReturnFoundWaiter() {
    var waiter = WaiterServiceFixtures.buildWaiter(1L);
    Mockito.when(waiterServiceMock.findById(waiter.getId())).thenReturn(Optional.of(waiter));
    var waiterFound = waiterController.findById(waiter.getId());
    Assertions.assertEquals(waiter, waiterFound);
    var waiterToTestVerify = WaiterServiceFixtures.buildWaiter(waiter);
    Mockito.verify(waiterServiceMock, Mockito.times(1))
        .findById(Mockito.eq(waiterToTestVerify.getId()));
  }

  @Test
  public void update_ShouldUpdateWaiter() {
    var waiterToUpdate = WaiterServiceFixtures.buildWaiter(1L);
    waiterToUpdate.setName("Testing Waiter fake");
    Mockito.when(waiterServiceMock.update(waiterToUpdate)).thenReturn(waiterToUpdate);
    var waiterToTestVerify = WaiterServiceFixtures.buildWaiter(waiterToUpdate);
    var waiterUpdated = waiterController.update(waiterToUpdate);
    Assertions.assertEquals(waiterToUpdate, waiterUpdated);
    Mockito.verify(waiterServiceMock, Mockito.times(1)).update(Mockito.eq(waiterToTestVerify));
  }
}
