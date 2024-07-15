# Web Automation Tests

## Overview

This Test Automation Framework is created using Java + TestNG + Selenium, which used fo web automation E2E testing.

## Prerequisites

Ensure you have the following software installed on your system:

* IntelliJ or Eclipse
* Git
* JDK 11
* Apache Maven 3.6.x (https://maven.apache.org/download.cgi)
* Google Chrome
* ChromeDriver (WebDriver for Chrome) (https://googlechromelabs.github.io/chrome-for-testing/)

## Getting Started

1. Clone the repository:
    ```bash
    git clone https://github.com/arsen-dadunts/Web_Automation.git
    cd WebAutomation
    ```
2. Build the project:
    ```bash
   mvn clean install
    ```
3. Install chromedriver

## Usage
To run your TestNG tests, use the following Maven command:
```bash
mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml
```

## Project Structure

The project consists of the following files:
- src to store tests: Page classes, tests' data, test scenarios, utility functions
- pom.xml maven project file

```bash
WebAutomation/
├── pom.xml
├── README.md
└── src
   ├── main
   │   ├── java
   │   │   └── com
   │   │       └── picsart
   │   │           ├── base
   │   │           │   └── BasePage.java
   │   │           ├── constants
   │   │           │   └── CommonAssertionMessages.java
   │   │           ├── pages
   │   │           │   ├── EditPage.java
   │   │           │   ├── HomePage.java
   │   │           │   └── SearchPage.java
   │   │           └── utils
   │   │               ├── Configs.java
   │   │               └── DriverManager.java
   │   └── resources
   │       └── config.properties
   └── test
       ├── java
       │   └── com
       │       └── picsart
       │           ├── BaseTest.java
       │           └── search
       │               └── CustomTest.java
       └── resources
           └── testng.xml
```
