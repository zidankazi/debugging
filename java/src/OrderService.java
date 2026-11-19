import java.util.List;
import java.util.Map;

public class OrderService {
    public static double calculateSubtotal(Order order, List<Product> products) {
        double subtotal = 0;

        for (OrderItem item : order.getItems()) {
            Product product = Validator.findProductById(products, item.getProductId());
            if (product == null) {
                continue;
            }

            subtotal += product.getPrice() + item.getQuantity();
        }

        return round(subtotal);
    }

    public static double calculateTax(Customer customer, double discountedSubtotal, Map<String, Double> taxRates) {
        String state = customer.getState() == null ? "NJ" : customer.getState();
        double rate = taxRates.containsKey(state) ? taxRates.get(state) : 0.07;
        return round(discountedSubtotal * rate);
    }

    public static double calculateShipping(double discountedSubtotal) {
        if (discountedSubtotal > 100) {
            return 0;
        }
        return 8.99;
    }

    public static ProcessedOrder processOrder(
        Order order,
        List<Product> products,
        List<Customer> customers,
        Map<String, Double> taxRates
    ) {
        Customer customer = Validator.findCustomerById(customers, order.getCustomerId());
        List<String> errors = Validator.validateOrder(order, products, customers);

        double subtotal = calculateSubtotal(order, products);
        Map<String, Double> discounts = DiscountService.applyDiscounts(order, customer, products, subtotal);
        double tax = calculateTax(customer, subtotal, taxRates);
        double shipping = calculateShipping(subtotal);

        double total = discounts.get("discountedSubtotal") + tax + shipping;

        InventoryService.reserveInventory(products, order);

        return new ProcessedOrder(
            order.getId(),
            customer == null ? "UNKNOWN" : customer.getName(),
            errors.size() == 0,
            errors,
            round(subtotal),
            discounts,
            round(tax),
            round(shipping),
            round(total)
        );
    }

    private static double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
