package TestListeners;

import UtilityClasses.UtilityMethods;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener extends UtilityMethods implements ITestListener {
    public Listener(WebDriver driver) {
        super(driver);
    }

    @Override
    public void onTestStart(ITestResult result) {
// TODO Auto-generated method stub
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Name: " + result.getName());
// TODO Auto-generated method stub
    }

    @Override
    public void onTestFailure(ITestResult result) {
// TODO Auto-generated method stub
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
    }
}
