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
import services.implementation.FinalProductProductServiceImpl;
import services.implementation.FinalProductServiceImpl;
import services.implementation.MenuServiceImpl;
import services.implementation.OrderDetailProductServiceImpl;
import services.implementation.OrderDetailServiceImpl;
import services.implementation.OrderDetailTaxServiceImpl;
import services.implementation.OrderServiceImpl;
import services.implementation.OrderTaxServiceImpl;
import services.implementation.ProductServiceImpl;
import services.implementation.StockProductServiceImpl;
import services.implementation.StockServiceImpl;
import services.implementation.TaxServiceImpl;
import services.implementation.WaiterServiceImpl;

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
    instanceMap.put(IocServices.WAITER_SERVICE_INSTANCE, new WaiterServiceImpl());
    instanceMap.put(IocServices.FINAL_PRODUCT_SERVICE_INSTANCE, new FinalProductServiceImpl());
    instanceMap.put(IocServices.STOCK_PRODUCT_SERVICE_INSTANCE, new StockProductServiceImpl());
    instanceMap.put(
        IocServices.FINAL_PRODUCT_PRODUCT_SERVICE_INSTANCE, new FinalProductProductServiceImpl());
    instanceMap.put(IocServices.TAX_SERVICE_INSTANCE, new TaxServiceImpl());
    instanceMap.put(IocServices.STOCK_SERVICE_INSTANCE, new StockServiceImpl());
    instanceMap.put(IocServices.STOCK_PRODUCT_SERVICE_INSTANCE, new StockProductServiceImpl());
    instanceMap.put(IocServices.ORDER_DETAIL_SERVICE_INSTANCE, new OrderDetailServiceImpl());
    instanceMap.put(
        IocServices.ORDER_DETAIL_PRODUCT_SERVICE_INSTANCE, new OrderDetailProductServiceImpl());
    instanceMap.put(IocServices.ORDER_SERVICE_INSTANCE, new OrderServiceImpl());
    instanceMap.put(IocServices.ORDER_DETAIL_TAX_SERVICE_INSTANCE, new OrderDetailTaxServiceImpl());
    instanceMap.put(IocServices.ORDER_TAX_SERVICE_INSTANCE, new OrderTaxServiceImpl());
    instanceMap.put(IocServices.MENU_SERVICE_INSTANCE, new MenuServiceImpl());
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
