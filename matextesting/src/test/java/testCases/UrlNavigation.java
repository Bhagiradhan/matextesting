package testCases;

import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
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
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import environment.SendAttachmentInEmail;
import environment.Utill;
import org.testng.annotations.Listeners;


@Listeners(environment.Listeners.class)

public class UrlNavigation {
	WebDriver driver;
	ExtentHtmlReporter reporter;
	ExtentTest logger;
	ExtentReports extent;
	Pages pages;
	Map<String, String> map;

	@BeforeSuite
	public void beforeSuit() {
		// reporter = new ExtentHtmlReporter("./Reports/matex.html");
		reporter = new ExtentHtmlReporter("./Reports/UrlNavigation.html");
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
		driver = new ChromeDriver(chromoption);
		 Dimension d = new Dimension(1382, 744);
		 driver.manage().window().setSize(d);
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.get(getvalue("url"));

	}

	@BeforeMethod
	public void setup(Method method) {
		logger = extent.createTest(method.getName());
		logger.pass(method.getName() + " Started");
		logger.assignAuthor("Ganesh");

		pages = new Pages(driver, logger);
		//System.out.println("before method");
		map = new LinkedHashMap<String, String>();
	}

	@Test(priority = 1, enabled = true)
	public void demotlpage() throws Exception {
		pages.loginpage().Login(getvalue("uname"));
		final String path = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[1]/ul/li/a";
		final String finalqc = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[2]/ul/li/a";
		final String crt = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[3]/ul/li/a";
		final String DailyActivity = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[4]/ul/li/a";
		final String MIS = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[5]/ul/li/a";

		pages.MatrixPortalNavigation().geturl("Dashboard", map, path);
		pages.MatrixPortalNavigation().geturl("Final QC", map, finalqc);
		pages.MatrixPortalNavigation().geturl("CRT", map, crt);
		pages.MatrixPortalNavigation().geturl("Daily Activity", map, DailyActivity);
		pages.MatrixPortalNavigation().geturl("MIS", map, MIS);
		pages.Utill().printMap(map);
		pages.loginpage().Logout();
		
	}

	

	@Test(priority = 2, enabled = true)
	public void AddressCheckPage() throws Exception {

		try {
			pages.loginpage().Login(getvalue("uname"));
			pages.CaseRegistration().navigateTo("Dashboard", "Residence TM");
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_grdteamleader_ctl02_btnMatrixRefNo");
			pages.MatrixPortalNavigation().verificationpage("Candidate Verify Address");
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(e.getMessage().toString(),false);
			logger.fail(e.getMessage().toString());
			String temp = Utill.getScreenshot(driver);
			logger.fail(e.getMessage().toString(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		} finally {
			String currenturl = getvalue("url") + "/Matrix/UserHome.aspx";
			pages.Utill().GoTo(currenturl);
		}
	}

	@Test(priority = 3, enabled = true)
	public void EmploymentCheckPage() throws Exception {
		String currentWindow = driver.getWindowHandle();;
		try {

			pages.CaseRegistration().navigateTo("Dashboard", "Prior TM");
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnOutStandingFW");
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_lbtnNormalUpt");
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_grdUpdation_ctl02_btnemp");
			pages.Wait().wait_until_loader_is_invisible();
			Thread.sleep(1000);
			
			Set<String> handles = driver.getWindowHandles();
			System.out.println(handles);
			for (String e : handles) {
				if (!(e.equals(currentWindow))) {
					driver.switchTo().window(e);
					break;
				}
			}

			pages.MatrixPortalNavigation().verificationpage("Candidate Verified Employement DataEntry Page");
			assertTrue(true);
		} catch (Exception e) {

			assertTrue(e.getMessage().toString(),false);
			logger.fail(e.getMessage().toString());
			String temp = Utill.getScreenshot(driver);
			logger.fail(e.getMessage().toString(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		} finally {
			// driver.switchTo().window(currentWindow);
			driver.switchTo().window(currentWindow);
		}

	}

	@Test(priority = 4, enabled = true)
	public void EducationCheckPage() throws Exception {
		try {
			pages.CaseRegistration().navigateTo("Dashboard", "Academic TM");
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnOutStandingFW");
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_lbtnNormalUpdation");
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_grdAllocFW_ctl02_imgEdit");
			pages.Wait().wait_until_loader_is_invisible();
			pages.MatrixPortalNavigation().verificationpage("Candidate Verified Education DataEntry Page");
			assertTrue(true);
		} catch (Exception e) {

			assertTrue(e.getMessage().toString(),false);
			logger.fail(e.getMessage().toString());
			String temp = Utill.getScreenshot(driver);
			logger.fail(e.getMessage().toString(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		} finally {

			pages.Utill().GoTo(getvalue("url") + "/Matrix/UserHome.aspx");
		}

	}
	@Test(priority = 5, enabled = true)
	public void ReferenceCheckPage() throws Exception {
		try {
			
			pages.CaseRegistration().navigateTo("Dashboard", "Reference TM");
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_OutStandBeyondTAT");
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_lbtnNormal");
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_grdOutstanding_ctl02_Imgrefedit");
			pages.Wait().wait_until_loader_is_invisible();
			pages.MatrixPortalNavigation().verificationpage("Candidate Verified Reference DataEntry Page");
			assertTrue(true);
		} catch (Exception e) {

			assertTrue(e.getMessage().toString(),false);
			logger.fail(e.getMessage().toString());
			String temp = Utill.getScreenshot(driver);
			logger.fail(e.getMessage().toString(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		} finally {

			pages.Utill().GoTo(getvalue("url") + "/Matrix/UserHome.aspx");
		}

	}
	@Test(priority = 6, enabled = true)
	public void CriminalCheckPage() throws Exception {
		try {
			
			pages.CaseRegistration().navigateTo("Dashboard", "Criminal TM");
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_lnkOutstanding_updationBeyond");
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_lbtnNormalUpt");
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_grupdation_ctl02_btnShowAddress");
			pages.Wait().wait_until_loader_is_invisible();
			pages.MatrixPortalNavigation().verificationpage("Candidate Verified Criminal DataEntry Page");
			assertTrue(true);
		} catch (Exception e) {

			assertTrue(e.getMessage().toString(),false);
			logger.fail(e.getMessage().toString());
			String temp = Utill.getScreenshot(driver);
			logger.fail(e.getMessage().toString(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		} finally {

			pages.Utill().GoTo(getvalue("url") + "/Matrix/UserHome.aspx");
		}

	}
	@Test(priority = 7, enabled = true)
	public void DBCheckPage() throws Exception {
		try {
			//pages.loginpage().Login(getvalue("uname"));
			pages.CaseRegistration().navigateTo("Dashboard", "DB Check TM");
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnBeyondOutStanding");
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_lbtnNormal");
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_grdCandidate_ctl02_btnEdit");
			pages.Wait().wait_until_loader_is_invisible();
			pages.MatrixPortalNavigation().verificationpage("Database check verification");
			assertTrue(true);
		} catch (Exception e) {

			assertTrue(e.getMessage().toString(),false);
			logger.fail(e.getMessage().toString());
			String temp = Utill.getScreenshot(driver);
			logger.fail(e.getMessage().toString(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		} finally {

			pages.Utill().GoTo(getvalue("url") + "/Matrix/UserHome.aspx");
		}

	}
	@Test(priority = 8, enabled = true)
	public void DrugCheckPage() throws Exception {
		try {
			//pages.loginpage().Login(getvalue("uname"));
			pages.CaseRegistration().navigateTo("Dashboard", "Drug Check TM");
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnBeyondOutStanding_1");
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_lbtnNormalUpt");
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_grdCandidate_ctl02_btnEdit");
			pages.Wait().wait_until_loader_is_invisible();
			pages.MatrixPortalNavigation().verificationpage("DrugCheckVerification Page");
			assertTrue(true);
		} catch (Exception e) {

			assertTrue(e.getMessage().toString(),false);
			logger.fail(e.getMessage().toString());
			String temp = Utill.getScreenshot(driver);
			logger.fail(e.getMessage().toString(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		} finally {

			pages.Utill().GoTo(getvalue("url") + "/Matrix/UserHome.aspx");
		}

	}
	@Test(priority = 9, enabled = true)
	public void IDCheckPage() throws Exception {
		try {
			//pages.loginpage().Login(getvalue("uname"));
			pages.CaseRegistration().navigateTo("Dashboard", "ID Check TM");
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnBeyondOutStanding");
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_lbtnNormal");
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_grdCandidate_ctl02_btnEdit");
			pages.Wait().wait_until_loader_is_invisible();
			pages.MatrixPortalNavigation().verificationpage("ID Check Verification");
			assertTrue(true);
		} catch (Exception e) {

			assertTrue(e.getMessage().toString(),false);
			logger.fail(e.getMessage().toString());
			String temp = Utill.getScreenshot(driver);
			logger.fail(e.getMessage().toString(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		} finally {

			pages.Utill().GoTo(getvalue("url") + "/Matrix/UserHome.aspx");
		}

	}
	@Test(priority = 10, enabled = true)
	public void CourtCheckPage() throws Exception {
		try {
			//pages.loginpage().Login(getvalue("uname"));
			pages.CaseRegistration().navigateTo("Dashboard", "CourtTm");
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnBeyondOutStandingUpt");
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_lbtnNormalUpt");
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_grdCandidateUpt_ctl02_btnEdit");
			pages.Wait().wait_until_loader_is_invisible();
			pages.MatrixPortalNavigation().verificationpage("Court Check Verification Page");
			assertTrue(true);
		} catch (Exception e) {

			assertTrue(e.getMessage().toString(),false);
			logger.fail(e.getMessage().toString());
			String temp = Utill.getScreenshot(driver);
			logger.fail(e.getMessage().toString(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		} finally {

			pages.Utill().GoTo(getvalue("url") + "/Matrix/UserHome.aspx");
		}

	}
	@Test(priority = 11, enabled = true)
	public void facisCheckPage() throws Exception {
		try {
			//pages.loginpage().Login(getvalue("uname"));
			pages.CaseRegistration().navigateTo("Dashboard", "FACIS TM");
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_OutStandBeyondTAT");
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_lbtnNormal");
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_grdOutstanding_ctl02_Imgrefedit");
			pages.Wait().wait_until_loader_is_invisible();
			boolean result=pages.MatrixPortalNavigation().OthersVerificationpage("FACIS check verification");
			assertTrue(result);
		} catch (Exception e) {

			assertTrue(e.getMessage().toString(),false);
			logger.fail(e.getMessage().toString());
			String temp = Utill.getScreenshot(driver);
			logger.fail(e.getMessage().toString(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		} finally {

			pages.Utill().GoTo(getvalue("url") + "/Matrix/UserHome.aspx");
		}

	}
	@Test(priority = 12, enabled = true)
	public void CreditCheckPage() throws Exception {
		try {
			//pages.loginpage().Login(getvalue("uname"));
			pages.CaseRegistration().navigateTo("Dashboard", "Credit TM");
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_OutStandBeyondTAT");
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_lbtnNormal");
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_grdOutstanding_ctl02_Imgrefedit");
			pages.Wait().wait_until_loader_is_invisible();
			boolean result=pages.MatrixPortalNavigation().OthersVerificationpage("Credit check verification");
			assertTrue(result);
		} catch (Exception e) {

			assertTrue(e.getMessage().toString(),false);
			logger.fail(e.getMessage().toString());
			String temp = Utill.getScreenshot(driver);
			logger.fail(e.getMessage().toString(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		} finally {

			pages.Utill().GoTo(getvalue("url") + "/Matrix/UserHome.aspx");
		}

	}
	@Test(priority = 13, enabled = true)
	public void ITCheckPage() throws Exception {
		try {
			//pages.loginpage().Login(getvalue("uname"));
			pages.CaseRegistration().navigateTo("Dashboard", "IT TM");
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_OutStandBeyondTAT");
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_lbtnNormal");
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_grdOutstanding_ctl02_Imgrefedit");
			pages.Wait().wait_until_loader_is_invisible();
			boolean result=pages.MatrixPortalNavigation().OthersVerificationpage("IT check verification");
			assertTrue(result);
		} catch (Exception e) {

			assertTrue(e.getMessage().toString(),false);
			logger.fail(e.getMessage().toString());
			String temp = Utill.getScreenshot(driver);
			logger.fail(e.getMessage().toString(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		} finally {

			pages.Utill().GoTo(getvalue("url") + "/Matrix/UserHome.aspx");
		}

	}
	@Test(priority = 14, enabled = true)
	public void PFCheckPage() throws Exception {
		try {
			//pages.loginpage().Login(getvalue("uname"));
			pages.CaseRegistration().navigateTo("Dashboard", "PF TM");
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_OutStandBeyondTAT");
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_lbtnNormal");
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_grdOutstanding_ctl02_Imgrefedit");
			pages.Wait().wait_until_loader_is_invisible();
			boolean result=pages.MatrixPortalNavigation().OthersVerificationpage("PF check verification");
			assertTrue(result);
		} catch (Exception e) {

			assertTrue(e.getMessage().toString(),false);
			logger.fail(e.getMessage().toString());
			String temp = Utill.getScreenshot(driver);
			logger.fail(e.getMessage().toString(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		} finally {

			pages.Utill().GoTo(getvalue("url") + "/Matrix/UserHome.aspx");
		}

	}
	@Test(priority = 15, enabled = true)
	public void BVCheckPage() throws Exception {
		try {
			//pages.loginpage().Login(getvalue("uname"));
			pages.CaseRegistration().navigateTo("Dashboard", "BV TM");
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_OutStandBeyondTAT");
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_lbtnNormal");
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_grdOutstanding_ctl02_Imgrefedit");
			pages.Wait().wait_until_loader_is_invisible();
			boolean result=pages.MatrixPortalNavigation().OthersVerificationpage("BV check verification");
			assertTrue(result);
		} catch (Exception e) {

			assertTrue(e.getMessage().toString(),false);
			logger.fail(e.getMessage().toString());
			String temp = Utill.getScreenshot(driver);
			logger.fail(e.getMessage().toString(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		} finally {

			pages.Utill().GoTo(getvalue("url") + "/Matrix/UserHome.aspx");
		}

	}
	
	@Test(priority = 16, enabled = true)
	public void adminpage() throws Exception {
		pages.loginpage().Logout();
		pages.loginpage().Login("admin");
		final String masters = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[1]/ul/li/a";
		final String clients = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[2]/ul/li/a";
		final String Matrixuser = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[3]/ul/li/a";
		// final String Approvals = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[4]/ul/li/a";
		final String ManagerMIS = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[5]/ul/li/a";
		pages.MatrixPortalNavigation().geturl("Clients", map, clients);
		pages.MatrixPortalNavigation().geturl("Matrix user", map, Matrixuser);
		// pages.MatrixPortalNavigation().geturl("Approvals", map,Approvals);
		pages.MatrixPortalNavigation().geturl("Manager MIS", map, ManagerMIS);
		pages.MatrixPortalNavigation().geturl("Masters", map, masters);
		pages.Utill().printMap(map);
		pages.loginpage().Logout();

	}

	@Test(priority = 17, enabled = true)
	public void clientpage() throws Exception {
		pages.loginpage().Login("bala");
		final String Candidate = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[1]/ul/li/a";
		final String Operations = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[2]/ul/li/a";
		final String MIS = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[3]/ul/li/a";
		pages.MatrixPortalNavigation().geturl("Candidate", map, Candidate);
		pages.MatrixPortalNavigation().geturl("Operations", map, Operations);
		pages.MatrixPortalNavigation().geturl("MIS", map, MIS);
		System.out.println("------------------------------------------");
		pages.Utill().printMap(map);
		pages.loginpage().Logout();

	}

	@Test(priority = 18, enabled = true)
	public void reportmanager() throws Exception {
		pages.loginpage().Login("demormgr");
		final String Dashboard = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[1]/ul/li/a";
		final String CRT = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[2]/ul/li/a";
		final String MIS = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[3]/ul/li/a";
		pages.MatrixPortalNavigation().geturlpage("Dashboard", map, Dashboard);
		pages.MatrixPortalNavigation().geturlpage("CRT", map, CRT);
		pages.MatrixPortalNavigation().geturlpage("MIS", map, MIS);
		System.out.println("------------------------------------------");
		pages.Utill().printMap(map);
		pages.loginpage().Logout();

	}

	@Test(priority = 19, enabled = true)
	public void crtpage() throws Exception {
		pages.loginpage().Login("crt");
		final String Dashboard = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[1]/ul/li/a";
		final String CRT = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[2]/ul/li/a";
		final String MIS = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[3]/ul/li/a";
		pages.MatrixPortalNavigation().geturlpage("Dashboard", map, Dashboard);
		pages.MatrixPortalNavigation().geturlpage("CRT", map, CRT);
		pages.MatrixPortalNavigation().geturlpage("MIS", map, MIS);
		System.out.println("------------------------------------------");
		pages.Utill().printMap(map);
		pages.loginpage().Logout();

	}

	@Test(priority = 20, enabled = true)
	public void deliveryclient() throws Exception {
		pages.loginpage().Login("vinothrustee");
		final String Operations = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[1]/ul/li/a";
		final String MIS = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[2]/ul/li/a";
		pages.MatrixPortalNavigation().geturl("Operations", map, Operations);
		pages.MatrixPortalNavigation().geturl("MIS", map, MIS);

		System.out.println("------------------------------------------");
		pages.Utill().printMap(map);
		pages.loginpage().Logout();

	}@Test(priority = 21, enabled = true)
	public void genpactpage() throws Exception {
		pages.loginpage().Login("demogpt");
		pages.MatrixPortalNavigation().GetGenPactpage("ctl00_dashboard1", "Client Home Page");
		pages.MatrixPortalNavigation().GetGenPactpage("ctl00_initiator4", "Initiator Page");
		pages.MatrixPortalNavigation().GetGenPactpage("ctl00_rejector5", "ReinitatorPage");
		pages.MatrixPortalNavigation().GetGenPactpage("ctl00_casetracker6", "Client case tracker");
		pages.MatrixPortalNavigation().GetGenPactpage("ctl00_insuffclear7", "CRTInsuff Clear");
		pages.Utill().mouseover("ctl00_dataentry2");
		pages.MatrixPortalNavigation().GetGenPactpage("ctl00_LinkButton1", "Verification Entry");
		pages.Utill().mouseover("ctl00_dataentry2");
		pages.MatrixPortalNavigation().GetGenPactpage("ctl00_LinkButton2", "Incomplete Data Entry");
		pages.Utill().mouseover("ctl00_dataentry2");
		pages.MatrixPortalNavigation().GetGenPactpage("ctl00_LinkButton6", "CRT: Gen Subcheck dataentry");
		pages.Utill().mouseover("ctl00_dataentry2");
		pages.MatrixPortalNavigation().GetGenPactpage("ctl00_LinkButton7", "MIS Report Upload");
		
		pages.Utill().mouseover("ctl00_coeupdate3");
		pages.MatrixPortalNavigation().GetGenPactpage("ctl00_LinkButton3", "CoeMaster");
		pages.Utill().mouseover("ctl00_coeupdate3");
		pages.MatrixPortalNavigation().GetGenPactpage("ctl00_LinkButton5", "COE Check Setup");
		
		pages.Utill().mouseover("ctl00_candidatecreation8");
		pages.MatrixPortalNavigation().GetGenPactpage("ctl00_candidatecreation", "Genpact Candidate Users");
		pages.Utill().mouseover("ctl00_candidatecreation8");
		pages.MatrixPortalNavigation().GetGenPactpage("ctl00_LinkButton4", "Genpact Candidate Users Search");
	}
	@AfterMethod
	public void tearDown(ITestResult result, Method method) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			String temp = Utill.getScreenshot(driver);

			logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

			logger.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.skip(result.getThrowable().getMessage());
		} else {
		}

		logger.pass(method.getName() + " completed");
	}
	@AfterTest
	public void teardown() {
		// pages.loginpage().Logout();
		 driver.close();
	}

	@AfterSuite
	public void afterSuite() {
		extent.flush();
		System.out.println("report published successfully");
		 driver.quit();
		 SendAttachmentInEmail email = new SendAttachmentInEmail();
		email.sendPDFReportByGMail("test404mail@gmail.com", "guhanarun1", "ganesh.baskaran@kadambatechnologies.com", "Testing","");

	}

	private String getvalue(String key) throws FileNotFoundException, IOException {
		Properties pr = new Properties();
		pr.load(new FileInputStream(new File("./src\\test\\resources\\property\\dataentry_values.properties")));
		return pr.getProperty(key);
	}
}
