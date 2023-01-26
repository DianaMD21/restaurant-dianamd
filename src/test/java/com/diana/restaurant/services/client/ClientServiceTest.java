package com.diana.restaurant.services.client;

import com.diana.restaurant.entities.Client;
import com.diana.restaurant.enums.IocServices;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.exceptions.services.IdNullPointerException;
import com.diana.restaurant.exceptions.services.NullEntityException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.services.implementation.ClientServiceImpl;
import com.diana.restaurant.util.fixtures.entities.UserFixtures;
import com.diana.restaurant.util.fixtures.services.ClientServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClientServiceTest {
  private ClientServiceImpl clientService;

  @BeforeEach
  public void setup() {
    clientService = Ioc.getInstance().get(IocServices.CLIENT_SERVICE_INSTANCE);
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var client = ClientServiceFixtures.buildClient();
    var newClient = clientService.add(client);
    clientService.delete(newClient.getId());
    Assertions.assertThrows(
        EntityNotFoundException.class, () -> clientService.findById(newClient.getId()));
  }

  @Test()
  public void findById_ShouldThrowIdNullPointerException() {
    Assertions.assertThrows(IdNullPointerException.class, () -> clientService.findById(null));
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException() {
    Assertions.assertThrows(EntityNotFoundException.class, () -> clientService.findById(10L));
  }

  @Test
  public void findById_ShouldReturnOptionalOfClientFound() {
    var client = ClientServiceFixtures.buildClient();
    var newClient = clientService.add(client);
    Assertions.assertEquals(newClient, clientService.findById(client.getId()).get());
  }

  @Test
  public void update_ShouldReturnUpdatedClient() {
    var client = ClientServiceFixtures.buildClient();
    var updatedClientExample = new Client();
    updatedClientExample.setName("Diana");
    updatedClientExample.setUsername("dxmd");
    var newClient = clientService.add(client);
    var updatedClient = ClientServiceFixtures.buildClient(updatedClientExample);
    updatedClient.setId(client.getId());
    Assertions.assertEquals(newClient, clientService.update(updatedClient));
  }

  @Test()
  public void update_ShouldThrowIdNullPointerException() {
    var client = new Client();
    Assertions.assertThrows(IdNullPointerException.class, () -> clientService.update(client));
  }

  @Test()
  public void update_ShouldThrowNullEntityException() {
    Assertions.assertThrows(NullEntityException.class, () -> clientService.update(null));
  }

  @Test()
  public void update_ShouldThrowEntityNotFoundException() {
    var clientExample = new Client();
    clientExample.setId(UserFixtures.FAKEID);
    var client = ClientServiceFixtures.buildClient(clientExample);
    Assertions.assertThrows(EntityNotFoundException.class, () -> clientService.update(client));
  }

  @Test()
  public void delete_ShouldThrowIdNullPointerException() {
    var client = new Client();
    Assertions.assertThrows(
        IdNullPointerException.class, () -> clientService.delete(client.getId()));
  }

  @Test
  public void delete_ShouldDeletelient() {
    var client = ClientServiceFixtures.buildClient();
    var newClient = clientService.add(client);
    Assertions.assertEquals(newClient, clientService.delete(client.getId()));
  }

  @Test
  public void add_ShouldAddClient() {
    var client = ClientServiceFixtures.buildClient();
    Assertions.assertEquals(client, clientService.add(client));
  }

  @Test
  public void getAll_ShouldReturnListOfClients() {
    var clients = ClientServiceFixtures.buildClients(3);
    clients.stream().forEach(clientService::add);
    Assertions.assertEquals(clients, clientService.getAll());
  }
}
