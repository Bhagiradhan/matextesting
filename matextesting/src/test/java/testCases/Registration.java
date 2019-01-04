package testCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import environment.BaseClass;
import environment.SendAttachmentInEmail;
import environment.Utill;

public class Registration {
	WebDriver driver;
	ExtentHtmlReporter reporter;
	ExtentTest logger;
	ExtentReports extent;
	Pages pages;
	int candid = 3015270;
	String candidateName = "Dusthan";
	String MatrixRefNo = "DEMOTAF140";

	@BeforeSuite
	public void beforeSuit() {
		// reporter = new ExtentHtmlReporter("./Reports/matex.html");
		reporter = new ExtentHtmlReporter("./Reports/matrixflow.html");
		reporter.config().setDocumentTitle("Matrix test report");
		reporter.config().setReportName("test report");
		reporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}

	@BeforeTest
	public void start() throws IOException {
		driver = BaseClass.getDriver();
		driver.get(getvalue("url"));
		// driver.get("http://192.168.2.16/MatexTesting");
	}

	@BeforeMethod
	public void setup(Method method) throws Exception {
		logger = extent.createTest(method.getName());
		logger.pass(method.getName() + " Started");
		logger.assignAuthor("Gopinath");
		pages = new Pages(driver, logger);
		pages.Utill().GoTo(getvalue("url") + "/Matrix/UserHome.aspx");
	}

	@Test(priority = 1, enabled = false)
	public void Login() throws Exception {
		pages.loginpage().Login(getvalue("uname"));

//		Assert.assertEquals(pages.Utill().find("ctl00_lblUsername").getText(), "Demotl");

	}

	@Test(priority = 2, enabled = false, dependsOnMethods = "Login")
	public void caseregistration() throws Exception {
		candid = pages.Utill().candidateid();
		candidateName = pages.Utill().candidateName();
////		for (int i = 0; i < 70; i++) {
			pages.CaseRegistration().caseRegistration(getvalue("clientname"), candid, candidateName);
////			Assert.assertEquals(re, "Registered Successfully.");
////		}

	}
	@Test(priority = 3, enabled = true)
	public void newcase() throws Exception {
		String test;
		candid = pages.Utill().candidateid();
		candidateName = pages.Utill().candidateName();
//		driver.get("https://parivahan.gov.in/rcdlstatus/?pur_cd=101");
//		driver.findElement(By.id("form_rcdl:tf_dlNO")).sendKeys("");
//		driver.findElement(By.id("form_rcdl:tf_dob_input")).sendKeys("");
//		test=JOptionPane.showInputDialog("Please input mark for test 1: ");
//		driver.findElement(By.id("form_rcdl:j_idt35:CaptchaID")).sendKeys(test);
//		System.out.println(test);
//		driver.findElement(By.id("form_rcdl:j_idt45")).click();
		String xp = "ctl00_ContentPlaceHolder1_CaseRegistrationUC";

		pages.Utill().input_text(xp + "_txtClientName", getvalue("clientname"));
		pages.Wait().waitForVisibilityOfData(xp + "_ace1_completionListElem", 5);
		pages.Utill().click_element(".//*[text()='" + getvalue("clientname") + "']");
		Select sel = new Select(pages.Utill().find(xp + "_TypeofMedium"));
		sel.selectByIndex(1);
		for (int i = 0; i < 1000; i++) {

			System.err.println(i);
			try {
				sel = new Select(pages.Utill().find(xp + "_ddlSubGroup"));
				sel.selectByIndex(1);
			} catch (Exception e) {

			}

			pages.Utill().input_text(xp + "_Case_CandidateName", candidateName);
			pages.Utill().input_text(xp + "_Case_CandidateId", Integer.toString(candid));
			pages.Utill().input_text(xp + "_Case_EmployeeId", "43232");
			pages.Utill().select_by_label("ctl00_ContentPlaceHolder1_CaseRegistrationUC_TypeofMedium", "SoftCopy");
			try {
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_CaseRegistrationUC_grdSubcheck_ctl01_chkAllSubcheckSelect");
			}
			 catch (WebDriverException e) {
					// TODO: handle exception
					pages.Utill().click_element("ok");
					pages.Utill().click_element("ctl00_ContentPlaceHolder1_CaseRegistrationUC_grdSubcheck_ctl01_chkAllSubcheckSelect");
				}
		try {
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_CaseRegistrationUC_btnRegisterCase");
			pages.Utill().wait_until_loader_is_invisible();
		} catch (WebDriverException e) {
			// TODO: handle exception
			pages.Utill().click_element("ok");
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_CaseRegistrationUC_btnRegisterCase");
		}
			WebDriverWait w = new WebDriverWait(driver, 100);
			w.until(ExpectedConditions.presenceOfElementLocated(By.id("ok")));
			String result = pages.Utill().get_text("//*[@class='m_content']");
			try {
				pages.Utill().click_element("ok");
			} catch (WebDriverException e) {
				// TODO: handle exception
				pages.Utill().click_element("ok");
			}
		}

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
//		driver.close();
	}

	@AfterSuite
	public void afterSuite() {
//		SendAttachmentInEmail email = new SendAttachmentInEmail();
//		email.sendPDFReportByGMail("gopinath.n@kadambatechnologies.com", "KILLER@007", "gopinathvijay7@gmail.com", "PDF Report", "");
		extent.flush();
//		driver.quit();
		// SendAttachmentInEmail email = new SendAttachmentInEmail();
		// email.sendhtmlemail();

	}

	private String getvalue(String key) throws FileNotFoundException, IOException {
		Properties pr = new Properties();
		pr.load(new FileInputStream(new File("./src\\test\\resources\\property\\dataentry_values.properties")));
		return pr.getProperty(key);
	}

	public WebDriver getwebdriver() {
		return driver;
	}
}
