package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    public static ExtentReports getReportObject() {

        String reportPath =
                System.getProperty("user.dir") + "/reports/TestExecutionReport.html";

        ExtentSparkReporter reporter =
                new ExtentSparkReporter(reportPath);

        reporter.config().setReportName("Rocio Website Automation Report");
        reporter.config().setDocumentTitle("Selenium Test Results");

        ExtentReports extent = new ExtentReports();

        extent.attachReporter(reporter);

        extent.setSystemInfo("Tester", "Rocio Mendoza");
        extent.setSystemInfo("Project", "Rocio Website Automation");
        extent.setSystemInfo("Framework", "Selenium + TestNG + POM");

        return extent;
    }
}