# java-exceptions


**UUID** (Universally Unique Identifier), also known as GUID (Globally Unique Identifier) represents a 128-bit long value that is unique for all practical purposes. 
The standard representation of the UUID uses hex digits (octets):
A UUID is made of up of hex digits  (4 chars each) along with 4 “-” symbols which make its length equal to 36 characters.

The UUID uses java.security.SecureRandom, which must be "cryptographically strong." Although this means that all results must be performed with a statistical test of a random number generator.

An exception  is a problem that arises during the execution of a program. It is not normal to terminate abnormally, and therefore it is recommended.
An exception can occur for many different reasons. Following are some scenarios where an exception occurs.
A user has entered an invalid data.
Cannot be found.

Based on these, we have three categories of exceptions.

**Checked exceptions** - This option is checked by the compiler at compilation time. The programmer should not take care of (handle) these exceptions.

**Unchecked exceptions** − An unchecked exception is an exception that occurs at the time of execution. These are also called as Runtime Exceptions.
These include programming bugs, such as logic errors or improper use of an API. Runtime exceptions are ignored at the time of compilation.

**Errors** − These are not exceptions at all, but problems that arise beyond the control of the user or the programmer. 
Errors are typically ignored in your code because you can rarely do anything about an error. For example, if a stack overflow occurs, an error will arise. 
They are also ignored at the time of compilation.

Operations with URL:
   
   **ADD** - you need to enter the link that will be added. This method returns the ID of the given link. You can add up to 10 links;
   
   **CHECK** - you need to enter the link, if the link has already been added, its ID will be returned. Otherwise, a message will be displayed that this link does not exist;
   
   **GET** - you need to enter the ID, if the ID has already been created, then its link will be returned. Otherwise, a message will be displayed that this ID does not exist;
   
   **DELETE** - you need to enter an ID to remove the link. The name of the link that was deleted will be returned;
   
   **CLEAN_ALL** - when you start the CLEAN method, all links will be deleted and the empty table returned.