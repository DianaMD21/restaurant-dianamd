package com.diana.restaurant.services.implementation;

import com.diana.restaurant.entities.Chef;
import com.diana.restaurant.services.interfaces.ChefService;

public class ChefServiceImpl extends BaseServiceImpl<Chef, Long> implements ChefService {
  public ChefServiceImpl() {
    super(0L, Chef.class, Long.class);
  }
}
