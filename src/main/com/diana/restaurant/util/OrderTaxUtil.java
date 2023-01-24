package com.diana.restaurant.util;

import com.diana.restaurant.entities.OrderTax;
import java.util.Optional;

public final class OrderTaxUtil {
  public static OrderTax fetchOrderTax(OrderTax target, OrderTax source) {
    BaseEntityUtil.fetchBaseEntity(target, source);
    target.setOrder(Optional.ofNullable(source).map(OrderTax::getOrder).orElse(target.getOrder()));
    return target;
  }
}
