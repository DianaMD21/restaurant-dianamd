package util.fixtures;

import entities.Tax;
import java.util.Optional;

public final class TaxFixtures {
  public static Tax buildTax(Tax tax, Tax taxExample) {
    BaseEntityFixtures.buildBaseEntity(tax, taxExample);
    tax.setName(Optional.ofNullable(taxExample).map(Tax::getName).orElse("testing-name"));
    tax.setTaxPercentage(Optional.ofNullable(taxExample).map(Tax::getTaxPercentage).orElse(15.7));
    return tax;
  }
}
