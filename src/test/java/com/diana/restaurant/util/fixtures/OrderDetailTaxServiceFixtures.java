package com.diana.restaurant.util.fixtures;

import com.diana.restaurant.entities.OrderDetailTax;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public final class OrderDetailTaxServiceFixtures {
  public static List<OrderDetailTax> buildOrderDetailTaxs(Integer size) {
    return LongStream.range(0, size)
        .mapToObj(OrderDetailTaxServiceFixtures::buildOrderDetailTax)
        .collect(Collectors.toList());
  }

  public static OrderDetailTax buildOrderDetailTax() {
    return buildOrderDetailTax((OrderDetailTax) null);
  }

  public static OrderDetailTax buildOrderDetailTax(Long id) {
    var orderDetailTaxExample = new OrderDetailTax();
    orderDetailTaxExample.setId(id);
    return buildOrderDetailTax(orderDetailTaxExample);
  }

  public static OrderDetailTax buildOrderDetailTax(OrderDetailTax orderDetailTaxExample) {
    var orderDetailTax = new OrderDetailTax();
    OrderDetailTaxFixtures.buildOrderDetailTax(orderDetailTax, orderDetailTaxExample);
    return orderDetailTax;
  }
}
