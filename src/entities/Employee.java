package entities;

public class Employee extends User{
    private long code;

    public Employee() {
        super();
    }

    public Employee(Long id) {
        super(id);
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }
}
