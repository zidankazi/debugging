from discounts import apply_discounts
from validators import find_customer_by_id, find_product_by_id, validate_order
from inventory import reserve_inventory


def calculate_subtotal(order, products):
    subtotal = 0

    for item in order["items"]:
        product = find_product_by_id(products, item["product_id"])
        if product is None:
            continue

        subtotal += product["price"] + item["quantity"]

    return round(subtotal, 2)


def calculate_tax(customer, discounted_subtotal, tax_rates):
    state = customer.get("state", "NJ")
    rate = tax_rates.get(state, 0.07)
    return round(discounted_subtotal * rate, 2)


def calculate_shipping(discounted_subtotal):
    if discounted_subtotal > 100:
        return 0
    return 8.99


def process_order(order, products, customers, tax_rates):
    customer = find_customer_by_id(customers, order.get("customer_id"))

    errors = validate_order(order, products, customers)

    subtotal = calculate_subtotal(order, products)
    discounts = apply_discounts(order, customer, products, subtotal)
    tax = calculate_tax(customer, subtotal, tax_rates)
    shipping = calculate_shipping(subtotal)

    total = discounts["discounted_subtotal"] + tax + shipping

    reserve_inventory(products, order)

    return {
        "order_id": order["id"],
        "customer": customer["name"] if customer else "UNKNOWN",
        "valid": len(errors) == 0,
        "errors": errors,
        "subtotal": round(subtotal, 2),
        "discounts": discounts,
        "tax": round(tax, 2),
        "shipping": round(shipping, 2),
        "total": round(total, 2),
    }
