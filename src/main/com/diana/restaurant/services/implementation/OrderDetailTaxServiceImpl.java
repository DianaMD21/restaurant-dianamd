package com.diana.restaurant.services.implementation;

import com.diana.restaurant.entities.OrderDetailTax;
import com.diana.restaurant.services.interfaces.OrderDetailTaxService;

public class OrderDetailTaxServiceImpl extends BaseServiceImpl<OrderDetailTax, Long>
    implements OrderDetailTaxService {
  public OrderDetailTaxServiceImpl() {
    super(0L, OrderDetailTax.class, Long.class);
  }
}
