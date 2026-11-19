import java.util.List;
import java.util.Map;

public class DebugScenarios {
    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new RuntimeException(message);
        }
    }

    public static void main(String[] args) {
        List<Product> products = DataStore.getProducts();
        List<Customer> customers = DataStore.getCustomers();
        List<Order> orders = DataStore.getOrders();
        Map<String, Double> taxRates = DataStore.getTaxRates();

        check(ReportService.findProductByName(products, "  keyboard ") != null, "Search should trim and ignore case");
        check(ReportService.findProductByName(products, "MOUSE") != null, "Search should ignore case");

        List<Product> low = ReportService.lowStockProducts(products, 5);
        boolean hasPenPack = false;
        for (Product product : low) {
            if (product.getName().equals("Pen Pack")) {
                hasPenPack = true;
            }
        }
        check(hasPenPack, "Low-stock report should include out-of-stock products");

        Map<String, Map<String, Integer>> summary = ReportService.categorySummary(products);
        check(summary.get("Electronics").get("productCount") == 2, "Electronics should have 2 products");
        check(summary.get("Electronics").get("totalStock") == 16, "Electronics should have 16 total stock");

        double subtotal = OrderService.calculateSubtotal(orders.get(0), products);
        check(Math.abs(subtotal - 119.97) < 0.001, "Order 1001 subtotal should be 119.97");

        ProcessedOrder result = OrderService.processOrder(orders.get(0), products, customers, taxRates);
        check(result.isValid(), "Order 1001 should be valid");
        check(products.get(0).getStock() == 10, "Keyboard stock should decrease by 2");
        check(products.get(1).getStock() == 3, "Mouse stock should decrease by 1");

        ProcessedOrder invalidResult = OrderService.processOrder(orders.get(1), products, customers, taxRates);
        check(!invalidResult.isValid(), "Order 1002 should be invalid because Pen Pack is out of stock");

        System.out.println("All debugging scenarios passed.");
    }
}
