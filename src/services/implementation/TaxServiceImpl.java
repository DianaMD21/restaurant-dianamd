package services.implementation;

import entities.Tax;
import services.interfaces.TaxService;

public class TaxServiceImpl extends BaseServiceImpl<Tax, Long> implements TaxService {
  public TaxServiceImpl() {
    super(0L, Tax.class, Long.class);
  }
}
