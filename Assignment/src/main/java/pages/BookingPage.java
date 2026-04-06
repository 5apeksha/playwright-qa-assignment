package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BookingPage  {
    WebDriver driver;
    By bookingForm = By.id("booking");
    By bookingHeader = By.xpath("//h2[text()='Booking Form']");
    By ReserveButton = By.xpath("//*[@id='doReservation']");
    By ReserverNowButton = By.xpath("//button[normalize-space()='Reserve Now']");
    By firstName = By.xpath("//*[@class='form-control room-firstname']");
    By lastName = By.xpath("//*[@class='form-control room-lastname']");
    By email = By.xpath("//*[@class='form-control room-email']");
    By phoneEle = By.xpath("//*[@class='form-control room-phone']");  
    By confirmText = By.xpath("//h2[@class='card-title fs-4 fw-bold mb-3']");

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

        // 1️⃣ Wait for element to be clickable
        WebElement reserveBtn = wait.until(ExpectedConditions.elementToBeClickable(ReserveButton));

        // 2️⃣ Scroll to the element safely (avoid sticky headers)
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'center'});", reserveBtn);

        // 3️⃣ Click using Actions (simulates real user click)
        Actions actions = new Actions(driver);
        actions.moveToElement(reserveBtn).click().perform();
	}
    
    public void clickReserveNowButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1️⃣ Wait for element to be clickable
        WebElement reserveBtn = wait.until(ExpectedConditions.elementToBeClickable(ReserverNowButton));

        // 2️⃣ Scroll to the element safely (avoid sticky headers)
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'center'});", reserveBtn);

        // 3️⃣ Click using Actions (simulates real user click)
        Actions actions = new Actions(driver);
        actions.moveToElement(reserveBtn).click().perform();
	}
    
    public void scrollToBookingForm() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(bookingForm));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(bookingForm));
	}
  
    
  
    
    public void enterBookingDetails(String fName, String lName, String mail, String phone) {
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    	    // Switch to iframe if needed
    	    // driver.switchTo().frame("iframeName");

    	    WebElement firstNameEl = wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
    	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", firstNameEl);
    	    firstNameEl.clear();
    	    firstNameEl.sendKeys(fName);
    	    

    	    WebElement lastNameEl = wait.until(ExpectedConditions.visibilityOfElementLocated(lastName));
    	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", lastNameEl);
    	    lastNameEl.clear();
    	    lastNameEl.sendKeys(lName);

    	    WebElement emailEl = wait.until(ExpectedConditions.visibilityOfElementLocated(email));
    	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", emailEl);
    	    emailEl.clear();
    	    emailEl.sendKeys(mail);

    	    WebElement phoneEl = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneEle));
    	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", phoneEl);
    	    phoneEl.clear();
    	    phoneEl.sendKeys(phone);

    
    }

    public void verifyConfirmation() {
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    	WebElement confirmLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmText));
 	   
 	   ((JavascriptExecutor) driver).executeScript(
 			    "arguments[0].scrollIntoView(true); window.scrollBy(0, -100);", confirmLabel);

 	    
 	     
 	    String actualText = confirmLabel.getText();
 	    System.out.println("Confirmation Label Text: " + actualText);

 	   // Verify it equals "Booking Confirmed"
 	   if(actualText.equals("Booking Confirmed")) {
 	       System.out.println("Booking confirmed successfully");
 	   } else {
 	       System.out.println("Booking confirmation failed");
 	   }
    }

 
   

}