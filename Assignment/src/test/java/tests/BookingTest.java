package tests;

import org.testng.annotations.*;
import pages.*;
import utils.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import java.time.Duration;

public class BookingTest {

	WebDriver driver;
	ExcelUtil excel;

	@BeforeClass
	public void setup() throws Exception {
		
		driver = DriverFactory.getDriver();
		driver.manage().window().maximize();
		driver.get("https://automationintesting.online/");
	
    
		excel = new ExcelUtil("C:\\Users\\SAMEER-PC\\eclipse-workspace\\Assignment\\testdata\\bookingData.xlsx",
				"Sheet1");
	}

	@Test
	public void testBookingFlow() {
		
		HomePage home = new HomePage(driver);
		home.clickFirstBookNowButton();
		// Read data from Excel (row 1)
		String firstName = excel.getCellData(1, 0);
		String lastName = excel.getCellData(1, 1);
		String email = excel.getCellData(1, 2);
		String phone = excel.getCellData(1, 3);
		String CheckInDt = excel.getCellData(1, 4);
		String CheckOutDt = excel.getCellData(1, 5);
		home.enterCheckInDate(CheckInDt);
		home.enterCheckOutDate(CheckOutDt);

		home.clickCheckAvailability();

		home.goToBookingPage();
		BookingPage booking = new BookingPage(driver);
		
		booking.clickReserveButton();
		
		booking.enterBookingDetails(firstName, lastName, email, phone);
		booking.clickReserveNowButton();
		booking.verifyConfirmation();

		//ConfirmationPage confirm = new ConfirmationPage(driver);
	//	System.out.println("Booking Confirmation: " + confirm.getConfirmationText());
		// booking.enterBookingDetails(firstName, lastName, email, room, checkIn,
		// checkOut);
		// booking.submitBooking();

		// ConfirmationPage confirm = new ConfirmationPage(driver);
		// System.out.println("Booking Confirmation: " + confirm.getConfirmationText());
	}

	
	/*@Test
	public void testElementVisibility() {
		// Debugging: Log the page source to verify the state of the page
		System.out.println("Page Source: " + driver.getPageSource());

		// Wait for the element to be present and visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id=\"root-container\"]/div/div[2]/div/div[1]/div[1]/div/div[1]/span")));
		System.out.println("Element text: " + element.getText());
	}
*/
	
	@AfterClass
	public void teardown() {
		driver.quit();
	}
}