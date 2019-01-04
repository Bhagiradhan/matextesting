package environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseClass {

	static WebDriver driver;
	static Properties loc;
	static Properties val;

	public static WebDriver getDriver() {
		if (driver == null) {

			String chrome_path = System.getProperty("user.dir") + "\\src\\test\\resources\\driver\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chrome_path);
			ChromeOptions chromoption = new ChromeOptions();
			chromoption.setHeadless(true);
			
//			driver = new ChromeDriver(chromoption);
			driver=new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//			Dimension d = new Dimension(1382, 744);
//			driver.manage().window().setSize(d);
			driver.manage().window().maximize();
		}
		return driver;
	}

	public static Properties getlocator() throws FileNotFoundException, IOException {
		if (loc == null) {
			loc = new Properties();
			loc.load(new FileInputStream(new File("./src\\test\\resources\\property\\dataentry_locators.properties")));
			// src\test\resources\property

		}
		return loc;
	}

	public static Properties getvalue() throws FileNotFoundException, IOException {
		if (val == null) {
			val = new Properties();
			val.load(new FileInputStream(new File("./src\\test\\resources\\property\\dataentry_values.properties")));
		}
		return val;
	}

}
