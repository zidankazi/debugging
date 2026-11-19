const { getProducts, getCustomers, getOrders, getTaxRates } = require("./data");
const { restockProduct } = require("./inventory");
const { processOrder } = require("./orders");
const {
  categorySummary,
  findProductByName,
  lowStockProducts,
  productsSortedByPrice,
  revenueSummary
} = require("./reports");

function printProductTable(products) {
  console.log("ID     NAME           CATEGORY       PRICE    STOCK");
  console.log("-------------------------------------------------------");

  for (const product of products) {
    console.log(
      `${product.id.padEnd(6)} ` +
      `${product.name.padEnd(14)} ` +
      `${product.category.padEnd(13)} ` +
      `$${product.price.toFixed(2).padEnd(7)} ` +
      `${String(product.stock).padEnd(5)}`
    );
  }

  console.log();
}

function main() {
  const products = getProducts();
  const customers = getCustomers();
  const orders = getOrders();
  const taxRates = getTaxRates();

  console.log("=== STARTING CATALOG ===");
  printProductTable(products);

  console.log("=== SEARCH ===");
  console.log("Searching for '  keyboard ':", findProductByName(products, "  keyboard "));
  console.log("Searching for 'MOUSE':", findProductByName(products, "MOUSE"));
  console.log();

  console.log("=== LOW STOCK ===");
  for (const product of lowStockProducts(products)) {
    console.log(product);
  }
  console.log();

  console.log("=== CATEGORY SUMMARY ===");
  console.log(categorySummary(products));
  console.log();

  console.log("=== PROCESSING ORDERS ===");
  const processed = [];
  for (const order of orders) {
    const result = processOrder(order, products, customers, taxRates);
    processed.push(result);
    console.log(result);
  }
  console.log();

  console.log("=== CATALOG AFTER ORDERS ===");
  printProductTable(products);

  console.log("=== SORTED BY PRICE ===");
  const sortedProducts = productsSortedByPrice(products);
  printProductTable(sortedProducts);

  console.log("=== RESTOCK P103 BY 20 ===");
  console.log(restockProduct(products, "P103", 20));
  console.log();

  console.log("=== REVENUE SUMMARY ===");
  console.log(revenueSummary(processed));
}

main();
