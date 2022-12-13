package End2EndProjects.FlightBooking;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class ErrorValidationsTest extends BaseTest{

	@Test(dataProvider="getMinErrorData")
	public void minAdultErrorTest(HashMap<String, String> input)
	{
		mainPage.inputOrigin(input.get("searchFrom"), input.get("from"));
		mainPage.inputDestination(input.get("searchDest"), input.get("dest"));
		mainPage.departCurrentDate();
		mainPage.selectDiscountType(input.get("discountType"));
		mainPage.inputPassengers(Integer.parseInt(input.get("adult")), Integer.parseInt(input.get("child")), Integer.parseInt(input.get("infant")));
		mainPage.selectCurrency(input.get("currency"));
		mainPage.submit();
		Assert.assertTrue(mainPage.getErrorMessage().contains("minimum"));
	}
	
	@Test(dataProvider="getInfantErrorData")
	public void infantErrorTest(HashMap<String, String> input)
	{
		mainPage.inputOrigin(input.get("searchFrom"), input.get("from"));
		mainPage.inputDestination(input.get("searchDest"), input.get("dest"));
		mainPage.departCurrentDate();
		mainPage.selectDiscountType(input.get("discountType"));
		mainPage.inputPassengers(Integer.parseInt(input.get("adult")), Integer.parseInt(input.get("child")), Integer.parseInt(input.get("infant")));
		mainPage.selectCurrency(input.get("currency"));
		mainPage.submit();
		Assert.assertTrue(mainPage.getErrorMessage().contains("Infants than Adults"));
		
	}
	
	@DataProvider
	public Object[][] getMinErrorData() throws IOException
	{
		List<HashMap<String, String>> data=  getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\End2EndProjects\\data\\ErrorMinPassengersBooking.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	@DataProvider
	public Object[][] getInfantErrorData() throws IOException
	{
		List<HashMap<String, String>> data=  getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\End2EndProjects\\data\\ErrorInfantBooking.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
}
