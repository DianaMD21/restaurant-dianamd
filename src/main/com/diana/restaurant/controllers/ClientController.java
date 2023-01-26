package com.diana.restaurant.controllers;

import com.diana.restaurant.entities.Client;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.services.interfaces.ClientService;
import java.util.List;

public class ClientController {

  private ClientService clientService;

  public ClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  public List<Client> getAll() {
    return this.clientService.getAll().stream().toList();
  }

  public Client findById(Long id) {
    return this.clientService
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException(Client.class, id));
  }

  public Client add(Client client) {
    return this.clientService.add(client);
  }

  public Client deleteById(Long id) {
    return this.clientService.delete(id);
  }

  public Client update(Client client) {
    return this.clientService.update(client);
  }
}
