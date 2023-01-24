package com.diana.restaurant.services.implementation;

import com.diana.restaurant.entities.Client;
import com.diana.restaurant.services.interfaces.ClientService;

public class ClientServiceImpl extends BaseServiceImpl<Client, Long> implements ClientService {
  public ClientServiceImpl() {
    super(0L, Client.class, Long.class);
  }
}
