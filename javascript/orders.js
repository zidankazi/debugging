const { applyDiscounts } = require("./discounts");
const { reserveInventory } = require("./inventory");
const { findCustomerById, findProductById, validateOrder } = require("./validators");

function calculateSubtotal(order, products) {
  let subtotal = 0;

  for (const item of order.items) {
    const product = findProductById(products, item.productId);
    if (!product) continue;

    subtotal += product.price + item.quantity;
  }

  return Number(subtotal.toFixed(2));
}

function calculateTax(customer, discountedSubtotal, taxRates) {
  const state = customer.state || "NJ";
  const rate = taxRates[state] || 0.07;
  return Number((discountedSubtotal * rate).toFixed(2));
}

function calculateShipping(discountedSubtotal) {
  if (discountedSubtotal > 100) {
    return 0;
  }
  return 8.99;
}

function processOrder(order, products, customers, taxRates) {
  const customer = findCustomerById(customers, order.customerId);
  const errors = validateOrder(order, products, customers);

  const subtotal = calculateSubtotal(order, products);
  const discounts = applyDiscounts(order, customer, products, subtotal);
  const tax = calculateTax(customer, subtotal, taxRates);
  const shipping = calculateShipping(subtotal);

  const total = discounts.discountedSubtotal + tax + shipping;

  reserveInventory(products, order);

  return {
    orderId: order.id,
    customer: customer ? customer.name : "UNKNOWN",
    valid: errors.length === 0,
    errors,
    subtotal: Number(subtotal.toFixed(2)),
    discounts,
    tax: Number(tax.toFixed(2)),
    shipping: Number(shipping.toFixed(2)),
    total: Number(total.toFixed(2))
  };
}

module.exports = {
  calculateSubtotal,
  calculateTax,
  calculateShipping,
  processOrder
};
