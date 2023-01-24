package com.diana.restaurant.util;

import com.diana.restaurant.entities.OrderDetail;
import java.util.Optional;

public final class OrderDetailUtil {
  public static OrderDetail fetchOrderDetail(OrderDetail target, OrderDetail source) {
    BaseEntityUtil.fetchBaseEntity(target, source);
    target.setOrderDetailProduct(
        Optional.ofNullable(source)
            .map(OrderDetail::getOrderDetailProduct)
            .orElse(target.getOrderDetailProduct()));
    target.setPrice(
        Optional.ofNullable(source).map(OrderDetail::getPrice).orElse(target.getPrice()));
    target.setQuantity(
        Optional.ofNullable(source).map(OrderDetail::getQuantity).orElse(target.getQuantity()));
    target.setOrderDetailTaxes(
        Optional.ofNullable(source)
            .map(OrderDetail::getOrderDetailTaxes)
            .orElse(target.getOrderDetailTaxes()));
    target.setChef(Optional.ofNullable(source).map(OrderDetail::getChef).orElse(target.getChef()));
    target.setOrder(
        Optional.ofNullable(source).map(OrderDetail::getOrder).orElse(target.getOrder()));
    return target;
  }
}
