package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HomePage {
    WebDriver driver;

    By bookingButton = By.xpath("//a[@class='btn btn-primary btn-lg']");
    By checkIn = By.xpath("//*[text()='Check In']//following-sibling::div/div/input");
    By checkOut = By.xpath("//*[text()='Check Out']//following-sibling::div/div/input");
    By checkAvailabilityButton = By.xpath("//button[normalize-space()='Check Availability']");
    By bookRoomBtn = By.xpath("(//*[@id='rooms']//a)[1]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFirstBookNowButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(bookingButton));
        el.click();
    }

    public void enterCheckInDate(String checkInDate) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        setCalendarDate(checkIn, checkInDate);
    }

    public void enterCheckOutDate(String checkOutDate) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       

        setCalendarDate(checkOut, checkOutDate);
    }

    public void clickCheckAvailability() {

        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(200));
        //WebElement element = wait.until(ExpectedConditions.elementToBeClickable(checkAvailabilityButton));

        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -100);", element);

        //((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         WebElement button = wait.until(ExpectedConditions.elementToBeClickable(checkAvailabilityButton));

         // Scroll a bit up to avoid sticky navbar
         ((JavascriptExecutor) driver).executeScript(
                 "arguments[0].scrollIntoView(true); window.scrollBy(0, -100);", button);

         // Click using Actions to simulate real user click
         Actions actions = new Actions(driver);
         actions.moveToElement(button).click().perform();
    }

    public void goToBookingPage() {
    	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
          WebElement element = wait.until(ExpectedConditions.elementToBeClickable(bookRoomBtn));

          ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -100);", element);

          ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }  
    
    public void setCalendarDate(By locator, String date) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[1]; " +
                "arguments[0].dispatchEvent(new Event('input', {bubbles: true})); " +
                "arguments[0].dispatchEvent(new Event('change', {bubbles: true}));", 
                element, 
                date
            );
        
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(d -> 
        element.getAttribute("value").equals(date)
    );
    }
    
    

}
