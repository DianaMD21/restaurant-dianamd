package services.implementation;

import entities.FinalProduct;
import services.interfaces.FinalProductService;

public class FinalProductServiceImpl extends BaseServiceImpl<FinalProduct, Long>
    implements FinalProductService {
  public FinalProductServiceImpl() {
    super(0L, FinalProduct.class, Long.class);
  }
}
