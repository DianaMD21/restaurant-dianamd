package com.diana.restaurant.services.implementation;

import com.diana.restaurant.entities.StockProduct;
import com.diana.restaurant.services.interfaces.StockProductService;

public class StockProductServiceImpl extends BaseServiceImpl<StockProduct, Long>
    implements StockProductService {
  public StockProductServiceImpl() {
    super(0L, StockProduct.class, Long.class);
  }
}
