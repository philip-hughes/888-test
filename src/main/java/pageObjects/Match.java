package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Match {
	WebElement eventElement;
	SportsPage page;
	String homeTeamName;
	String awayTeamName;
	String awayWinDisplayedOdds;
	String homeWinDisplayedOdds;
	String drawDisplayedOdds;

	public Match(WebElement event, SportsPage pageObject) {
		this.eventElement = event;
		this.page = pageObject;
		this.setTeamNames();
		this.setOdds();
	}
	
	/**
	 * Checks the number of bet option buttons to determine if the type of event has the potential for a 
	 * draw e.g. a soccer match, or if win/lose is the only possibility e.g basketball. In the scenarios
	 * where there are only two bet options, then only awayWinDisplayedOdds and homeWinDisplayedOdds will be 
	 * set.  In the scenario where there are 3 betting options, then drawDisplayedOdds will also be set.
	 * Note that in order for this method to work with other sports, it is assumed that bet buttons for those
	 * sports will also have the class "preplay-bet-button". If not then the method can be easily adjusted
	 * to use different selectors.
	 */
	private void setOdds() {
		List <WebElement> betOptions = page.getBetButtons(eventElement);
		if (betOptions.size() == 3) {
			this.homeWinDisplayedOdds = betOptions.get(0).getText();
			this.drawDisplayedOdds = betOptions.get(1).getText();
			this.awayWinDisplayedOdds = betOptions.get(2).getText();
		}
		else if(betOptions.size() == 2) {
			this.homeWinDisplayedOdds = betOptions.get(0).getText();
			this.awayWinDisplayedOdds = betOptions.get(1).getText();
		}
	}
	
	private void setTeamNames() {
		this.homeTeamName = page.getHomeTeamName(eventElement);
		this.awayTeamName = page.getAwayTeamName(eventElement);
	}
	
	public String getHomeTeamName() {
		return this.homeTeamName;
	}
	
	public String getAwayTeamName() {
		return this.awayTeamName;
	}
	
	
	public String getAwayWinDisplayedOdds() {
		return this.awayWinDisplayedOdds;
	}
	
	public String getDrawDisplayedOdds() {
		return this.drawDisplayedOdds;
	}
	
	public String getHomeWinDisplayedOdds() {
		return this.homeWinDisplayedOdds;
	}
}
