package com.diana.restaurant.util;

import com.diana.restaurant.entities.Cashier;

public final class CashierUtil {

  public static Cashier fetchCashier(Cashier target, Cashier source) {
    return (Cashier) EmployeeUtil.fetchEmployee(target, source);
  }
}
