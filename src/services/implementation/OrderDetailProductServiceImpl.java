package services.implementation;

import entities.OrderDetailProduct;
import services.interfaces.OrderDetailProductService;

public class OrderDetailProductServiceImpl extends BaseServiceImpl<OrderDetailProduct, Long>
    implements OrderDetailProductService {
  public OrderDetailProductServiceImpl() {
    super(0L, OrderDetailProduct.class, Long.class);
  }
}
