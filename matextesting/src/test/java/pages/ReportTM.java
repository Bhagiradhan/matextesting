package pages;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import environment.BaseClass;
import testCases.Pages;

public class ReportTM {
	protected final WebDriver driver;
	protected final Pages pages;
	ExtentTest logger;

	public ReportTM(WebDriver driver, ExtentTest logger) {
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

	public void Reporttm(String no) throws Exception {
		try {
			// pages.CaseRegistration().navigateTo("Dashboard", "Report TM Dashboard");
			pages.Utill().click_element(".//*[@id='ctl00_pnlMenu']/div/div[2]/div[2]/nav/ul/li[2]/ul/li[1]/a");
			pages.Utill().click_element(".//*[text()='Report TM Dashboard']");
		} catch (Exception e) {
			logger.log(Status.WARNING, e.getMessage().toString());
			pages.Utill().GoTo(getvalue("url") + "/ReportPrepare/AssignCaseTM.aspx");
		}
		pages.Utill().click_element(getlocator("re_getnext"));
		// pages.Wait().wait_until_spinner_is_invisible();
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("re_refon"))));
		String refno = pages.Utill().get_text(getlocator("re_refon"));
		if (refno.equals(no)) {
			pages.Utill().click_element(getlocator("re_ewview"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("re_table"))));
			List<WebElement> tabledata = driver.findElements(By.xpath(getlocator("re_tabledata")));
			List<String> result = new ArrayList<String>();
			for (int i = 0; i < tabledata.size(); i++) {
				String t = tabledata.get(i).getText();
				result.add(t);

			}
			System.out.println(result);
			// .//*[@id='ctl00_ContentPlaceHolder1_grd_check_status']/tbody/tr/td[3]/span
			pages.Utill().click_element(getlocator("re_toqc"));
			pages.Utill().handle_Alert();

		} else {
			System.out.println(refno + "  found instead of " + no);
			// pages.Utill().input_text(getlocator("re_matref"), no);
			// pages.Utill().click_element(getlocator("re_searchs"));
			// pages.Wait().wait_until_spinner_is_invisible();

		}
	}
}
