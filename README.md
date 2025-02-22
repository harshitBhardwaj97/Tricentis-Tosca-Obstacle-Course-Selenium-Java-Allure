# Tricentis Tosca Obstacle Course (Selenium-Java)

## Overview

This automation testing framework is built for automating
the [Obstacle-Course](https://obstaclecourse.tricentis.com/Obstacles/List), offered by Tricentis-Tosca.

![image](https://github.com/user-attachments/assets/895cea60-b788-4990-9d59-8d898553332c)

![image](https://github.com/user-attachments/assets/ad0e1838-9c0d-422d-b629-043a2bb797e5)

![image](https://github.com/user-attachments/assets/941dd02e-26fd-4c40-84f2-bcc06f5e9645)

![image](https://github.com/user-attachments/assets/9932a89e-36ae-422c-a9ea-5f961b52b6e8)

[Obstacle-Course](https://obstaclecourse.tricentis.com/Obstacles/List) is a set of mini-challenges, ranging
from simple tasks such as **clicking on a button** to complex ones like **Bubble-Sort** or playing
**Tricentis-Olympics**, among others.

Although the [course](https://obstaclecourse.tricentis.com/Obstacles/List) is designed to gauge the working
knowledge of the Tosca automation tool, I have automated the challenges using
the following **cutting-edge tools and technologies** - Selenium WebDriver, Java, TestNG, and Allure Reporter.

The framework uses **Maven** for project management and dependency resolution. It follows **best practices** in test
automation (and some hacks also wherever absolutely needed), and is designed to showcase the **capabilities and power of
Selenium** when used properly.

### Dependency Injection (DI) in the Framework

In this framework, we implement **Dependency Injection** (DI) to manage the dependencies between different components
efficiently. DI allows us to decouple the components and make the framework more modular and easier to maintain.

We leverage **constructor-based injection** to inject dependencies into our classes, ensuring that the required
components are available to be used in the concerned class.

Additionally, **TestNG**'s `@BeforeMethod` and `@AfterMethod` annotations, in combination with DI, allow for proper
initialization of dependencies before each test method and cleanup afterward. This ensures that each test case starts
with a fresh state, making tests isolated and repeatable.

This DI setup also aids in writing scalable, maintainable, and reusable tests across various pages and actions within
the application.

## Project Structure

The project is organized as follows:

### `src/main/java/`

This folder contains the main application code for the framework.

- **`config/`**: Contains the code to read the configuration properties such as browser type,
- headless mode or any other runtime configurations.
- **`driver/`**: Manages WebDriver instances and browser setup for tests.
- **`listeners/`**: Contains the implementation of TestNG listeners to capture test lifecycle events and generate Allure
  reports (such as screenshots, logs, etc.).
- **`pages/`**: Contains page objects for the all the tests. These are used to model the different pages of the
  Tricentis obstacle course,
  and contains the core logic for the current challenge/problem at hand.
- **`support/`**: One of the most important package, abstracts the common operations and waiting mechanisms,
  to make tests resilient and easy and efficient to write.
- **`utils/`**: Includes general utility classes for common tasks across the framework (e.g., path creation, files
  creation etc.).
- **`constants/`**: Defines constants used throughout the framework, such as URLs, file paths, and wait times.

### `src/main/resources/`

This folder contains the non-code resources used by the framework.

- **`general.properties`**: Holds the configuration properties like browser and headless mode.

### `src/test/java/`

This folder contains the test code.

- **`base/`**: Contains base classes for tests, responsible for setup and teardown, and also instantiation of the driver
  and injecting that in the required classes.
- **`tests/`**: Contains the actual test classes, implementing test cases for the Tricentis obstacle course.

### `src/test/resources/`

This folder contains test resources.

- **`all.xml`**: The TestNG suite file to define and run tests, including configurations for parallel execution,
  listeners, and reporting. Includes all the 40 challenges listed on the site.

## Key Components

### 1. **Configuration Management (`Configuration` & `ConfigurationManager`)**

- **`Configuration`** defines the application properties (e.g., browser, headless mode) using the **Owner** library.
- **`ConfigurationManager`** is a singleton utility to load and manage configuration properties for the framework.

### 2. **WebDriver Management (`WebDriverManager`)**

- Class to manage Selenium WebDriver instances.
- It utilizes **BrowserFactory** and **DriverFactory** to efficiently handle browser-specific WebDriver creation,
  especially when running tests in parallel.
    - **DriverFactory** provides a utility to initialize WebDriver instances for different browsers (Chrome, Firefox,
      Edge, Safari) by using **BrowserFactory**. It ensures that each test gets a separate, isolated WebDriver instance
      to run parallel tests without conflicts.
    - **BrowserFactory** defines a browser-specific configuration and is responsible for creating the appropriate
      WebDriver instance, including options like headless execution and downloading preferences.

### 3. **Allure Reporting (`AllureTestLifeCycleListener`)**

- Custom listener that integrates **TestNG** with **Allure**.
- Automatically captures screenshots, logs, and other relevant details for test success, failure, or skip, and attaches
  them to the Allure report.
- Allows detailed visual reporting, including failure reasons, screenshots, and logs for every test case.

### 4. **Test Execution (`BaseTest` and `Test Classes`)**

- **`BaseTest`** serves as the foundation for all test classes. It initializes the WebDriver, manages test execution
  lifecycle events (setup, teardown), and handles reporting.
- **Test classes** extend `BaseTest` and contain the actual test cases for the Tricentis obstacle course application.
- **Sample Page and Sample Test**:
    - These sample classes provide a better understanding of the framework, and how to apply all the concepts.

## Dependencies

This project uses the following key dependencies:

- **Selenium**: For browser automation.
- **TestNG**: For test execution and reporting.
- **Rest-Assured**: For performing API operations in the relevant challenges.
- **Allure**: For generating beautiful and detailed reports.
- **Owner**: For managing configuration properties.
- **SLF4J & Logback**: For logging purposes.

## Reporting

Test execution results are captured and displayed using **Allure** reports. The following events are logged:

- **Test Start**: Each test execution is logged when it begins.
- **Test Success**: If the test passes, a screenshot is captured, and the test status is logged.
- **Test Failure**: In case of a failure, a screenshot is captured, and the failure details are logged in Allure.
- **Test Skip**: If a test is skipped, the skip reason is captured.

If you found this project useful, and learned something from it, then feel free to give it a star! ⭐, and also share
it with your colleagues.
