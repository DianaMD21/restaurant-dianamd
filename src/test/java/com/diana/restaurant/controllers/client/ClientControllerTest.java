package com.diana.restaurant.controllers.client;

import com.diana.restaurant.controllers.ClientController;
import com.diana.restaurant.services.interfaces.ClientService;
import com.diana.restaurant.util.fixtures.services.ClientServiceFixtures;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ClientControllerTest {
  private ClientController clientController;
  private ClientService clientServiceMock;

  @BeforeEach
  public void setup() {
    clientServiceMock = Mockito.mock(ClientService.class);
    clientController = new ClientController(clientServiceMock);
  }

  @Test
  public void getAll_ShouldReturnListOfClients() {
    var clients = ClientServiceFixtures.buildClients(3);
    Mockito.when(clientServiceMock.getAll()).thenReturn(clients);
    var clientsReturned = clientController.getAll();
    Assertions.assertEquals(clients, clientsReturned);
    Mockito.verify(clientServiceMock, Mockito.times(1)).getAll();
  }

  @Test
  public void add_ShouldAddClient() {
    var client = ClientServiceFixtures.buildClient();
    var expectedClient = ClientServiceFixtures.buildClient(1L);
    Mockito.when(clientServiceMock.add(Mockito.eq(client))).thenReturn(expectedClient);
    var clientAdded = clientController.add(client);
    Assertions.assertEquals(expectedClient, clientAdded);
    Mockito.verify(clientServiceMock, Mockito.times(1)).add(Mockito.eq(client));
  }

  @Test()
  public void delete_ShouldDeleteClient() {
    var client = ClientServiceFixtures.buildClient(1L);
    Mockito.when(clientServiceMock.delete(client.getId())).thenReturn(client);
    var clientDeleted = clientController.deleteById(client.getId());
    Assertions.assertEquals(client, clientDeleted);
    var clientToTestVerify = ClientServiceFixtures.buildClient(client);
    Mockito.verify(clientServiceMock, Mockito.times(1))
        .delete(Mockito.eq(clientToTestVerify.getId()));
  }

  @Test
  public void findById_ShouldReturnFoundClient() {
    var client = ClientServiceFixtures.buildClient(1L);
    Mockito.when(clientServiceMock.findById(client.getId())).thenReturn(Optional.of(client));
    var clientFound = clientController.findById(client.getId());
    Assertions.assertEquals(client, clientFound);
    var clientToTestVerify = ClientServiceFixtures.buildClient(client);
    Mockito.verify(clientServiceMock, Mockito.times(1))
        .findById(Mockito.eq(clientToTestVerify.getId()));
  }

  @Test
  public void update_ShouldUpdateClient() {
    var clientToUpdate = ClientServiceFixtures.buildClient(1L);
    clientToUpdate.setName("Diana");
    Mockito.when(clientServiceMock.update(clientToUpdate)).thenReturn(clientToUpdate);
    var clientToTestVerify = ClientServiceFixtures.buildClient(clientToUpdate);
    var clientUpdated = clientController.update(clientToUpdate);
    Assertions.assertEquals(clientToUpdate, clientUpdated);
    Mockito.verify(clientServiceMock, Mockito.times(1)).update(Mockito.eq(clientToTestVerify));
  }
}
