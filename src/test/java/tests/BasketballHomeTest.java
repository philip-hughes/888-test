package tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.BasketballHomePage;
import pageObjects.EuroHomePage;
import pageObjects.Match;

public class BasketballHomeTest extends BaseTest{
	WebDriver driver;
	BasketballHomePage basketballHome;
	
	
	@BeforeClass
	public void loadEuroHome() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		basketballHome = new BasketballHomePage(driver);
		driver.get(BASE_URL + "basketball/");
		
	}
	
	@AfterClass
	public void closeBrowsers() {
		driver.quit();
	}

	@Test
	public void firstMatchOddsFormat() {
		List <Match> matches = getMatchesByDay(basketballHome, "All");
		Match firstMatch = matches.get(0);
		String [] allMatchOdds = {firstMatch.getHomeWinDisplayedOdds(),firstMatch.getAwayWinDisplayedOdds() };
		
		for(String matchOdds: allMatchOdds) {
			isFractional(matchOdds);
		}
		
	}
	
	@Test
	public void teamNotListedTomorrow() {
		List <Match> matches = getMatchesByDay(basketballHome, "Tomorrow");
		for(Match match: matches) {
			String homeTeam = match.getHomeTeamName().toUpperCase();
			String awayTeam = match.getAwayTeamName().toUpperCase();
			Assert.assertNotEquals(homeTeam, "LA LAKERS");
			Assert.assertNotEquals(awayTeam, "LA LAKERS");
		}
		
	}
	
}
