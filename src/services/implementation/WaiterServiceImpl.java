package services.implementation;

import entities.Waiter;
import services.interfaces.WaiterService;

public class WaiterServiceImpl extends BaseServiceImpl<Waiter, Long> implements WaiterService {
  public WaiterServiceImpl() {
    super(0L, Waiter.class, Long.class);
  }
}
