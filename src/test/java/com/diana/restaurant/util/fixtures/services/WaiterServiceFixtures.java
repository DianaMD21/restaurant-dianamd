package com.diana.restaurant.util.fixtures.services;

import com.diana.restaurant.entities.Waiter;
import com.diana.restaurant.util.fixtures.entities.EmployeeFixtures;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public final class WaiterServiceFixtures {

  public static List<Waiter> buildWaiters(Integer size) {
    return LongStream.range(0, size)
        .mapToObj(WaiterServiceFixtures::buildWaiter)
        .collect(Collectors.toList());
  }

  public static Waiter buildWaiter() {
    return buildWaiter((Waiter) null);
  }

  public static Waiter buildWaiter(Long id) {
    var waiterExample = new Waiter();
    waiterExample.setId(id);
    return buildWaiter(waiterExample);
  }

  public static Waiter buildWaiter(Waiter waiterExample) {
    var waiter = new Waiter();
    EmployeeFixtures.buildEmployee(waiter, waiterExample);
    return waiter;
  }
}