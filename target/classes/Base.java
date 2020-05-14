package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
	
	public static WebDriver driver ;
	public Properties prop ;
	
	public WebDriver initializeDriver () throws IOException
	{
		
		prop = new Properties();
		FileInputStream f = new FileInputStream(".\\data.properties");
		prop.load(f);
		if (prop.getProperty("browser").contains("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", ".\\WebDrivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions ();
			if (prop.getProperty("browser").contains("headless"))
			options.addArguments("headless");
			driver = new ChromeDriver (options);
		}
		else if(prop.getProperty("browser").equals("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", ".\\WebDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
			
	}
	
	public String takeScreenshot (String TCname) throws IOException
	{  
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String currdate = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss").format(new Date());
		String destPath = ".\\ExecutionFailureScreenshots\\"+TCname+"Screenshot "+currdate+".png";
		File sf = new File (destPath);
		FileUtils.copyFile(src,sf);
		return sf.getAbsolutePath();
	}

}
