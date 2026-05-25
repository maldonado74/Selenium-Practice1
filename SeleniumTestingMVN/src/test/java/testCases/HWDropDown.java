package testCases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class HWDropDown {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.tizag.com/htmlT/htmlselect.php");
	  	
		Options op = driver.manage();
		Window win = op.window();
		win.maximize();
		

		Options op1 = driver.manage();
		Timeouts time = op1.timeouts();
		time.implicitlyWait(Duration.ofSeconds(10));
		
		WebElement drowpdown = driver.findElement(By.name("selectionField"));
		Select select = new Select(drowpdown);
		select.selectByVisibleText("Colorado -- CO");
		
		List<WebElement> states = driver.findElements(By.tagName("option"));
		for (int i=0; i<states.size(); i++) {
			System.out.println(states.get(i).getDomAttribute("value")+"--> "+states.get(i).getText());
			}
	}

}
