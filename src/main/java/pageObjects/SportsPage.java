package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface SportsPage {
	
	public abstract List<WebElement> getSections();
	public abstract List<WebElement> getEvents(WebElement section);
	public abstract List<WebElement> getBetButtons(WebElement event);
	public abstract String getSectionDay(WebElement section);
	public abstract String getHomeTeamName(WebElement event);
	public abstract String getAwayTeamName(WebElement event);
	
	

}
