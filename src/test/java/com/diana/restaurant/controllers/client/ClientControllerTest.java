package com.diana.restaurant.controllers.client;

import static org.junit.Assert.assertEquals;

import com.diana.restaurant.controllers.ClientController;
import com.diana.restaurant.entities.Client;
import com.diana.restaurant.enums.IocControllers;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.util.fixtures.ClientServiceFixtures;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClientControllerTest {
  private ClientController clientController;

  @Before
  public void setup() {
    clientController = Ioc.getInstance().get(IocControllers.CLIENT_CONTROLLER);
  }

  @Test
  public void getAll_ShouldReturnListOfClients() {
    var clients = ClientServiceFixtures.buildClients(3);
    clients.stream().forEach(clientController::add);
    assertEquals(clients, clientController.getAll());
  }

  @Test
  public void add_ShouldInsertAClient() {
    var client = ClientServiceFixtures.buildClient();
    Assert.assertEquals(client, clientController.add(client));
  }

  @Test(expected = EntityNotFoundException.class)
  public void delete_ShouldDeleteAClient() {
    var client = ClientServiceFixtures.buildClient();
    var newClient = clientController.add(client);
    clientController.deleteById(client.getId());
    clientController.findById(newClient.getId());
  }

  @Test
  public void findById_ShouldReturnFoundClient() {
    var client = ClientServiceFixtures.buildClient();
    var newClient = clientController.add(client);
    assertEquals(newClient, clientController.findById(client.getId()));
  }

  @Test
  public void update_ShouldUpdateClient() {
    var client = ClientServiceFixtures.buildClient();
    var newClient = clientController.add(client);
    var updatedClientExample = new Client();
    updatedClientExample.setName("Diana");
    updatedClientExample.setUsername("dxmd");
    var updatedClient = ClientServiceFixtures.buildClient(updatedClientExample);
    updatedClient.setId(client.getId());
    assertEquals(newClient, clientController.update(updatedClient));
  }
}
