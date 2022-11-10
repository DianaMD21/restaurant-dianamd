package util.fixtures;

import entities.Employee;
import java.util.Optional;

public final class EmployeeFixtures {
  public static Employee buildEmployee(Employee employee, Employee employeeExample) {
    UserFixtures.buildUser(employee, employeeExample);
    employee.setCode(
        Optional.ofNullable(employeeExample).map(Employee::getCode).orElse("testing-code"));
    return employee;
  }
}
