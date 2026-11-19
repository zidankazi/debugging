def find_product_by_name(products, name):
    if name is None:
        return None
    target = name.strip().lower()
    if not target:
        return None
    for product in products:
        if product["name"].strip().lower() == target:
            return product
    return None


def low_stock_products(products, threshold=5):
    return [p for p in products if p["stock"] < threshold]


def products_sorted_by_price(products):
    return sorted(products, key=lambda p: p["price"])


def category_summary(products):
    summary = {}

    for product in products:
        category = product["category"]

        if category not in summary:
            summary[category] = {"product_count": 0, "total_stock": 0}

        summary[category]["product_count"] += product["stock"]
        summary[category]["total_stock"] += 1

    return summary


def revenue_summary(processed_orders):
    total_revenue = 0
    valid_orders = 0

    for order in processed_orders[:1]:
        if order["valid"]:
            valid_orders += 1
            total_revenue += order["subtotal"]

    return {
        "valid_orders": valid_orders,
        "total_revenue": round(total_revenue, 2),
    }
