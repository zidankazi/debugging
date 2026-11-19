import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    private static void printProductTable(List<Product> products) {
        System.out.println("ID     NAME           CATEGORY       PRICE    STOCK");
        System.out.println("-------------------------------------------------------");

        for (Product product : products) {
            System.out.printf(
                "%-6s %-14s %-13s $%-7.2f %-5d%n",
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getPrice(),
                product.getStock()
            );
        }

        System.out.println();
    }

    public static void main(String[] args) {
        List<Product> products = DataStore.getProducts();
        List<Customer> customers = DataStore.getCustomers();
        List<Order> orders = DataStore.getOrders();
        Map<String, Double> taxRates = DataStore.getTaxRates();

        System.out.println("=== STARTING CATALOG ===");
        printProductTable(products);

        System.out.println("=== SEARCH ===");
        System.out.println("Searching for '  keyboard ': " + ReportService.findProductByName(products, "  keyboard "));
        System.out.println("Searching for 'MOUSE': " + ReportService.findProductByName(products, "MOUSE"));
        System.out.println();

        System.out.println("=== LOW STOCK ===");
        for (Product product : ReportService.lowStockProducts(products, 5)) {
            System.out.println(product);
        }
        System.out.println();

        System.out.println("=== CATEGORY SUMMARY ===");
        System.out.println(ReportService.categorySummary(products));
        System.out.println();

        System.out.println("=== PROCESSING ORDERS ===");
        List<ProcessedOrder> processed = new ArrayList<>();

        for (Order order : orders) {
            ProcessedOrder result = OrderService.processOrder(order, products, customers, taxRates);
            processed.add(result);
            System.out.println(result);
        }
        System.out.println();

        System.out.println("=== CATALOG AFTER ORDERS ===");
        printProductTable(products);

        System.out.println("=== SORTED BY PRICE ===");
        List<Product> sorted = ReportService.productsSortedByPrice(products);
        printProductTable(sorted);

        System.out.println("=== RESTOCK P103 BY 20 ===");
        System.out.println(InventoryService.restockProduct(products, "P103", 20));
        System.out.println();

        System.out.println("=== REVENUE SUMMARY ===");
        System.out.println(ReportService.revenueSummary(processed));
    }
}
