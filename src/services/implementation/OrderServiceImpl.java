package services.implementation;

import entities.Order;
import entities.Product;
import services.interfaces.OrderService;
import services.interfaces.ProductService;

public class OrderServiceImpl extends BaseServiceImpl<Order, Long> implements OrderService {
    public OrderServiceImpl() {
        super(0L, Order.class, Long.class);
    }
}
