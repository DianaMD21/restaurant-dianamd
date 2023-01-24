package com.diana.restaurant.services.implementation;

import com.diana.restaurant.entities.Waiter;
import com.diana.restaurant.services.interfaces.WaiterService;

public class WaiterServiceImpl extends BaseServiceImpl<Waiter, Long> implements WaiterService {
  public WaiterServiceImpl() {
    super(0L, Waiter.class, Long.class);
  }
}
