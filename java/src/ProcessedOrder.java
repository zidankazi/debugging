import java.util.List;
import java.util.Map;

public class ProcessedOrder {
    private int orderId;
    private String customerName;
    private boolean valid;
    private List<String> errors;
    private double subtotal;
    private Map<String, Double> discounts;
    private double tax;
    private double shipping;
    private double total;

    public ProcessedOrder(
        int orderId,
        String customerName,
        boolean valid,
        List<String> errors,
        double subtotal,
        Map<String, Double> discounts,
        double tax,
        double shipping,
        double total
    ) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.valid = valid;
        this.errors = errors;
        this.subtotal = subtotal;
        this.discounts = discounts;
        this.tax = tax;
        this.shipping = shipping;
        this.total = total;
    }

    public boolean isValid() {
        return valid;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getTotal() {
        return total;
    }

    public int getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "ProcessedOrder{" +
            "orderId=" + orderId +
            ", customerName='" + customerName + '\'' +
            ", valid=" + valid +
            ", errors=" + errors +
            ", subtotal=" + subtotal +
            ", discounts=" + discounts +
            ", tax=" + tax +
            ", shipping=" + shipping +
            ", total=" + total +
            '}';
    }
}
