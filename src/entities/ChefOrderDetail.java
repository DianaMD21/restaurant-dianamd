package entities;

public class ChefOrderDetail extends BaseEntity<Long>{
    private Chef chef;
    private OrderDetail orderDetail;
    private Double preparationTime;

    public ChefOrderDetail() {
    }

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Double getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(Double preparationTime) {
        this.preparationTime = preparationTime;
    }
}
