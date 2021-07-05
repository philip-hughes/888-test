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
	By betCard = By.cssSelector(".bet-card");
	By betButton = By.cssSelector(".bet-button");
	By homeTeam = By.cssSelector(".featured-matches-widget__event-link> span:first-child");
	By awayTeam = By.cssSelector(".featured-matches-widget__event-link > span:nth-child(2)");
	
		
	public List<WebElement> getSections(){
		
		return driver.findElements(section);
	}
	
	public List<WebElement> getBetCards(WebElement section){		
		
		return driver.findElements(betCard);
	}

	public String getSectionDay(WebElement section) {
		 String day = driver.findElement(sectionDay).getText();
		return day;
		
	}

	public List<WebElement> getBetButtons(WebElement betCard){
		return betCard.findElements(betButton);
	}
	
	public String getHomeTeamName(WebElement betCard) {
		return betCard.findElement(homeTeam).getText();
	}
	
	public String getAwayTeamName(WebElement betCard) {
		return betCard.findElement(awayTeam).getText();
	}

}
