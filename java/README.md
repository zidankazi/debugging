# Java Version

No external dependencies are required.

## Compile

From the `java/` folder:

```bash
javac -d out src/*.java
```

## Run the App

```bash
java -cp out Main
```

## Run Debug Scenarios

```bash
java -cp out DebugScenarios
```

The debug scenarios intentionally fail at first. Use the VS Code debugger to identify why.

## Suggested VS Code Launch Config

You can create `.vscode/launch.json` with:

```json
{
  "version": "0.2.0",
  "configurations": [
    {
      "name": "Java: Main",
      "type": "java",
      "request": "launch",
      "mainClass": "Main",
      "projectName": "java"
    },
    {
      "name": "Java: Debug Scenarios",
      "type": "java",
      "request": "launch",
      "mainClass": "DebugScenarios",
      "projectName": "java"
    }
  ]
}
```
