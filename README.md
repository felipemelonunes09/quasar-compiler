# QuasarGrammar - Grammar for Quasar Language

## Project Overview
This project defines the grammar for the **Quasar** programming language using ANTLR, a powerful parser generator for reading, processing, executing, and translating structured text or binary files. The grammar file includes definitions for various language components, such as variable declarations, arithmetic operations, conditional logic, loops, and I/O commands.

## Grammar Structure
The grammar leverages ANTLR’s syntax to define how the **Quasar** language is parsed, including commands like `if`, `while`, `read`, and `write`, as well as custom operators and terms for handling arithmetic and logical expressions.

### Key Features
- **Variable Handling**: Variables are declared, initialized, and type-checked within the `symbolTable` HashMap.
- **Type System**: Supports various types like `number`, `real`, and `text`, with semantic checks to prevent type mismatches.
- **Commands**:
  - **Variable Declaration**: `-var <identifier> : <type>;`
  - **Assignment**: `<identifier> -> <expression>;`
  - **If-Else**: `if (<condition>) { <commands> } else { <commands> }`
  - **While Loop**: `while (<condition>) { <commands> }` or  `do:while (<condition>) { <commands> }`
  - **I/O**: `read(<identifier>);`, `write(<value>);`
   
### Suported Types
- **number**: for integer numbers
- **realnumber**: for float numbers
- **text**: for strings

### ANTLR Sections
- **Header**: Imports essential Java classes and custom types, such as `Command`, `Var`, `Types`, and exception handling.
- **Members**: Defines utility methods for managing variables, types, and expressions.
- **Commands**: Implements Quasar’s control structures (e.g., `if`, `while`) and I/O commands.
- **Expressions**: Manages boolean and arithmetic expressions, enforcing type consistency.

### Target Generation
The compiler can compile the `.qs` file to C++ or java

## Getting Started

### Requirements
- Java Development Kit (JDK) installed.
- ANTLR version 4.x.

### How to Generate the Parser
1. After the installation of ANTLR and added to the project Execute
```bash
java -cp antlr-4.13.2-complete.jar org.antlr.v4.Tool QuasarGrammar.g4 -o src/io/compiler/core -package io.compiler.core
```

## How to Compile
```bash
java <path> <filename (without extension)> <target (java or cpp)>
```

## Code Examples
- Simples Calculator
- Voting System



