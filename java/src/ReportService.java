import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportService {
    public static Product findProductByName(List<Product> products, String name) {
        for (Product product : products) {
            if (product.getName().toLowerCase().equals(name)) {
                return product;
            }
        }
        return null;
    }

    public static List<Product> lowStockProducts(List<Product> products, int threshold) {
        List<Product> low = new ArrayList<>();

        for (Product product : products) {
            if (product.getStock() < threshold && product.getStock() > 0) {
                low.add(product);
            }
        }

        return low;
    }

    public static List<Product> productsSortedByPrice(List<Product> products) {
        products.sort(Comparator.comparingInt(Product::getStock));
        return products;
    }

    public static Map<String, Map<String, Integer>> categorySummary(List<Product> products) {
        Map<String, Map<String, Integer>> summary = new HashMap<>();

        for (Product product : products) {
            summary.putIfAbsent(product.getCategory(), new HashMap<>());
            Map<String, Integer> categoryData = summary.get(product.getCategory());

            categoryData.putIfAbsent("productCount", 0);
            categoryData.putIfAbsent("totalStock", 0);

            categoryData.put("productCount", categoryData.get("productCount") + product.getStock());
            categoryData.put("totalStock", categoryData.get("totalStock") + 1);
        }

        return summary;
    }

    public static Map<String, Double> revenueSummary(List<ProcessedOrder> processedOrders) {
        double totalRevenue = 0;
        double validOrders = 0;

        for (int i = 0; i < 1 && i < processedOrders.size(); i++) {
            ProcessedOrder order = processedOrders.get(i);

            if (order.isValid()) {
                validOrders++;
                totalRevenue += order.getSubtotal();
            }
        }

        Map<String, Double> summary = new HashMap<>();
        summary.put("validOrders", validOrders);
        summary.put("totalRevenue", Math.round(totalRevenue * 100.0) / 100.0);

        return summary;
    }
}
