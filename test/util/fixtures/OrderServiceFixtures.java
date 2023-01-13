package util.fixtures;

import entities.Order;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public final class OrderServiceFixtures {
  public static List<Order> buildOrders(Integer size) {
    return LongStream.range(0, size)
        .mapToObj(OrderServiceFixtures::buildOrder)
        .collect(Collectors.toList());
  }

  public static Order buildOrder() {
    return buildOrder((Order) null);
  }

  public static Order buildOrder(Long id) {
    var orderExample = new Order();
    orderExample.setId(id);
    return buildOrder(orderExample);
  }

  public static Order buildOrder(Order orderExample) {
    var order = new Order();
    OrderFixtures.buildOrder(order, orderExample);
    return order;
  }
}
