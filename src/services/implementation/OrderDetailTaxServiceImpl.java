package services.implementation;

import entities.OrderDetailTax;
import services.interfaces.OrderDetailTaxService;

public class OrderDetailTaxServiceImpl extends BaseServiceImpl<OrderDetailTax, Long>
    implements OrderDetailTaxService {
  public OrderDetailTaxServiceImpl() {
    super(0L, OrderDetailTax.class, Long.class);
  }
}
