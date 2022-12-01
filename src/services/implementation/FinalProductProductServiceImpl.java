package services.implementation;

import entities.FinalProductProduct;
import services.interfaces.FinalProductProductService;

public class FinalProductProductServiceImpl extends BaseServiceImpl<FinalProductProduct, Long>
    implements FinalProductProductService {
  public FinalProductProductServiceImpl() {
    super(0L, FinalProductProduct.class, Long.class);
  }
}
