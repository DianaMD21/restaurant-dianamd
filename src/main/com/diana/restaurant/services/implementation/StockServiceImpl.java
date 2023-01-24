package com.diana.restaurant.services.implementation;

import com.diana.restaurant.entities.Stock;
import com.diana.restaurant.services.interfaces.StockService;

public class StockServiceImpl extends BaseServiceImpl<Stock, Long> implements StockService {
  public StockServiceImpl() {
    super(0L, Stock.class, Long.class);
  }
}
