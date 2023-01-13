package util;

import entities.OrderDetailTax;
import java.util.Optional;

public final class OrderDetailTaxUtil {
  public static OrderDetailTax fetchOrderDetailTax(OrderDetailTax target, OrderDetailTax source) {
    BaseEntityUtil.fetchBaseEntity(target, source);
    target.setOrderDetailProduct(
        Optional.ofNullable(source)
            .map(OrderDetailTax::getOrderDetailProduct)
            .orElse(target.getOrderDetailProduct()));
    target.setOrderDetail(
        Optional.ofNullable(source)
            .map(OrderDetailTax::getOrderDetail)
            .orElse(target.getOrderDetail()));
    return target;
  }
}
