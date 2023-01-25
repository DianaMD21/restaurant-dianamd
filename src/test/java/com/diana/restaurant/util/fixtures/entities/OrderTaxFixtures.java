package com.diana.restaurant.util.fixtures.entities;

import com.diana.restaurant.entities.OrderTax;
import com.diana.restaurant.util.fixtures.services.OrderServiceFixtures;
import java.util.Optional;

public final class OrderTaxFixtures {
  public static final Long FAKEID = 10L;

  public static OrderTax buildOrderTax(OrderTax orderTax, OrderTax orderTaxExample) {
    BaseEntityFixtures.buildBaseEntity(orderTax, orderTaxExample);
    orderTax.setOrder(
        Optional.ofNullable(orderTaxExample)
            .map(OrderTax::getOrder)
            .orElse(OrderServiceFixtures.buildOrder()));
    return orderTax;
  }
}
