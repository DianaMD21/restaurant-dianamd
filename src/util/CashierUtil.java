package util;

import entities.Cashier;

public final class CashierUtil {

  public static Cashier fetchCashier(Cashier target, Cashier source) {
    return (Cashier) EmployeeUtil.fetchEmployee(target, source);
  }
}
