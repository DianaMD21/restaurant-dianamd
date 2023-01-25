package com.diana.restaurant.ioc;

import com.diana.restaurant.exceptions.ioc.IocDuplicatedKeyException;
import com.diana.restaurant.exceptions.ioc.IocKeyNotFoundException;
import com.diana.restaurant.exceptions.ioc.IocKeyNullPointerException;
import com.diana.restaurant.exceptions.ioc.IocValueNullPointerException;
import com.diana.restaurant.util.fixtures.ioc.IocFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IocTest {
  private Ioc ioc = null;

  @BeforeEach
  public void setup() {
    ioc = Ioc.getInstance();
    ioc.register(IocFixtures.TESTING_KEY_1, IocFixtures.TESTING_VALUE_1);
    ioc.register(IocFixtures.TESTING_KEY_2, IocFixtures.TESTING_VALUE_2);
    ioc.register(IocFixtures.TESTING_KEY_3, IocFixtures.TESTING_VALUE_3);
  }

  @Test()
  public void register_ShouldThrowIocKeyNullPointerException() {
    Assertions.assertThrows(
        IocKeyNullPointerException.class, () -> ioc.register(null, IocFixtures.TESTING_VALUE_1));
    Assertions.assertThrows(IocKeyNullPointerException.class, () -> ioc.register(null, null));
  }

  @Test()
  public void register_ShouldThrowIocValueNullPointerException() {
    Assertions.assertThrows(IocValueNullPointerException.class, () -> ioc.register("Test", null));
  }

  @Test()
  public void register_ShouldThrowIocDuplicatedKeyException() {
    Assertions.assertThrows(
        IocDuplicatedKeyException.class,
        () -> ioc.register(IocFixtures.TESTING_KEY_1, IocFixtures.TESTING_VALUE_1));
  }

  @Test()
  public void get_ShouldThrowIocKeyNullPointerException() {
    Assertions.assertThrows(IocKeyNullPointerException.class, () -> ioc.get(null));
  }

  @Test()
  public void get_ShouldThrowIocKeyNotFoundException() {
    Assertions.assertThrows(IocKeyNotFoundException.class, () -> ioc.get("KeyTest1"));
  }

  @Test
  public void get_ShouldReturnValue() {
    Assertions.assertEquals(IocFixtures.TESTING_VALUE_1, ioc.get(IocFixtures.TESTING_KEY_1));
    Assertions.assertEquals(IocFixtures.TESTING_VALUE_2, ioc.get(IocFixtures.TESTING_KEY_2));
    Assertions.assertEquals(IocFixtures.TESTING_VALUE_3, ioc.get(IocFixtures.TESTING_KEY_3));
  }
}
