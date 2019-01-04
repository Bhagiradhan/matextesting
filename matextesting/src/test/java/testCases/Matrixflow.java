package testCases;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import environment.BaseClass;
import environment.SendAttachmentInEmail;
import environment.Utill;

//@Listeners(environment.Listeners.class)
@Listeners(environment.JyperionListener.class)
public class Matrixflow {
	WebDriver driver;
	ExtentHtmlReporter reporter;
	ExtentTest logger;
	ExtentReports extent;
	Pages pages;
	int candid = 3015270;
	String candidateName = "gopi";
	String MatrixRefNo;// = "DEMOTAF140";

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
		//pages.Utill().GoTo(getvalue("url") + "/Matrix/UserHome.aspx");
	}

	@Test(priority = 1, enabled = true)
	public void Login() throws Exception {
		pages.loginpage().Login(getvalue("uname"));

		Assert.assertEquals(pages.Utill().find("ctl00_lblUsername").getText(), "Demotl");

	}

	@Test(priority = 2, enabled = true, dependsOnMethods = "Login")
	public void caseregistration() throws Exception {
		candid = pages.Utill().candidateid();
		candidateName = pages.Utill().candidateName();
		 pages.CaseRegistration().caseRegistration(getvalue("clientname"), candid, candidateName);
//		Assert.assertEquals(re, "Registered Successfully.");
	}

	@Test(priority = 3, enabled = true, dependsOnMethods = "caseregistration")
	public void aasignToDE() throws Exception {
		pages = new Pages(driver, logger);
		pages.CaseRegistration().navigateTo("Daily Activity", "Assign Cases");
		MatrixRefNo = pages.CaseRegistration().assignToDETM(candidateName, candid);
		logger.info(MatrixRefNo);
		System.out.println(MatrixRefNo);
	}

	@Test(priority = 4, enabled = true, dependsOnMethods = "aasignToDE")
	public void dataentry() throws Exception {
		pages = new Pages(driver, logger);
		pages.CaseRegistration().navigateTo("Daily Activity", "Data Entry");
		pages.Utill().find("ctl00_ContentPlaceHolder1_txtMatrixRefNo").sendKeys(MatrixRefNo);
		pages.Utill().find("ctl00_ContentPlaceHolder1_butnSearch").click();
		pages.Wait().wait_until_loader_is_invisible();
		String no = pages.Utill().find("ctl00_ContentPlaceHolder1_grdCandidate_ctl02_btnMatrixRefNo").getText();
		if (no.equals(MatrixRefNo)) {
			driver.findElement(By.linkText(MatrixRefNo)).click();
			pages.Wait().wait_until_loader_is_invisible();
			pages.DataEntryTM().Personal();
			pages.DataEntryTM().AddressCheck(0);
			pages.DataEntryTM().EducationCheck();
			pages.DataEntryTM().EmploymentCheck();
			pages.DataEntryTM().ReeferenceCheck();
			pages.DataEntryTM().GapCheck();
			pages.DataEntryTM().FacisCheck();
			pages.DataEntryTM().CreditCheck();
			pages.DataEntryTM().BvCheck();
			pages.DataEntryTM().ItCheck();
			pages.DataEntryTM().PfCheck();
			String t = "ctl00_ContentPlaceHolder1_ImgBtnSearch";
			pages.Utill().find(t).click();
			try {
				System.out.println("try block");
				Alert alert = driver.switchTo().alert();
				t = alert.getText();
				logger.info(t);
				alert.accept();
				pages.Wait().wait_until_loader_is_invisible();
				logger.pass("data entry completed");
				assertTrue(true);
			} catch (NoAlertPresentException e) {
				System.out.println("NoAlertPresentException");
				WebDriverWait w = new WebDriverWait(driver, 10);
				w.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				t = alert.getText();
				logger.info(t);
				alert.accept();
				pages.Wait().wait_until_loader_is_invisible();
				logger.pass("data entry completed");
				assertTrue(true);
			} catch (UnhandledAlertException e) {
				System.out.println("UnhandledAlertException");
				Alert alert = driver.switchTo().alert();
				t = alert.getText();
				logger.info(t);
				alert.accept();
				pages.Wait().wait_until_loader_is_invisible();
				logger.pass("data entry completed");
				assertTrue(true);
			} catch (Exception e) {
				System.out.println("Exception");
				assertTrue(false);
			}
		} else {
			assertTrue("Matrix ref no not found", false);
		}
	}

	@Test(priority = 5, enabled = true, dependsOnMethods = "dataentry")
	public void assigncase() throws Exception {
		Thread.sleep(6000);

		//pages.Utill().GoTo(getvalue("url") + "/Matrix/AssignerHome.aspx");
		pages.Utill().click_element("//*[@id=\"ctl00_pnlHide\"]/div[2]/ul/li/a[contains(text(),'Dashboard')]");
		pages.Utill().click_element("//*[@id=\"ctl00_pnlHide\"]/div[2]/ul/li[1]/ul/li/a[contains(text(),'Assignor')]");
		pages.Assignor().assign_Address(MatrixRefNo);
		pages.Assignor().assign_Employment(MatrixRefNo);
		pages.Assignor().assign_Education(MatrixRefNo);
		pages.Assignor().assign_Reference(MatrixRefNo);
		pages.Assignor().assign_Criminal(MatrixRefNo);
		pages.Assignor().assign_DB(MatrixRefNo);
		pages.Assignor().assign_Drug(MatrixRefNo);
		pages.Assignor().assign_ID(MatrixRefNo);
		pages.Assignor().assign_ID(MatrixRefNo);
		pages.Assignor().assign_Court(MatrixRefNo);
		pages.Assignor().assign_Facis(MatrixRefNo);
		pages.Assignor().assign_Credit(MatrixRefNo);
		pages.Assignor().assign_BV(MatrixRefNo);
		pages.Assignor().assign_IT(MatrixRefNo);
		pages.Assignor().assign_PF(MatrixRefNo);
		assertTrue(true);
	}

	@Test(priority = 6, enabled = true, dependsOnMethods = "assigncase")
	public void OperationtmAssign() throws Exception {
		//pages.OperationTL().Addresstl(MatrixRefNo);
		pages.OperationTL().Educationtl(MatrixRefNo);
		pages.OperationTL().Employementtl(MatrixRefNo);
		pages.OperationTL().Referencetl(MatrixRefNo);
		pages.OperationTL().Criminaltl(MatrixRefNo);
		pages.OperationTL().Dbtl(MatrixRefNo);
		pages.OperationTL().Drugtl(MatrixRefNo);
		pages.OperationTL().Idtl(MatrixRefNo);
		pages.OperationTL().Idtl(MatrixRefNo);
		pages.OperationTL().Courttl(MatrixRefNo);
		pages.OperationTL().Facistl(MatrixRefNo);
		pages.OperationTL().Credittl(MatrixRefNo);
		pages.OperationTL().BVtl(MatrixRefNo);
		pages.OperationTL().ITtl(MatrixRefNo);
		pages.OperationTL().PFtl(MatrixRefNo);

	}

	@Test(priority = 7, enabled = true, dependsOnMethods = "OperationtmAssign")
	public void Operationtm() throws Exception {
		pages.OperationTM().Address(MatrixRefNo);
		pages.OperationTM().Education(MatrixRefNo);
		pages.OperationTM().Employment(MatrixRefNo);
		//pages.OperationTM().Address(MatrixRefNo);
		pages.OperationTM().Reference(MatrixRefNo);
		pages.OperationTM().Criminal(MatrixRefNo);
		pages.OperationTM().DB(MatrixRefNo);
		pages.OperationTM().Drug(MatrixRefNo);
		pages.OperationTM().ID(MatrixRefNo);
		pages.OperationTM().ID(MatrixRefNo);
		pages.OperationTM().Court(MatrixRefNo);
		pages.OperationTM().Facis(MatrixRefNo);
		pages.OperationTM().IT(MatrixRefNo);
		pages.OperationTM().BV(MatrixRefNo);
		pages.OperationTM().PF(MatrixRefNo);
		pages.OperationTM().Credit(MatrixRefNo);

	}

	@Test(priority = 8, enabled = true, dependsOnMethods = "Operationtm")
	public void Report() throws Exception {
		pages.ReportTL().assignReport(MatrixRefNo);
		pages.ReportTM().Reporttm(MatrixRefNo);

	}

	@Test(priority = 9, enabled = true, dependsOnMethods = "Report")
	public void publishCase() throws Exception {
		pages.CrtDashboard().publishcase(MatrixRefNo);
	}
/*	@Test(priority = 4, enabled = true, dependsOnMethods = "aasignToDEinsuffraise")
	public void dataentryinsuffraise() throws Exception {
		pages = new Pages(driver, logger);
		pages.CaseRegistration().navigateTo("Daily Activity", "Data Entry");
		pages.Utill().find("ctl00_ContentPlaceHolder1_txtMatrixRefNo").sendKeys(MatrixRefNo);
		pages.Utill().find("ctl00_ContentPlaceHolder1_butnSearch").click();
		pages.Wait().wait_until_loader_is_invisible();
		String no = pages.Utill().find("ctl00_ContentPlaceHolder1_grdCandidate_ctl02_btnMatrixRefNo").getText();
		if (no.equals(MatrixRefNo)) {
			driver.findElement(By.linkText(MatrixRefNo)).click();
			pages.Wait().wait_until_loader_is_invisible();
			//pages.DataEntryTM().Personal();
			pages.DataEntryTM().AddressCheck(0);
//			pages.DataEntryTM().EducationCheck();
//			pages.DataEntryTM().EmploymentCheck();
//			pages.DataEntryTM().ReeferenceCheck();
//			pages.DataEntryTM().GapCheck();
//			pages.DataEntryTM().FacisCheck();
//			pages.DataEntryTM().CreditCheck();
//			pages.DataEntryTM().BvCheck();
//			pages.DataEntryTM().ItCheck();
//			pages.DataEntryTM().PfCheck();
			String t = "ctl00_ContentPlaceHolder1_ImgBtnSearch";
			pages.Utill().find(t).click();
			try {
				System.out.println("try block");
				Alert alert = driver.switchTo().alert();
				t = alert.getText();
				logger.info(t);
				alert.accept();
				pages.Wait().wait_until_loader_is_invisible();
				logger.pass("data entry completed");
				assertTrue(true);
			} catch (NoAlertPresentException e) {
				System.out.println("NoAlertPresentException");
				WebDriverWait w = new WebDriverWait(driver, 10);
				w.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				t = alert.getText();
				logger.info(t);
				alert.accept();
				pages.Wait().wait_until_loader_is_invisible();
				logger.pass("data entry completed");
				assertTrue(true);
			} catch (UnhandledAlertException e) {
				System.out.println("UnhandledAlertException");
				Alert alert = driver.switchTo().alert();
				t = alert.getText();
				logger.info(t);
				alert.accept();
				pages.Wait().wait_until_loader_is_invisible();
				logger.pass("data entry completed");
				assertTrue(true);
			} catch (Exception e) {
				System.out.println("Exception");
				assertTrue(false);
			}
		} else {
			assertTrue("Matrix ref no not found", false);
		}
	}*/
	
	/*@Test(priority = 4, enabled = false)
	public void misreports() throws Exception {
		MatrixRefNo="DEMOTAR228";
		pages = new Pages(driver, logger);
		pages.CaseRegistration().navigateTo("MIS", "Manager MIS");
		pages.Misreport().Addresscompleted_mis(MatrixRefNo);
	}*/
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
		//driver.close();
	}

	@AfterSuite
	public void afterSuite() {
		SendAttachmentInEmail email = new SendAttachmentInEmail();
		email.sendPDFReportByGMail("test404mail@gmail.com", "guhanarun1", "ganesh.baskaran@kadambatechnologies.com", "PDF Report", "");
		//email.sendPDFReportByGMail(test404mail@gmail.com, "", ganesh.baskaran@kadambatechnologies.com, "Report_Testing", "");
		extent.flush();
		//driver.quit();
		//SendAttachmentInEmail email = new SendAttachmentInEmail();
		email.sendhtmlemail();

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
