package com.diana.restaurant.util.fixtures.entities;

import com.diana.restaurant.entities.OrderDetail;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public final class OrderDetailServiceFixtures {
  public static List<OrderDetail> buildOrderDetails(Integer size) {
    return LongStream.range(0, size)
        .mapToObj(OrderDetailServiceFixtures::buildOrderDetail)
        .collect(Collectors.toList());
  }

  public static OrderDetail buildOrderDetail() {
    return buildOrderDetail((OrderDetail) null);
  }

  public static OrderDetail buildOrderDetail(Long id) {
    var orderDetailExample = new OrderDetail();
    orderDetailExample.setId(id);
    return buildOrderDetail(orderDetailExample);
  }

  public static OrderDetail buildOrderDetail(OrderDetail orderDetailExample) {
    var orderDetail = new OrderDetail();
    OrderDetailFixtures.buildOrderDetail(orderDetail, orderDetailExample);
    return orderDetail;
  }
}
