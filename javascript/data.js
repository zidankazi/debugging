function getProducts() {
  return [
    { id: "P100", name: "Keyboard", category: "Electronics", price: 49.99, stock: 12 },
    { id: "P101", name: "Mouse", category: "Electronics", price: 19.99, stock: 4 },
    { id: "P102", name: "Notebook", category: "Office", price: 3.5, stock: 30 },
    { id: "P103", name: "Pen Pack", category: "Office", price: 5.25, stock: 0 },
    { id: "P104", name: "Water Bottle", category: "Fitness", price: 14.0, stock: 7 },
    { id: "P105", name: "Yoga Mat", category: "Fitness", price: 22.5, stock: 2 }
  ];
}

function getCustomers() {
  return [
    { id: "C001", name: "Alice Carter", state: "NJ", loyalty: true },
    { id: "C002", name: "Ben Diaz", state: "NY", loyalty: false },
    { id: "C003", name: "Cara Ellis", state: "PA", loyalty: true }
  ];
}

function getOrders() {
  return [
    {
      id: 1001,
      customerId: "C001",
      coupon: "SAVE10",
      items: [
        { productId: "P100", quantity: 2 },
        { productId: "P101", quantity: 1 }
      ]
    },
    {
      id: 1002,
      customerId: "C002",
      coupon: "FIVEOFF",
      items: [
        { productId: "P102", quantity: 10 },
        { productId: "P103", quantity: 2 }
      ]
    },
    {
      id: 1003,
      customerId: "C003",
      coupon: null,
      items: [
        { productId: "P105", quantity: 2 },
        { productId: "P104", quantity: 3 }
      ]
    }
  ];
}

function getTaxRates() {
  return {
    NJ: 0.06625,
    NY: 0.08875,
    PA: 0.06
  };
}

module.exports = {
  getProducts,
  getCustomers,
  getOrders,
  getTaxRates
};
