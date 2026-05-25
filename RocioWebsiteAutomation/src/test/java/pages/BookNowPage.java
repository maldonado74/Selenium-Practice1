package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookNowPage {

    WebDriver driver;

    // Constructor
    public BookNowPage(WebDriver driver) {

        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    // Locators
    @FindBy(name = "firstName")
    WebElement firstNameField;
    By lastNameField = By.name("lastName");
    By emailField = By.name("email");
    By telephoneField = By.name("telephone");
    By submitButton = By.name("Submit");
    By stateField = By.name("state");
    By descriptionField = By.name("description");
    By countryDropdown = By.name("country");
    By tourDropdown = By.name("location");
    By guestDropdown = By.name("guest");
    By emailRadioButton = By.xpath("//input[@name='contactBy' and @value='email']");
    By phoneRadioButton = By.xpath("//input[@name='contactBy' and @value='phone']");

    By newsletterCheckbox = By.name("alerts");

    By resetButton = By.xpath("//input[@type='reset']");
    
  
    // Methods / Actions

    public void enterFirstName(String firstName) {

    	firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {

        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void enterEmail(String email) {

        driver.findElement(emailField).sendKeys(email);
    }

    public void enterTelephone(String phone) {

        driver.findElement(telephoneField).sendKeys(phone);
    }

    public void clickSubmitButton() {

        driver.findElement(submitButton).click();
    }
    public void enterState(String state) {
        driver.findElement(stateField).sendKeys(state);
    }

    public void enterDescription(String description) {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
            "arguments[0].value='" + description + "';",
            driver.findElement(descriptionField)
        );
    }

    public void selectCountry(String countryName) {
        Select country = new Select(driver.findElement(countryDropdown));
        country.selectByVisibleText(countryName);
    }

    public void selectTour(String tourName) {
        Select tour = new Select(driver.findElement(tourDropdown));
        tour.selectByVisibleText(tourName);
    }

    public void selectGuests(String guests) {
        Select guest = new Select(driver.findElement(guestDropdown));
        guest.selectByVisibleText(guests);
    }

    public void selectEmailContactMethod() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(emailRadioButton));
    }

    public void selectPhoneContactMethod() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(phoneRadioButton));
    }

    public void selectNewsletterCheckbox() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(newsletterCheckbox));
    }

    public void clickResetButton() {
        driver.findElement(resetButton).click();
    }
    
}