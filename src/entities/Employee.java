package entities;


public class Employee extends User{
    private Long code;

    public Employee() {
        super();
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }
}
