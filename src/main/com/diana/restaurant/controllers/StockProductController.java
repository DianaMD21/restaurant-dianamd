package com.diana.restaurant.controllers;

import com.diana.restaurant.entities.StockProduct;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.services.interfaces.StockProductService;
import java.util.List;

public class StockProductController {
  private StockProductService stockProductService;

  public StockProductController(StockProductService stockProductService) {
    this.stockProductService = stockProductService;
  }

  public List<StockProduct> getAll() {
    return this.stockProductService.getAll();
  }

  public StockProduct findById(Long id) {
    return this.stockProductService
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException(StockProduct.class, id));
  }

  public StockProduct add(StockProduct stockProduct) {
    return this.stockProductService.add(stockProduct);
  }

  public StockProduct deleteById(Long id) {
    return this.stockProductService.delete(id);
  }

  public StockProduct update(StockProduct stockProduct) {
    return this.stockProductService.update(stockProduct);
  }
}
