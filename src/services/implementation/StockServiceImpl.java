package services.implementation;

import entities.Stock;
import services.interfaces.StockService;

public class StockServiceImpl extends BaseServiceImpl<Stock, Long> implements StockService {
  public StockServiceImpl() {
    super(0L, Stock.class, Long.class);
  }
}
