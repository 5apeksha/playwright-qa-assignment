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
        
        driver.get("https://automationintesting.online/");
        
        excel = new ExcelUtil("C:\\Users\\SAMEER-PC\\eclipse-workspace\\Assignment\\testdata\\bookingData.xlsx", "Sheet1");
    }

    @Test
    public void testBookingFlow() {
        HomePage home = new HomePage(driver);
        home.goToBookingPage();

        BookingPage booking = new BookingPage(driver);

        // Read data from Excel (row 1)
        String firstName = excel.getCellData(0, 0);
        String lastName = excel.getCellData(0, 2);
        String email = excel.getCellData(0, 3);
        String room = excel.getCellData(0, 4);
        String checkIn = excel.getCellData(0,5);
        String checkOut = excel.getCellData(0, 6);

        //booking.enterBookingDetails(firstName, lastName, email, room, checkIn, checkOut);
       // booking.submitBooking();

        //ConfirmationPage confirm = new ConfirmationPage(driver);
        //System.out.println("Booking Confirmation: " + confirm.getConfirmationText());
    }

    @Test
    public void testCheckAvailabilityAndBookRoom() {
        HomePage home = new HomePage(driver);
        home.goToBookingPage();

        // Enter hardcoded dates in Check-in and Check-out fields
        BookingPage booking = new BookingPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String checkIn = excel.getCellData(1,4);
        String checkOut = excel.getCellData(1, 5);
        
        // Scroll to Check-in field and enter date
        WebElement checkInField = driver.findElement(By.xpath("//*[@id='booking']/div/div/div/form/div/div[1]/div[1]/div/input"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkInField);
        wait.until(ExpectedConditions.elementToBeClickable(checkInField));
        checkInField.sendKeys(checkIn);

        // Scroll to Check-out field and enter date
        WebElement checkOutField = driver.findElement(By.xpath("//*[@id='booking']/div/div/div/form/div/div[2]/div/div/input"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkOutField);
        wait.until(ExpectedConditions.elementToBeClickable(checkOutField));
        checkOutField.sendKeys(checkOut);

        // Click on Check Availability button
        WebElement checkAvailabilityButton = driver.findElement(By.xpath("//*[@id=\"booking\"]/div/div/div/form/div/div[4]/button"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkAvailabilityButton);
        wait.until(ExpectedConditions.elementToBeClickable(checkAvailabilityButton));
        // Use JavaScript to click the button to bypass obstruction
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkAvailabilityButton);

        // Wait for the 'Book Now' button to be present and visible
        WebElement firstBookNowButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='rooms']/div/div[2]/div[1]/div/div[3]/a")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstBookNowButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstBookNowButton);

        // Verify navigation to the next page
       
        // Wait for the page to load completely after navigation
        wait.until(ExpectedConditions.urlContains("/reservation"));
        
        //reservbtn = driver.findElement(By.xpath("//button[@id="doReservation"]"));
        
        
        
        


    }

    @Test
    public void testElementVisibility() {
        // Debugging: Log the page source to verify the state of the page
        System.out.println("Page Source: " + driver.getPageSource());

        // Wait for the element to be present and visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root-container\"]/div/div[2]/div/div[1]/div[1]/div/div[1]/span")));
        System.out.println("Element text: " + element.getText());
    }

    @AfterClass
    public void teardown() {
        try {
            if (excel != null) {
                excel.close();
            }
        } catch (Exception e) {
            System.err.println("Error closing Excel file: " + e.getMessage());
        }
        DriverFactory.quitDriver();
    }
}