# Four Function Calculator

This is a modular Four Function Calculator written in Java. The user interface and internal operations of the calculator are separated via Interfaces and are therefore modular. Here is the main structure of this repository:
* The `calc` package contains different implementations of the back end. The class with the main function (`calc.CalcFactory`) will also be foud in this package. 
* The `ui` package contains different implementations of the Front End, whether it be an interface to use the calculator through graphical windows, or through standard input.
* The `test` package contains classes for testing with JUnit4. The JUnit4.jar is included in the repository by convenience, but it will only be used if a system JUnit4.jar file is not available.
* Finally, a script `run_test.sh` is provided to manually compile and run tests.


# Acknowledgments
This calculator program is based off of a series of class projects (CSCI 245 - Programming II: Object Oriented Programming) from my time at Wheaton College. Although I wrote all the code in this repository, I acknowledge that the code is very inspired from Dr. Thomas VanDrunen's and Dr. Devin Pohly's starting code for the projects.
