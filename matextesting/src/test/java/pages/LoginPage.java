package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import environment.DbConnection;
import testCases.Pages;

public class LoginPage {
	protected final WebDriver driver;
	protected final Pages pages;
	ExtentTest logger;

	public LoginPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		pages = new Pages(driver, logger);
	}

	public  void Login(String uname) throws Exception {
		//System.out.println(uname+":"+pass);
		//String pass=getpassword(uname);
		
		pages.Utill().input_text("//*[@id='txtUsername']", "demotl");
		pages.Utill().input_text("//*[@id='txtPassword']", "pass@123");
		pages.Utill().click_element("btnLogin");
	}

	public void Logout() throws Exception {
		// pages=new Pages(driver);
		pages.Utill().click_element("ctl00_btnLogout");
		WebDriverWait w = new WebDriverWait(driver, 10);
		w.until(ExpectedConditions.presenceOfElementLocated(By.id("cOK")));
		pages.Utill().click_element("cOK");
	}
	private  String getpassword(String uname) throws Exception{
		String url=driver.getCurrentUrl();
		if(url.contains("http://192.168.2.16")) {
			return DbConnection.GetlocalPassword(uname);
		}
		else if(url.contains("http://103.60.137.37/Matex/")) {
			return DbConnection.GetuatPassword(uname);
		}
		else if(url.contains("https://www.matex.co.in/MatEx/")) {
			return DbConnection.GetlivePassword(uname);
		}
		else {
			return null;
		}
	}
}
