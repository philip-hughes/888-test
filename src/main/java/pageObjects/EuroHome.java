package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EuroHome {

	WebDriver driver;
	
	public EuroHome(WebDriver driver) {
		this.driver = driver;
	}
	By selectedTab = By.cssSelector("li[data-selected-item='true']");
	By matchResultTab = By.cssSelector("li[data-item-slug='match-result']");
	By sections = By.cssSelector(".panel-body-section");
	By event = By.cssSelector(".sport-event");
	
	
	public WebElement matchResultTab() {
		return driver.findElement(matchResultTab);
	}
	
	public List<WebElement> getSections(){
		
		return driver.findElements(sections);
	}

	public List<Match> getMatchesByDay(String day){
		List<WebElement> eventElements = new ArrayList<WebElement>();
		List<WebElement> sections = getSections();
		
		if(day.equalsIgnoreCase("All")) {
			for(WebElement e: sections) {
				eventElements.addAll(e.findElements(event));			
			}
		}
		else {
			for(WebElement e: sections) {
				String sectionDay = e.findElement(By.className("cell-body")).getText();
				if(sectionDay.toLowerCase().contains(day.toLowerCase())) {
					eventElements = e.findElements(event);
				}			
			}			
		}
		
		
		List <Match> matches = new ArrayList<Match>();
		
		for(WebElement e: eventElements) {
			matches.add(new Match(e));
		}
				
		return matches;				
	}
	

}
