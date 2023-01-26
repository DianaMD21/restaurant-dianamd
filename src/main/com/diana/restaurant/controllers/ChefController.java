package com.diana.restaurant.controllers;

import com.diana.restaurant.entities.Chef;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.services.interfaces.ChefService;
import java.util.List;

public class ChefController {
  private ChefService chefService;

  public ChefController(ChefService chefService) {
    this.chefService = chefService;
  }

  public List<Chef> getAll() {
    return this.chefService.getAll().stream().toList();
  }

  public Chef findById(Long id) {
    return this.chefService
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException(Chef.class, id));
  }

  public Chef add(Chef chef) {
    return this.chefService.add(chef);
  }

  public Chef deleteById(Long id) {
    return this.chefService.delete(id);
  }

  public Chef update(Chef chef) {
    return this.chefService.update(chef);
  }
}
