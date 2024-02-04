package TestListeners;

import UtilityClasses.ExtentReportConfig;
import UtilityClasses.UtilityMethods;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listener implements ITestListener {

    ExtentReports reports = ExtentReportConfig.getReportObject();
    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
// TODO Auto-generated method stub
        test = reports.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Name: " + result.getName());
// TODO Auto-generated method stub
        test.log(Status.PASS, "Test passed.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
// TODO Auto-generated method stub
        test.fail(result.getThrowable());
//        try {
//            String path = saveScreenshot(result.getMethod().getMethodName());
//            test.addScreenCaptureFromPath(path, result.getMethod().getMethodName());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
// TODO Auto-generated method stub
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
// TODO Auto-generated method stub
    }

    @Override
    public void onStart(ITestContext context) {
// TODO Auto-generated method stub
    }

    @Override
    public void onFinish(ITestContext context) {
// TODO Auto-generated method stub
        reports.flush();
    }
}
