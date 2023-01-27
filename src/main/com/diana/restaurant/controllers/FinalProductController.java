package com.diana.restaurant.controllers;

import com.diana.restaurant.entities.FinalProduct;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.services.interfaces.FinalProductService;
import java.util.List;

public class FinalProductController {
  private FinalProductService finalProductService;

  public FinalProductController(FinalProductService finalProductService) {
    this.finalProductService = finalProductService;
  }

  public List<FinalProduct> getAll() {
    return this.finalProductService.getAll();
  }

  public FinalProduct findById(Long id) {
    return this.finalProductService
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException(FinalProduct.class, id));
  }

  public FinalProduct add(FinalProduct finalProduct) {
    return this.finalProductService.add(finalProduct);
  }

  public FinalProduct deleteById(Long id) {
    return this.finalProductService.delete(id);
  }

  public FinalProduct update(FinalProduct finalProduct) {
    return this.finalProductService.update(finalProduct);
  }
}
