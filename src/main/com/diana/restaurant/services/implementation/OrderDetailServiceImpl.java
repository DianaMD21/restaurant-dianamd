package com.diana.restaurant.services.implementation;

import com.diana.restaurant.entities.OrderDetail;
import com.diana.restaurant.services.interfaces.OrderDetailService;

public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetail, Long>
    implements OrderDetailService {
  public OrderDetailServiceImpl() {
    super(0L, OrderDetail.class, Long.class);
  }
}
