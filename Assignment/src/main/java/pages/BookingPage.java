package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BookingPage  {
    WebDriver driver;
    By bookingForm = By.id("booking");
    By bookingHeader = By.xpath("//h2[text()='Booking Form']");
    By Reservbutton = By.xpath("(//button[text()='Reserve'])[1]");
    By firstName = By.id("firstname");
    By lastName = By.id("lastname");
    By email = By.id("email");
    By roomType = By.id("roomtype");  
    By submitButton = By.cssSelector("button[type='submit']");

    public BookingPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isBookingFormDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(bookingForm));
		return driver.findElement(bookingForm).isDisplayed();
	}
    
    public boolean isBookingHeaderDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(bookingHeader));
		return driver.findElement(bookingHeader).isDisplayed();
	}
    
    public void clickReserveButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(Reservbutton));
		driver.findElement(Reservbutton).click();
	}
    
    public void scrollToBookingForm() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(bookingForm));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(bookingForm));
	}
  
    
  
    
    public void enterBookingDetails(String fName, String lName, String mail, String room) {
        driver.findElement(firstName).sendKeys(fName);
        driver.findElement(lastName).sendKeys(lName);
        driver.findElement(email).sendKeys(mail);
        driver.findElement(roomType).sendKeys(room);
    
    }

    public void submitBooking() {
        driver.findElement(submitButton).click();
    }

 
   

}