package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Match {
	WebElement eventElement;
	String homeTeam;
	String awayTeam;
	String awayWin;
	String homeWin;
	String draw;

	public Match(WebElement event) {
		this.eventElement = event;
		this.setTeams();
		this.setOdds();
	}
	
	
	private void setOdds() {
		List <WebElement> betOptions = eventElement.findElements(By.cssSelector(".preplay-bet-button"));
		if (betOptions.size() == 3) {
			System.out.println("Printing number of bet options" + betOptions.size());
			this.homeWin = betOptions.get(0).getText();
			this.draw = betOptions.get(1).getText();
			this.awayWin = betOptions.get(2).getText();
		}		
	}
	
	private void setTeams() {
		this.homeTeam = eventElement.findElement(By.cssSelector(".competitor-home")).getText();
		this.awayTeam = eventElement.findElement(By.cssSelector(".competitor-away")).getText();
	}
	
	public String getHomeTeam() {
		return this.homeTeam;
	}
	
	public String getAwayTeam() {
		return this.awayTeam;
	}
	
	
	public String getAwayWin() {
		return this.awayWin;
	}
	
	public String getDraw() {
		return this.draw;
	}
	
	public String getHomeWin() {
		return this.homeWin;
	}
}
