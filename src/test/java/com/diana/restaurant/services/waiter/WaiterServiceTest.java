package com.diana.restaurant.services.waiter;

import com.diana.restaurant.entities.Waiter;
import com.diana.restaurant.enums.IocServices;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.exceptions.services.IdNullPointerException;
import com.diana.restaurant.exceptions.services.NullEntityException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.services.implementation.WaiterServiceImpl;
import com.diana.restaurant.util.fixtures.entities.UserFixtures;
import com.diana.restaurant.util.fixtures.services.WaiterServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WaiterServiceTest {
  private WaiterServiceImpl waiterService;

  @BeforeEach
  public void setup() {
    waiterService = Ioc.getInstance().get(IocServices.WAITER_SERVICE_INSTANCE);
  }

  @Test()
  public void findById_ShouldThrowIdNullPointerException() {
    Assertions.assertThrows(IdNullPointerException.class, () -> waiterService.findById(null));
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException() {
    Assertions.assertThrows(EntityNotFoundException.class, () -> waiterService.findById(10L));
  }

  @Test
  public void findById_ShouldReturnOptionalOfWaiter() {
    var waiter = WaiterServiceFixtures.buildWaiter();
    var newWaiter = waiterService.insert(waiter);
    Assertions.assertEquals(newWaiter, waiterService.findById(waiter.getId()).get());
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var waiter = WaiterServiceFixtures.buildWaiter();
    var newWaiter = waiterService.insert(waiter);
    waiterService.delete(newWaiter.getId());
    Assertions.assertThrows(
        EntityNotFoundException.class, () -> waiterService.findById(newWaiter.getId()));
  }

  @Test()
  public void update_ShouldThrowIdNullPointerException() {
    var waiter = new Waiter();
    Assertions.assertThrows(IdNullPointerException.class, () -> waiterService.update(waiter));
  }

  @Test()
  public void update_ShouldThrowNullEntityException() {
    Assertions.assertThrows(NullEntityException.class, () -> waiterService.update(null));
  }

  @Test()
  public void update_ShouldThrowEntityNotFoundException() {
    var waiterExample = new Waiter();
    waiterExample.setId(UserFixtures.FAKEID);
    var waiter = WaiterServiceFixtures.buildWaiter(waiterExample);
    Assertions.assertThrows(EntityNotFoundException.class, () -> waiterService.update(waiter));
  }

  @Test
  public void update_ShouldReturnUpdatedWaiter() {
    var waiter = WaiterServiceFixtures.buildWaiter();
    var updatedWaiterExample = new Waiter();
    updatedWaiterExample.setName("Diana");
    updatedWaiterExample.setUsername("dxmd");
    var newWaiter = waiterService.insert(waiter);
    var updatedWaiter = WaiterServiceFixtures.buildWaiter(updatedWaiterExample);
    updatedWaiter.setId(waiter.getId());
    Assertions.assertEquals(newWaiter, waiterService.update(updatedWaiter));
  }

  @Test
  public void insert_ShouldInsertWaiter() {
    var waiter = WaiterServiceFixtures.buildWaiter();
    Assertions.assertEquals(waiter, waiterService.insert(waiter));
  }

  @Test()
  public void delete_ShouldThrowIdNullPointerException() {
    var waiter = new Waiter();
    Assertions.assertThrows(
        IdNullPointerException.class, () -> waiterService.delete(waiter.getId()));
  }

  @Test
  public void delete_ShouldDeleteWaiter() {
    var waiter = WaiterServiceFixtures.buildWaiter();
    var newWaiter = waiterService.insert(waiter);
    Assertions.assertEquals(newWaiter, waiterService.delete(waiter.getId()));
  }

  @Test
  public void findAll_ShouldReturnListOfWaiters() {
    var waiters = WaiterServiceFixtures.buildWaiters(3);
    waiters.stream().forEach(waiterService::insert);
    Assertions.assertEquals(waiters, waiterService.findAll());
  }
}
