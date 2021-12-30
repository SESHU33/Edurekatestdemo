package com.Edureka.demo.Edurekatest;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(listeners.TestNGListener.class)
public class SpiceJet {
	static WebDriver driver;

	@Test(priority = 1)
	public void homepage() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Asus\\eclipse\\chromedriver_win32 (2)\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.spicejet.com/");
		// driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		// driver.switchTo().alert().dismiss();
	}

	@Test(priority = 2)
	public void bookticket() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Asus\\eclipse\\chromedriver_win32 (2)\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.spicejet.com/");
		Thread.sleep(3000);
		// driver.switchTo().alert().dismiss();
		WebElement roundtrip = driver.findElement(By.xpath("//div[text()='round trip']"));
		if (roundtrip.isSelected()) {
			System.out.println("round trip is selected");
		} else {
			roundtrip.click();
			System.out.println("round trip is clicked");
		}
	}

	@Test(priority = 3)
	public void signin() {
		WebElement Login = driver.findElement(By.xpath("//div[text()='Login']"));
		Login.click();
		WebElement mobilenumber = driver.findElement(
				By.xpath("//*[@id=\"main-container\"]/div/div[3]/div[2]/div[2]/div[2]/div/div[4]/div[2]/input"));
		mobilenumber.sendKeys("9989518413");
		WebElement password = driver.findElement(
				By.xpath("//*[@id=\"main-container\"]/div/div[3]/div[2]/div[2]/div[2]/div/div[5]/div[1]/div[2]/input"));
		password.sendKeys("Gmail@9989");
		WebElement login = driver
				.findElement(By.xpath("//*[@id=\"main-container\"]/div/div[1]/div/div/div/div/div[2]/div/div[5]"));
		login.click();
	}
}
