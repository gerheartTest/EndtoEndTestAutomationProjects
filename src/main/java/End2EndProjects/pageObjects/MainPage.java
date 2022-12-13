package End2EndProjects.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends AbstractComponent {

	WebDriver driver;
	Actions act;
	WebDriverWait eWait;

	public MainPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		act = new Actions(driver);
		eWait = new WebDriverWait(driver, Duration.ofSeconds(3));
	}

	@FindBy(id="ctl00_mainContent_ddl_originStation1_CTXT")
	WebElement txtOrigin;

	@FindBy(css="#ctl00_mainContent_ddl_originStation1_CTNR .dropdownDiv .livecl")
	List<WebElement> lstEle_from;

	@FindBy(id="ctl00_mainContent_ddl_destinationStation1_CTXT")
	WebElement txtDest;

	@FindBy(css="#glsctl00_mainContent_ddl_destinationStation1_CTNR .dropdownDiv .livecl")
	List<WebElement> lstEle_dest;

	@FindBy(css=".ui-state-highlight")
	WebElement currentDate;

	@FindBy(id="ctl00_mainContent_chk_friendsandfamily")
	WebElement chkFriendsAndFamily;

	@FindBy(id="ctl00_mainContent_chk_SeniorCitizenDiscount")
	WebElement chkSeniorCitizenDiscount;

	@FindBy(id="ctl00_mainContent_chk_IndArm")
	WebElement chkIndArm;

	@FindBy(id="ctl00_mainContent_chk_StudentDiscount")
	WebElement chkStudentDiscount;

	@FindBy(id="ctl00_mainContent_chk_Unmr")
	WebElement chkUnmr;

	@FindBy(id="divpaxinfo")
	WebElement ddlPassengers;

	@FindBy(id="hrefIncAdt")
	WebElement incAdult;

	@FindBy(id="hrefIncChd")
	WebElement incChild;

	@FindBy(id="hrefIncInf")
	WebElement incInfant;

	@FindBy(id="ctl00_mainContent_DropDownListCurrency")
	WebElement ddlCurrency;

	@FindBy(id="ctl00_mainContent_btn_FindFlights")
	WebElement btnSubmit;
	
	@FindBy(xpath="(//span[@class='ui-datepicker-month'])[1]")
	WebElement lblDepartMN;
	
	@FindBy(css=".ui-icon-circle-triangle-e")
	WebElement btnNextDepartMN;
	
	@FindBy(xpath="//a[@class='ui-state-default']")
	List<WebElement> departDays;

	@FindBy(id="ctl00_mainContent_view_date2")
	WebElement dtpReturn;
	
	@FindBy(css=".ui-datepicker-multi-2 .ui-datepicker-month")
	WebElement lblReturnMN;
	
	@FindBy(css=".ui-datepicker-multi-2 .ui-icon-circle-triangle-e")
	WebElement btnNextReturnMN;
	
	@FindBy(css=".ui-datepicker-multi-2 a[class='ui-state-default']")
	List<WebElement> returnDays;
	
	

	public void inputOrigin(String searchFrom, String from){
		txtOrigin.sendKeys(searchFrom);
		WebElement ele_from = lstEle_from.stream().filter(s->s.getText().contains(from)).findFirst().orElse(null);
		act.doubleClick(ele_from).build().perform();
	}

	public void inputDestination(String searchDest, String dest)
	{
		txtDest.sendKeys(searchDest);
		WebElement ele_dest = lstEle_dest.stream().filter(s->s.getText().contains(dest)).findFirst().orElse(null);
		act.doubleClick(ele_dest).build().perform();
	}

	public void departCurrentDate()
	{
		waitForElementToBeClickable(currentDate).click();
	}

	public void setDepartDate(String departMN, String departDY)
	{
		while(!lblDepartMN.getText().equalsIgnoreCase(departMN))
		{
			waitForElementToBeClickable(btnNextDepartMN).click();
		}
		
		eWait.until(ExpectedConditions.visibilityOfAllElements(departDays));
		departDays.stream().filter(s->s.getText().contains(departDY)).findFirst().orElse(null).click();
	}

	public void setReturnDate(String returnMN, String returnDY)
	{
		dtpReturn.click();

		while(!lblReturnMN.getText().equalsIgnoreCase(returnMN))
		{
			waitForElementToBeClickable(btnNextReturnMN).click();
		}

		eWait.until(ExpectedConditions.visibilityOfAllElements(returnDays));
		returnDays.stream().filter(s->s.getText().contains(returnDY)).findFirst().orElse(null).click();

	}

	public void selectDiscountType(String discountType)
	{
		switch(discountType) {
		case "Family and Friends":
			chkFriendsAndFamily.click();
			break;
		case "Senior Citizen":
			chkSeniorCitizenDiscount.click();
			break;
		case "Indian Armed Forces":
			chkIndArm.click();
			break;
		case "Student":
			chkStudentDiscount.click();
			break;
		case "Unaccompanied Minor":
			chkUnmr.click();
			break;
		}
	}

	public void inputPassengers(int adult, int child, int infant)
	{
		ddlPassengers.click();

		for(int i=1 ; i<adult ; i++)
		{
			waitForElementToBeClickable(incAdult).click();
		}
		for(int i=0 ; i<child ; i++)
		{
			waitForElementToBeClickable(incChild).click();
		}
		for(int i=0 ; i<infant ; i++)
		{
			waitForElementToBeClickable(incInfant).click();
		}
	}

	public void selectCurrency(String currency)
	{
		Select selCurrency = new Select(ddlCurrency);
		selCurrency.selectByValue(currency);
	}

	public void submit()
	{
		btnSubmit.click();
	}
	
	public String getErrorMessage()
	{
		String errorMessage = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		return errorMessage;
	}
	
}
