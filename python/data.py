def get_products():
    return [
        {"id": "P100", "name": "Keyboard", "category": "Electronics", "price": 49.99, "stock": 12},
        {"id": "P101", "name": "Mouse", "category": "Electronics", "price": 19.99, "stock": 4},
        {"id": "P102", "name": "Notebook", "category": "Office", "price": 3.50, "stock": 30},
        {"id": "P103", "name": "Pen Pack", "category": "Office", "price": 5.25, "stock": 0},
        {"id": "P104", "name": "Water Bottle", "category": "Fitness", "price": 14.00, "stock": 7},
        {"id": "P105", "name": "Yoga Mat", "category": "Fitness", "price": 22.50, "stock": 2},
    ]


def get_customers():
    return [
        {"id": "C001", "name": "Alice Carter", "state": "NJ", "loyalty": True},
        {"id": "C002", "name": "Ben Diaz", "state": "NY", "loyalty": False},
        {"id": "C003", "name": "Cara Ellis", "state": "PA", "loyalty": True},
    ]


def get_orders():
    return [
        {
            "id": 1001,
            "customer_id": "C001",
            "coupon": "SAVE10",
            "items": [
                {"product_id": "P100", "quantity": 2},
                {"product_id": "P101", "quantity": 1},
            ],
        },
        {
            "id": 1002,
            "customer_id": "C002",
            "coupon": "FIVEOFF",
            "items": [
                {"product_id": "P102", "quantity": 10},
                {"product_id": "P103", "quantity": 2},
            ],
        },
        {
            "id": 1003,
            "customer_id": "C003",
            "coupon": None,
            "items": [
                {"product_id": "P105", "quantity": 2},
                {"product_id": "P104", "quantity": 3},
            ],
        },
    ]


def get_tax_rates():
    return {
        "NJ": 0.06625,
        "NY": 0.08875,
        "PA": 0.06,
    }
