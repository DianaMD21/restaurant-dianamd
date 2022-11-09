package services.implementation;

import entities.Chef;
import services.interfaces.ChefService;

public class ChefServiceImpl extends BaseServiceImpl<Chef, Long> implements ChefService {
  public ChefServiceImpl() {
    super(0L, Chef.class, Long.class);
  }
}
