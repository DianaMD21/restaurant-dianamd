package com.diana.restaurant.util.fixtures.entities;

import com.diana.restaurant.entities.User;
import com.diana.restaurant.enums.Roles;
import com.diana.restaurant.enums.StatusEnum;
import java.util.Optional;

public final class UserFixtures {
  public static final Long FAKEID = 10L;

  public static User buildUser(User user, User userExample) {
    BaseEntityFixtures.buildBaseEntity(user, userExample);
    user.setName(Optional.ofNullable(userExample).map(User::getName).orElse("testing-name"));
    user.setStatus(Optional.ofNullable(userExample).map(User::getStatus).orElse(StatusEnum.ACTIVE));
    user.setUserRole(Optional.ofNullable(userExample).map(User::getUserRole).orElse(Roles.CLIENT));
    user.setUsername(
        Optional.ofNullable(userExample).map(User::getUsername).orElse("testing-username"));
    user.setAge(Optional.ofNullable(userExample).map(User::getAge).orElse(21));
    user.setLastName(
        Optional.ofNullable(userExample).map(User::getLastName).orElse("testing-lastName"));
    user.setPhone(Optional.ofNullable(userExample).map(User::getPhone).orElse("8090000000"));
    user.setIdentification(
        Optional.ofNullable(userExample)
            .map(User::getIdentification)
            .orElse("testing-identification"));
    user.setPassword(
        Optional.ofNullable(userExample).map(User::getPassword).orElse("testing-password"));
    return user;
  }
}
