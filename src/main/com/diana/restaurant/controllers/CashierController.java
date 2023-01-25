package com.diana.restaurant.controllers;

import com.diana.restaurant.entities.Cashier;
import com.diana.restaurant.services.interfaces.CashierService;
import java.util.List;

public class CashierController {
  private CashierService cashierService;

  public CashierController(CashierService cashierService) {
    this.cashierService = cashierService;
  }

  public List<Cashier> getAll() {
    return this.cashierService.findAll().stream().toList();
  }

  public Cashier findById(Long id) {
    return this.cashierService.findById(id).get();
  }

  public Cashier add(Cashier cashier) {
    return this.cashierService.insert(cashier);
  }

  public Cashier deleteById(Long id) {
    return this.cashierService.delete(id);
  }

  public Cashier update(Cashier cashier) {
    return this.cashierService.update(cashier);
  }
}
