package util;

import entities.Waiter;

public final class WaiterUtil {
  public static Waiter fetchWaiter(Waiter target, Waiter source) {
    return (Waiter) EmployeeUtil.fetchEmployee(target, source);
  }
}
