package entities;

public class Client extends User{
    private Long code;

    public Client() {
        super();
    }

    public Client(Long id) {
        super(id);
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }
}
