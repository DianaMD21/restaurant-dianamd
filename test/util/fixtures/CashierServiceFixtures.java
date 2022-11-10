package util.fixtures;

import entities.Cashier;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public final class CashierServiceFixtures {
  public static final Long FAKEID = 10L;

  public static List<Cashier> buildCashiers(Integer size) {
    return LongStream.range(0, size)
        .mapToObj(CashierServiceFixtures::buildCashier)
        .collect(Collectors.toList());
  }

  public static Cashier buildCashier() {
    return buildCashier((Cashier) null);
  }

  public static Cashier buildCashier(Long id) {
    var cashierExample = new Cashier();
    cashierExample.setId(id);
    return buildCashier(cashierExample);
  }

  public static Cashier buildCashier(Cashier cashierExample) {
    var cashier = new Cashier();
    EmployeeFixtures.buildEmployee(cashier, cashierExample);
    return cashier;
  }
}
