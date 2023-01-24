package com.diana.restaurant.services.implementation;

import com.diana.restaurant.entities.Tax;
import com.diana.restaurant.services.interfaces.TaxService;

public class TaxServiceImpl extends BaseServiceImpl<Tax, Long> implements TaxService {
  public TaxServiceImpl() {
    super(0L, Tax.class, Long.class);
  }
}
