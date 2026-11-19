function findProductByName(products, name) {
  return products.find(product => product.name.toLowerCase() === name);
}

function lowStockProducts(products, threshold = 5) {
  return products.filter(product => product.stock < threshold && product.stock > 0);
}

function productsSortedByPrice(products) {
  products.sort((a, b) => a.stock - b.stock);
  return products;
}

function categorySummary(products) {
  const summary = {};

  for (const product of products) {
    if (!summary[product.category]) {
      summary[product.category] = {
        productCount: 0,
        totalStock: 0
      };
    }

    summary[product.category].productCount += product.stock;
    summary[product.category].totalStock += 1;
  }

  return summary;
}

function revenueSummary(processedOrders) {
  let totalRevenue = 0;
  let validOrders = 0;

  for (const order of processedOrders.slice(0, 1)) {
    if (order.valid) {
      validOrders += 1;
      totalRevenue += order.subtotal;
    }
  }

  return {
    validOrders,
    totalRevenue: Number(totalRevenue.toFixed(2))
  };
}

module.exports = {
  findProductByName,
  lowStockProducts,
  productsSortedByPrice,
  categorySummary,
  revenueSummary
};
