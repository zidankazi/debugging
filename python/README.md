# Python Version

## Run the App

```bash
python main.py
```

## Run Debug Scenarios

```bash
python debug_scenarios.py
```

The debug scenarios intentionally fail at first. Use the VS Code debugger to identify why.

## Suggested VS Code Launch Config

You can create `.vscode/launch.json` with:

```json
{
  "version": "0.2.0",
  "configurations": [
    {
      "name": "Python: Main",
      "type": "debugpy",
      "request": "launch",
      "program": "${workspaceFolder}/python/main.py",
      "console": "integratedTerminal"
    },
    {
      "name": "Python: Debug Scenarios",
      "type": "debugpy",
      "request": "launch",
      "program": "${workspaceFolder}/python/debug_scenarios.py",
      "console": "integratedTerminal"
    }
  ]
}
```
