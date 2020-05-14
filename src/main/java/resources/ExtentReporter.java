package resources;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
 
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;
 
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
 
public class ExtentReporter implements IReporter {
    private ExtentReports extent;
    public ExtentTest test;
    
    
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        extent = new ExtentReports(outputDirectory + File.separator + "ExecutionReport.html", true);
        extent.addSystemInfo("Host Name", "Sara PC");
		extent.addSystemInfo("User Name", "Sara Nasr");
		extent.addSystemInfo("Environment", "QA");
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();
 
            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
 
                try {
					buildTestNodes(context.getPassedTests(), LogStatus.PASS);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                try {
					buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                try {
					buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
 
        extent.flush();
        extent.close();
    }
 
    private void buildTestNodes(IResultMap tests, LogStatus status) throws IOException {
        
 
        if (tests.size() > 0) {
        	
            for (ITestResult result : tests.getAllResults()) {
                test = extent.startTest(result.getMethod().getMethodName());
 
                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);
               
                if(result.getStatus()==ITestResult.FAILURE){
        			test.log(LogStatus.FAIL, "Failed Test case: "+result.getName()); //to add name in extent report
        			test.log(LogStatus.FAIL, "Failure Reason of '"+result.getName()+"' is:  "+result.getThrowable()); //to add error/exception in extent report
        			test.log(LogStatus.FAIL, test.addScreenCapture(Listeners.screenshotpath));
        		}
                else if(result.getStatus()==ITestResult.SKIP){
        			test.log(LogStatus.SKIP, "Skipped Test case: " + result.getName());
        		}
        		else if(result.getStatus()==ITestResult.SUCCESS){
        			test.log(LogStatus.PASS, "Passed Test case: " + result.getName());

        		}
                
                extent.endTest(test);
            }
        }
    }
}