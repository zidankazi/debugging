import java.util.ArrayList;
import java.util.List;

public class Validator {
    public static boolean isNonEmptyString(String value) {
        return value != null && value.length() > 0;
    }

    public static Product findProductById(List<Product> products, String productId) {
        for (Product product : products) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    public static Customer findCustomerById(List<Customer> customers, String customerId) {
        for (Customer customer : customers) {
            if (customer.getId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    public static List<String> validateOrder(Order order, List<Product> products, List<Customer> customers) {
        List<String> errors = new ArrayList<>();

        if (order.getId() == 0) {
            errors.add("Order is missing an id");
        }

        if (!isNonEmptyString(order.getCustomerId())) {
            errors.add("Order is missing a customer id");
        }

        Customer customer = findCustomerById(customers, order.getCustomerId());
        if (customer == null) {
            errors.add("Customer does not exist");
        }

        if (order.getItems() == null || order.getItems().isEmpty()) {
            errors.add("Order must contain at least one item");
        }

        for (OrderItem item : order.getItems()) {
            Product product = findProductById(products, item.getProductId());

            if (product == null) {
                errors.add("Product does not exist: " + item.getProductId());
                continue;
            }

            if (item.getQuantity() < 0) {
                errors.add("Quantity must be positive for " + product.getName());
            }

            if (item.getQuantity() != 0 && product.getStock() < 1) {
                errors.add("Not enough inventory for " + product.getName());
            }
        }

        return errors;
    }
}
