package ioc;

import exceptions.ioc.IocDuplicatedKeyException;
import exceptions.ioc.IocKeyNotFoundException;
import exceptions.ioc.IocKeyNullPointerException;
import exceptions.ioc.IocValueNullPointerException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Ioc {
  private static Ioc instance;
  private final Map<String, Object> instanceMap = new HashMap<>();

  private Ioc() {}

  public static Ioc getInstance() {
    return Optional.ofNullable(instance).orElseGet(Ioc::new);
  }

  public void register(String key, Object value) {
    Optional.ofNullable(key).orElseThrow(IocKeyNullPointerException::new);
    Optional.ofNullable(key)
        .filter(this.instanceMap::containsKey)
        .orElseThrow(IocDuplicatedKeyException::new);
    Optional.ofNullable(value).orElseThrow(IocValueNullPointerException::new);

    this.instanceMap.put(key, value);
  }

  public <T> T get(String key) {
    return (T) Optional.ofNullable(instanceMap.get(key)).orElseThrow(IocKeyNotFoundException::new);
  }
}
