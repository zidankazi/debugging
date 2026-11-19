const { getProducts, getCustomers, getOrders, getTaxRates } = require("./data");
const { processOrder, calculateSubtotal } = require("./orders");
const { findProductByName, lowStockProducts, categorySummary } = require("./reports");

function check(condition, message) {
  if (!condition) {
    throw new Error(message);
  }
}

function main() {
  const products = getProducts();
  const customers = getCustomers();
  const orders = getOrders();
  const taxRates = getTaxRates();

  check(findProductByName(products, "  keyboard "), "Search should trim and ignore case");
  check(findProductByName(products, "MOUSE"), "Search should ignore case");

  const lowNames = lowStockProducts(products, 5).map(p => p.name);
  check(lowNames.includes("Pen Pack"), "Low-stock report should include out-of-stock products");

  const summary = categorySummary(products);
  check(summary.Electronics.productCount === 2, "Electronics should have 2 products");
  check(summary.Electronics.totalStock === 16, "Electronics should have 16 total stock");

  const subtotal = calculateSubtotal(orders[0], products);
  check(Math.abs(subtotal - 119.97) < 0.001, "Order 1001 subtotal should be 119.97");

  const result = processOrder(orders[0], products, customers, taxRates);
  check(result.valid === true, "Order 1001 should be valid");
  check(products[0].stock === 10, "Keyboard stock should decrease by 2");
  check(products[1].stock === 3, "Mouse stock should decrease by 1");

  const invalidResult = processOrder(orders[1], products, customers, taxRates);
  check(invalidResult.valid === false, "Order 1002 should be invalid because Pen Pack is out of stock");

  console.log("All debugging scenarios passed.");
}

main();
