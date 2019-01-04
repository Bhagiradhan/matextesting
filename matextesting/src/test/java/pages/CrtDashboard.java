package pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import environment.BaseClass;
import environment.Utill;
import testCases.Pages;

public class CrtDashboard {
	WebDriver driver;
	protected final Pages pages;
	ExtentTest logger;

	public CrtDashboard(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		pages = new Pages(driver, logger);
	}

	private String getvalue(String key) throws FileNotFoundException, IOException {
		Properties pr = BaseClass.getvalue();
		return pr.getProperty(key);
	}

	private String getlocator(String key) throws FileNotFoundException, IOException {
		Properties pr = BaseClass.getlocator();
		return pr.getProperty(key);
	}

	public void publishcase(String no) throws Exception {
		pages.Utill().GoTo(getvalue("url") + "/Matrix/CRTDashboardN.aspx");
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rdbtnrpp");
		WebDriverWait w = new WebDriverWait(driver, 30);
		w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("crt_matref"))));
		pages.Utill().input_text(getlocator("crt_matref"), no);
		pages.Utill().click_element(getlocator("crt_search"));
		w.until(ExpectedConditions.visibilityOf(pages.Utill().find(getlocator("crt_finalqc"))));
		String co = pages.Utill().get_text(getlocator("crt_finalqc"));
		if (Integer.parseInt(co) == 1) {
			pages.Utill().click_element(getlocator("crt_finalqc"));
			w.until(ExpectedConditions.visibilityOf(pages.Utill().find(getlocator("FinalReportLink"))));
			pages.Utill().click_element(getlocator("FinalReportLink"));
			w.until(ExpectedConditions.visibilityOf(pages.Utill().find("ddlRQCactoion0")));
			pages.Utill().select_by_label("ddlRQCactoion0", "Publish");
			pages.Utill().click_element(getlocator("crt_publish"));
			try {
				pages.Wait().wait_until_crtspinner_is_invisible();
			} catch (StaleElementReferenceException e) {
				logger.log(Status.WARNING, e.getMessage().toString());
			}
			WebDriverWait wa = new WebDriverWait(driver, 300);
			wa.until(ExpectedConditions.presenceOfElementLocated(By.id("ok")));
			pages.Utill().clickAlertbox();
		} else {
			System.out.println(co + "  found in dash board");
			String temp = Utill.getScreenshot(driver);
			logger.fail(co + "  found in dash board", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}

	}
}
