package com.diana.restaurant.services.implementation;

import com.diana.restaurant.entities.FinalProductProduct;
import com.diana.restaurant.services.interfaces.FinalProductProductService;

public class FinalProductProductServiceImpl extends BaseServiceImpl<FinalProductProduct, Long>
    implements FinalProductProductService {
  public FinalProductProductServiceImpl() {
    super(0L, FinalProductProduct.class, Long.class);
  }
}
