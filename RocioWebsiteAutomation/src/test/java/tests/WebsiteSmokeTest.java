package tests;

import base.BaseTest;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import org.openqa.selenium.Dimension;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;

import java.net.HttpURLConnection;
import java.net.URL;

import org.testng.Assert;
import pages.HomePage;
import pages.BookNowPage;
import utils.ScreenshotUtility;
import java.sql.ResultSet;
import utils.DatabaseUtility;
import utils.ConfigReader;

import io.restassured.response.Response;
import utils.ApiUtility;

import org.testng.annotations.DataProvider;
import utils.CsvUtility;

public class WebsiteSmokeTest extends BaseTest {
	
	@Test(priority = 1)
	public void openWebsite() {

	    System.out.println("======================================");
	    System.out.println("Test 1: Verify Website Loads Successfully");
	    System.out.println("======================================");

	    WebDriverManager.chromedriver().setup();
	    WebDriver driver = new ChromeDriver();

	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	    driver.get("https://rociopmendoza.com");

	    System.out.println("Website opened successfully");

	    driver.quit();
	}

	@Test(priority = 2)
    public void validateNavigationLinks() {
    	
    	System.out.println("======================================");
        System.out.println("Test 2: Verify Navigation Menu Links");
        System.out.println("======================================");


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://rociopmendoza.com");

        driver.findElement(By.id("lunita-close")).click();

        driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
        System.out.println("Clicked Home menu");

        driver.findElement(By.xpath("//a[contains(text(),'Book Now')]")).click();
        System.out.println("Clicked Book Now menu");

        driver.navigate().back();

        driver.findElement(By.xpath("//a[contains(text(),'Contact')]")).click();
        System.out.println("Clicked Contact menu");

        System.out.println("PASS - Navigation links are working");

        driver.quit();
    }
    
	@Test(priority = 3)
    public void validateDestinationLinks() {
    	
    	System.out.println("======================================");
        System.out.println("Test 3: Verify Destination Links");
        System.out.println("======================================");

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://rociopmendoza.com");

        driver.findElement(By.id("lunita-close")).click();

        driver.findElement(By.xpath("//a[contains(text(),'Tambopata')]")).click();
        System.out.println("Clicked Tambopata destination link");

        driver.navigate().back();

        driver.findElement(By.xpath("//a[contains(text(),'Machu Picchu')]")).click();
        System.out.println("Clicked Machu Picchu destination link");

        driver.navigate().back();

        driver.findElement(By.xpath("//a[contains(text(),'Sacred Valley')]")).click();
        System.out.println("Clicked Sacred Valley destination link");

        driver.navigate().back();

        driver.findElement(By.xpath("//a[contains(text(),'Puerto Maldonado')]")).click();
        System.out.println("Clicked Puerto Maldonado destination link");

        System.out.println("PASS - Destination links are working");

        driver.quit();
    }
    
	@Test(priority = 4)
    public void validateLunitaChatbot() {

        System.out.println("======================================");
        System.out.println("Test 4: Verify Lunita Chatbot");
        System.out.println("======================================");

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://rociopmendoza.com");

        driver.findElement(By.xpath("//button[contains(text(),'What is this website about?')]")).click();
        System.out.println("Clicked website about question");

        driver.findElement(By.xpath("//button[contains(text(),'What tools were used?')]")).click();
        System.out.println("Clicked tools question");

        driver.findElement(By.xpath("//button[contains(text(),'How can I contact Rocio?')]")).click();
        System.out.println("Clicked contact question");
        
        driver.findElement(By.xpath("//button[contains(text(),'What QA skills does Rocio have?')]")).click();
        System.out.println("Clicked QA skills question");

        System.out.println("PASS - Lunita chatbot responses displayed");

        driver.quit();
    }
	
	@Test(priority = 5)
	public void validateBookingFormFields() {

	    System.out.println("======================================");
	    System.out.println("Test 5: Verify Booking Form Fields");
	    System.out.println("======================================");

	    WebDriverManager.chromedriver().setup();
	    WebDriver driver = new ChromeDriver();

	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	    driver.get("https://rociopmendoza.com");

	    driver.findElement(By.id("lunita-close")).click();

	    driver.findElement(By.xpath("//a[contains(text(),'Book Now')]")).click();

	    System.out.println("Clicked Book Now menu");

	    boolean firstName =
	        driver.findElement(By.name("firstName")).isDisplayed();

	    boolean lastName =
	        driver.findElement(By.name("lastName")).isDisplayed();

	    boolean email =
	        driver.findElement(By.name("email")).isDisplayed();

	    boolean submitButton =
	        driver.findElement(By.xpath("//input[@type='submit']")).isDisplayed();

	    if(firstName && lastName && email && submitButton) {

	        System.out.println("PASS - Booking form fields are displayed");

	    } else {

	        System.out.println("FAIL - Booking form fields missing");
	    }

	    driver.quit();
	}
	
	@Test(priority = 6)
	public void submitBookingForm() {

	    System.out.println("======================================");
	    System.out.println("Test 6: Verify Booking Form Submission");
	    System.out.println("======================================");

	    HomePage homePage = new HomePage(driver);
	    BookNowPage bookNowPage = new BookNowPage(driver);

	    homePage.closeLunitaChatbot();
	    homePage.clickBookNowMenu();

	    System.out.println("Clicked Book Now menu");

	    bookNowPage.enterFirstName("Automation");
	    bookNowPage.enterLastName("Tester");
	    bookNowPage.enterEmail("automationtest@example.com");
	    bookNowPage.enterTelephone("3015551234");
	    bookNowPage.enterState("MD");

	    bookNowPage.enterDescription("This is a Selenium automation test submission.");

	    bookNowPage.selectCountry("United States");
	    bookNowPage.selectTour("Machu Picchu");
	    bookNowPage.selectGuests("1");

	    bookNowPage.selectEmailContactMethod();

	    bookNowPage.clickSubmitButton();

	    System.out.println("Clicked Submit button");

	    String pageText = driver.findElement(By.tagName("body")).getText();

	    System.out.println("PHP response page text:");
	    System.out.println(pageText);

	    Assert.assertTrue(
	    	    pageText.contains("submitted successfully"),
	    	    "Booking confirmation message was not displayed!"
	    	);

	    	System.out.println("PASS - Booking form submitted and confirmation displayed");
	}
	
	@Test(priority = 7)
	public void validateRequiredFieldErrors() {

	    System.out.println("======================================");
	    System.out.println("Test 7: Verify Required Field Validation");
	    System.out.println("======================================");

	    WebDriverManager.chromedriver().setup();
	    WebDriver driver = new ChromeDriver();

	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	    driver.get("https://rociopmendoza.com");

	    driver.findElement(By.id("lunita-close")).click();

	    driver.findElement(By.xpath("//a[contains(text(),'Book Now')]")).click();

	    System.out.println("Clicked Book Now menu");

	    // Submit empty form
	    driver.findElement(By.name("Submit")).click();

	    // Handle alert popup
	    Alert alert = driver.switchTo().alert();

	    String alertText = alert.getText();

	    System.out.println("Validation popup message:");
	    System.out.println(alertText);

	    if(alertText.contains("Please enter your First Name")) {

	        System.out.println("PASS - Required field validation displayed");

	    } else {

	        System.out.println("FAIL - Validation popup not displayed correctly");
	    }

	    alert.accept();

	    driver.quit();
	}
	
	@Test(priority = 8)
	public void validateInvalidEmailFormat() {

	    System.out.println("======================================");
	    System.out.println("Test 8: Verify Invalid Email Validation");
	    System.out.println("======================================");

	    WebDriverManager.chromedriver().setup();
	    WebDriver driver = new ChromeDriver();

	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	    driver.get("https://rociopmendoza.com");

	    driver.findElement(By.id("lunita-close")).click();

	    driver.findElement(By.xpath("//a[contains(text(),'Book Now')]")).click();
	    System.out.println("Clicked Book Now menu");

	    // Fill form with invalid email format
	    driver.findElement(By.name("firstName")).sendKeys("Automation");
	    driver.findElement(By.name("lastName")).sendKeys("Tester");
	    driver.findElement(By.name("email")).sendKeys("invalidemail");
	    driver.findElement(By.name("telephone")).sendKeys("3015551234");

	    // Select contact method
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    js.executeScript("arguments[0].click();",
	        driver.findElement(By.xpath("//input[@name='contactBy' and @value='email']"))
	    );

	    // Submit form
	    driver.findElement(By.name("Submit")).click();
	    System.out.println("Clicked Submit button");

	    // Capture JavaScript validation alert
	    Alert alert = driver.switchTo().alert();

	    String alertText = alert.getText();

	    System.out.println("Validation popup message:");
	    System.out.println(alertText);

	    if(alertText.toLowerCase().contains("email")) {

	        System.out.println("PASS - Invalid email validation displayed");

	    } else {

	        System.out.println("FAIL - Invalid email validation missing");
	    }

	    alert.accept();
	    driver.quit();
	}
	
	@Test(priority = 9)
	public void validateResetButton() {

	    System.out.println("======================================");
	    System.out.println("Test 9: Verify Reset Button Functionality");
	    System.out.println("======================================");

	    WebDriverManager.chromedriver().setup();
	    WebDriver driver = new ChromeDriver();

	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	    driver.get("https://rociopmendoza.com");

	    driver.findElement(By.id("lunita-close")).click();

	    driver.findElement(By.xpath("//a[contains(text(),'Book Now')]")).click();

	    System.out.println("Clicked Book Now menu");

	    // Fill form fields
	    driver.findElement(By.name("firstName"))
	          .sendKeys("Automation");

	    driver.findElement(By.name("lastName"))
	          .sendKeys("Tester");

	    driver.findElement(By.name("email"))
	          .sendKeys("automation@test.com");

	    driver.findElement(By.name("telephone"))
	          .sendKeys("3015551234");

	    System.out.println("Entered form data");

	    // Click Reset button
	    driver.findElement(By.xpath("//input[@type='reset']")).click();

	    System.out.println("Clicked Reset button");

	    // Verify fields are cleared
	    String firstName =
	        driver.findElement(By.name("firstName"))
	              .getAttribute("value");

	    String lastName =
	        driver.findElement(By.name("lastName"))
	              .getAttribute("value");

	    String email =
	        driver.findElement(By.name("email"))
	              .getAttribute("value");

	    if(firstName.isEmpty() &&
	       lastName.isEmpty() &&
	       email.isEmpty()) {

	        System.out.println("PASS - Form fields cleared successfully");

	    } else {

	        System.out.println("FAIL - Form fields were not cleared");
	    }

	    driver.quit();
	}
	
	@Test(priority = 10)
	public void validateDropdownSelections() {

	    System.out.println("======================================");
	    System.out.println("Test 10: Verify Dropdown Selection Functionality");
	    System.out.println("======================================");

	    WebDriverManager.chromedriver().setup();
	    WebDriver driver = new ChromeDriver();

	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	    driver.get("https://rociopmendoza.com");

	    driver.findElement(By.id("lunita-close")).click();

	    driver.findElement(By.xpath("//a[contains(text(),'Book Now')]")).click();
	    System.out.println("Clicked Book Now menu");

	    Select country = new Select(driver.findElement(By.name("country")));
	    country.selectByVisibleText("United States");

	    Select tour = new Select(driver.findElement(By.name("location")));
	    tour.selectByVisibleText("Machu Picchu");

	    Select guests = new Select(driver.findElement(By.name("guest")));
	    guests.selectByVisibleText("1");

	    String selectedCountry = country.getFirstSelectedOption().getText();
	    String selectedTour = tour.getFirstSelectedOption().getText();
	    String selectedGuests = guests.getFirstSelectedOption().getText();

	    System.out.println("Selected Country: " + selectedCountry);
	    System.out.println("Selected Tour: " + selectedTour);
	    System.out.println("Selected Guests: " + selectedGuests);

	    if(selectedCountry.equals("United States") &&
	       selectedTour.equals("Machu Picchu") &&
	       selectedGuests.equals("1")) {

	        System.out.println("PASS - Dropdown selections validated successfully");

	    } else {

	        System.out.println("FAIL - Dropdown selections did not match expected values");
	    }

	    driver.quit();
	}
	
	@Test(priority = 11)
	public void validateNewsletterCheckbox() {

	    System.out.println("======================================");
	    System.out.println("Test 11: Verify Newsletter Checkbox Functionality");
	    System.out.println("======================================");

	    WebDriverManager.chromedriver().setup();
	    WebDriver driver = new ChromeDriver();

	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	    driver.get("https://rociopmendoza.com");

	    driver.findElement(By.id("lunita-close")).click();

	    driver.findElement(By.xpath("//a[contains(text(),'Book Now')]")).click();
	    System.out.println("Clicked Book Now menu");

	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    js.executeScript("arguments[0].click();",
	        driver.findElement(By.xpath("//input[@type='checkbox']"))
	    );

	    boolean checkboxSelected =
	        driver.findElement(By.xpath("//input[@type='checkbox']")).isSelected();

	    if(checkboxSelected) {
	        System.out.println("PASS - Newsletter checkbox selected successfully");
	    } else {
	        System.out.println("FAIL - Newsletter checkbox was not selected");
	    }

	    driver.quit();
	}
	
	@Test(priority = 12)
	public void validateContactMethodRadioButtons() {

	    System.out.println("======================================");
	    System.out.println("Test 12: Verify Contact Method Radio Buttons");
	    System.out.println("======================================");

	    WebDriverManager.chromedriver().setup();
	    WebDriver driver = new ChromeDriver();

	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	    driver.get("https://rociopmendoza.com");

	    driver.findElement(By.id("lunita-close")).click();

	    driver.findElement(By.xpath("//a[contains(text(),'Book Now')]")).click();

	    System.out.println("Clicked Book Now menu");

	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    // Select Email radio button
	    WebElement emailRadio =
	        driver.findElement(By.xpath("//input[@name='contactBy' and @value='email']"));

	    js.executeScript("arguments[0].click();", emailRadio);

	    boolean emailSelected = emailRadio.isSelected();

	    // Select Phone radio button
	    WebElement phoneRadio =
	        driver.findElement(By.xpath("//input[@name='contactBy' and @value='phone']"));

	    js.executeScript("arguments[0].click();", phoneRadio);

	    boolean phoneSelected = phoneRadio.isSelected();

	    System.out.println("Email selected: " + emailSelected);
	    System.out.println("Phone selected: " + phoneSelected);

	    if(phoneSelected) {

	        System.out.println("PASS - Radio button selection validated successfully");

	    } else {

	        System.out.println("FAIL - Radio button selection validation failed");
	    }

	    driver.quit();
	}
	
	@Test(priority = 13)
	public void validateExplicitWaitForBookNowLink() {

	    System.out.println("======================================");
	    System.out.println("Test 13: Verify Explicit Wait Synchronization");
	    System.out.println("======================================");

	    WebDriverManager.chromedriver().setup();
	    WebDriver driver = new ChromeDriver();

	    driver.manage().window().maximize();

	    driver.get("https://rociopmendoza.com");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    wait.until(ExpectedConditions.elementToBeClickable(By.id("lunita-close"))).click();

	    wait.until(ExpectedConditions.elementToBeClickable(
	        By.xpath("//a[contains(text(),'Book Now')]")
	    )).click();

	    System.out.println("Clicked Book Now menu using explicit wait");

	    String currentUrl = driver.getCurrentUrl();

	    System.out.println("Current URL: " + currentUrl);

	    if(currentUrl.contains("format.html")) {
	        System.out.println("PASS - Explicit wait worked and Book Now page opened");
	    } else {
	        System.out.println("FAIL - Book Now page did not open");
	    }

	    driver.quit();
	}
	
	@Test(priority = 14)
	public void validatePageTitle() {

	    System.out.println("======================================");
	    System.out.println("Test 14: Verify Page Title");
	    System.out.println("======================================");

	    // Get actual title
	    String actualTitle = driver.getTitle();

	    // Expected title
	    String expectedTitle = "Professional Tours";

	    System.out.println("Actual Page Title: " + actualTitle);

	    // Validation
	    if(actualTitle.equals(expectedTitle)) {

	        System.out.println("PASS - Page title validated successfully");

	    } else {

	        System.out.println("FAIL - Incorrect page title displayed");
	    }

	
	}
	
	@Test(priority = 15)
	public void validateBookNowCurrentUrl() {

	    System.out.println("======================================");
	    System.out.println("Test 15: Verify Book Now Current URL");
	    System.out.println("======================================");

 
	    HomePage homePage = new HomePage(driver);
	    
	    ScreenshotUtility screenshotUtility =
	            new ScreenshotUtility(driver);

	    homePage.closeLunitaChatbot();

	    homePage.clickBookNowMenu();
	    screenshotUtility.captureScreenshot("Test15Screenshot");

	    String currentUrl = driver.getCurrentUrl();

	    System.out.println("Current URL: " + currentUrl);

	    Assert.assertTrue(
	    	    currentUrl.contains("format.html"),
	    	    "Book Now page URL validation failed!"
	    	);

	    	System.out.println("PASS - Book Now URL validated successfully");
	 
	}
	
	@Test(priority = 16, groups = {"navigation"})
	public void validateAllLinks() {

	    System.out.println("======================================");
	    System.out.println("Test 16: Validate Website Links");
	    System.out.println("======================================");

	    // Close Lunita chatbot
	    driver.findElement(By.id("lunita-close")).click();

	    // Capture all links
	    List<WebElement> links = driver.findElements(By.tagName("a"));

	    System.out.println("Total links found: " + links.size());

	    int validLinks = 0;

	    for(WebElement link : links) {

	        String url = link.getAttribute("href");

	        System.out.println("Link URL: " + url);

	        if(url != null &&
	        		   !url.trim().isEmpty() &&
	        		   url.startsWith("http")) {

	            validLinks++;
	        }
	    }

	    System.out.println("Valid links found: " + validLinks);

	    if(validLinks > 0) {

	        System.out.println("PASS - Website links validated successfully");

	    } else {

	        System.out.println("FAIL - No valid links found");
	    }

	}
	
	@Test(priority = 17, groups = {"navigation"})
	public void validateBrowserBackNavigation() {

	    System.out.println("======================================");
	    System.out.println("Test 17: Verify Browser Back Navigation");
	    System.out.println("======================================");

	    // Close Lunita chatbot
	    HomePage homePage = new HomePage(driver);

	    homePage.closeLunitaChatbot();
	    // Click book now
	    homePage.clickBookNowMenu();

	    System.out.println("Clicked Book Now menu");

	    // Navigate back
	    driver.navigate().back();

	    System.out.println("Browser navigated back");

	    String currentUrl = driver.getCurrentUrl();

	    System.out.println("Current URL after back navigation: " + currentUrl);

	    if(currentUrl.equals("https://rociopmendoza.com/")) {

	        System.out.println("PASS - Browser back navigation validated successfully");

	    } else {

	        System.out.println("FAIL - Browser did not return to homepage");
	    }
	}
	
	@Test(priority = 18, groups = {"navigation"})
	public void validateBrowserForwardNavigation() {

	    System.out.println("======================================");
	    System.out.println("Test 18: Verify Browser Forward Navigation");
	    System.out.println("======================================");

	 // Close Lunita chatbot
	    HomePage homePage = new HomePage(driver);
	    homePage.closeLunitaChatbot();
	    // Click book now
	    homePage.clickBookNowMenu();
	    
	    System.out.println("Clicked Book Now menu");

	    driver.navigate().back();
	    System.out.println("Browser navigated back");

	    driver.navigate().forward();
	    System.out.println("Browser navigated forward");

	    String currentUrl = driver.getCurrentUrl();

	    System.out.println("Current URL after forward navigation: " + currentUrl);

	    if(currentUrl.contains("format.html")) {
	        System.out.println("PASS - Browser forward navigation validated successfully");
	    } else {
	        System.out.println("FAIL - Browser did not return to Book Now page");
	    }

	}
	
	@Test(priority = 19, groups = {"navigation"})
	public void validateBrowserRefresh() {

	    System.out.println("======================================");
	    System.out.println("Test 19: Verify Browser Refresh Functionality");
	    System.out.println("======================================");

	    
	    // Close Lunita chatbot
	    HomePage homePage = new HomePage(driver);
	    homePage.closeLunitaChatbot();
	    // Click book now
	    homePage.clickBookNowMenu();
	    System.out.println("Clicked Book Now menu");

	    // Refresh browser
	    driver.navigate().refresh();

	    System.out.println("Browser refreshed successfully");

	    // Capture current URL
	    String currentUrl = driver.getCurrentUrl();

	    System.out.println("Current URL after refresh: " + currentUrl);

	    // Validation
	    if(currentUrl.contains("format.html")) {

	        System.out.println("PASS - Browser refresh validated successfully");

	    } else {

	        System.out.println("FAIL - Browser refresh failed");
	    }


	}
	
	@Test(priority = 20)
	public void validateBrowserWindowMaximize() {

	    System.out.println("======================================");
	    System.out.println("Test 20: Verify Browser Window Maximization");
	    System.out.println("======================================");

	    driver.manage().window().maximize();

	    Dimension windowSize = driver.manage().window().getSize();

	    System.out.println("Browser window size: " + windowSize);

	    if(windowSize.getWidth() > 1000) {
	        System.out.println("PASS - Browser window maximized successfully");
	    } else {
	        System.out.println("FAIL - Browser window was not maximized");
	    }

	 
	}
	
	@Test(priority = 21)
	public void validateElementVisibility() {

	    System.out.println("======================================");
	    System.out.println("Test 21: Verify Element Visibility");
	    System.out.println("======================================");

	    // Close Lunita chatbot
	    driver.findElement(By.id("lunita-close")).click();

	    // Capture Book Now link
	    WebElement bookNowLink =
	            driver.findElement(By.xpath("//a[contains(text(),'Book Now')]"));

	    boolean isVisible = bookNowLink.isDisplayed();

	    System.out.println("Book Now link displayed: " + isVisible);

	    if(isVisible) {

	        System.out.println("PASS - Book Now link is visible");

	    } else {

	        System.out.println("FAIL - Book Now link is not visible");
	    }


	}
	
	@Test(priority = 22)
	public void validateElementEnabled() {

	    System.out.println("======================================");
	    System.out.println("Test 22: Verify Element Enabled State");
	    System.out.println("======================================");

	    // Close Lunita chatbot
	    driver.findElement(By.id("lunita-close")).click();

	    // Open Book Now page
	    driver.findElement(By.xpath("//a[contains(text(),'Book Now')]")).click();

	    // Capture Submit button
	    WebElement submitButton =
	            driver.findElement(By.name("Submit"));

	    boolean isEnabled = submitButton.isEnabled();

	    System.out.println("Submit button enabled: " + isEnabled);

	    if(isEnabled) {

	        System.out.println("PASS - Submit button is enabled");

	    } else {

	        System.out.println("FAIL - Submit button is disabled");
	    }

	}
	
	@Test(priority = 23)
	public void validateScrollFunctionality() {

	    System.out.println("======================================");
	    System.out.println("Test 23: Verify Scroll Functionality");
	    System.out.println("======================================");

	    // Close Lunita chatbot
	    driver.findElement(By.id("lunita-close")).click();

	    // Open Book Now page
	    driver.findElement(By.xpath("//a[contains(text(),'Book Now')]")).click();

	    // Locate description/comments field
	    WebElement descriptionField =
	            driver.findElement(By.name("description"));

	    // Scroll into view
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    js.executeScript(
	        "arguments[0].scrollIntoView(true);",
	        descriptionField
	    );

	    System.out.println("Scrolled to Description field");

	    boolean isVisible = descriptionField.isDisplayed();

	    if(isVisible) {

	        System.out.println("PASS - Scroll functionality validated successfully");

	    } else {

	        System.out.println("FAIL - Description field not visible after scrolling");
	    }


	}
	
	@Test(priority = 24)
	public void validateScreenshotCaptureOnFailure() throws IOException {

	    System.out.println("======================================");
	    System.out.println("Test 24: Verify Screenshot Capture on Failure");
	    System.out.println("======================================");

	    try {

	        // Intentionally incorrect locator to force failure
	        driver.findElement(By.id("element-does-not-exist")).click();

	        System.out.println("PASS - Element found");

	    } catch (Exception e) {

	        System.out.println("FAIL - Element not found. Capturing screenshot...");

	        File screenshot =
	            ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	        File destination =
	            new File("screenshots/Test24_FailureScreenshot.png");

	        FileUtils.copyFile(screenshot, destination);

	        System.out.println("Screenshot saved at: " + destination.getAbsolutePath());
	    }

	}
	
	@Test(priority = 25)
	public void validateHomepageText() {

	    System.out.println("======================================");
	    System.out.println("Test 25: Verify Element Text Validation");
	    System.out.println("======================================");

	    // Close Lunita chatbot
	    driver.findElement(By.id("lunita-close")).click();

	    // Capture homepage heading
	    WebElement heading =
	    		driver.findElement(By.id("logo"));

	    String actualText = heading.getText();

	    System.out.println("Homepage heading text: " + actualText);

	    // Expected text
	    String expectedText = "Professional Tours";

	    if(actualText.contains(expectedText)) {

	        System.out.println("PASS - Homepage text validated successfully");

	    } else {

	        System.out.println("FAIL - Homepage text validation failed");
	    }

	}
	
	@Test(priority = 26)
	public void validateBrokenLinks() {

	    System.out.println("======================================");
	    System.out.println("Test 26: Verify Broken Links");
	    System.out.println("======================================");

	    // Close Lunita chatbot
	    driver.findElement(By.id("lunita-close")).click();

	    // Capture all links
	    List<WebElement> links =
	            driver.findElements(By.tagName("a"));

	    System.out.println("Total links found: " + links.size());

	    int validLinks = 0;
	    int brokenLinks = 0;

	    for(WebElement link : links) {

	        String url = link.getAttribute("href");

	        if(url != null &&
	           !url.trim().isEmpty() &&
	           url.startsWith("http")) {

	            try {

	                URL linkUrl = new URL(url);

	                HttpURLConnection connection =
	                    (HttpURLConnection) linkUrl.openConnection();

	                connection.setConnectTimeout(5000);

	                connection.connect();

	                int responseCode =
	                    connection.getResponseCode();

	                System.out.println(url +
	                    " --> Response Code: " + responseCode);

	                if(responseCode >= 400) {

	                    brokenLinks++;

	                    System.out.println(
	                        "BROKEN LINK: " + url);

	                } else {

	                    validLinks++;
	                }

	            } catch(Exception e) {

	                brokenLinks++;

	                System.out.println(
	                    "ERROR CHECKING LINK: " + url);
	            }
	        }
	    }

	    System.out.println("Valid links: " + validLinks);
	    System.out.println("Broken links: " + brokenLinks);

	    if(brokenLinks == 0) {

	        System.out.println(
	            "PASS - No broken links detected");

	    } else {

	        System.out.println(
	            "FAIL - Broken links detected");
	    }

	}
	
	@Test(priority = 27)
	public void validatePageTitleWithAssertion() {

	    System.out.println("======================================");
	    System.out.println("Test 27: Verify Page Title Using TestNG Assertion");
	    System.out.println("======================================");

	    String actualTitle = driver.getTitle();
	    String expectedTitle = "Professional Tours";

	    System.out.println("Actual Title: " + actualTitle);
	    System.out.println("Expected Title: " + expectedTitle);

	    Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match!");

	    System.out.println("PASS - Page title validated using TestNG assertion");

	}
	
	@Test(priority = 28)
	public void validateBookNowButtonVisibility() {

	    System.out.println("======================================");
	    System.out.println("Test 28: Verify Book Now Button Visibility");
	    System.out.println("======================================");

	    // Close Lunita chatbot
	    driver.findElement(By.id("lunita-close")).click();

	    // Locate Book Now button
	    WebElement bookNowButton =
	            driver.findElement(By.xpath("//a[contains(text(),'Book Now')]"));

	    // Validate visibility
	    boolean isVisible = bookNowButton.isDisplayed();

	    System.out.println("Book Now button visible: " + isVisible);

	    Assert.assertTrue(isVisible,
	            "Book Now button is NOT visible!");

	    System.out.println("PASS - Book Now button visibility validated");

	}
	
	@Test(priority = 29)
	public void validateBookingRecordInDatabase() throws Exception {

	    ConfigReader configReader = new ConfigReader();
	    DatabaseUtility databaseUtility = new DatabaseUtility();

	    databaseUtility.connectToDatabase(
	            configReader.getDbUrl(),
	            configReader.getDbUser(),
	            configReader.getDbPassword()
	    );

	    ResultSet resultSet =
	            databaseUtility.executeQuery(
	                    "SELECT * FROM Peru WHERE email='automationtest@example.com' ORDER BY id DESC LIMIT 1"
	            );

	    if(resultSet.next()) {

	        String firstName = resultSet.getString("firstName");
	        String lastName = resultSet.getString("lastName");
	        String email = resultSet.getString("email");

	        Assert.assertEquals(firstName, "Automation");
	        Assert.assertEquals(lastName, "Tester");
	        Assert.assertEquals(email, "automationtest@example.com");

	        System.out.println("PASS - Booking record validated in database");

	    } else {

	        Assert.fail("No booking record found in database");
	    }

	    databaseUtility.closeConnection();
	}
	
	@Test(priority = 30)
	public void validateLatestBookingApiResponse() {

	    System.out.println("======================================");
	    System.out.println("Test 30: Validate Latest Booking API Response");
	    System.out.println("======================================");

	    ConfigReader configReader = new ConfigReader();
	    ApiUtility apiUtility = new ApiUtility();

	    Response response =
	            apiUtility.sendGetRequest(configReader.getApiBaseUrl());

	    System.out.println("API Response:");
	    System.out.println(response.asPrettyString());

	    Assert.assertEquals(response.getStatusCode(), 200, "API status code is not 200");

	    Assert.assertEquals(apiUtility.getJsonValue(response, "status"), "success", "API status is not success");

	    Assert.assertEquals(apiUtility.getJsonValue(response, "data.firstName"), "Ana", "First name does not match");

	    Assert.assertEquals(apiUtility.getJsonValue(response, "data.email"), "automationtest@example.com", "Email does not match");

	    System.out.println("PASS - Latest booking API response validated successfully");
	}
	
	@DataProvider(name = "bookingData")
	public Object[][] getBookingData() {

	    return new Object[][] {
	        {"Automation", "Tester", "automation1@example.com", "3015551111"},
	        {"Selenium", "User", "automation2@example.com", "3015552222"},
	        {"QA", "Engineer", "automation3@example.com", "3015553333"}
	    };
	}
	@Test(priority = 31, dataProvider = "bookingData")
	public void submitBookingFormWithDataProvider(String firstName,
	                                              String lastName,
	                                              String email,
	                                              String phone) {

	    System.out.println("======================================");
	    System.out.println("Test 31: Verify Booking Form Submission Using DataProvider");
	    System.out.println("======================================");

	    HomePage homePage = new HomePage(driver);
	    BookNowPage bookNowPage = new BookNowPage(driver);

	    homePage.closeLunitaChatbot();
	    homePage.clickBookNowMenu();

	    bookNowPage.enterFirstName(firstName);
	    bookNowPage.enterLastName(lastName);
	    bookNowPage.enterEmail(email);
	    bookNowPage.enterTelephone(phone);
	    bookNowPage.enterState("MD");
	    bookNowPage.enterDescription("This is a data-driven Selenium test submission.");

	    bookNowPage.selectCountry("United States");
	    bookNowPage.selectTour("Machu Picchu");
	    bookNowPage.selectGuests("1");
	    bookNowPage.selectEmailContactMethod();

	    bookNowPage.clickSubmitButton();

	    String pageText = driver.findElement(By.tagName("body")).getText();

	    Assert.assertTrue(
	        pageText.contains("submitted successfully"),
	        "Booking confirmation message was not displayed!"
	    );

	    System.out.println("PASS - Data-driven booking form submitted successfully for: " + email);
	}
	
	@DataProvider(name = "invalidEmailData")
	public Object[][] getInvalidEmailData() {

	    return new Object[][] {
	        {"invalidemail"},
	        {"test@"},
	        {"test.com"}
	    };
	}
	@Test(priority = 32, dataProvider = "invalidEmailData")
	public void validateInvalidEmailWithDataProvider(String invalidEmail) {

	    System.out.println("======================================");
	    System.out.println("Test 32: Validate Invalid Email Using DataProvider");
	    System.out.println("======================================");

	    HomePage homePage = new HomePage(driver);
	    BookNowPage bookNowPage = new BookNowPage(driver);

	    homePage.closeLunitaChatbot();
	    homePage.clickBookNowMenu();

	    bookNowPage.enterFirstName("Automation");
	    bookNowPage.enterLastName("Tester");
	    bookNowPage.enterEmail(invalidEmail);
	    bookNowPage.enterTelephone("3015551234");
	    bookNowPage.selectEmailContactMethod();

	    bookNowPage.clickSubmitButton();

	    Alert alert = driver.switchTo().alert();

	    String alertText = alert.getText();

	    System.out.println("Validation alert message: " + alertText);

	    Assert.assertTrue(
	        alertText.toLowerCase().contains("email"),
	        "Invalid email validation alert was not displayed!"
	    );

	    alert.accept();

	    System.out.println("PASS - Invalid email validation displayed for: " + invalidEmail);
	}
	
	@DataProvider(name = "csvBookingData")
	public Object[][] getCsvBookingData() {

	    return CsvUtility.getCsvData(
	        "src/test/resources/bookingData.csv"
	    );
	}
	@Test(priority = 33, dataProvider = "csvBookingData")
	public void submitBookingFormUsingCsvData(String firstName,
	                                          String lastName,
	                                          String email,
	                                          String phone) {
		
		System.out.println("======================================");
		System.out.println("Test 33: Verify Booking Form Submission Using CSV Data");
		System.out.println("======================================");
		
		HomePage homePage = new HomePage(driver);
		BookNowPage bookNowPage = new BookNowPage(driver);
		
		homePage.closeLunitaChatbot();
		homePage.clickBookNowMenu();
		
		bookNowPage.enterFirstName(firstName);
		bookNowPage.enterLastName(lastName);
		bookNowPage.enterEmail(email);
		bookNowPage.enterTelephone(phone);
		bookNowPage.enterState("MD");
		bookNowPage.enterDescription("This is a data-driven Selenium test submission.");
		
		bookNowPage.selectCountry("United States");
		bookNowPage.selectTour("Machu Picchu");
		bookNowPage.selectGuests("1");
		bookNowPage.selectEmailContactMethod();
		
		bookNowPage.clickSubmitButton();
		
		String pageText = driver.findElement(By.tagName("body")).getText();
		
		Assert.assertTrue(
		pageText.contains("submitted successfully"),
		"Booking confirmation message was not displayed!"
		);
		
		System.out.println("PASS - Data-driven booking form submitted successfully for: " + email);
		}
	
}