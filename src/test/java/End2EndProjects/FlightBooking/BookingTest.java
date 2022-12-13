package End2EndProjects.FlightBooking;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BookingTest extends BaseTest{

	
	@Test(dataProvider="getOneWayData")
	public void oneWayTest(HashMap<String, String> input)
	{
	
		mainPage.inputOrigin(input.get("searchFrom"), input.get("from"));
		mainPage.inputDestination(input.get("searchDest"), input.get("dest"));
		mainPage.departCurrentDate();
		mainPage.selectDiscountType(input.get("discountType"));
		mainPage.inputPassengers(Integer.parseInt(input.get("adult")) , Integer.parseInt(input.get("child")), Integer.parseInt(input.get("infant")));
		mainPage.selectCurrency(input.get("currency"));
		mainPage.submit();
	}
	
	@Test(dataProvider="getRoundTripData")
	public void roundTripTest(HashMap<String, String> input)
	{
		mainPage.inputOrigin(input.get("searchFrom"), input.get("from"));
		mainPage.inputDestination(input.get("searchDest"), input.get("dest"));
		mainPage.setDepartDate(input.get("departMN"), input.get("departDY"));
		mainPage.setReturnDate(input.get("returnMN"), input.get("returnDY"));
		mainPage.selectDiscountType(input.get("discountType"));
		mainPage.inputPassengers(Integer.parseInt(input.get("adult")) , Integer.parseInt(input.get("child")), Integer.parseInt(input.get("infant")));
		mainPage.selectCurrency(input.get("currency"));
		mainPage.submit();
	}
	
	
	
	
	@DataProvider
	public Object[][] getOneWayData() throws IOException
	{
		List<HashMap<String, String>> data=  getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\End2EndProjects\\data\\OnewayBooking.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	@DataProvider
	public Object[][] getRoundTripData() throws IOException
	{
		List<HashMap<String, String>> data=  getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\End2EndProjects\\data\\RoundTripBooking.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
