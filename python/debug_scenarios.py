from data import get_products, get_customers, get_orders, get_tax_rates
from orders import process_order, calculate_subtotal
from reports import find_product_by_name, low_stock_products, category_summary


def check(condition, message):
    if not condition:
        raise AssertionError(message)


def main():
    products = get_products()
    customers = get_customers()
    orders = get_orders()
    tax_rates = get_tax_rates()

    check(find_product_by_name(products, "  keyboard ") is not None, "Search should trim and ignore case")
    check(find_product_by_name(products, "MOUSE") is not None, "Search should ignore case")

    low_names = [p["name"] for p in low_stock_products(products, 5)]
    check("Pen Pack" in low_names, "Low-stock report should include out-of-stock products")

    summary = category_summary(products)
    check(summary["Electronics"]["product_count"] == 2, "Electronics should have 2 products")
    check(summary["Electronics"]["total_stock"] == 16, "Electronics should have 16 total stock")

    subtotal = calculate_subtotal(orders[0], products)
    check(abs(subtotal - 119.97) < 0.001, "Order 1001 subtotal should be 119.97")

    result = process_order(orders[0], products, customers, tax_rates)
    check(result["valid"] is True, "Order 1001 should be valid")
    check(products[0]["stock"] == 10, "Keyboard stock should decrease by 2")
    check(products[1]["stock"] == 3, "Mouse stock should decrease by 1")

    invalid_result = process_order(orders[1], products, customers, tax_rates)
    check(invalid_result["valid"] is False, "Order 1002 should be invalid because Pen Pack is out of stock")

    print("All debugging scenarios passed.")


if __name__ == "__main__":
    main()
