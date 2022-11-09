package util.fixtures;

import entities.Employee;
import entities.User;
import util.BaseEntityUtil;
import util.EmployeeUtil;
import util.UserUtil;

import java.util.Optional;

public final class EmployeeFixtures {
    public static Employee buildEmployee(Employee employee, Employee employeeExample) {
        UserFixtures.buildUser(employee, employeeExample);
        employee.setCode(Optional.ofNullable(employeeExample).map(Employee::getCode).orElse("testing-code"));
        return employee;
    }
}
