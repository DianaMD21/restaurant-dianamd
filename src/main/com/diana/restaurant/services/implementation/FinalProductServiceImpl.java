package com.diana.restaurant.services.implementation;

import com.diana.restaurant.entities.FinalProduct;
import com.diana.restaurant.services.interfaces.FinalProductService;

public class FinalProductServiceImpl extends BaseServiceImpl<FinalProduct, Long>
    implements FinalProductService {
  public FinalProductServiceImpl() {
    super(0L, FinalProduct.class, Long.class);
  }
}
