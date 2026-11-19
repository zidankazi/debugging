import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountService {
    private static final int BULK_DISCOUNT_THRESHOLD = 10;
    private static final double BULK_DISCOUNT_RATE = 0.07;
    private static final double LOYALTY_RATE = 0.05;

    public static double couponDiscountAmount(String couponCode, double subtotal) {
        if (couponCode == null) {
            return 0;
        }

        String code = couponCode.toUpperCase();

        if (code.equals("SAVE10")) {
            return 10;
        }

        if (code.equals("FIVEOFF")) {
            return subtotal * 0.05;
        }

        return 0;
    }

    public static double bulkDiscountAmount(List<OrderItem> items, List<Product> products) {
        double discount = 0;

        for (OrderItem item : items) {
            Product product = Validator.findProductById(products, item.getProductId());
            if (product == null) {
                continue;
            }

            if (item.getQuantity() > BULK_DISCOUNT_THRESHOLD) {
                double lineTotal = product.getPrice() * item.getQuantity();
                discount += lineTotal * BULK_DISCOUNT_RATE;
            }
        }

        return discount;
    }

    public static double loyaltyDiscountAmount(Customer customer, double subtotalAfterCoupon) {
        return subtotalAfterCoupon * LOYALTY_RATE;
    }

    public static Map<String, Double> applyDiscounts(Order order, Customer customer, List<Product> products, double subtotal) {
        double couponDiscount = couponDiscountAmount(order.getCoupon(), subtotal);
        double subtotalAfterCoupon = subtotal - couponDiscount;

        double loyaltyDiscount = loyaltyDiscountAmount(customer, subtotal);
        double bulkDiscount = bulkDiscountAmount(order.getItems(), products);

        double discountedSubtotal = subtotalAfterCoupon - loyaltyDiscount - bulkDiscount;

        Map<String, Double> discounts = new HashMap<>();
        discounts.put("couponDiscount", round(couponDiscount));
        discounts.put("loyaltyDiscount", round(loyaltyDiscount));
        discounts.put("bulkDiscount", round(bulkDiscount));
        discounts.put("discountedSubtotal", round(discountedSubtotal));

        return discounts;
    }

    private static double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
