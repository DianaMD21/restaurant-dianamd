package com.diana.restaurant.util.fixtures.services;

import com.diana.restaurant.entities.Client;
import com.diana.restaurant.util.fixtures.entities.UserFixtures;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public final class ClientServiceFixtures {

  public static List<Client> buildClients(Integer size) {
    return LongStream.range(0, size)
        .mapToObj(ClientServiceFixtures::buildClient)
        .collect(Collectors.toList());
  }

  public static Client buildClient() {
    return buildClient((Client) null);
  }

  public static Client buildClient(Long id) {
    var clientExample = new Client();
    clientExample.setId(id);
    return buildClient(clientExample);
  }

  public static Client buildClient(Client clientExample) {
    var client = new Client();
    client.setCode(Optional.ofNullable(clientExample).map(Client::getCode).orElse("testing-code"));
    UserFixtures.buildUser(client, clientExample);
    return client;
  }
}
