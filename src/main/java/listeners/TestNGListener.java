package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.ITestContext;
public class TestNGListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Testcases are started and the details are"+result.getName());
		ITestListener.super.onTestStart(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Testcases are passed and the details are"+result.getName());
		ITestListener.super.onTestSuccess(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Testcases are Failed and the details are"+result.getName());
		ITestListener.super.onTestFailure(result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Testcases are Skipped and the details are"+result.getName());
		ITestListener.super.onTestSkipped(result);
	}


  
}

