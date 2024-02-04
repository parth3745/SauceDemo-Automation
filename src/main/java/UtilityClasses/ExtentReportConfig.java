package UtilityClasses;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportConfig {
    public static ExtentReports getReportObject() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/index.html");
        reporter.config().setReportName("SauceDemo Automation Test Results");
        reporter.config().setDocumentTitle("SauceDemo results");
        ExtentReports reports = new ExtentReports();
        reports.attachReporter(reporter);
        reports.setSystemInfo("Tester", "Parth Sharma");
        return reports;
    }
}
