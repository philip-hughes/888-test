package tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.EuroHomePage;
import pageObjects.Match;

public class EuroHomeTest extends BaseTest {
	
	WebDriver driver;
	EuroHomePage euroHome;
	
	
	@BeforeClass
	public void loadEuroHome() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
		driver = new ChromeDriver();
		driver.get("https://www.888sport.com/football/europe/euro-2020/");
		euroHome = new EuroHomePage(driver);
	}
	
	@AfterClass
	public void closeBrowsers() {
		driver.quit();
	}
	
		
	@Test
	public void tabTest() {
		euroHome.matchResultTab();
		String attr = euroHome.matchResultTab().getAttribute("data-selected-item");
		Assert.assertEquals("true", attr);
	}
	
	@Test
	public void firstMatchOddsFormat() {
		List <Match> matches = getMatchesByDay(euroHome, "All");
		Match firstMatch = matches.get(0);
		String [] allMatchOdds = {firstMatch.getHomeWinDisplayedOdds(),firstMatch.getAwayWinDisplayedOdds(), firstMatch.getDrawDisplayedOdds() };

		for(String matchOdds: allMatchOdds) {
			isFractional(matchOdds);
		}
		
	}
	
	@Test
	public void irelandNotListedTomorrow() {
		List <Match> matches = getMatchesByDay(euroHome, "Tomorrow");
		for(Match match: matches) {
			String homeTeam = match.getHomeTeamName().toUpperCase();
			String awayTeam = match.getAwayTeamName().toUpperCase();
			Assert.assertNotEquals(homeTeam, "IRELAND");
			Assert.assertNotEquals(awayTeam, "IRELAND");
		}
		
	}
	
	@Test
	public void checkHomeOddsForCountry() {
		
		float maxOdds = convertToDecimal("3/2");
		List <Match> matches = getMatchesByDay(euroHome, "All");
		for(Match match: matches) {
			String homeTeam = match.getHomeTeamName().toUpperCase();
			if(homeTeam.equals("ENGLAND") ) {
				float homeWinOdds = convertToDecimal(match.getHomeWinDisplayedOdds());
				Assert.assertTrue(homeWinOdds < maxOdds);
			}

		}
		
	}
	

	
}
