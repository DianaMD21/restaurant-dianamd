package util;

import entities.User;
import java.util.Optional;

public final class UserUtil {
  public static User fetchUser(User target, User source) {
    BaseEntityUtil.fetchBaseEntity(target, source);
    target.setUsername(
        Optional.ofNullable(source).map(User::getUsername).orElse(target.getUsername()));
    target.setUserRole(
        Optional.ofNullable(source).map(User::getUserRole).orElse(target.getUserRole()));
    target.setAddress(
        Optional.ofNullable(source).map(User::getAddress).orElse(target.getAddress()));
    target.setAge(Optional.ofNullable(source).map(User::getAge).orElse(target.getAge()));
    target.setIdentification(
        Optional.ofNullable(source)
            .map(User::getIdentification)
            .orElse(target.getIdentification()));
    target.setName(Optional.ofNullable(source).map(User::getName).orElse(target.getName()));
    target.setPassword(
        Optional.ofNullable(source).map(User::getPassword).orElse(target.getPassword()));
    target.setLastName(
        Optional.ofNullable(source).map(User::getLastName).orElse(target.getLastName()));
    target.setPhone(Optional.ofNullable(source).map(User::getPhone).orElse(target.getPhone()));
    return target;
  }
}
