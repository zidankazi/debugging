import java.util.List;

public class Order {
    private int id;
    private String customerId;
    private String coupon;
    private List<OrderItem> items;

    public Order(int id, String customerId, String coupon, List<OrderItem> items) {
        this.id = id;
        this.customerId = customerId;
        this.coupon = coupon;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCoupon() {
        return coupon;
    }

    public List<OrderItem> getItems() {
        return items;
    }
}
