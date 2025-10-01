# My Custom Programming Language
A lightweight custom programming language designed for learning and experimentation. It supports basic data types, arithmetic operations, control flow, and functions with nested structures.


## Flow to understand code
1.  First go to Tokanize file in MyTokens
2.  Explore each and every file in MyTokes
3.  Go to MyAst
4.  Just read NodeTypes file first
5.  Then try to understand other files implimeted by it
6.  Then Explore AstTree file
7.  Then go to Evalaturs folder
8.  First Explore ValueTypes
9.  Then Explore other file
10. Then try to understand Evaualte file
## Features

- **Data Types**:  
  - `int` — integer numbers  
  - `bool` — boolean values (`true` / `false`)  

- **Variables**:  
Declare variables like:  
    ```
    int a = 2;
    bool b = false;
    ```
- **Arithmetic Operations**;

    Supports + and - operations, including expressions like:
    ```
    int x = (a + b) - c;
    ```

- **Control Flow**;

    if, else if, else with nested blocks:
    ```
    if(condition){
        // code
    } else if(condition){
        // code
    } else {
        // code
    }
    ```
- **Loops**;

    while loops for repeating code blocks:
    ```
    while(condition){
        // code
    }
    ```
- **Functions**;

    Define functions using:

    ```
    fun functionName(){
        // code
    }
    ```
    Call functions with:
    ```
    call functionName;
    ```