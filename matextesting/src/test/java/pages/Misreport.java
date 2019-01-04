package pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import environment.BaseClass;
import testCases.Pages;

public class Misreport {
	protected final WebDriver driver;
	protected final Pages pages;
	ExtentTest logger;
	WebDriverWait w;

	public Misreport(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		pages = new Pages(driver, logger);
		w = new WebDriverWait(driver, 20);
	}

	private String getvalue(String key) throws FileNotFoundException, IOException {
		Properties pr = BaseClass.getvalue();
		return pr.getProperty(key);
	}

	private String getlocator(String key) throws FileNotFoundException, IOException {
		Properties pr = BaseClass.getlocator();
		return pr.getProperty(key);
	}
	
	public void Addresscompleted_mis(String no) throws Exception {
		
		pages.Utill().select_by_label(getlocator("select_mis"), "Address Completed MIS report");
		pages.Wait().wait_until_loader_is_invisible();
		pages.Utill().click_element(getlocator("mis_within"));
		Thread.sleep(1000);
		//pages.Utill().input_text(getlocator("mis_matrefno"), getvalue(no));
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtMatRefNo")).sendKeys(no);
		pages.Utill().click_element(getlocator("mis_generate"));
		Thread.sleep(4000);
		String currentWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		System.out.println(handles);
		for (String e : handles) {
			if (!(e.equals(currentWindow))) {
				driver.switchTo().window(e);
				break;
			}
		}
		driver.switchTo().alert().sendKeys("Administator");
		
	}
	

}
