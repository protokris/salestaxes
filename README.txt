README

Table of Contents
1. Program Description
2. Build Instructions
3. Running All Tests
4. Running the Carts Test Cases
5. Test Coverage



1. Program Description

The application provides a general, extensible API which can be used to setup 
kinds of items (at runtime) and apply taxes to types of items (at runtime).  

There is a store API for creating items, adding them to the store's inventory,
and purchasing items- which adds them to receipts that can then be printed.  

I have supplied a test case to show that it can meet the criteria below: 

"Basic sales tax is applicable at a rate of 10% on all goods, except books,
food, and medical products that are exempt. Import duty is an additional
sales tax applicable on all imported goods at a rate of 5%, with no 
exemptions.

When I purchase items I receive a receipt which lists the name of all the
items and their price (including tax), finishing with the total cost of the
items, and the total amounts of sales taxes paid.  The rounding rules for
sales tax are that for a tax rate of n%, a shelf price of p contains
(n*p/100 rounded up to the nearest 0.05) amount of sales tax. (See: PROBLEM.txt)"

This application (via the InputTest test case) prints out the receipt 
details for three example shopping baskets.  

** Javadoc for API is available in /doc **




2. Build Instructions

You can build this project with Eclipse, or using Ant with the supplied build.xml file.

Ant is recommended but you must install Ant on your local machine and put 'ant' into
your path.  Ant requires that you have both the JAVA_HOME and ANT_HOME variables set. 

To build the project using ant from the console, cd to the project folder, and type: 

	ant compile

To build the project with eclipse, import the project and then make sure that both
the "src" and "test" folders are sources for the project. Make sure that eclipse has 
imported the jar files from the "lib" folder onto the classpath. 





3. Running All Tests

This program comes with a fairly large suite of unit tests, and was developed with a 
test-driven approach. 

To run the tests in eclipse, you will want to launch 'AllTests' as a JUnit test. To 
run the tests with ant, cd to the project folder and type: 

	ant test





4. Running the Carts Test Case

To run tests which simulate three shopping baskets, run 'InputTest' as a Junit test, or cd to the 
project folder and type: 

	ant
	
	or 

	ant run

This test outputs the receipt details to standard out (and asserts that all of the
output values are correct). 





5. Test Coverage

Cobertura has been used to generate test coverage documentation in /coverage.

To re-generate the code coverage documents (unnecessary) cd to the project folder and type: 

	ant coverage
