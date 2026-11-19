function isNonEmptyString(value) {
  return typeof value === "string" && value.length > 0;
}

function findProductById(products, productId) {
  return products.find(product => product.id === productId);
}

function findCustomerById(customers, customerId) {
  return customers.find(customer => customer.id === customerId);
}

function validateOrder(order, products, customers) {
  const errors = [];

  if (!order.id) {
    errors.push("Order is missing an id");
  }

  if (!isNonEmptyString(order.customerId)) {
    errors.push("Order is missing a customer id");
  }

  const customer = findCustomerById(customers, order.customerId);
  if (!customer) {
    errors.push("Customer does not exist");
  }

  if (!order.items || order.items.length === 0) {
    errors.push("Order must contain at least one item");
  }

  for (const item of order.items || []) {
    const product = findProductById(products, item.productId);

    if (!product) {
      errors.push(`Product does not exist: ${item.productId}`);
      continue;
    }

    if (typeof item.quantity !== "number") {
      errors.push(`Invalid quantity for ${product.name}`);
    } else if (item.quantity < 0) {
      errors.push(`Quantity must be positive for ${product.name}`);
    }

    if (item.quantity && product.stock < 1) {
      errors.push(`Not enough inventory for ${product.name}`);
    }
  }

  return errors;
}

module.exports = {
  isNonEmptyString,
  findProductById,
  findCustomerById,
  validateOrder
};
