package com.diana.restaurant.util;

import com.diana.restaurant.entities.Employee;
import java.util.Optional;

public final class EmployeeUtil {

  public static Employee fetchEmployee(Employee target, Employee source) {
    var fetchedTarget = (Employee) UserUtil.fetchUser(target, source);
    fetchedTarget.setCode(
        Optional.ofNullable(source).map(Employee::getCode).orElse(fetchedTarget.getCode()));
    return fetchedTarget;
  }
}
