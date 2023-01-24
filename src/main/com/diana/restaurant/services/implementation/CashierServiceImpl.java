package com.diana.restaurant.services.implementation;

import com.diana.restaurant.entities.Cashier;
import com.diana.restaurant.services.interfaces.CashierService;

public class CashierServiceImpl extends BaseServiceImpl<Cashier, Long> implements CashierService {
  public CashierServiceImpl() {
    super(0L, Cashier.class, Long.class);
  }
}
