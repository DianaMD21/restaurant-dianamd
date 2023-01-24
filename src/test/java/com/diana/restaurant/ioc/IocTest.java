package com.diana.restaurant.ioc;

import static org.junit.Assert.assertEquals;

import com.diana.restaurant.exceptions.ioc.IocDuplicatedKeyException;
import com.diana.restaurant.exceptions.ioc.IocKeyNotFoundException;
import com.diana.restaurant.exceptions.ioc.IocKeyNullPointerException;
import com.diana.restaurant.exceptions.ioc.IocValueNullPointerException;
import com.diana.restaurant.util.fixtures.IocFixtures;
import org.junit.Before;
import org.junit.Test;

public class IocTest {
  private Ioc ioc = null;

  @Before
  public void setup() {
    ioc = Ioc.getInstance();
    ioc.register(IocFixtures.TESTING_KEY_1, IocFixtures.TESTING_VALUE_1);
    ioc.register(IocFixtures.TESTING_KEY_2, IocFixtures.TESTING_VALUE_2);
    ioc.register(IocFixtures.TESTING_KEY_3, IocFixtures.TESTING_VALUE_3);
  }

  @Test(expected = IocKeyNullPointerException.class)
  public void register_ShouldThrowIocKeyNullPointerException() {
    ioc.register(null, IocFixtures.TESTING_VALUE_1);
    ioc.register(null, null);
  }

  @Test(expected = IocValueNullPointerException.class)
  public void register_ShouldThrowIocValueNullPointerException() {
    ioc.register("Test", null);
  }

  @Test(expected = IocDuplicatedKeyException.class)
  public void register_ShouldThrowIocDuplicatedKeyException() {
    ioc.register(IocFixtures.TESTING_KEY_1, IocFixtures.TESTING_VALUE_1);
  }

  @Test(expected = IocKeyNullPointerException.class)
  public void get_ShouldThrowIocKeyNullPointerException() {
    ioc.get(null);
  }

  @Test(expected = IocKeyNotFoundException.class)
  public void get_ShouldThrowIocKeyNotFoundException() {
    ioc.get("KeyTest1");
  }

  @Test
  public void get_ShouldReturnValue() {
    assertEquals(IocFixtures.TESTING_VALUE_1, ioc.get(IocFixtures.TESTING_KEY_1));
    assertEquals(IocFixtures.TESTING_VALUE_2, ioc.get(IocFixtures.TESTING_KEY_2));
    assertEquals(IocFixtures.TESTING_VALUE_3, ioc.get(IocFixtures.TESTING_KEY_3));
  }
}
