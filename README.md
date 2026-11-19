# Advanced Debugging Workshop: Order Processing System

This workshop contains three parallel versions of the same intentionally buggy app:

- `python/`
- `javascript/`
- `java/`

Each version models a small order processing system with products, customers, orders, discounts, inventory updates, and reports.

The goal is not to rewrite the program. The goal is to use the VS Code debugger to identify and fix defects.

## Suggested Team Structure

Each team should have around 6 students. Suggested ownership:

1. **Data + models**: understand the product/customer/order structures
2. **Validation**: fix customer/order/product validation logic
3. **Discounts**: fix coupon, loyalty, and bulk-discount logic
4. **Order totals**: fix subtotal, tax, shipping, and final total
5. **Inventory**: fix stock reservation and restocking behavior
6. **Reports/search**: fix revenue reports, low-stock reports, and search behavior

Students should coordinate because bugs interact across modules.

## What the App Should Do

The app should correctly:

1. Load sample data
2. Validate orders
3. Calculate order subtotals from product price × quantity
4. Apply coupons correctly
5. Apply loyalty discounts only to eligible customers
6. Apply bulk discounts only when the quantity threshold is met
7. Calculate tax based on the customer's state
8. Apply free shipping only when the correct threshold is reached
9. Reserve inventory by subtracting purchased quantities
10. Reject orders with invalid customers/products/quantities
11. Generate accurate revenue and category summaries
12. Find products case-insensitively and with trimmed input
13. Sort products correctly
14. Avoid mutating data unexpectedly during reports

## VS Code Debugging Requirements

Students should use:

- Breakpoints
- Step Over / Step Into
- Variables panel
- Watch expressions
- Call stack
- Conditional breakpoints where useful

## Do Not Give Students the Instructor Key

The file `instructor-key.md` contains the intended bug list and expected fixes.
