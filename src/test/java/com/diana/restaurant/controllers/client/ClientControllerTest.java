package com.diana.restaurant.controllers.client;

import com.diana.restaurant.controllers.ClientController;
import com.diana.restaurant.entities.Client;
import com.diana.restaurant.enums.IocControllers;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.util.fixtures.services.ClientServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClientControllerTest {
  private ClientController clientController;

  @BeforeEach
  public void setup() {
    clientController = Ioc.getInstance().get(IocControllers.CLIENT_CONTROLLER);
  }

  @Test
  public void getAll_ShouldReturnListOfClients() {
    var clients = ClientServiceFixtures.buildClients(3);
    clients.stream().forEach(clientController::add);
    Assertions.assertEquals(clients, clientController.getAll());
  }

  @Test
  public void add_ShouldInsertClient() {
    var client = ClientServiceFixtures.buildClient();
    Assertions.assertEquals(client, clientController.add(client));
  }

  @Test()
  public void delete_ShouldDeleteClient() {
    var client = ClientServiceFixtures.buildClient();
    var newClient = clientController.add(client);
    clientController.deleteById(client.getId());
    Assertions.assertThrows(
        EntityNotFoundException.class, () -> clientController.findById(newClient.getId()));
  }

  @Test
  public void findById_ShouldReturnFoundClient() {
    var client = ClientServiceFixtures.buildClient();
    var newClient = clientController.add(client);
    Assertions.assertEquals(newClient, clientController.findById(client.getId()));
  }

  @Test
  public void update_ShouldUpdateClient() {
    var client = ClientServiceFixtures.buildClient();
    var updatedClientExample = new Client();
    updatedClientExample.setName("Diana");
    updatedClientExample.setUsername("dxmd");
    var newClient = clientController.add(client);
    var updatedClient = ClientServiceFixtures.buildClient(updatedClientExample);
    updatedClient.setId(client.getId());
    Assertions.assertEquals(newClient, clientController.update(updatedClient));
  }
}
