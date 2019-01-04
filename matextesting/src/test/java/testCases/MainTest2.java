package testCases;

import static org.testng.Assert.assertEquals;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import environment.BaseClass;
import environment.SendAttachmentInEmail;
import environment.Utill;

@Listeners(environment.JyperionListener.class)
public class MainTest2 {
	WebDriver driver;
	ExtentHtmlReporter reporter;
	ExtentTest logger;
	ExtentReports extent;
	Pages pages;
	int candid = 3015270;
	String candidateName = "gopi";
	String MatrixRefNo = "AUTOMAA098";
	Map<String, String> map;
	
	@BeforeSuite
	public void beforeSuit() {
		reporter = new ExtentHtmlReporter("./Reports/demo.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}
	

	@BeforeTest
	public void start() throws IOException {
		driver=BaseClass.getDriver();
		driver.get(getvalue("url"));
	}

	@BeforeMethod
	public void setup(Method method) throws Exception{
		logger = extent.createTest(method.getName());
		logger.pass(method.getName() + " Started");
		pages = new Pages(driver, logger);
		System.out.println("before method");
		//pages.Utill().GoTo(getvalue("url") + "/Matrix/UserHome.aspx");
	}

	@Test(priority = 1, enabled = true)
	public void Login() throws Exception {
		pages.loginpage().Login(getvalue("uname"));
		
		Assert.assertEquals(pages.Utill().find("ctl00_lblUsername").getText(), "Demotl");

	}
	@Test(priority = 2, enabled = true)
	public void testtwo() {
		System.out.println("this is test two");
	assertEquals("actual", "actual");
	}
	
	
	
	@AfterMethod
	public void tearDown(ITestResult result, Method method) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String temp = Utill.getScreenshot(driver);
			logger.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		} else {
			logger.pass(method.getName() + " completed");
		}

	}

	@AfterTest
	public void teardown() throws Exception{
		// pages.loginpage().Logout();
		Thread.sleep(10000);
//		driver.close();
	}

	@AfterSuite
	public void afterSuite() {
		SendAttachmentInEmail email = new SendAttachmentInEmail();
		email.sendPDFReportByGMail("gopinath.n@kadambatechnologies.com", "KILLER@007", "gopinathvijay7@gmail.com", "PDF Report", "");
		extent.flush();
//		 driver.quit();
		// SendAttachmentInEmail email = new SendAttachmentInEmail();
		// email.sendhtmlemail();

	}
	private String getvalue(String key) throws FileNotFoundException, IOException {
		Properties pr = BaseClass.getvalue();
		return pr.getProperty(key);
	}
}
