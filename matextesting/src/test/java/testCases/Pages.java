package testCases;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import environment.Utill;
import environment.Wait;
import pages.*;

public class Pages {
	WebDriver driver;
	ExtentTest logger;
	private LoginPage plogin;
	private Caseregistration pcasereg;
	private Utill putil;
	private Wait pwait;
	private DataEntryTM pde;
	private Assignor pas;
	private OperationTL ptl;
	private OperationTM ptm;
	private ReportTL prtl;
	private ReportTM prtm;
	private CrtDashboard pcrt;
	private MatrixPortalNavigation purl;
	private CandidateInitiation pcc;
	private Misreport pmis;

	public Pages(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}

	public LoginPage loginpage() {
		return (plogin == null) ? plogin = new LoginPage(driver, logger) : plogin;

	}

	public Caseregistration CaseRegistration() {
		return (pcasereg == null) ? pcasereg = new Caseregistration(driver, logger) : pcasereg;

	}

	public Utill Utill() {
		return (putil == null) ? putil = new Utill(driver, logger) : putil;

	}

	public Wait Wait() {
		return (pwait == null) ? pwait = new Wait(driver, logger) : pwait;

	}

	public DataEntryTM DataEntryTM() {
		return (pde == null) ? pde = new DataEntryTM(driver, logger) : pde;

	}

	public Assignor Assignor() {
		return (pas == null) ? pas = new Assignor(driver, logger) : pas;

	}

	public OperationTL OperationTL() {
		return (ptl == null) ? ptl = new OperationTL(driver, logger) : ptl;

	}

	public OperationTM OperationTM() {
		return (ptm == null) ? ptm = new OperationTM(driver, logger) : ptm;

	}

	public ReportTL ReportTL() {
		return (prtl == null) ? prtl = new ReportTL(driver, logger) : prtl;

	}

	public ReportTM ReportTM() {
		return (prtm == null) ? prtm = new ReportTM(driver, logger) : prtm;

	}

	public CrtDashboard CrtDashboard() {
		return (pcrt == null) ? pcrt = new CrtDashboard(driver, logger) : pcrt;

	}

	public MatrixPortalNavigation MatrixPortalNavigation() {
		return (purl == null) ? purl = new MatrixPortalNavigation(driver, logger) : purl;

	}

	public CandidateInitiation CandidateInitiation() {
		return (pcc == null) ? pcc = new CandidateInitiation(driver, logger) : pcc;

	}
	public Misreport Misreport() {
		return (pmis == null) ? pmis = new Misreport(driver, logger) : pmis;
	}
}
