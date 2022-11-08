package client;

import static org.junit.Assert.assertEquals;

import entities.Client;
import enums.IocServices;
import exceptions.services.EntityNotFoundException;
import exceptions.services.IdNullPointerException;
import exceptions.services.NullEntityException;
import ioc.Ioc;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import services.implementation.ClientServiceImpl;
import util.fixtures.ClientServiceFixtures;

public class ClientServiceTest {
  private ClientServiceImpl clientService;

  @Before
  public void setup() {
    clientService = Ioc.getInstance().get(IocServices.CLIENT_SERVICE_INSTANCE);
  }

  @Test(expected = IdNullPointerException.class)
  public void findById_ShouldThrowIdNullPointerException() {
    clientService.findById(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException() {
    clientService.findById(10L);
  }

  @Test
  public void findById_ShouldReturnOptionalOfClient() {
    var client = ClientServiceFixtures.buildClient();
    var newClient = clientService.insert(client);
    assertEquals(newClient, clientService.findById(client.getId()));
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException_IfStatusIsDeleted() {
    var client = ClientServiceFixtures.buildClient();
    var newClient = clientService.insert(client).get();
    clientService.delete(newClient.getId());
    clientService.findById(newClient.getId());
  }

  @Test(expected = IdNullPointerException.class)
  public void update_ShouldThrowIdNullPointerException() {
    var client = new Client();
    clientService.update(client);
  }

  @Test(expected = NullEntityException.class)
  public void update_ShouldThrowNullEntityException() {
    clientService.update(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void update_ShouldThrowEntityNotFoundException() {
    var clientExample = new Client();
    clientExample.setId(ClientServiceFixtures.FAKEID);
    var client = ClientServiceFixtures.buildClient(clientExample);
    clientService.update(client);
  }

  @Test
  public void update_ShouldReturnUpdatedObject() {
    var client = ClientServiceFixtures.buildClient();
    var newClient = clientService.insert(client);
    var updatedClientExample = new Client();
    updatedClientExample.setName("Diana");
    updatedClientExample.setUsername("dxmd");
    var updatedClient = ClientServiceFixtures.buildClient(updatedClientExample);
    updatedClient.setId(client.getId());
    assertEquals(newClient, clientService.update(updatedClient));
  }

  @Test
  public void insert_ShouldReturnOptionalOfClient() {
    var client = ClientServiceFixtures.buildClient();
    assertEquals(Optional.of(client), clientService.insert(client));
  }

  @Test(expected = IdNullPointerException.class)
  public void delete_ShouldThrowIdNullPointerException() {
    var client = new Client();
    clientService.delete(client.getId());
  }

  @Test
  public void delete_ShouldReturnDeletedValue() {
    var client = ClientServiceFixtures.buildClient();
    var newClient = clientService.insert(client);
    assertEquals(newClient, clientService.delete(client.getId()));
  }

  @Test
  public void findAll_ShouldReturnListOfClients() {
    var clients = ClientServiceFixtures.buildClients(3);
    clients.stream().forEach(clientService::insert);
    assertEquals(clients, clientService.findAll());
  }
}