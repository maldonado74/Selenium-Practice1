package testCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/*
 * This is an exercise to enter the credentials to login in to a website using xpath
 */
public class TestBrowser {
	static String browser = "Firefox";
	static WebDriver driver;//both can be used RemoteWebDriver or WebDriver
	public static void main(String[] args) {
		//better approach is to use the if else
		if(browser.equals("Chrome")) {
		driver = new ChromeDriver();
		}else if(browser.equals("Firefox")) {
		driver = new FirefoxDriver();
		}else if(browser.equals("Edge")) {
		driver = new EdgeDriver();
		}
		driver.get("https://www.journeytoautomation.org/practice/auth"); // this works, will take you to the website
		System.out.println(driver.getTitle()); //this will print out the title of the website
		WebElement email = driver.findElement(By.id("email")); //find the id=email in website source to add the email value
		email.sendKeys("admin"); //this add the value of the email
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div/form/div[2]/button")).click(); //will click the next button of the email
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10)); //will wait 10 seconds in order to go to next input
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password"))).sendKeys("admin");// enter the input password 
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div/form/div[2]/button[2]")).click();//will click the next button of the password
		System.out.println("Completed this HW with credentials and Github");
		}

}
