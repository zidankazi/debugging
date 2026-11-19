import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStore {
    public static List<Product> getProducts() {
        return new ArrayList<>(Arrays.asList(
            new Product("P100", "Keyboard", "Electronics", 49.99, 12),
            new Product("P101", "Mouse", "Electronics", 19.99, 4),
            new Product("P102", "Notebook", "Office", 3.50, 30),
            new Product("P103", "Pen Pack", "Office", 5.25, 0),
            new Product("P104", "Water Bottle", "Fitness", 14.00, 7),
            new Product("P105", "Yoga Mat", "Fitness", 22.50, 2)
        ));
    }

    public static List<Customer> getCustomers() {
        return new ArrayList<>(Arrays.asList(
            new Customer("C001", "Alice Carter", "NJ", true),
            new Customer("C002", "Ben Diaz", "NY", false),
            new Customer("C003", "Cara Ellis", "PA", true)
        ));
    }

    public static List<Order> getOrders() {
        return new ArrayList<>(Arrays.asList(
            new Order(
                1001,
                "C001",
                "SAVE10",
                Arrays.asList(
                    new OrderItem("P100", 2),
                    new OrderItem("P101", 1)
                )
            ),
            new Order(
                1002,
                "C002",
                "FIVEOFF",
                Arrays.asList(
                    new OrderItem("P102", 10),
                    new OrderItem("P103", 2)
                )
            ),
            new Order(
                1003,
                "C003",
                null,
                Arrays.asList(
                    new OrderItem("P105", 2),
                    new OrderItem("P104", 3)
                )
            )
        ));
    }

    public static Map<String, Double> getTaxRates() {
        Map<String, Double> rates = new HashMap<>();
        rates.put("NJ", 0.06625);
        rates.put("NY", 0.08875);
        rates.put("PA", 0.06);
        return rates;
    }
}
