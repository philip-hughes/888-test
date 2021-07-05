package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EuroHomePage implements SportsPage{

	WebDriver driver;
	
	public EuroHomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	By selectedTab = By.cssSelector("li[data-selected-item='true']");
	By matchResultTab = By.cssSelector("li[data-item-slug='match-result']");
	By section = By.cssSelector(".panel-body-section");
	By sectionDay = By.className("cell-body");
	By betCard = By.cssSelector(".sport-event");
	By betButton = By.cssSelector(".preplay-bet-button");
	By homeTeam = By.cssSelector(".competitor-home");
	By awayTeam = By.cssSelector(".competitor-away");
	
	
	public WebElement matchResultTab() {
		return driver.findElement(matchResultTab);
	}
	
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
