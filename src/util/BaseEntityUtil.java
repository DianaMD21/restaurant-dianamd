package util;

import entities.BaseEntity;
import entities.Cashier;
import entities.Client;
import entities.Product;
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
          (Product target, Product source) -> ProductUtil.fetchProduct(target, source));

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
