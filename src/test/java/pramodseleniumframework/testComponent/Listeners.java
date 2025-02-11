package pramodseleniumframework.testComponent;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pramodseleniumframework.resources.ExtentReportsNG;

public class Listeners extends BaseTest implements ITestListener{
	
	ExtentReports extent = ExtentReportsNG.getExtentRepObj();
	ExtentTest test;
	
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result) {
		
		test = extent.createTest(result.getMethod().getMethodName());
	
		extentTest.set(test);
		
	}
	
	
	
	public void onTestSuccess(ITestResult result) {
		
		extentTest.get().log(Status.PASS, "Test Passed");
		
	}
	
	public void onTestFailure(ITestResult result) {
		
		extentTest.get().fail(result.getThrowable());
		
		try {
			driver =(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String filepath = null;
		try {
			filepath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
		
	}
	

		public void onFinish(ITestContext context) {
		
		extent.flush();
		
	}

	
}
