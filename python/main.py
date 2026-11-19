from data import get_products, get_customers, get_orders, get_tax_rates
from inventory import restock_product
from orders import process_order
from reports import (
    category_summary,
    find_product_by_name,
    low_stock_products,
    products_sorted_by_price,
    revenue_summary,
)


def print_product_table(products):
    print("ID     NAME           CATEGORY       PRICE    STOCK")
    print("-" * 55)
    for product in products:
        print(
            f"{product['id']:<6} "
            f"{product['name']:<14} "
            f"{product['category']:<13} "
            f"${product['price']:<7.2f} "
            f"{product['stock']:<5}"
        )
    print()


def main():
    products = get_products()
    customers = get_customers()
    orders = get_orders()
    tax_rates = get_tax_rates()

    print("=== STARTING CATALOG ===")
    print_product_table(products)

    print("=== SEARCH ===")
    print("Searching for '  keyboard ':", find_product_by_name(products, "  keyboard "))
    print("Searching for 'MOUSE':", find_product_by_name(products, "MOUSE"))
    print()

    print("=== LOW STOCK ===")
    for product in low_stock_products(products):
        print(product)
    print()

    print("=== CATEGORY SUMMARY ===")
    print(category_summary(products))
    print()

    print("=== PROCESSING ORDERS ===")
    processed = []
    for order in orders:
        result = process_order(order, products, customers, tax_rates)
        processed.append(result)
        print(result)
    print()

    print("=== CATALOG AFTER ORDERS ===")
    print_product_table(products)

    print("=== SORTED BY PRICE ===")
    sorted_products = products_sorted_by_price(products)
    print_product_table(sorted_products)

    print("=== RESTOCK P103 BY 20 ===")
    print(restock_product(products, "P103", 20))
    print()

    print("=== REVENUE SUMMARY ===")
    print(revenue_summary(processed))


if __name__ == "__main__":
    main()
