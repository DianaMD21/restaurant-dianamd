package util;

import entities.BaseEntity;
import entities.Cashier;
import entities.Chef;
import entities.Client;
import entities.FinalProduct;
import entities.FinalProductProduct;
import entities.Order;
import entities.OrderDetail;
import entities.OrderDetailProduct;
import entities.OrderDetailTax;
import entities.OrderTax;
import entities.Product;
import entities.Stock;
import entities.StockProduct;
import entities.Tax;
import entities.Waiter;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public final class BaseEntityUtil {

  public static final Map<Class<?>, Function<?, ?>> ENTITY_KEY_TO_GENERATOR_FUNCTION_MAP =
      Map.of(Long.class, (Long currentId) -> currentId + 1);
  public static final Map<Class<?>, BiFunction<?, ?, ?>> ENTITY_TO_FETCH_FUNCTION_MAP =
      MapUtil.of(
          new AbstractMap.SimpleEntry<Class<?>, BiFunction<?, ?, ?>>(
              Client.class,
              (Client target, Client source) -> ClientUtil.fetchClient(target, source)),
          new AbstractMap.SimpleEntry<Class<?>, BiFunction<?, ?, ?>>(
              Cashier.class,
              (Cashier target, Cashier source) -> CashierUtil.fetchCashier(target, source)),
          new AbstractMap.SimpleEntry<Class<?>, BiFunction<?, ?, ?>>(
              Product.class,
              (Product target, Product source) -> ProductUtil.fetchProduct(target, source)),
          new AbstractMap.SimpleEntry<Class<?>, BiFunction<?, ?, ?>>(
              Chef.class, (Chef target, Chef source) -> ChefUtil.fetchChef(target, source)),
          new AbstractMap.SimpleEntry<Class<?>, BiFunction<?, ?, ?>>(
              Waiter.class,
              (Waiter target, Waiter source) -> WaiterUtil.fetchWaiter(target, source)),
          new AbstractMap.SimpleEntry<Class<?>, BiFunction<?, ?, ?>>(
              FinalProduct.class,
              (FinalProduct target, FinalProduct source) ->
                  FinalProductUtil.fetchFinalProduct(target, source)),
          new AbstractMap.SimpleEntry<Class<?>, BiFunction<?, ?, ?>>(
              FinalProductProduct.class,
              (FinalProductProduct target, FinalProductProduct source) ->
                  FinalProductProductUtil.fetchFinalProductProduct(target, source)),
          new AbstractMap.SimpleEntry<Class<?>, BiFunction<?, ?, ?>>(
              OrderDetail.class,
              (OrderDetail target, OrderDetail source) ->
                  OrderDetailUtil.fetchOrderDetail(target, source)),
          new AbstractMap.SimpleEntry<Class<?>, BiFunction<?, ?, ?>>(
              Tax.class, (Tax target, Tax source) -> TaxUtil.fetchTax(target, source)),
          new AbstractMap.SimpleEntry<Class<?>, BiFunction<?, ?, ?>>(
              Stock.class, (Stock target, Stock source) -> StockUtil.fetchStock(target, source)),
          new AbstractMap.SimpleEntry<Class<?>, BiFunction<?, ?, ?>>(
              StockProduct.class,
              (StockProduct target, StockProduct source) ->
                  StockProductUtil.fetchStockProduct(target, source)),
          new AbstractMap.SimpleEntry<Class<?>, BiFunction<?, ?, ?>>(
              OrderDetail.class,
              (OrderDetail target, OrderDetail source) ->
                  OrderDetailUtil.fetchOrderDetail(target, source)),
          new AbstractMap.SimpleEntry<Class<?>, BiFunction<?, ?, ?>>(
              OrderDetailProduct.class,
              (OrderDetailProduct target, OrderDetailProduct source) ->
                  OrderDetailProductUtil.fetchOrderDetailProduct(target, source)),
          new AbstractMap.SimpleEntry<Class<?>, BiFunction<?, ?, ?>>(
              Order.class, (Order target, Order source) -> OrderUtil.fetchOrder(target, source)),
          new AbstractMap.SimpleEntry<Class<?>, BiFunction<?, ?, ?>>(
              OrderDetailTax.class,
              (OrderDetailTax target, OrderDetailTax source) ->
                  OrderDetailTaxUtil.fetchOrderDetailTax(target, source)),
          new AbstractMap.SimpleEntry<Class<?>, BiFunction<?, ?, ?>>(
              OrderTax.class,
              (OrderTax target, OrderTax source) -> OrderTaxUtil.fetchOrderTax(target, source)));

  public static BaseEntity fetchBaseEntity(BaseEntity target, BaseEntity source) {
    target.setUpdatedAt(
        Optional.ofNullable(source).map(BaseEntity::getUpdatedAt).orElse(target.getUpdatedAt()));
    target.setCreatedAt(
        Optional.ofNullable(source).map(BaseEntity::getCreatedAt).orElse(target.getCreatedAt()));
    target.setCreatedBy(
        Optional.ofNullable(source).map(BaseEntity::getCreatedBy).orElse(target.getCreatedBy()));
    target.setUpdatedBy(
        Optional.ofNullable(source).map(BaseEntity::getUpdatedBy).orElse(target.getUpdatedBy()));
    target.setStatus(
        Optional.ofNullable(source).map(BaseEntity::getStatus).orElse(target.getStatus()));
    return target;
  }
}
