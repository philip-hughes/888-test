package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;

public interface SportsPage {
	
	public abstract List<WebElement> getSections();
	public abstract List<WebElement> getBetCards(WebElement section);
	public abstract List<WebElement> getBetButtons(WebElement betCard);
	public abstract String getSectionDay(WebElement section);
	public abstract String getHomeTeamName(WebElement betCard);
	public abstract String getAwayTeamName(WebElement betCard);
	
	

}
