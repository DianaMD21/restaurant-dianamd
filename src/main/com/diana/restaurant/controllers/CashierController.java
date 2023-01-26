package com.diana.restaurant.controllers;

import com.diana.restaurant.entities.Cashier;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.services.interfaces.CashierService;
import java.util.List;

public class CashierController {
  private CashierService cashierService;

  public CashierController(CashierService cashierService) {
    this.cashierService = cashierService;
  }

  public List<Cashier> getAll() {
    return this.cashierService.getAll().stream().toList();
  }

  public Cashier findById(Long id) {
    return this.cashierService
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException(Cashier.class, id));
  }

  public Cashier add(Cashier cashier) {
    return this.cashierService.add(cashier);
  }

  public Cashier deleteById(Long id) {
    return this.cashierService.delete(id);
  }

  public Cashier update(Cashier cashier) {
    return this.cashierService.update(cashier);
  }
}
