package com.diana.restaurant.services.implementation;

import com.diana.restaurant.entities.OrderDetailProduct;
import com.diana.restaurant.services.interfaces.OrderDetailProductService;

public class OrderDetailProductServiceImpl extends BaseServiceImpl<OrderDetailProduct, Long>
    implements OrderDetailProductService {
  public OrderDetailProductServiceImpl() {
    super(0L, OrderDetailProduct.class, Long.class);
  }
}
