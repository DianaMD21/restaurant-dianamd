package util;

import entities.BaseEntity;
import entities.Cashier;
import entities.Chef;
import entities.Client;
import entities.FinalProduct;
import entities.FinalProductProduct;
import entities.Product;
import entities.Stock;
import entities.StockProduct;
import entities.Tax;
import entities.Waiter;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public final class BaseEntityUtil {

  public static final Map<Class<?>, Function<?, ?>> ENTITY_KEY_TO_GENERATOR_FUNCTION_MAP =
      Map.of(Long.class, (Long currentId) -> currentId + 1);
  public static final Map<Class<?>, BiFunction<?, ?, ?>> ENTITY_TO_FETCH_FUNCTION_MAP =
      Map.of(
          Client.class,
          (Client target, Client source) -> ClientUtil.fetchClient(target, source),
          Cashier.class,
          (Cashier target, Cashier source) -> CashierUtil.fetchCashier(target, source),
          Product.class,
          (Product target, Product source) -> ProductUtil.fetchProduct(target, source),
          Chef.class,
          (Chef target, Chef source) -> ChefUtil.fetchChef(target, source),
          Waiter.class,
          (Waiter target, Waiter source) -> WaiterUtil.fetchWaiter(target, source),
          FinalProduct.class,
          (FinalProduct target, FinalProduct source) ->
              FinalProductUtil.fetchFinalProduct(target, source),
          FinalProductProduct.class,
          (FinalProductProduct target, FinalProductProduct source) ->
              FinalProductProductUtil.fetchFinalProductProduct(target, source),
          Tax.class,
          (Tax target, Tax source) -> TaxUtil.fetchTax(target, source),
          Stock.class,
          (Stock target, Stock source) -> StockUtil.fetchStock(target, source),
              StockProduct.class,
              (StockProduct target, StockProduct source) -> StockProductUtil.fetchStockProduct(target, source));

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
