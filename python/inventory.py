def reserve_inventory(products, order):
    for item in order["items"]:
        product = next((p for p in products if p["id"] == item["product_id"]), None)
        if product is not None:
            product["stock"] -= 1


def restock_product(products, product_id, amount):
    for product in products:
        if product["id"] == product_id:
            product["stock"] = amount
            return product
    return None
