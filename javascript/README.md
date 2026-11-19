# JavaScript Version

Requires Node.js.

## Run the App

```bash
node main.js
```

or:

```bash
npm start
```

## Run Debug Scenarios

```bash
node debugScenarios.js
```

or:

```bash
npm run debug-scenarios
```

The debug scenarios intentionally fail at first. Use the VS Code debugger to identify why.

## Suggested VS Code Launch Config

You can create `.vscode/launch.json` with:

```json
{
  "version": "0.2.0",
  "configurations": [
    {
      "name": "Node: Main",
      "type": "node",
      "request": "launch",
      "program": "${workspaceFolder}/javascript/main.js",
      "skipFiles": ["<node_internals>/**"]
    },
    {
      "name": "Node: Debug Scenarios",
      "type": "node",
      "request": "launch",
      "program": "${workspaceFolder}/javascript/debugScenarios.js",
      "skipFiles": ["<node_internals>/**"]
    }
  ]
}
```
