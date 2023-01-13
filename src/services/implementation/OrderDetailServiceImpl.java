package services.implementation;

import entities.OrderDetail;
import services.interfaces.OrderDetailService;

public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetail, Long>
    implements OrderDetailService {
  public OrderDetailServiceImpl() {
    super(0L, OrderDetail.class, Long.class);
  }
}
