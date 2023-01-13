package services.implementation;

import entities.OrderTax;
import services.interfaces.OrderTaxService;

public class OrderTaxServiceImpl extends BaseServiceImpl<OrderTax, Long>
    implements OrderTaxService {
  public OrderTaxServiceImpl() {
    super(0L, OrderTax.class, Long.class);
  }
}
