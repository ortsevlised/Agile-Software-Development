# Agile-Software-Development
## Assignment 2: Junit 5

This is a simple Program to calculate the price of a car insurance

As part of the assignment some modifications were made to the original InsuranceProgram class to make it testable:
- Private methods were changed to public in order to test them without using a nested test class, generally a unit test is intended to exercise the public interface of a class or unit. Therefore, private methods are implementation detail that you would not expect to test explicitly.
- Changed the number of accidents that makes the customer non insurable from 6 to 5, as there was nothing to handle the case when he had 6 accidents.
- These tests are probably not exactly what one would do in real life but it is just an attempt to demonstrate a few of the Junit 5 features.