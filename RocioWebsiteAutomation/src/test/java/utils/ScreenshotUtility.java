package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtility {

    WebDriver driver;

    // Constructor
    public ScreenshotUtility(WebDriver driver) {

        this.driver = driver;
    }

    // Capture screenshot method
    public String captureScreenshot(String screenshotName) {

        File srcFile =
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        File destinationFile =
                new File("./screenshots/" + screenshotName + ".png");

        try {

            FileUtils.copyFile(srcFile, destinationFile);

            System.out.println("Screenshot saved successfully");

        } catch (IOException e) {

            System.out.println("Screenshot capture failed");

            e.printStackTrace();
        }
        return destinationFile.getAbsolutePath();
    }
}