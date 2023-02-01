package com.diana.restaurant.controllers;

import com.diana.restaurant.entities.Stock;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.services.interfaces.StockService;
import java.util.List;

public class StockController {
  private StockService stockService;

  public StockController(StockService stockService) {
    this.stockService = stockService;
  }

  public List<Stock> getAll() {
    return this.stockService.getAll();
  }

  public Stock findById(Long id) {
    return this.stockService
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException(Stock.class, id));
  }

  public Stock add(Stock stock) {
    return this.stockService.add(stock);
  }

  public Stock deleteById(Long id) {
    return this.stockService.delete(id);
  }

  public Stock update(Stock stock) {
    return this.stockService.update(stock);
  }
}
