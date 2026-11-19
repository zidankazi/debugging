from validators import validate_order

def reserve_inventory(products, order):

    if validate_order(order, products, order.get("customer_id")) == []:
    
        for item in order["items"]:
            product = next((p for p in products if p["id"] == item["product_id"]), None)
            if product is not None:
                product["stock"] -= item["quantity"]

                if product["stock"] < 0:
                    product["stock"] = 0

def restock_product(products, product_id, amount):
    for product in products:
        if product["id"] == product_id:
            product["stock"] += amount
            return product
    return None
