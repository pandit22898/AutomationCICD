package pramodseleniumframework.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {

	public static ExtentReports getExtentRepObj() {
		
		String path = System.getProperty("user.dir")+"\\report\\Index.html";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		
		reporter.config().setReportName("Pramod Automation Test");
		reporter.config().setDocumentTitle("Pramod's Execution Result");
		
		ExtentReports extent = new ExtentReports();
		
		extent.attachReporter(reporter);
		
		extent.setSystemInfo("Tester", "Pramod Pandit");
		
		return extent;
		
	}
	
}
