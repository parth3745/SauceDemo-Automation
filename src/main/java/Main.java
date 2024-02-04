import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/index.html");
        reporter.config().setReportName("SauceDemo Automation Test Results");
        reporter.config().setDocumentTitle("SauceDemo Test Results");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Parth Sharma");
        extent.createTest("demo");
        extent.flush();
        System.exit(0);
    }
}
