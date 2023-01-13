package util.fixtures;

import entities.Order;
import enums.MeasureUnitEnum;
import enums.OrderStatusEnum;

import java.util.Optional;

public final class OrderFixtures {
    public static final Long FAKEID = 10L;

    public static Order buildOrder(Order order, Order orderExample) {
        BaseEntityFixtures.buildBaseEntity(order, orderExample);
        order.setClient(Optional.ofNullable(orderExample).map(Order::getClient).orElse(ClientServiceFixtures.buildClient()));
        order.setCashier(Optional.ofNullable(orderExample).map(Order::getCashier).orElse(CashierServiceFixtures.buildCashier()));
        order.setWaiter(
                Optional.ofNullable(orderExample).map(Order::getWaiter).orElse(WaiterServiceFixtures.buildWaiter()));
        order.setOrderDetails(
                Optional.ofNullable(orderExample)
                        .map(Order::getOrderDetails)
                        .orElse(OrderDetailServiceFixtures.buildOrderDetails(3)));
        order.setTotal(Optional.ofNullable(orderExample).map(Order::getTotal).orElse(593.2));
        order.setOrderStatusEnum(Optional.ofNullable(orderExample).map(Order::getOrderStatusEnum).orElse(OrderStatusEnum.ACTIVE));
        order.setOrderTaxes(Optional.ofNullable(orderExample).map(Order::getOrderTaxes).orElse(null));
        return order;
    }
}
