package com.diana.restaurant.util;

import com.diana.restaurant.entities.Order;
import java.util.Optional;

public final class OrderUtil {
  public static Order fetchOrder(Order target, Order source) {
    BaseEntityUtil.fetchBaseEntity(target, source);
    target.setClient(Optional.ofNullable(source).map(Order::getClient).orElse(target.getClient()));
    target.setCashier(
        Optional.ofNullable(source).map(Order::getCashier).orElse(target.getCashier()));
    target.setWaiter(Optional.ofNullable(source).map(Order::getWaiter).orElse(target.getWaiter()));
    target.setOrderDetails(
        Optional.ofNullable(source).map(Order::getOrderDetails).orElse(target.getOrderDetails()));
    target.setTotal(Optional.ofNullable(source).map(Order::getTotal).orElse(target.getTotal()));
    target.setOrderStatusEnum(
        Optional.ofNullable(source)
            .map(Order::getOrderStatusEnum)
            .orElse(target.getOrderStatusEnum()));
    target.setOrderTaxes(
        Optional.ofNullable(source).map(Order::getOrderTaxes).orElse(target.getOrderTaxes()));
    return target;
  }
}
