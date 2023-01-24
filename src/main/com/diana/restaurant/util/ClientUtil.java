package com.diana.restaurant.util;

import com.diana.restaurant.entities.Client;
import java.util.Optional;

public final class ClientUtil {
  public static Client fetchClient(Client target, Client source) {
    var fetchedTarget = (Client) UserUtil.fetchUser(target, source);
    fetchedTarget.setCode(
        Optional.ofNullable(source).map(Client::getCode).orElse(fetchedTarget.getCode()));
    return fetchedTarget;
  }
}
