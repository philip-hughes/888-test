package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasketballHomePage implements SportsPage {
	WebDriver driver;

	public BasketballHomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	By section = By.className("bb-content-section");
	By sectionDay = By.cssSelector(".bb-content-section__title-container > .bb-content-section__title-item:first-child");
	By event = By.cssSelector(".bet-card");
	By betButton = By.cssSelector(".bet-button");
	By homeTeam = By.cssSelector(".featured-matches-widget__event-link> span:first-child");
	By awayTeam = By.cssSelector(".featured-matches-widget__event-link > span:nth-child(2)");
	
		
	public List<WebElement> getSections(){
		
		return driver.findElements(section);
	}
	
	public List<WebElement> getEvents(WebElement section){		
		
		return driver.findElements(event);
	}

	public String getSectionDay(WebElement section) {
		 String day = driver.findElement(sectionDay).getText();
		return day;
		
	}

	public List<WebElement> getBetButtons(WebElement event){
		return event.findElements(betButton);
	}
	
	public String getHomeTeamName(WebElement event) {
		return event.findElement(homeTeam).getText();
	}
	
	public String getAwayTeamName(WebElement event) {
		return event.findElement(awayTeam).getText();
	}

}
