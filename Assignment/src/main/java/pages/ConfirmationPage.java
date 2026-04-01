package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {
    WebDriver driver;
    By confirmationMessage = By.xpath("(//h2[@class=\"fs-4 fw-bold mb-3\"])[1]");

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getConfirmationText() {
        return driver.findElement(confirmationMessage).getText();
    }
    
    
}