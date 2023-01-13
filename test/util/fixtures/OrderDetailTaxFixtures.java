package util.fixtures;

import entities.OrderDetailTax;
import java.util.Optional;

public final class OrderDetailTaxFixtures {
  public static final Long FAKEID = 10L;

  public static OrderDetailTax buildOrderDetailTax(
      OrderDetailTax orderDetailTax, OrderDetailTax orderDetailTaxExample) {
    BaseEntityFixtures.buildBaseEntity(orderDetailTax, orderDetailTaxExample);
    orderDetailTax.setOrderDetailProduct(
        Optional.ofNullable(orderDetailTaxExample)
            .map(OrderDetailTax::getOrderDetailProduct)
            .orElse(OrderDetailProductServiceFixtures.buildOrderDetailProduct()));
    orderDetailTax.setOrderDetail(
        Optional.ofNullable(orderDetailTaxExample)
            .map(OrderDetailTax::getOrderDetail)
            .orElse(OrderDetailServiceFixtures.buildOrderDetail()));
    return orderDetailTax;
  }
}
