BULK_DISCOUNT_THRESHOLD = 10
BULK_DISCOUNT_RATE = 0.07
LOYALTY_RATE = 0.05


def coupon_discount_amount(coupon_code, subtotal):
    if coupon_code is None:
        return 0

    code = coupon_code.upper()

    if code == "SAVE10":
        return subtotal * 0.10

    if code == "FIVEOFF":
        return subtotal * 0.05

    return 0


def bulk_discount_amount(items, products):
    discount = 0

    for item in items:
        product = next((p for p in products if p["id"] == item["product_id"]), None)
        if product is None:
            continue

        if item["quantity"] > BULK_DISCOUNT_THRESHOLD:
            line_total = product["price"] * item["quantity"]
            discount += line_total * BULK_DISCOUNT_RATE

    return discount


def loyalty_discount_amount(customer, subtotal_after_coupon):
    return subtotal_after_coupon * LOYALTY_RATE


def apply_discounts(order, customer, products, subtotal):
    coupon_discount = coupon_discount_amount(order.get("coupon"), subtotal)
    subtotal_after_coupon = subtotal - coupon_discount

    loyalty_discount = loyalty_discount_amount(customer, subtotal)
    bulk_discount = bulk_discount_amount(order.get("items", []), products)

    discounted_subtotal = subtotal_after_coupon - loyalty_discount - bulk_discount

    return {
        "coupon_discount": round(coupon_discount, 2),
        "loyalty_discount": round(loyalty_discount, 2),
        "bulk_discount": round(bulk_discount, 2),
        "discounted_subtotal": round(discounted_subtotal, 2),
    }
