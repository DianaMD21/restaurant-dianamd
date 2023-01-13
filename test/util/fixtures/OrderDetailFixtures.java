package util.fixtures;

import entities.OrderDetail;
import java.util.Optional;

public final class OrderDetailFixtures {
  public static final Long FAKEID = 10L;

  public static OrderDetail buildOrderDetail(
      OrderDetail orderDetail, OrderDetail orderDetailExample) {
    BaseEntityFixtures.buildBaseEntity(orderDetail, orderDetailExample);
    orderDetail.setOrder(
        Optional.ofNullable(orderDetailExample).map(OrderDetail::getOrder).orElse(null));
    orderDetail.setOrderDetailProduct(
        Optional.ofNullable(orderDetailExample)
            .map(OrderDetail::getOrderDetailProduct)
            .orElse(null));
    orderDetail.setPrice(
        Optional.ofNullable(orderDetailExample).map(OrderDetail::getPrice).orElse(100.5));
    orderDetail.setOrderDetailTaxes(
        Optional.ofNullable(orderDetailExample).map(OrderDetail::getOrderDetailTaxes).orElse(null));
    orderDetail.setChef(
        Optional.ofNullable(orderDetailExample)
            .map(OrderDetail::getChef)
            .orElse(ChefServiceFixtures.buildChef()));
    orderDetail.setQuantity(
        Optional.ofNullable(orderDetailExample).map(OrderDetail::getQuantity).orElse(50.3));
    return orderDetail;
  }
}
