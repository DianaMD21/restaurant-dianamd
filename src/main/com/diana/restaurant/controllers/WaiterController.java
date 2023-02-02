package com.diana.restaurant.controllers;

import com.diana.restaurant.entities.Waiter;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.services.interfaces.WaiterService;
import java.util.List;

public class WaiterController {
  private WaiterService waiterService;

  public WaiterController(WaiterService waiterService) {
    this.waiterService = waiterService;
  }

  public List<Waiter> getAll() {
    return this.waiterService.getAll();
  }

  public Waiter findById(Long id) {
    return this.waiterService
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException(Waiter.class, id));
  }

  public Waiter add(Waiter waiter) {
    return this.waiterService.add(waiter);
  }

  public Waiter deleteById(Long id) {
    return this.waiterService.delete(id);
  }

  public Waiter update(Waiter waiter) {
    return this.waiterService.update(waiter);
  }
}
