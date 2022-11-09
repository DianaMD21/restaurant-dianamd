package ioc;

import enums.IocServices;
import exceptions.ioc.IocDuplicatedKeyException;
import exceptions.ioc.IocKeyNotFoundException;
import exceptions.ioc.IocKeyNullPointerException;
import exceptions.ioc.IocValueNullPointerException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import services.implementation.CashierServiceImpl;
import services.implementation.ChefServiceImpl;
import services.implementation.ClientServiceImpl;
import services.implementation.ProductServiceImpl;

public class Ioc {
  private static Ioc instance;
  private final Map<String, Object> instanceMap = new HashMap<>();

  private Ioc() {
    this.registerServices(this.instanceMap);
  }

  private void registerServices(Map<String, Object> instanceMap) {
    instanceMap.put(IocServices.CLIENT_SERVICE_INSTANCE, new ClientServiceImpl());
    instanceMap.put(IocServices.CASHIER_SERVICE_INSTANCE, new CashierServiceImpl());
    instanceMap.put(IocServices.PRODUCT_SERVICE_INSTANCE, new ProductServiceImpl());
    instanceMap.put(IocServices.CHEF_SERVICE_INSTANCE, new ChefServiceImpl());
  }

  public static Ioc getInstance() {
    return Optional.ofNullable(instance).orElseGet(Ioc::new);
  }

  public void register(String key, Object value) {
    Optional.ofNullable(key).orElseThrow(IocKeyNullPointerException::new);
    Optional.ofNullable(key)
        .filter(this.instanceMap::containsKey)
        .ifPresent(
            s -> {
              throw new IocDuplicatedKeyException();
            });
    Optional.ofNullable(value).orElseThrow(IocValueNullPointerException::new);
    this.instanceMap.put(key, value);
  }

  public <T> T get(String key) {
    Optional.ofNullable(key).orElseThrow(IocKeyNullPointerException::new);
    return (T) Optional.ofNullable(instanceMap.get(key)).orElseThrow(IocKeyNotFoundException::new);
  }
}
