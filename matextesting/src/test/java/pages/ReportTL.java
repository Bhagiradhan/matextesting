package pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import environment.BaseClass;
import environment.Utill;
import testCases.Pages;

public class ReportTL {
	protected final WebDriver driver;
	protected final Pages pages;
	ExtentTest logger;
	WebDriverWait wait;

	public ReportTL(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		pages = new Pages(driver, logger);
		wait = new WebDriverWait(driver, 20);

	}

	private String getvalue(String key) throws FileNotFoundException, IOException {
		Properties pr = BaseClass.getvalue();
		return pr.getProperty(key);
	}

	private String getlocator(String key) throws FileNotFoundException, IOException {
		Properties pr = BaseClass.getlocator();
		return pr.getProperty(key);
	}

	public void assignReport(String no) throws Exception {
		try {
			pages.CaseRegistration().navigateTo("Dashboard", "Report TL Dashboard");
		} catch (Exception e) {
			logger.log(Status.WARNING, e.getMessage().toString());
			pages.Utill().GoTo(getvalue("url") + "/ReportPrepare/AssignCase.aspx");
		}
		pages.Utill().input_text(getlocator("re_matref"), no);
		pages.Utill().click_element(getlocator("re_search"));
		pages.Wait().wait_until_spinner_is_invisible("ctl00_ContentPlaceHolder1_overlayScreen");
		String fcount = pages.Utill().get_text(getlocator("re_final"));
		if (Integer.parseInt(fcount) == 1) {
			pages.Utill().click_element(getlocator("re_final"));
			pages.Wait().wait_until_spinner_is_invisible("ctl00_ContentPlaceHolder1_overlayScreen");
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("re_matno"))));
			String refno = pages.Utill().get_text(getlocator("re_matno"));
			if (refno.equalsIgnoreCase(no)) {
				pages.Utill().click_element(getlocator("re_check"));
				pages.Utill().click_element(getlocator("re_pri"));
				pages.Wait().wait_until_spinner_is_invisible("ctl00_ContentPlaceHolder1_overlayScreen");
				pages.Utill().click_element(getlocator("re_check"));
				// Thread.sleep(2000);
				pages.Utill().select_by_label(getlocator("re_tmname"), getvalue("re_tmname"));
				pages.Utill().click_element(getlocator("re_assi"));
				pages.Wait().wait_until_spinner_is_invisible("ctl00_ContentPlaceHolder1_overlayScreen");
			} else {
				System.out.println(no + "  not found in table");
				logger.log(Status.FAIL, no + "  not found in table");
				String temp = Utill.getScreenshot(driver);
				logger.fail("", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			}
		} else {
			System.out.println("final is :" + fcount);
			logger.log(Status.FAIL, "final is :" + fcount);
			String temp = Utill.getScreenshot(driver);
			logger.fail("", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			throw new Exception("final is :" + fcount);

		}

	}
}
