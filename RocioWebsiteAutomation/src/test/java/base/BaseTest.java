package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.ITestResult;
import utils.ScreenshotUtility;
import utils.ConfigReader;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import utils.ExtentReportManager;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    protected WebDriver driver;
    ConfigReader configReader;
    
    public static ExtentReports extent;
    public static ExtentTest test;

    @BeforeMethod(alwaysRun = true)
    public void setUp(java.lang.reflect.Method method) {

        if(extent == null) {
            extent = ExtentReportManager.getReportObject();
        }

        test = extent.createTest(method.getName());

        configReader = new ConfigReader();

        String browser = configReader.getBrowser();
        
        System.out.println("Browser selected from config: " + browser);

        if(browser.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();

            driver = new ChromeDriver();
        }

        else if(browser.equalsIgnoreCase("headless")) {

            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            options.addArguments("--headless=new");

            driver = new ChromeDriver(options);
        }

        else if(browser.equalsIgnoreCase("firefox")) {

            WebDriverManager.firefoxdriver().setup();

            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(configReader.getBaseUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        if(result.getStatus() == ITestResult.FAILURE) {

            ScreenshotUtility screenshotUtility =
                    new ScreenshotUtility(driver);

            String screenshotPath =
                    screenshotUtility.captureScreenshot(result.getName());
            test.addScreenCaptureFromPath(screenshotPath);
        }

        if(driver != null) {
            driver.quit();
      
        
        }
        extent.flush();
    }
}