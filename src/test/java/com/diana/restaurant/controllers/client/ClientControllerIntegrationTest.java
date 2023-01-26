package com.diana.restaurant.controllers.client;

import com.diana.restaurant.controllers.ClientController;
import com.diana.restaurant.services.implementation.ClientServiceImpl;
import com.diana.restaurant.services.interfaces.ClientService;
import com.diana.restaurant.util.fixtures.services.ClientServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ClientControllerIntegrationTest {
  private ClientController clientController;
  private ClientService clientServiceSpy;

  @BeforeEach
  public void setUp() {
    ClientService clientService = new ClientServiceImpl();
    clientServiceSpy = Mockito.spy(clientService);
    clientController = new ClientController(clientServiceSpy);
  }

  @Test
  public void findById_ShouldReturnFoundClient() {
    var client = ClientServiceFixtures.buildClient();
    clientController.add(client);
    var clientFound = clientController.findById(client.getId());
    Assertions.assertEquals(clientFound, client);
    Mockito.verify(clientServiceSpy, Mockito.times(1)).findById(Mockito.eq(client.getId()));
  }

  @Test
  public void getAll_ShouldReturnAllClients() {
    var clients = ClientServiceFixtures.buildClients(3);
    clients.stream().forEach(clientController::add);
    var clientsFound = clientController.getAll();
    Assertions.assertEquals(clients, clientsFound);
  }

  @Test
  public void add_ShouldAddClient() {
    var client = ClientServiceFixtures.buildClient();
    var clientAdded = clientController.add(client);
    Assertions.assertEquals(clientAdded, client);
    Mockito.verify(clientServiceSpy, Mockito.times(1)).add(Mockito.eq(client));
  }

  @Test
  public void delete_ShouldDeleteClient() {
    var clients = ClientServiceFixtures.buildClients(3);
    clients.stream().forEach(clientController::add);
    var deletedClient = clientController.deleteById(clients.get(0).getId());
    var deletedClientToVerify = ClientServiceFixtures.buildClient(clients.get(0));
    Assertions.assertEquals(deletedClient, clients.get(0));
    Mockito.verify(clientServiceSpy, Mockito.times(1))
        .delete(Mockito.eq(deletedClientToVerify.getId()));
  }

  @Test
  public void update_ShouldUpdateClient() {
    var clientToUpdate = ClientServiceFixtures.buildClient();
    clientController.add(clientToUpdate);
    clientToUpdate.setName("DIANA MARIA");
    var clientToTestVerify = ClientServiceFixtures.buildClient(clientToUpdate);
    var updatedClient = clientController.update(clientToUpdate);
    Assertions.assertEquals(updatedClient, clientToUpdate);
    Mockito.verify(clientServiceSpy, Mockito.times(1)).update(Mockito.eq(clientToTestVerify));
  }
}
