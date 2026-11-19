# Instructor Key: Advanced Debugging Workshop

Do not distribute this file to students.

This key describes the intended defects. Line numbers may vary if you edit the files.

## Cross-Language Bug Map

Each language version contains substantially the same bug categories.

---

## Validation Bugs

### Bug: Blank strings are treated as valid
Some validators check only for `null` / `None` but do not trim strings.

Expected:
- Empty strings and whitespace-only strings should be invalid.

### Bug: Product/customer lookup is case-sensitive in places where it should not be
Expected:
- Product search by name should be case-insensitive and should trim input.

### Bug: Quantity validation allows zero or negative quantities
Expected:
- Quantity must be a positive integer.

### Bug: Missing customer/product may not stop order processing
Expected:
- Invalid orders should be rejected before totals or inventory updates happen.

---

## Discount Bugs

### Bug: Coupon `SAVE10` is treated as 10 dollars off instead of 10 percent off
Expected:
- `SAVE10` should reduce subtotal by 10%.

### Bug: Coupon `FIVEOFF` sometimes behaves like 5 percent off
Expected:
- `FIVEOFF` should subtract $5.00 from the subtotal.

### Bug: Loyalty discount is applied to all customers
Expected:
- Loyalty discount applies only when the customer is a loyalty member.

### Bug: Bulk discount uses `>` instead of `>=`
Expected:
- Bulk discount should apply when quantity is at least the threshold.

### Bug: Discount can push total below zero
Expected:
- Discounted subtotal should not go below zero.

---

## Order Total Bugs

### Bug: Subtotal uses price + quantity instead of price × quantity
Expected:
- Each line total should be product price multiplied by quantity.

### Bug: Tax is calculated from the wrong base
Expected:
- Tax should be based on discounted subtotal, not original subtotal.

### Bug: Shipping threshold is checked before discount in some versions
Expected:
- Free shipping should be based on the discounted subtotal.

### Bug: Rounding occurs too early
Expected:
- Round only final displayed totals, not every intermediate operation.

### Bug: Unsupported states default to the wrong tax rate
Expected:
- Unsupported/missing states should use 0 tax unless a policy says otherwise.

---

## Inventory Bugs

### Bug: Restock replaces stock rather than adding to stock
Expected:
- Restock should add the incoming amount.

### Bug: Reserve inventory subtracts 1 instead of the ordered quantity
Expected:
- Stock should decrease by the purchased quantity.

### Bug: Inventory can go negative
Expected:
- Orders should not reserve inventory if stock is insufficient.

### Bug: Inventory update may occur even when order validation fails
Expected:
- Invalid orders should not mutate inventory.

---

## Reports and Search Bugs

### Bug: Low-stock report excludes out-of-stock products
Expected:
- Out-of-stock products should be included in low-stock report.

### Bug: Product sorting uses stock or name instead of price
Expected:
- Products should sort by price ascending unless otherwise stated.

### Bug: Category summary mixes count and stock
Expected:
- Category summary should separately track number of products and total stock.

### Bug: Revenue report counts only first item or first order
Expected:
- Revenue should include all valid processed orders and all order items.

### Bug: Search does not trim/case-normalize input
Expected:
- Searching `"  keyboard "` should find `"Keyboard"`.

### Bug: Report methods mutate product order
Expected:
- Reporting functions should not unexpectedly reorder the original catalog.

---

## Suggested Expected Values Using the Sample Data

These values assume the corrected behavior and the provided sample data.

### Product Search
- Searching `"  keyboard "` should find `Keyboard`.
- Searching `"MOUSE"` should find `Mouse`.

### Low Stock With Threshold 5
Should include:
- Mouse, stock 4
- Pen Pack, stock 0
- Yoga Mat, stock 2

### Category Summary Before Processing Orders

Electronics:
- product count: 2
- total stock: 16

Office:
- product count: 2
- total stock: 30

Fitness:
- product count: 2
- total stock: 9

### Order 1001

Customer:
- Alice Carter, NJ, loyalty member

Items:
- Keyboard × 2
- Mouse × 1

Expected subtotal:
- 49.99 × 2 + 19.99 × 1 = 119.97

Expected discounts:
- SAVE10 coupon: 10% off = 11.997
- Loyalty discount: 5% off after coupon, depending on chosen policy
- The important workshop point is that teams should document and implement a consistent policy.

### Order 1002

Customer:
- Ben Diaz, NY, not loyalty

Items:
- Notebook × 10
- Pen Pack × 2

Expected issue:
- Pen Pack has stock 0, so the order should be rejected or partially rejected depending on the policy.
- Recommended policy: reject the whole order.

### Order 1003

Customer:
- Cara Ellis, PA, loyalty member

Items:
- Yoga Mat × 2
- Water Bottle × 3

Expected:
- Should process if stock is sufficient.

## Instructor Notes

The exercises are intentionally not pure syntax debugging. Most bugs require students to inspect state across functions.

Good prompts while circulating:
- “What did you expect this value to be at this breakpoint?”
- “Where did this value first become incorrect?”
- “Did this function mutate the shared product list?”
- “Is this bug in the caller or callee?”
- “What assumption is this module making about the data?”
