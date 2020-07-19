# Polynomial Arithmetic

## Background

Linked lists are useful for storing objects in chained containers (nodes). In this lab, you will be storing polynomials in (doubly) linked lists, then utilize them for polynomial arithmetic.

## Problem Statement

Your task in this project is to write a complete implementation for the class Polynomial which is used to implement the polynomial operations. You should only make changes inside this Polynomial class. 

You will need to store terms of a polynomial in the nodes of a linked list. The head should store the term with highest degree and the tail the term with lowest degree. You must represent all terms of the polynomial in descending order and make sure to represent missing terms with a zero constant.

The implementations of the classes for DNode, DList and Term are fully implemented for you. (The "D" indicates it is for a Doubly Linked List.) An abstract class PolynomialInterface provides a method to print Polynomials. 


## Test

A sample main function is provided so that you may test your code on sample inputs.
Inputs can be provided in the box below your code, please format your input as follows:

Example input
```
x^2 - x - 6
x + 2
```
output
```
p =  X^2 -X -6.0
q =  X + 2.0
Sum  X^2 -4.0
Difference  X^2 -2.0 X -8.0
Product  X^3 + X^2 -8.0 X -12.0
Quotient  X -3.0
Remainder 0
```

## Requirements

- The Polynomial constructor and add, subtract, product, quotient, and remainder methods must be completed.
- The code must work with the provided PolynomialInterface.java, DNode.java, DList.java, Term.java, and Utility.java classes and use the provided methods.







