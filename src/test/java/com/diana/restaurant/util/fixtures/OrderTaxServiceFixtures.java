package com.diana.restaurant.util.fixtures;

import com.diana.restaurant.entities.OrderTax;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public final class OrderTaxServiceFixtures {
  public static List<OrderTax> buildOrderTaxs(Integer size) {
    return LongStream.range(0, size)
        .mapToObj(OrderTaxServiceFixtures::buildOrderTax)
        .collect(Collectors.toList());
  }

  public static OrderTax buildOrderTax() {
    return buildOrderTax((OrderTax) null);
  }

  public static OrderTax buildOrderTax(Long id) {
    var orderTaxExample = new OrderTax();
    orderTaxExample.setId(id);
    return buildOrderTax(orderTaxExample);
  }

  public static OrderTax buildOrderTax(OrderTax orderTaxExample) {
    var orderTax = new OrderTax();
    OrderTaxFixtures.buildOrderTax(orderTax, orderTaxExample);
    return orderTax;
  }
}
