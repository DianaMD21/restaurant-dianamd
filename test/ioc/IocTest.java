package ioc;

import static org.junit.Assert.assertEquals;

import exceptions.ioc.IocDuplicatedKeyException;
import exceptions.ioc.IocKeyNotFoundException;
import exceptions.ioc.IocKeyNullPointerException;
import exceptions.ioc.IocValueNullPointerException;
import org.junit.Before;
import org.junit.Test;
import util.fixtures.IocFixtures;

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
