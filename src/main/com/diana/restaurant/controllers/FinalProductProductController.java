package com.diana.restaurant.controllers;

import com.diana.restaurant.entities.FinalProductProduct;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.services.interfaces.FinalProductProductService;
import java.util.List;

public class FinalProductProductController {
  private FinalProductProductService finalProductProductService;

  public FinalProductProductController(FinalProductProductService finalProductProductService) {
    this.finalProductProductService = finalProductProductService;
  }

  public List<FinalProductProduct> getAll() {
    return this.finalProductProductService.getAll();
  }

  public FinalProductProduct findById(Long id) {
    return this.finalProductProductService
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException(FinalProductProduct.class, id));
  }

  public FinalProductProduct add(FinalProductProduct finalProductProduct) {
    return this.finalProductProductService.add(finalProductProduct);
  }

  public FinalProductProduct deleteById(Long id) {
    return this.finalProductProductService.delete(id);
  }

  public FinalProductProduct update(FinalProductProduct finalProductProduct) {
    return this.finalProductProductService.update(finalProductProduct);
  }
}
