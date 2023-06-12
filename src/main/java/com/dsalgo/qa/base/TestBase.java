//Initialized my prop variable and reading my properties.Prop is a global variable that can be used	inside test class and child class also

package com.dsalgo.qa.base;

import java.beans.EventHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;
import com.dsalgo.qa.util.testUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	private static final long PAGE_LOAD_TIMEOUT = 0;
	private static final long IMPLICIT_WAIT = 0;
	public static WebDriver driver;
	public static Properties prop;
	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();
	
	
	public TestBase() {	
		
		//Inside the Constructor of base class we are intilialising the properties
		
		try {
			prop= new Properties();
			FileInputStream ip = new FileInputStream("C:\\Users\\venka\\git\\DSAlgoFinalProject\\src\\main\\java\\com\\dsalgo\\qa\\config\\config.properties");
			prop.load(ip);			
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}				
	}

//Initialization method
@SuppressWarnings("deprecation")
public static WebDriver initilization() {
	
	String browserName=prop.getProperty("browser");
	
	if (browserName.equalsIgnoreCase("chrome")) 
	{
		WebDriverManager.chromedriver().setup();
		/*ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);*/
		driver = new ChromeDriver();

		tdriver.set(driver);
	}
	
	else if (browserName.equalsIgnoreCase("edge")) {

		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
	} else if (browserName.equalsIgnoreCase("safari")) {

		WebDriverManager.safaridriver().setup();
		driver = new SafariDriver();
	}
		
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().pageLoadTimeout(testUtil.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(testUtil.IMPLICIT_WAIT,TimeUnit.SECONDS);	
	driver.get(prop.getProperty("url")); 		
	return getDriver();
}

	public static synchronized WebDriver getDriver() {
	return tdriver.get();
}
	
	
	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("Capture Failed " + e.getMessage());
		}
		return path;
	}

}
	

