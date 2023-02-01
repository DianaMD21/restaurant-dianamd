package com.diana.restaurant.ioc;

import com.diana.restaurant.controllers.CashierController;
import com.diana.restaurant.controllers.ChefController;
import com.diana.restaurant.controllers.ClientController;
import com.diana.restaurant.controllers.FinalProductController;
import com.diana.restaurant.controllers.FinalProductProductController;
import com.diana.restaurant.controllers.MenuController;
import com.diana.restaurant.controllers.OrderController;
import com.diana.restaurant.controllers.OrderDetailController;
import com.diana.restaurant.controllers.OrderDetailProductController;
import com.diana.restaurant.controllers.OrderDetailTaxController;
import com.diana.restaurant.enums.IocControllers;
import com.diana.restaurant.enums.IocServices;
import com.diana.restaurant.exceptions.ioc.IocDuplicatedKeyException;
import com.diana.restaurant.exceptions.ioc.IocKeyNotFoundException;
import com.diana.restaurant.exceptions.ioc.IocKeyNullPointerException;
import com.diana.restaurant.exceptions.ioc.IocValueNullPointerException;
import com.diana.restaurant.services.implementation.CashierServiceImpl;
import com.diana.restaurant.services.implementation.ChefServiceImpl;
import com.diana.restaurant.services.implementation.ClientServiceImpl;
import com.diana.restaurant.services.implementation.FinalProductProductServiceImpl;
import com.diana.restaurant.services.implementation.FinalProductServiceImpl;
import com.diana.restaurant.services.implementation.MenuServiceImpl;
import com.diana.restaurant.services.implementation.OrderDetailProductServiceImpl;
import com.diana.restaurant.services.implementation.OrderDetailServiceImpl;
import com.diana.restaurant.services.implementation.OrderDetailTaxServiceImpl;
import com.diana.restaurant.services.implementation.OrderServiceImpl;
import com.diana.restaurant.services.implementation.OrderTaxServiceImpl;
import com.diana.restaurant.services.implementation.ProductServiceImpl;
import com.diana.restaurant.services.implementation.StockProductServiceImpl;
import com.diana.restaurant.services.implementation.StockServiceImpl;
import com.diana.restaurant.services.implementation.TaxServiceImpl;
import com.diana.restaurant.services.implementation.WaiterServiceImpl;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Ioc {
  private static Ioc instance;
  private final Map<String, Object> instanceMap = new HashMap<>();

  private Ioc() {
    this.registerServices(this.instanceMap);
    this.registerControllers(this.instanceMap);
  }

  private void registerControllers(Map<String, Object> instanceMap) {
    instanceMap.put(
        IocControllers.CLIENT_CONTROLLER,
        new ClientController(this.get(IocServices.CLIENT_SERVICE_INSTANCE)));
    instanceMap.put(
        IocControllers.CHEF_CONTROLLER,
        new ChefController(this.get(IocServices.CHEF_SERVICE_INSTANCE)));
    instanceMap.put(
        IocControllers.CASHIER_CONTROLLER,
        new CashierController(this.get(IocServices.CASHIER_SERVICE_INSTANCE)));
    instanceMap.put(
        IocControllers.FINAL_PRODUCT_CONTROLLER,
        new FinalProductController(this.get(IocServices.FINAL_PRODUCT_SERVICE_INSTANCE)));
    instanceMap.put(
        IocControllers.FINAL_PRODUCT_PRODUCT_CONTROLLER,
        new FinalProductProductController(
            this.get(IocServices.FINAL_PRODUCT_PRODUCT_SERVICE_INSTANCE)));
    instanceMap.put(
        IocControllers.MENU_CONTROLLER,
        new MenuController(this.get(IocServices.MENU_SERVICE_INSTANCE)));
    instanceMap.put(
        IocControllers.ORDER_CONTROLLER,
        new OrderController(this.get(IocServices.ORDER_SERVICE_INSTANCE)));
    instanceMap.put(
        IocControllers.ORDER_DETAIL_CONTROLLER,
        new OrderDetailController(this.get(IocServices.ORDER_DETAIL_SERVICE_INSTANCE)));
    instanceMap.put(
        IocControllers.ORDER_DETAIL_PRODUCT_CONTROLLER,
        new OrderDetailProductController(
            this.get(IocServices.ORDER_DETAIL_PRODUCT_SERVICE_INSTANCE)));
    instanceMap.put(
        IocControllers.ORDER_DETAIL_TAX_CONTROLLER,
        new OrderDetailTaxController(this.get(IocServices.ORDER_DETAIL_TAX_SERVICE_INSTANCE)));
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
