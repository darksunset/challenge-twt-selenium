# challenge-twt-selenium
TWT challenge

## Stack & Libraries
- Java
- Selenium Webdriver
- TestNG
- Datafaker
- Maven

## Prerequisites
- JDK (Version 22)
- Maven configuration
- IDE (IntelliJ)

## Project Components
- Page object models located in ```demo/src/main/java/org/evershop/pages```.
- Test classes located in ```demo/test/java/org/evershop/test/```.
- Data generation and DTOs located in ```demo/src/main/java/org/evershop/data/dto/```.

## Highlights
- It was implemented for Chrome browser only.

## How to run
1. Download Java JDK and configure environment variables as needed. https://www.oracle.com/java/technologies/downloads/
2. Install and configure Maven. https://maven.apache.org/download.cgi
3. In terminal, clone the repository
```
git clone https://github.com/darksunset/challenge-twt-selenium.git
```
### First way
1. Modify "Run configurations" of a test cycle with the information below.
```
test -DsuiteXmlFile=./src/test/java/org/evershop/resources/challengeTests.xml -f pom.xml
```

  <img width="476" alt="Screenshot 2024-08-06 at 12 46 47 AM" src="https://github.com/user-attachments/assets/8884616a-d6b8-432c-b9d8-4db6edf821f9">

2. Run the configuration

### Second way
1. Open the java project with IntelliJ.
2. Go to the test classes directory ```demo/test/java/org/evershop/test/generateOrder```
3. Open the test.
4. Locate over the class name, then right click.
5. Click on "Run CheckoutOrderTest".
   <img width="559" alt="Screenshot 2024-08-06 at 12 21 18 AM" src="https://github.com/user-attachments/assets/45e31029-d0df-4ecc-bcd1-041ad3def8e9">


