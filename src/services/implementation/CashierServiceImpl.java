package services.implementation;

import entities.Cashier;
import services.interfaces.CashierService;

public class CashierServiceImpl extends BaseServiceImpl<Cashier, Long> implements CashierService {
  public CashierServiceImpl() {
    super(0L, Cashier.class, Long.class);
  }
}
