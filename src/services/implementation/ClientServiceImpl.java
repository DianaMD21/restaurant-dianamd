package services.implementation;

import entities.Client;
import services.interfaces.ClientService;

public class ClientServiceImpl extends BaseServiceImpl<Client, Long> implements ClientService {
  public ClientServiceImpl() {
    super(0L, Client.class, Long.class);
  }
}
