package com.diana.restaurant.util;

import com.diana.restaurant.entities.Tax;
import java.util.Optional;

public final class TaxUtil {
  public static Tax fetchTax(Tax target, Tax source) {
    BaseEntityUtil.fetchBaseEntity(target, source);
    target.setName(Optional.ofNullable(source).map(Tax::getName).orElse(target.getName()));
    target.setTaxPercentage(
        Optional.ofNullable(source).map(Tax::getTaxPercentage).orElse(target.getTaxPercentage()));
    return target;
  }
}
