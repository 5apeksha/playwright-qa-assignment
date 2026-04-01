package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;

    By bookingButton = By.xpath("(//*[@class=\"btn btn-primary btn-lg\"])");
    By checkIn = By.id("checkin");
     By checkOut = By.id("checkout");
    By checkAvailabilityButton = By.xpath("(//button[text()='Check Availability'])[1]");
    By BookRoomBtn = By.xpath("(//*[@class='btn btn-primary'])[1])");
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToBookingPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(bookingButton));
        driver.findElement(bookingButton).click();
    }
    
    
    
  /*  public void enterCheckInDate(String date) {
		driver.findElement(checkIn).sendKeys(date);
	}
    
    public void enterCheckOutDate(String date) {
    			driver.findElement(checkOut).sendKeys(date);
    }
    
    */
    
    
    public void clickCheckAvailability() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By checkAvailabilityButton = By.xpath("(//button[text()='Check Availability'])[1]");
        wait.until(ExpectedConditions.elementToBeClickable(checkAvailabilityButton));

        // Scroll to the element and click
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(checkAvailabilityButton));
        driver.findElement(checkAvailabilityButton).click();
    }
    
    
    
    
    
    public void clickFirstBookNowButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By firstBookNowButton = By.xpath("(//button[text()='Book Now'])[1]");
        wait.until(ExpectedConditions.elementToBeClickable(firstBookNowButton));

        // Scroll to the element and click
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(firstBookNowButton));
        driver.findElement(firstBookNowButton).click();
    }

}