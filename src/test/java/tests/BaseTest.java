package tests;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import pageObjects.Match;
import pageObjects.SportsPage;

public class BaseTest {
	
	public static final String DRIVER_PATH = "/Users/Phugh/OneDrive/Documents/test-automation/chromedriver.exe";
	
	public static void isFractional(String odds) {

		int slashOccurance = StringUtils.countMatches(odds, "/");

		if (slashOccurance == 1) {
			String[] oddsArray = odds.split("/");
			Assert.assertTrue(oddsArray.length == 2);
			Assert.assertTrue(StringUtils.isNumeric(oddsArray[0]));
			Assert.assertTrue(StringUtils.isNumeric(oddsArray[1]));

		} else {
			Assert.fail("Displayed odds are not in expected fractional format");
		}

	}

	/**
	 * Converts fractional odds string to a decimal float
	 * 
	 * @param odds
	 * @return
	 */
	public static float convertToDecimal(String odds) {
		String[] oddsArray = odds.split("/");
		int numerator = Integer.parseInt(oddsArray[0]);
		int denominator = Integer.parseInt(oddsArray[1]);
		float decimalOdds = ((float) numerator / denominator) + 1;

		return decimalOdds;

	}
	
	public static List<Match> getMatchesByDay(SportsPage pageObject, String day){
		List<WebElement> eventElements = new ArrayList<WebElement>();
		List<WebElement> sections = pageObject.getSections();
		
		if(day.equalsIgnoreCase("All")) {
			for(WebElement e: sections) {
				eventElements.addAll(pageObject.getBetCards(e));			
			}
		}
		else {
			for(WebElement e: sections) {
				String sectionDay = pageObject.getSectionDay(e);
				if(sectionDay.toLowerCase().contains(day.toLowerCase())) {
					eventElements.addAll(pageObject.getBetCards(e));
				}			
			}			
		}
		
		
		List <Match> matches = new ArrayList<Match>();
		
		for(WebElement e: eventElements) {
			matches.add(new Match(e, pageObject));
		}
				
		return matches;				
	} 

}
