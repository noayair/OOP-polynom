The project written by:

Noa Yair - 313431702

Oriya Kronfeld - 314078585

Our project enable the user to build functions (there are 3 types - monom, polynom and complex function), do all kinds of mathematical operations, and draw them on the screen.
The project has 4 main classes: Monom, Polynom, ComplexFunctions and Function_GUI, when each of them has functions that can be used.

**Monom**- represents a simple "Monom" of shape a*x^b, where a is a real number and b is an integer (summed a none negative).

**Polynom**- represents a Polynom of shape a_1x^b_1+a_2x^b_2+….+a_nx^b_n and consists of linked list of monoms.

**ComplexFunction**- represents a complex function of type y=g(f1(x), f2(x)), where both f1, f2 are functions (or complex functions), y and x are real numbers and g is an operation: plus, mul, div, max, min, comp (f1(f2(x))). for example: "plus(mul(2x,-4+5x-x^5),3.2x)".
 
**Function_GUI** - can hold a collection of functions, write and read the functions to file and show them on GUI.

![ללא שם](https://user-images.githubusercontent.com/58184656/70723768-1f455900-1d02-11ea-9677-b34e08f3b409.png)
