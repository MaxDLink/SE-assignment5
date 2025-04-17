# Design Pattern Test Cases

## Prerequisites
- Java JDK 11 or higher
  - Verify installation: `java -version`
  - Download: [Oracle JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or [OpenJDK](https://adoptium.net/)
- Maven 3.6 or higher
  - Verify installation: `mvn -version`
  - Download: [Apache Maven](https://maven.apache.org/download.cgi)

## Project Structure
```
src/
  ├-- # files and their tests were collated together in one test directory 
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

2. Verify your Java and Maven installations:
```bash
java -version
mvn -version
```

3. Install dependencies and build the project:
```bash
mvn clean install
```
This will:
- Download all required dependencies
- Compile the source code
- Run the tests
- Create the necessary build artifacts

4. Verify the build was successful:
- Look for "BUILD SUCCESS" in the terminal output
- Check that the `target` directory was created

## Running Tests

To run all tests:
```bash
mvn test
```

To run specific test class (example):
```bash
mvn test -Dtest=TwoGrantTest
```

To run tests for a specific pattern (example):
```bash
mvn test -Dtest=com.example.twoGrant.*Test
```

## Dependencies
All dependencies are managed through Maven in the `pom.xml` file:
- Java JDK 11
- JUnit Jupiter 5.9.2 (Testing framework)
- Maven Surefire Plugin 3.0.0 (Test execution)

The project uses Maven for dependency management and build automation. All necessary dependencies will be automatically downloaded when you run `mvn clean install`.

## Troubleshooting

If you encounter any issues:

1. Java version mismatch:
   - Ensure you have JDK 11 or higher installed
   - Verify JAVA_HOME environment variable is set correctly

2. Maven build failures:
   - Try cleaning the project first: `mvn clean`
   - Then rebuild: `mvn install`

3. Test failures:
   - Check the `target/surefire-reports` directory for detailed test reports
   - Review the specific test class output for error messages

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
