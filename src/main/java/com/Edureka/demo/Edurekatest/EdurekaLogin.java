package com.Edureka.demo.Edurekatest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

@Listeners(listeners.TestNGListener.class)
public class EdurekaLogin {
	public WebDriver driver;
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	@BeforeTest
	public void setExtent() {
		// specify location of the report
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/myReport.html");

		htmlReporter.config().setDocumentTitle("Automation Report"); // Tile of report
		htmlReporter.config().setReportName("Functional Testing"); // Name of the report
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		// Passing General information
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("user", "pavan");
	}

	@AfterTest
	public void endReport() {
		extent.flush();
	}

	@BeforeMethod
	public void Login() {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Asus\\eclipse\\chromedriver_win32 (2)\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get("https://www.edureka.co/");
		System.out.println(driver.getTitle());
	}

	@Test(priority=1)
	public void edurekaTitleTest() {
		test = extent.createTest("edurekaTitleTest");
		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertEquals(title, "Instructor-Led Online Training with 24X7 Lifetime Support | Edureka");
	}
	@Test (priority=2)
	public void edurekalogintest() {
		test=extent.createTest("edurekalogintest");
		WebElement login=driver.findElement(By.xpath("//span[text()='Log In']"));
		login.click();
		WebElement Email=driver.findElement(By.id("si_popup_email"));
		Email.sendKeys("seshuhappy33@gmail.com");
		WebElement Password=driver.findElement(By.id("si_popup_passwd"));
		Password.sendKeys("Gmail@9989");
		WebElement Login1=driver.findElement(By.xpath("//button[text()='Login']"));
		Login1.click();
		System.out.println(driver.getPageSource());
	}
	@Test (priority=3,dependsOnMethods = { "edurekalogintest" })  
	public void edurekalogout() throws InterruptedException {
		test=extent.createTest("edurekalogouttest");
		WebElement login=driver.findElement(By.xpath("//span[text()='Log In']"));
		login.click();
		WebElement Email=driver.findElement(By.id("si_popup_email"));
		Email.sendKeys("seshuhappy33@gmail.com");
		WebElement Password=driver.findElement(By.id("si_popup_passwd"));
		Password.sendKeys("Gmail@9989");
		WebElement Login1=driver.findElement(By.xpath("//button[text()='Login']"));
		Login1.click();
		Thread.sleep(3000);
		WebElement Edureka=driver.findElement(By.xpath("/html/body/header/nav/div[4]/ul/li[6]/a"));
		Edureka.click();
		Thread.sleep(3000);
		WebElement Logout=driver.findElement(By.xpath("/html/body/header/nav/div[4]/ul/li[6]/ul/li[12]/a"));
		Logout.click();
		
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
			test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in extent
																					// report
			String screenshotPath = EdurekaLogin.getScreenshot(driver, result.getName());
			test.addScreenCaptureFromPath(screenshotPath);// adding screen shot
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
		}
		driver.quit();
	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		// after execution, you could see a folder "FailedTestsScreenshots" under src
		// folder
		String destination = (System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png");
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
}
