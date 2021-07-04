package stepdefs;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.EuroHome;
import pageObjects.Match;

import static utils.OddsUtilitiesTests.convertToDecimal;
import static utils.OddsUtilitiesTests.isFractional;

public class EuroHomeTest {
	
	public static final String DRIVER_PATH = "/Users/Phugh/OneDrive/Documents/test-automation/chromedriver.exe";
	WebDriver driver;
	EuroHome euroHome;
	
	
	@BeforeClass
	public void loadEuroHome() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
		driver = new ChromeDriver();
		driver.get("https://www.888sport.com/football/europe/euro-2020/");
		euroHome = new EuroHome(driver);
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
		List <Match> matches = euroHome.getMatchesByDay("All");
		Match firstMatch = matches.get(0);
		System.out.println("Printing home team." + firstMatch.getHomeTeam());	
		System.out.println("Printing Away team." + firstMatch.getAwayTeam());
		String homeOdds = firstMatch.getHomeWin();
		String awayOdds = firstMatch.getAwayWin();
		String drawOdds = firstMatch.getHomeWin();
		System.out.println("Printing home odds." + homeOdds);
		
		isFractional(homeOdds);
		isFractional(awayOdds);
		isFractional(drawOdds);
		
	}
	
	@Test
	public void irelandNotListed() {
		List <Match> matches = euroHome.getMatchesByDay("All");
		for(Match match: matches) {
			String homeTeam = match.getHomeTeam().toUpperCase();
			String awayTeam = match.getAwayTeam().toUpperCase();
			Assert.assertNotEquals(homeTeam, "IRELAND");
			Assert.assertNotEquals(awayTeam, "IRELAND");
		}
		
	}
	
	@Test
	public void checkHomeOddsForCountry() {
		
		float maxOdds = convertToDecimal("3/2");
		System.out.print("New odds: " + maxOdds);
		List <Match> matches = euroHome.getMatchesByDay("All");
		for(Match match: matches) {
			String homeTeam = match.getHomeTeam().toUpperCase();
			System.out.println("Printing home team..." + homeTeam);
			if(homeTeam.equals("ENGLAND") ) {
				float homeWinOdds = convertToDecimal(match.getHomeWin());
				System.out.println("Printing both odds..." + homeWinOdds + " " + maxOdds);
				Assert.assertTrue(homeWinOdds < maxOdds);
			}

		}
		
	}
	

	
}
