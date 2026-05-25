package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtility;
import org.openqa.selenium.WebElement;

public class HomePage {

    WebDriver driver;
    WaitUtility waitUtility;

    // Constructor
    public HomePage(WebDriver driver) {

        this.driver = driver;
        waitUtility = new WaitUtility(driver);
    }

    // Locators
    By lunitaCloseButton = By.id("lunita-close");

    By bookNowMenu =
            By.xpath("//a[contains(text(),'Book Now')]");

    // Actions / Methods

    public void closeLunitaChatbot() {

        WebElement closeButton =
                driver.findElement(lunitaCloseButton);

        waitUtility.waitForElementToBeClickable(closeButton);

        closeButton.click();
    }

    public void clickBookNowMenu() {

        WebElement bookNow =
                driver.findElement(bookNowMenu);

        waitUtility.waitForElementToBeClickable(bookNow);

        bookNow.click();
    }
}