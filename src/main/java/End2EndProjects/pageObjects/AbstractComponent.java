package End2EndProjects.pageObjects;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {

		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement waitForElementToBeClickable(WebElement webElement)
	{
		WebDriverWait eWait = new WebDriverWait(driver, Duration.ofSeconds(3));
		return eWait.until(ExpectedConditions.elementToBeClickable(webElement));
		
	}
	
	
	
}
