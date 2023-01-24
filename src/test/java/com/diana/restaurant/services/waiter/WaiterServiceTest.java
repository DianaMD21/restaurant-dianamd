package com.diana.restaurant.services.waiter;

import static org.junit.Assert.assertEquals;

import com.diana.restaurant.entities.Waiter;
import com.diana.restaurant.enums.IocServices;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.exceptions.services.IdNullPointerException;
import com.diana.restaurant.exceptions.services.NullEntityException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.services.implementation.WaiterServiceImpl;
import com.diana.restaurant.util.fixtures.UserFixtures;
import com.diana.restaurant.util.fixtures.WaiterServiceFixtures;
import org.junit.Before;
import org.junit.Test;

public class WaiterServiceTest {
  private WaiterServiceImpl waiterService;

  @Before
  public void setup() {
    waiterService = Ioc.getInstance().get(IocServices.WAITER_SERVICE_INSTANCE);
  }

  @Test(expected = IdNullPointerException.class)
  public void findById_ShouldThrowIdNullPointerException() {
    waiterService.findById(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException() {
    waiterService.findById(10L);
  }

  @Test
  public void findById_ShouldReturnOptionalOfWaiter() {
    var waiter = WaiterServiceFixtures.buildWaiter();
    var newWaiter = waiterService.insert(waiter);
    assertEquals(newWaiter, waiterService.findById(waiter.getId()).get());
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var waiter = WaiterServiceFixtures.buildWaiter();
    var newWaiter = waiterService.insert(waiter);
    waiterService.delete(newWaiter.getId());
    waiterService.findById(newWaiter.getId());
  }

  @Test(expected = IdNullPointerException.class)
  public void update_ShouldThrowIdNullPointerException() {
    var waiter = new Waiter();
    waiterService.update(waiter);
  }

  @Test(expected = NullEntityException.class)
  public void update_ShouldThrowNullEntityException() {
    waiterService.update(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void update_ShouldThrowEntityNotFoundException() {
    var waiterExample = new Waiter();
    waiterExample.setId(UserFixtures.FAKEID);
    var waiter = WaiterServiceFixtures.buildWaiter(waiterExample);
    waiterService.update(waiter);
  }

  @Test
  public void update_ShouldReturnUpdatedObject() {
    var waiter = WaiterServiceFixtures.buildWaiter();
    var newWaiter = waiterService.insert(waiter);
    var updatedWaiterExample = new Waiter();
    updatedWaiterExample.setName("Diana");
    updatedWaiterExample.setUsername("dxmd");
    var updatedWaiter = WaiterServiceFixtures.buildWaiter(updatedWaiterExample);
    updatedWaiter.setId(waiter.getId());
    assertEquals(newWaiter, waiterService.update(updatedWaiter));
  }

  @Test
  public void insert_ShouldReturnWaiter() {
    var waiter = WaiterServiceFixtures.buildWaiter();
    assertEquals(waiter, waiterService.insert(waiter));
  }

  @Test(expected = IdNullPointerException.class)
  public void delete_ShouldThrowIdNullPointerException() {
    var waiter = new Waiter();
    waiterService.delete(waiter.getId());
  }

  @Test
  public void delete_ShouldReturnDeletedValue() {
    var waiter = WaiterServiceFixtures.buildWaiter();
    var newWaiter = waiterService.insert(waiter);
    assertEquals(newWaiter, waiterService.delete(waiter.getId()));
  }

  @Test
  public void findAll_ShouldReturnListOfWaiters() {
    var waiters = WaiterServiceFixtures.buildWaiters(3);
    waiters.stream().forEach(waiterService::insert);
    assertEquals(waiters, waiterService.findAll());
  }
}
