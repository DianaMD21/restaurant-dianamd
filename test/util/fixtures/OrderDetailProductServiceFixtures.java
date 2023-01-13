package util.fixtures;

import entities.OrderDetailProduct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public final class OrderDetailProductServiceFixtures {
  public static List<OrderDetailProduct> buildOrderDetailProducts(Integer size) {
    return LongStream.range(0, size)
        .mapToObj(OrderDetailProductServiceFixtures::buildOrderDetailProduct)
        .collect(Collectors.toList());
  }

  public static OrderDetailProduct buildOrderDetailProduct() {
    return buildOrderDetailProduct((OrderDetailProduct) null);
  }

  public static OrderDetailProduct buildOrderDetailProduct(Long id) {
    var orderDetailProductExample = new OrderDetailProduct();
    orderDetailProductExample.setId(id);
    return buildOrderDetailProduct(orderDetailProductExample);
  }

  public static OrderDetailProduct buildOrderDetailProduct(
      OrderDetailProduct orderDetailProductExample) {
    var orderDetailProduct = new OrderDetailProduct();
    OrderDetailProductFixtures.buildOrderDetailProduct(
        orderDetailProduct, orderDetailProductExample);
    return orderDetailProduct;
  }
}
