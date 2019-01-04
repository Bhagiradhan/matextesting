package testCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import environment.Utill;

@Listeners(environment.Listeners.class)
public class Insufficiency {
	WebDriver driver;
	ExtentHtmlReporter reporter;
	ExtentTest logger;
	ExtentReports extent;
	Pages pages;
	int candid = 3015270;
	String candidateName = "gopi";
	String MatrixRefNo = "AUTOMAA081";

	@BeforeSuite
	public void beforeSuit() {
		// reporter = new ExtentHtmlReporter("./Reports/matex.html");
		reporter = new ExtentHtmlReporter("./Reports/Insufficiency.html");
		reporter.config().setDocumentTitle("Matrix test report");
		reporter.config().setReportName("test report");

		reporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(reporter);
//		pages = new Pages(driver, logger);
	}

	@BeforeTest
	public void start() throws IOException {
		String chrome_path = System.getProperty("user.dir") + "\\src\\test\\resources\\driver\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chrome_path);
		ChromeOptions chromoption = new ChromeOptions();
		chromoption.setHeadless(true);
		driver = new ChromeDriver();
		Dimension d = new Dimension(1382, 744);
		driver.manage().window().setSize(d);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(getvalue("url"));
		// driver.get("http://192.168.2.16/MatexTesting");
	}

	@BeforeMethod
	public void setup(Method method) throws Exception {
		logger = extent.createTest(method.getName());
		logger.pass(method.getName() + " Started");
		logger.assignAuthor("Gopinath");
		pages = new Pages(driver, logger);
		// pages.Utill().GoTo(getvalue("url") + "/Matrix/UserHome.aspx");
	}
	@Test(priority=1, enabled=true)
	public void Login() throws Exception {
		pages.loginpage().Login("demotl");
//		candid = pages.Utill().candidateid();
//		candidateName = pages.Utill().candidateName();
//		pages.CaseRegistration().caseRegistration(getvalue("clientname"), candid, candidateName);
//		pages.CaseRegistration().navigateTo("Daily Activity", "Assign Cases");
//		MatrixRefNo = pages.CaseRegistration().assignToDETM(candidateName, candid);
//		logger.info(MatrixRefNo);
//		System.out.println(MatrixRefNo);
		pages.CaseRegistration().navigateTo("Daily Activity", "Data Entry");
		pages.Utill().find("ctl00_ContentPlaceHolder1_txtMatrixRefNo").sendKeys(MatrixRefNo);
		pages.Utill().find("ctl00_ContentPlaceHolder1_butnSearch").click();
		pages.Wait().wait_until_loader_is_invisible();
			driver.findElement(By.linkText(MatrixRefNo)).click();
			pages.Wait().wait_until_loader_is_invisible();
//			pages.DataEntryTM().Personal();
//			pages.DataEntryTM().AddressCheck();
//			pages.DataEntryTM().EducationCheck();
//			pages.DataEntryTM().EmploymentCheck();
//			pages.DataEntryTM().ReeferenceCheck();
//			pages.DataEntryTM().GapCheck();
//			pages.DataEntryTM().FacisCheck();
//			pages.DataEntryTM().CreditCheck();
//			pages.DataEntryTM().BvCheck();
//			pages.DataEntryTM().ItCheck();
//			pages.DataEntryTM().PfCheck();
	}
	@Test(priority=2, enabled=true, dependsOnMethods="Login")
	public void Raise_Address() throws Exception{
		pages.DataEntryTM().RaiseInduff("Address", "Period of Stay required");	
		
	}
	@Test(priority=3, enabled=true, dependsOnMethods="Login")
	public void Raise_Education() throws Exception{
		pages.DataEntryTM().RaiseInduff("Education", "Degree certificate required");
	}
	@Test(priority=4, enabled=true, dependsOnMethods="Login")
	public void Raise_Employment() throws Exception{
		pages.DataEntryTM().RaiseInduff("Employment", "Service Letter Required");
	}
	@Test(priority=5, enabled=true, dependsOnMethods="Login")
	public void Raise_Reference() throws Exception{
		pages.DataEntryTM().RaiseInduff("Reference", "Require email id of the referee");
	}
	@Test(priority=6, enabled=false, dependsOnMethods="Login")
	public void Raise_Gap() throws Exception{
		pages.DataEntryTM().RaiseInduff("Gap", "Require period of stay");
	}
	@Test(priority=7, enabled=false, dependsOnMethods="Login")
	public void Raise_Facis() throws Exception{
		pages.Utill().click_element("//span[contains(text(),'Other') and contains(@id,'tab')]");
		pages.DataEntryTM().RaiseInduff("FACIS", "Require candidate's complete address");
	}
	@Test(priority=8, enabled=true, dependsOnMethods="Login")
	public void Raise_BV() throws Exception{
		pages.Utill().click_element("//span[contains(text(),'Other') and contains(@id,'tab')]");
		pages.DataEntryTM().RaiseInduff("BV", "Require bank location");
	}
	@Test(priority=9, enabled=true, dependsOnMethods="Login")
	public void Raise_IT() throws Exception{
		pages.Utill().click_element("//span[contains(text(),'Other') and contains(@id,'tab')]");
		pages.DataEntryTM().RaiseInduff("IT", "Require company name");
	}
	@Test(priority=10, enabled=true, dependsOnMethods="Login")
	public void Raise_PF() throws Exception{
		pages.Utill().click_element("//span[contains(text(),'Other') and contains(@id,'tab')]");
		pages.DataEntryTM().RaiseInduff("PF", "Require PF number");
	}
	@Test(priority=11, enabled=false, dependsOnMethods="Login")
	public void Raise_CIBIL() throws Exception{
		pages.Utill().click_element("//span[contains(text(),'Other') and contains(@id,'tab')]");
		pages.DataEntryTM().RaiseInduff("CIBIL", "Require signed LOA of the candidate");
	}
	@AfterMethod
	public void tearDown(ITestResult result, Method method) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			String temp = Utill.getScreenshot(driver);
			logger.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.skip(result.getThrowable().getMessage());
		} else {

			logger.pass(method.getName() + " completed");
		}

	}

	@AfterTest
	public void teardown() {
		// pages.loginpage().Logout();
		// driver.close();
	}

	@AfterSuite
	public void afterSuite() {
		extent.flush();
		// driver.quit();
		// SendAttachmentInEmail email = new SendAttachmentInEmail();
		// email.sendhtmlemail();

	}

	private String getvalue(String key) throws FileNotFoundException, IOException {
		Properties pr = new Properties();
		pr.load(new FileInputStream(new File("./src\\test\\resources\\property\\dataentry_values.properties")));
		return pr.getProperty(key);
	}
	
}
