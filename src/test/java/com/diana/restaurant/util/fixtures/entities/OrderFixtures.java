package com.diana.restaurant.util.fixtures.entities;

import com.diana.restaurant.entities.Order;
import com.diana.restaurant.enums.OrderStatusEnum;
import com.diana.restaurant.util.fixtures.services.CashierServiceFixtures;
import com.diana.restaurant.util.fixtures.services.ClientServiceFixtures;
import com.diana.restaurant.util.fixtures.services.OrderDetailServiceFixtures;
import com.diana.restaurant.util.fixtures.services.WaiterServiceFixtures;
import java.util.Optional;

public final class OrderFixtures {
  public static final Long FAKEID = 10L;

  public static Order buildOrder(Order order, Order orderExample) {
    BaseEntityFixtures.buildBaseEntity(order, orderExample);
    order.setClient(
        Optional.ofNullable(orderExample)
            .map(Order::getClient)
            .orElse(ClientServiceFixtures.buildClient()));
    order.setCashier(
        Optional.ofNullable(orderExample)
            .map(Order::getCashier)
            .orElse(CashierServiceFixtures.buildCashier()));
    order.setWaiter(
        Optional.ofNullable(orderExample)
            .map(Order::getWaiter)
            .orElse(WaiterServiceFixtures.buildWaiter()));
    order.setOrderDetails(
        Optional.ofNullable(orderExample)
            .map(Order::getOrderDetails)
            .orElse(OrderDetailServiceFixtures.buildOrderDetails(3)));
    order.setTotal(Optional.ofNullable(orderExample).map(Order::getTotal).orElse(593.2));
    order.setOrderStatusEnum(
        Optional.ofNullable(orderExample)
            .map(Order::getOrderStatusEnum)
            .orElse(OrderStatusEnum.ACTIVE));
    order.setOrderTaxes(Optional.ofNullable(orderExample).map(Order::getOrderTaxes).orElse(null));
    return order;
  }
}
