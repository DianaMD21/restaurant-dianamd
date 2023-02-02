package com.diana.restaurant.controllers;

import com.diana.restaurant.entities.Tax;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.services.interfaces.TaxService;
import java.util.List;

public class TaxController {
  private TaxService taxService;

  public TaxController(TaxService taxService) {
    this.taxService = taxService;
  }

  public List<Tax> getAll() {
    return this.taxService.getAll();
  }

  public Tax findById(Long id) {
    return this.taxService
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException(Tax.class, id));
  }

  public Tax add(Tax tax) {
    return this.taxService.add(tax);
  }

  public Tax deleteById(Long id) {
    return this.taxService.delete(id);
  }

  public Tax update(Tax tax) {
    return this.taxService.update(tax);
  }
}
