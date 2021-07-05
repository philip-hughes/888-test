package tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(BASE_URL + "football/europe/euro-2020/");
		euroHome = new EuroHomePage(driver);
	}
	
	@AfterClass
	public void closeBrowsers() {
		driver.quit();
	}
	
	/*
	 * Test to verify that the Match Result tab is selected by default when the
	 * Euro-2020 page is loaded.	
	 */
	@Test
	public void tabTest() {
		String attr = euroHome.matchResultTab().getAttribute("data-selected-item");
		Assert.assertEquals(attr, "true");
	}
	
	/*
	 * Test to verify that all 3 odds (Home win '1', Draw 'X', Away win '2') for the first
	 *  match listed are all in the fractional format N/N, where Ns are numbers.
	 */
	@Test
	public void firstMatchOddsFormat() {
		List <Match> matches = getMatchesByDay(euroHome, "All");
		Match firstMatch = matches.get(0);
		String [] allMatchOdds = {firstMatch.getHomeWinDisplayedOdds(),firstMatch.getAwayWinDisplayedOdds(), firstMatch.getDrawDisplayedOdds() };

		for(String matchOdds: allMatchOdds) {
			isFractional(matchOdds);
		}
		
	}
	
	/*
	 * Test to verify that 'Ireland' is not listed in tomorrow's matches.
	 */
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
	
	/*
	 * Test to verify that the odds for England to win its next
	 *  Home match (where it's listed first against its opponent) are lower than 3/2.
	 */
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
