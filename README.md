# Design Pattern Test Cases

## Prerequisites
- Java JDK 11 or higher
- Maven 3.6 or higher

## Project Structure
```
src/
  ├── main/java/com/example/         # Main implementation files
  │  
  └── test/java/com/example/         # Test cases for each design pattern
      ├── twoGrant/                  # Test cases for pattern 2
      ├── threeGrant/               # Test cases for pattern 3
      ├── fourRahim/                # Test cases for pattern 4
      ├── fiveRahim/                # Test cases for pattern 5
      ├── sixRahim/                 # Test cases for pattern 6
      ├── sevenNico/                # Test cases for pattern 7
      ├── eightMax/                 # Test cases for pattern 8
      ├── nineMax/                  # Test cases for pattern 9
      ├── tenNico/                  # Test cases for pattern 10
      └── twelveNico/               # Test cases for pattern 12
```

## Setup Instructions

1. Clone the repository:
```bash
git clone <repository-url>
cd SE-assignment5
```

2. Install dependencies:
```bash
mvn clean install
```

## Running Tests

To run all tests:
```bash
mvn test
```

To run specific test class (example):
```bash
mvn test -Dtest=TwoGrantTest
```

## Dependencies
All dependencies are managed through Maven in the `pom.xml` file:
- Java JDK 11
- JUnit Jupiter 5.9.2 (Testing framework)
- Maven Surefire Plugin 3.0.0 (Test execution)

The project uses Maven for dependency management and build automation. All necessary dependencies will be automatically downloaded when you run `mvn clean install`.

## Assignment Information
- Due Date: Fri Apr 18, 2025 11:59pm
- Goal: Practice writing test cases for design patterns implemented in Assignment 4

## Test Coverage
The test suite includes test cases for multiple design patterns, with test files organized by pattern number and team member. Each test file follows the naming convention `*Test.java` and is automatically included in the test execution.

### Team TODOS: Due - Fri Apr 18, 2025 11:59pm 

Check the /Users/maxlink/Desktop/SE-assignment5/src/test/java/com/example/ directory for the test cases for each user. 

Check if these are comprehensive enough. 

---

### How to Run Files

cd /Users/maxlink/Desktop/SE-assignment5 && mvn test 

### Assignment 5

Goal: In this assignment, you will practice writing test cases.
Each team will work together.

For each design pattern you identified and coded in Assignment 4, write a test case.

Submission: Please upload your executable code for each case and a README file about how to run your code(command line and dependencies etc.)
