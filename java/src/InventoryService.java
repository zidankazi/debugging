import java.util.List;

public class InventoryService {
    public static void reserveInventory(List<Product> products, Order order) {
        for (OrderItem item : order.getItems()) {
            Product product = Validator.findProductById(products, item.getProductId());
            if (product != null) {
                product.setStock(product.getStock() - 1);
            }
        }
    }

    public static Product restockProduct(List<Product> products, String productId, int amount) {
        Product product = Validator.findProductById(products, productId);

        if (product == null) {
            return null;
        }

        product.setStock(amount);
        return product;
    }
}
