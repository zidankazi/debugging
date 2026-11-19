# Team Debugging Plan

Use this as a suggested process during the workshop.

## Round 1: Get Oriented

- Run the app.
- Read the module names.
- Sketch the flow from `main` to the service modules.
- Identify the expected app behavior from the README.

## Round 2: Divide the Code

Suggested assignments:

| Student | Area |
|---|---|
| Student 1 | Data structures and sample data |
| Student 2 | Validation |
| Student 3 | Discounts |
| Student 4 | Order totals |
| Student 5 | Inventory |
| Student 6 | Reports/search |

Each student should debug their area but coordinate on shared assumptions.

## Round 3: Debug One Failure at a Time

Do not try to fix everything at once.

Recommended order:

1. Validation
2. Subtotal calculation
3. Discount calculation
4. Tax and shipping
5. Inventory updates
6. Reports/search
7. Edge cases

## Round 4: Explain Your Fixes

Each team member should be ready to explain:

- One bug they found
- How they found it
- The incorrect behavior
- The corrected behavior
- What VS Code debugging feature helped

While this is a group effort, you will be graded individually. 
