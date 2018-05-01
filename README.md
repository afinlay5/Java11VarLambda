# Java 11 Sneak Peek: Local-Variable Type Inference (var) extended to Lambda Expression Parameters
Gradle source code repository for the examples from my blog post, http://bit.ly/Java11Ft1.
Built and tested on Linux Mint, SUSE Linux.

![alt text](https://raw.githubusercontent.com/afinlay5/Java11VarLambda/master/blog.png)

# Platform 
- Any supporting a JVM for Java SE 11+.

# Requirements
- Gradle 4.7 
- Java 11 or greater

# Gradle Tasks
- ./gradlew build - Build Application
- ./gradlew run - Runs the main file in the console, which calls the main method of all of the code samples in one file.

# Known Problems
- Fails with openJDK 11 EA+11 (04/30/2018).
- Fails with Oracle JDK 11 EA+11 (04/30/2018).
- Bug: Incorrect attempt to cast the result of java.lang.reflect.Method.invoke() to java.lang.invoke.SerializedLambda (04/30/2018).

# Execution Screenshot
<!-- ![alt text](https://raw.githubusercontent.com/afinlay5/Java11Var/master/gradle_run.png) -->
![alt text](https://raw.githubusercontent.com/afinlay5/Java11VarLambda/master/run.png)