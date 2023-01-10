package services.implementation;

import entities.StockProduct;
import services.interfaces.StockProductService;

public class StockProductServiceImpl extends BaseServiceImpl<StockProduct, Long>
    implements StockProductService {
  public StockProductServiceImpl() {
    super(0L, StockProduct.class, Long.class);
  }
}
