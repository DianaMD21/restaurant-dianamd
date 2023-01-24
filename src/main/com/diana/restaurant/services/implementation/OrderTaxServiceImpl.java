package com.diana.restaurant.services.implementation;

import com.diana.restaurant.entities.OrderTax;
import com.diana.restaurant.services.interfaces.OrderTaxService;

public class OrderTaxServiceImpl extends BaseServiceImpl<OrderTax, Long>
    implements OrderTaxService {
  public OrderTaxServiceImpl() {
    super(0L, OrderTax.class, Long.class);
  }
}
