package com.sevenmart.base;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.sevenmart.constants.Constants;
import com.sevenmart.utilities.ScreenshotUtility;
import com.sevenmart.utilities.WaitUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	public WebDriver driver;
	Properties properties = new Properties();
	FileInputStream fileinputstream;
	ScreenshotUtility screenshotutility= new ScreenshotUtility();

	/** launching corresponding browser **/
	public void initialize(String browser, String url) {
		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
		} else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WaitUtility.IMPLICIT_WAIT));
		driver.manage().deleteAllCookies();
	}

	@BeforeMethod(enabled=true,alwaysRun = true)
	public void launchBrowser() {
		String url=properties.getProperty("url");
		String browser=properties.getProperty("browser");
		initialize(browser, url);
	}
	@Parameters("browser")
	@BeforeMethod(enabled=false)
	public void launchBrowser(String browser) {
		String url=properties.getProperty("url");
		
		initialize(browser, url);
	}

	/** initialising config file **/
	public Base() {
		try {
			fileinputstream = new FileInputStream(Constants.CONFIG_FILE_PATH);
			properties.load(fileinputstream);
		} catch (Exception e) {
			System.out.println("file not found");
			e.printStackTrace();
		}
	}
	@AfterMethod(alwaysRun =true)
	public void terminateSession(ITestResult itestresult)
	{
		if(itestresult.getStatus()==ITestResult.FAILURE)
		{
		screenshotutility.takeScreenShot(driver, itestresult.getName());
		}
		driver.quit();
	}

}
