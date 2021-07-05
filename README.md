# 888 Test

This is a test automation coding exercise using the Selenium WebDriver in Java. Other tools and libraries include
Maven, TestNG

## Overview
Tests were written using the Page Object Model design pattern. In addition to the Page Objects a 'Match' class was created for easy management
and access to all match details of a given page.  The Match class is instantiated by passing a Page Object and a betCard element to the constructor.  The class will
then use these parameters to set the various details of the betCard / Match. e.g. homeTeamName, homeWinOdds etc.  All of these variables are then available on the object
via get methods and can be used in the test classes as required.

The Page Object Model pattern in combination with the Match class supports the testing of sports that use either 3-way or 2-way odds.  The 2-way odds tests
are demonstrated using the BasketballHomeTest.java which runs several tests against https://www.888sport.com/basketball/.

The type of odds (2-way or 3-way) is determined in the setOdds() method of the Match class by counting the number of bet-buttons in the specified bet-card.



```	
	+ src/main/java
		pageObjects
		 - BasketballHomePage.java
		 - EuroHomePage.java
		 - SportsPage.java
		utils
		 - Match.java
	
	+ src/test/java
		tests
		 - BaseTest.java
		 - BasketballHomeTest.java
		 - EuroHomeTest.java

```


## Installation and running tests
Prerequisites:
To run the tests locally you will first need the following installed on your machine.
- Maven
- Java 8
- ChromeDriver.  Available here - https://chromedriver.chromium.org/downloads
- Chrome browser

To get the code open a command window and run:

    git clone https://github.com/philip-hughes/888-test.git

You can also download the project zip from: 
    https://github.com/philip-hughes/888-test/archive/refs/heads/main.zip

You also need to configure the path to your ChromeDriver in the BaseTest class


```	
	+ src/test/java
		tests
		 - BaseTest.java	
```

e.g. public static final String DRIVER_PATH = "C:/test-automation/chromedriver.exe";

To run the tests open your terminal in the project root and run:

    mvn clean verify



