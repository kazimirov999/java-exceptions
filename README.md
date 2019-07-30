# java-exceptions
<b>[Exceptions in Java](https://www.geeksforgeeks.org/exceptions-in-java/)</b><br />
***What is an Exception?***

An exception is an unwanted or unexpected event, which occurs during the execution of a program i.e at run time, that disrupts the normal flow of the program’s instructions.

***Error vs Exception***

Error: An Error indicates serious problem that a reasonable application should not try to catch.
Exception: Exception indicates conditions that a reasonable application might try to catch.

***[Exception Hierarchy](http://www.benchresources.net/wp-content/uploads/2017/02/exception-hierarchy-in-java.png)***

All exception and errors types are sub classes of class Throwable, which is base class of hierarchy.
One branch is headed by Exception. This class is used for exceptional conditions that user programs should catch.
NullPointerException is an example of such an exception.
Another branch,Error are used by the Java run-time system(JVM) to indicate errors having to do with the run-time environment itself(JRE).
StackOverflowError is an example of such an error.

***[Check vs Unchecked Exceptions](https://www.geeksforgeeks.org/checked-vs-unchecked-exceptions-in-java/)***

Checked: are the exceptions that are checked at compile time.
If some code within a method throws a checked exception, then the method must either handle the exception or it must specify the exception using throws keyword.

Unchecked are the exceptions that are not checked at compiled time.
In C++, all exceptions are unchecked, so it is not forced by the compiler to either handle or specify the exception.
It is up to the programmers to be civilized, and specify or catch the exceptions.

<b>[UUID](https://docs.oracle.com/javase/7/docs/api/java/util/UUID.html)</b><br />

In Java, there is a way to generate unique identifiers - UUID (universal unique identifier). As Wikipedia says:
The main purpose of the UUID is to allow distributed systems to uniquely identify information without a central request.
Thus, anyone can create a UUID and use it for an acceptable level of confidence that the identifier will not be used for anything else.
Information can be added to a common database without the need to resolve named conflicts.
In Java with Java 5, the class java.util.UUID was introduced, which contains methods for generating a UUID.

## Operations with URL<br />
<b>At first,you should write the command and argument(if u need).</b><br /> 

<b>UUID add(String URL)</b>  - Associates the URL value with the UUID key and return UUID key. Method can throw SizeOutOfBoundsException.<br />
<b>UUID check(String URL)</b>  - Returns UUID key if contains URL else throw ElementNotFoundException.<br />
<b>String getUrlById(UUID uuid)</b> - Returns URL if present UUID key else throw ElementNotFoundException.<br />
<b>String deleteById(UUID uuid)</b> - Delete and return URL and UUID if present UUID key else throw ElementNotFoundException.<br />
<b>Map cleanAll()</b> - Delete and return Map. <br />