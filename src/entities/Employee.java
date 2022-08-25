package entities;

import java.util.Objects;

public class Employee extends User{
    private Long code;

    public Employee() {
        super();
    }

    public Employee(Long id) {
        super(id);
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }
}
