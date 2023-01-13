
package controllers;

import entities.Client;
import java.util.List;
import services.interfaces.ClientService;

public class ClientController {

  private ClientService clientService;

  public ClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  public List<Client> getAll() {
    return this.clientService.findAll().stream().toList();
  }

  public Client findById(Long id) {
    return this.clientService.findById(id).get();
  }

  public Client add(Client client) {
    return this.clientService.insert(client);
  }

  public Client deleteById(Long id) {
    return this.clientService.delete(id);
  }

  public Client update(Client client) {
    return this.clientService.update(client);
  }
}
