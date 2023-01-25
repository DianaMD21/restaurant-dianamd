package com.diana.restaurant.util.fixtures.services;

import com.diana.restaurant.entities.Tax;
import com.diana.restaurant.util.fixtures.entities.TaxFixtures;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public final class TaxServiceFixtures {

  public static List<Tax> buildTaxes(Integer size) {
    return LongStream.range(0, size)
        .mapToObj(TaxServiceFixtures::buildTax)
        .collect(Collectors.toList());
  }

  public static Tax buildTax() {
    return buildTax((Tax) null);
  }

  public static Tax buildTax(Long id) {
    var taxExample = new Tax();
    taxExample.setId(id);
    return buildTax(taxExample);
  }

  public static Tax buildTax(Tax taxExample) {
    var tax = new Tax();
    TaxFixtures.buildTax(tax, taxExample);
    return tax;
  }
}
