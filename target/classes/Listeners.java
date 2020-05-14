package resources;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {

	Base b = new Base();
	public static String screenshotpath ;
	
	public void onTestStart() {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess() {
		// TODO Auto-generated method stub
		
	}

	

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		try {
			screenshotpath = b.takeScreenshot(result.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	public void onTestSkipped() {
		// TODO Auto-generated method stub
	
	}

	public void onTestFailedButWithinSuccessPercentage() {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedWithTimeout() {
		// TODO Auto-generated method stub
		
	}

	public void onStart() {
		// TODO Auto-generated method stub
		
	}

	public void onFinish() {
		// TODO Auto-generated method stub
		
	}
	
	

}
