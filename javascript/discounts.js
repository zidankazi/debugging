const BULK_DISCOUNT_THRESHOLD = 10;
const BULK_DISCOUNT_RATE = 0.07;
const LOYALTY_RATE = 0.05;

function couponDiscountAmount(couponCode, subtotal) {
  if (!couponCode) {
    return 0;
  }

  const code = couponCode.toUpperCase();

  if (code === "SAVE10") {
    return 10;
  }

  if (code === "FIVEOFF") {
    return subtotal * 0.05;
  }

  return 0;
}

function bulkDiscountAmount(items, products) {
  let discount = 0;

  for (const item of items) {
    const product = products.find(p => p.id === item.productId);
    if (!product) continue;

    if (item.quantity > BULK_DISCOUNT_THRESHOLD) {
      const lineTotal = product.price * item.quantity;
      discount += lineTotal * BULK_DISCOUNT_RATE;
    }
  }

  return discount;
}

function loyaltyDiscountAmount(customer, subtotalAfterCoupon) {
  return subtotalAfterCoupon * LOYALTY_RATE;
}

function applyDiscounts(order, customer, products, subtotal) {
  const couponDiscount = couponDiscountAmount(order.coupon, subtotal);
  const subtotalAfterCoupon = subtotal - couponDiscount;

  const loyaltyDiscount = loyaltyDiscountAmount(customer, subtotal);
  const bulkDiscount = bulkDiscountAmount(order.items || [], products);

  const discountedSubtotal = subtotalAfterCoupon - loyaltyDiscount - bulkDiscount;

  return {
    couponDiscount: Number(couponDiscount.toFixed(2)),
    loyaltyDiscount: Number(loyaltyDiscount.toFixed(2)),
    bulkDiscount: Number(bulkDiscount.toFixed(2)),
    discountedSubtotal: Number(discountedSubtotal.toFixed(2))
  };
}

module.exports = {
  applyDiscounts,
  couponDiscountAmount,
  bulkDiscountAmount,
  loyaltyDiscountAmount
};
