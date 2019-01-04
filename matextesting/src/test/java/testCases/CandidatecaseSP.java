package testCases;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.omg.Messaging.SyncScopeHelper;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import environment.DbConnection;
import environment.ReadEmail;
import environment.Utill;

@Listeners(environment.Listeners.class)
public class CandidatecaseSP {
	WebDriver driver;
	ExtentHtmlReporter reporter;
	ExtentTest logger;
	ExtentReports extent;
	Pages pages;
	int candid = 3015270;
	String candidateName = "gopi";
	String MatrixRefNo = "DEMOTAF140";
	String loginid;

	@BeforeSuite
	public void beforeSuit() {
		// reporter = new ExtentHtmlReporter("./Reports/matex.html");
		reporter = new ExtentHtmlReporter("./Reports/CandidatecaseSP.html");
		reporter.config().setDocumentTitle("Matrix test report");
		reporter.config().setReportName("test report");

		reporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(reporter);
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
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
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

	@Test(priority = 1, enabled = true)
	public void CandidateInitiation() throws Exception {
		String candidateName = pages.Utill().candidateName();
		int candid = pages.Utill().candidateid();
		pages.loginpage().Login("demotl");
		pages.CaseRegistration().navigateTo("CRT", "Candidate users");
		loginid = pages.CandidateInitiation().InitiateCandidate(candidateName, candid);
		pages.loginpage().Logout();
		System.out.println(candidateName + " " + candid);
	}

	@Test(priority = 2, enabled = true)
	public void checkemail() throws Exception {
		String data = ReadEmail.read();
		assertTrue(data.contains(loginid));
	}

	@Test(priority = 3, enabled = true)
	public void Personal_de() throws Exception {
		System.out.println("LOGIN ID IS :" + loginid);
		// pages.loginpage().Login("Vign6436");
		pages.loginpage().Login(loginid);
		pages.CandidateInitiation().Personal();
		pages.Utill().SwitchDefault();
		pages.CandidateInitiation().wait_until_loader_is_invisible();
		String temps = Utill.getScreenshot(driver);
		logger.info("after address", MediaEntityBuilder.createScreenCaptureFromPath(temps).build());
	}

	@Test(priority = 4, enabled = true)
	public void Address_de() throws Exception {

		pages.Wait().visibilityOfElement(".//*[@id='Address']/a");
		pages.Utill().click_element(".//*[@id='Address']/a");
		pages.Utill().SwitchFramebyIndex(1);
		pages.CandidateInitiation().address("ctl05");

		pages.Utill().SwitchDefault();
		pages.CandidateInitiation().wait_until_loader_is_invisible();

	}

	@Test(priority = 5, enabled = true)
	public void Education_de() throws Exception {
		SoftAssert sf = new SoftAssert();
		// pages.loginpage().Login("Vign6436");
		// pages.CandidateInitiation().wait_until_loader_is_invisible();
		pages.Wait().visibilityOfElement(".//*[@id='Education']/a");
		pages.Utill().click_element(".//*[@id='Education']/a");
		pages.Utill().SwitchFramebyIndex(2);
		sf.assertEquals("success", pages.CandidateInitiation().Edcucation("10"));
		sf.assertEquals("success", pages.CandidateInitiation().Edcucation("12"));
		sf.assertEquals("success", pages.CandidateInitiation().Edcucation("Diploma"));
		sf.assertEquals("success", pages.CandidateInitiation().Edcucation("UG1"));
		sf.assertEquals("success", pages.CandidateInitiation().Edcucation("PG1"));
		sf.assertEquals("success", pages.CandidateInitiation().Edcucation("Highest"));

		pages.Utill().SwitchDefault();
		pages.CandidateInitiation().wait_until_loader_is_invisible();
		sf.assertAll();
	}

	@Test(priority = 6, enabled = true)
	public void Employment_de() throws Exception {

		Thread.sleep(1000);
		pages.Utill().scrollTo("Employment");
		pages.Utill().click_element(".//*[@id='Employment']");
		pages.Utill().SwitchFramebyIndex(3);
		pages.CandidateInitiation().Employment("Emp 1(Latest)", "demo employe123");
		pages.CandidateInitiation().Employment("Emp 2", "demo employer 108");
	}

	@Test(priority = 7, enabled = true)
	public void Reference_de() throws Exception {
		pages.Utill().SwitchDefault();
		SoftAssert sf = new SoftAssert();
		pages.Wait().visibilityOfElement(".//*[@id='Reference']/a");
		pages.Utill().click_element(".//*[@id='Reference']/a");
		pages.Utill().SwitchFramebyIndex(4);
		sf.assertEquals("success", pages.CandidateInitiation().reference("Ref 1"));
		sf.assertEquals("success", pages.CandidateInitiation().reference("Ref 2"));
		sf.assertAll();
	}

	@Test(priority = 8, enabled = true)
	public void Attach_Doc() throws Exception {

		pages.Utill().SwitchDefault();
		pages.Utill().scrollTo("ctl00_BtnAttach");
		pages.Utill().click_element("ctl00_BtnAttach");
		pages.Wait().wait_until_spinner_is_invisible("ctl00_ContentPlaceHolder1_overlayScreen_Laod_11");
		pages.CandidateInitiation().attachefile("pancard.jpg", "PAN Card", "");
		pages.CandidateInitiation().attachefile("passport.pdf", "Passport", "");
		pages.CandidateInitiation().attachefile("emp_one.pdf", "Pay Slip", "Emp 1");
		pages.CandidateInitiation().attachefile("vemp_one.pdf", "Offer Letter", "Emp 1");
		pages.CandidateInitiation().attachefile("emp_two.pdf", "Pay Slip", "Emp 2");
		pages.CandidateInitiation().attachefile("vemp_two.pdf", "Relieving Letter", "Emp 2");
		pages.CandidateInitiation().attachefile("edu_one.pdf", "Degree certificate", "Diploma");
		pages.CandidateInitiation().attachefile("Edu_two.pdf", "Degree certificate", "UG1");
		pages.CandidateInitiation().attachefile("Edu_three.pdf", "Degree certificate", "PG1");
		pages.CandidateInitiation().attachefile("Edu_four.pdf", "Degree certificate", "Highest");
		pages.Utill().click_element("ctl00_BtnPopupCloseAtt");
		pages.Wait().wait_until_spinner_is_invisible("ctl00_ContentPlaceHolder1_overlayScreen_Laod_11");
		pages.Utill().click_element("ctl00_ibtnSubmit");
		// pages.Wait().wait_until_spinner_is_invisible("ctl00_ContentPlaceHolder1_overlayScreen_Laod_11");
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkAccept");
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnAccept");
		pages.Utill().handle_Alert();
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
		driver.close();
	}

	@AfterSuite
	public void afterSuite() {
		extent.flush();
		driver.quit();
		// SendAttachmentInEmail email = new SendAttachmentInEmail();
		// email.sendhtmlemail();

	}

	private String getvalue(String key) throws FileNotFoundException, IOException {
		Properties pr = new Properties();
		pr.load(new FileInputStream(new File("./src\\test\\resources\\property\\dataentry_values.properties")));
		return pr.getProperty(key);
	}

}
