Components:  
-Java version 23.0.1  
-Gradle 8.11  
-TestNG 7.10.1  
-Playwright 1.45.1  
-Allure 2.29.0  

Key features:  
-Page Object Model (storing base page locators and common functionality)  
-Data-Driven (using TestNG DataProvider)  
-Provides detailed test report (using Allure reporting with detailed test steps)  
-Records trace logs for all requests (added to report and can be viewed here: https://trace.playwright.dev/)  
-Records video of test run (added to report)  
-Parallel test execution (test class based)  
-No separate test suite maintenance (tests executed by tag/group, managed on test case level)  
-Rerun tests on failure (using gradle.test-retry)  

Run tests via IDE:  
build.gradle test{} config for local IDE execution/debug  

Run tests via CMD:  
`./gradlew clean cmdRunTests -Dbrowser=chromium -Dtag=regression -Durl=https://wikipedia.org -Dheadless=false --info`  
Browsers - chromium/firefox/webkit, -Dtag executes tests by TestNG group - sanity/regression  

Display Allure report:  
`allure serve .\build\allure-results\`