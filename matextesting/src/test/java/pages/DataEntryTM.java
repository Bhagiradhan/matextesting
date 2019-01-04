package pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import environment.BaseClass;
import testCases.Pages;

public class DataEntryTM {
	WebDriver driver;
	protected final Pages pages;
	ExtentTest logger;

	public DataEntryTM(WebDriver driver, ExtentTest logger) {
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

	private void Scroll(String id) {
		// pages=new Pages(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", pages.Utill().find(id));
	}

	private String getnextMonth() {
		Calendar cal = GregorianCalendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		Date currentMonth = new Date();
		cal.setTime(currentMonth);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
		String nextMonthAsString = df.format(cal.getTime());
		return nextMonthAsString;

	}

	public String Personal() throws Exception {
		// pages=new Pages(driver);
		WebDriverWait w = new WebDriverWait(driver, 20);
		w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("gender"))));
		pages.Utill().select_by_label(getlocator("gender"), getvalue("gender"));
		pages.Utill().input_text(getlocator("no_gap"), "1");
		pages.Utill().input_text(getlocator("emp_id"), getvalue("emp_id"));
		Scroll(getlocator("Aadhar_card"));
		pages.Utill().select_by_label(getlocator("Aadhar_card"), getvalue("Aadhar_card"));
		pages.Utill().select_by_label(getlocator("pass_port"), getvalue("pass_port"));
		pages.Utill().select_by_label(getlocator("pan_card"), getvalue("pan_card"));
		pages.Utill().select_by_label(getlocator("drug"), getvalue("drug"));
		if (getvalue("drug").equalsIgnoreCase("Yes")) {
			pages.Utill().select_by_label(getlocator("drug_panel"), getvalue("drug_panel"));
		}
		pages.Utill().select_by_label(getlocator("db"), getvalue("db"));
		pages.Utill().select_by_label(getlocator("driving_license"), getvalue("driving_license"));
		pages.Utill().select_by_label(getlocator("voter_id"), getvalue("voter_id"));
		pages.Utill().select_by_label(getlocator("medical"), getvalue("medical"));
		if (getvalue("drug").equalsIgnoreCase("Yes")) {
			pages.Utill().select_by_label(getlocator("drug_panel"), getvalue("drug_panel"));
		}
		pages.Utill().select_by_label(getlocator("righttocheck"), getvalue("righttocheck"));

		if (getvalue("righttocheck").equals("Yes")) {
			pages.Utill().select_by_label(getlocator("right_check_type"), getvalue("right_check_type"));
		}
		pages.Utill().click_element(getlocator("per_save"));
		pages.Wait().wait_until_loader_is_invisible();
		String ss = pages.Utill().clickAlertbox();
		// System.out.println("personal " + ss);
		logger.pass("personal records saved successfully");
		return ss;
	}

	public void AddressCheck(int cou) throws Exception {
		logger.info("address check starting");
		if (getvalue("address").equalsIgnoreCase("yes")) {
			// pages=new Pages(driver);
			WebDriverWait w = new WebDriverWait(driver, 10);
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("address"))));
			pages.Utill().click_element(getlocator("address"));
			pages.Utill().select_by_label(getlocator("add_type"), getvalue("add_type"));
			pages.Wait().wait_until_loader_is_invisible();

			pages.Utill().input_text(getlocator("add_des"), getvalue("add_des"));
			pages.Utill().input_text(getlocator("pincode"), getvalue("pincode"));
			w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='" + getvalue("pincode") + "']")));
			pages.Utill().click_element("//*[text()='" + getvalue("pincode") + "']");
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().select_by_label(getlocator("Residence_type"), getvalue("Residence_type"));
			pages.Utill().input_text(getlocator("landmark"), getvalue("landmark"));
			pages.Utill().input_text(getlocator("add_fdate"), getvalue("add_fdate"));
			if (getvalue("add_tilldate").equalsIgnoreCase("yes")) {
				pages.Utill().click_element(getlocator("add_tdate"));
				pages.Utill().click_element(getlocator("add_tilldate"));
			} else {
				pages.Utill().input_text(getlocator("add_tdate"), getvalue("add_tdate"));
			}
			pages.Utill().input_text(getlocator("period_of_stay"), getvalue("period_of_stay"));
			pages.Utill().input_text(getlocator("police_station"), getvalue("police_station"));
			pages.Utill().input_text(getlocator("add_mobile"), getvalue("add_mobile"));
			pages.Utill().select_by_label(getlocator("location_type"), getvalue("location_type"));
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().select_by_label(getlocator("add_applicable"), getvalue("add_applicable"));
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().select_by_label(getlocator("add_court"), getvalue("add_court"));
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element(getlocator("add_save"));
			pages.Wait().wait_until_loader_is_invisible();
			String ss = pages.Utill().clickAlertbox();
			// System.out.println("Address " + ss);
			logger.pass("Address  " + ss);
			if(cou!=0) {
				pages.Utill().click_element(getlocator("add_insuff"));
				pages.Utill().select_by_label(getlocator("add_insuff_cmnt"), getvalue("add_insuff_cmnt"));
				pages.Utill().input_text(getlocator("add_insuff_rmks"), getvalue("Add_insuff_rmrk"));
				pages.Utill().click_element(getlocator("add_insuff_save"));
				pages.Wait().wait_until_loader_is_invisible();
				pages.Utill().clickAlertbox();
			}
			
		} else {
			System.out.println("address skipped");
		}

	}

	public void EducationCheck() throws Exception {
		logger.info("Education check starting");
		if (getvalue("education").equalsIgnoreCase("yes")) {
			WebDriverWait w = new WebDriverWait(driver, 10);
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("education"))));
			pages.Utill().click_element(getlocator("education"));
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("edu_type"))));
			pages.Utill().select_by_label(getlocator("edu_type"), getvalue("edu_type"));
			pages.Utill().input_text((getlocator("edu_name")), (getvalue("edu_name")));
			w.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//*[text()='" + getvalue("edu_name") + "']")));
			pages.Utill().click_element("//*[text()='" + getvalue("edu_name") + "']");
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().input_text(getlocator("Edu_StudentID"), getvalue("Edu_StudentID"));
			pages.Utill().input_text(getlocator("Edu_StartDt"), getvalue("Edu_StartDt"));
			pages.Utill().input_text(getlocator("Edu_EndDt"), getvalue("Edu_EndDt"));
			pages.Utill().input_text(getlocator("Edu_PassYear"), getvalue("Edu_PassYear"));
			pages.Utill().select_by_label(getlocator("Edu_DegreeType"), getvalue("Edu_DegreeType"));
			pages.Utill().input_text(getlocator("Edu_NameofDegree"), getvalue("Edu_NameofDegree"));
			pages.Utill().input_text(getlocator("Edu_MajorSubject"), getvalue("Edu_MajorSubject"));
			pages.Utill().select_by_label(getlocator("Edu_Graduated"), getvalue("Edu_Graduated"));
			pages.Utill().select_by_label(getlocator("Edu_ProgramType"), getvalue("Edu_ProgramType"));
			pages.Utill().input_text(getlocator("Edu_CGPA"), getvalue("Edu_CGPA"));
			pages.Utill().input_text(getlocator("Edu_ClassObtained"), getvalue("Edu_ClassObtained"));
			pages.Utill().click_element(getlocator("Edu_NotApplicable"));
			pages.Utill().click_element(getlocator("Education1_save"));
			pages.Wait().wait_until_loader_is_invisible();
			String ss = pages.Utill().clickAlertbox();
			// System.out.println("Education " + ss);
			logger.pass("Education " + ss);

		} else {
			System.out.println("education skipped");
		}
	}

	public void EmploymentCheck() throws Exception {
		logger.info("Employment check starting");
		if (getvalue("emp").equalsIgnoreCase("yes")) {
			WebDriverWait w = new WebDriverWait(driver, 10);
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("employment"))));
			pages.Utill().click_element(getlocator("employment"));
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("emp_Type"))));
			pages.Utill().select_by_label(getlocator("emp_Type"), getvalue("emp_Type"));
			pages.Wait().wait_until_loader_is_invisible();
			if (getvalue("emp_SameasClient").equalsIgnoreCase("yes")) {
				pages.Utill().click_element(getlocator("emp_SameasClient"));
				pages.Wait().wait_until_loader_is_invisible();
				pages.Utill().click_element(getlocator("Emp_save"));
				pages.Wait().wait_until_loader_is_invisible();
				String ss = pages.Utill().clickAlertbox();
				System.out.println("Education " + ss);
				logger.pass("Employment " + ss);
			} else {
				pages.Utill().input_text(getlocator("Emp_CmpName"), getvalue("Emp_CmpName"));
				w.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[text()='" + getvalue("Emp_CmpName") + "']")));
				pages.Utill().click_element("//*[text()='" + getvalue("Emp_CmpName") + "']");
				pages.Wait().wait_until_loader_is_invisible();
				pages.Utill().input_text(getlocator("Emp_CmpAddr"), getvalue("Emp_CmpAddr"));
				pages.Utill().input_text(getlocator("Emp_Position"), getvalue("Emp_Position"));
				pages.Utill().input_text(getlocator("Emp_Department"), getvalue("Emp_Department"));
				pages.Utill().input_text(getlocator("Emp_HOAddr"), getvalue("Emp_HOAddr"));
				pages.Utill().input_text(getlocator("Emp_FromDt"), getvalue("Emp_FromDt"));
				if (getvalue("Emp_Tilldate").equalsIgnoreCase("YES")) {
					pages.Utill().click_element(getlocator("Emp_ToDt"));
					pages.Utill().click_element(getlocator("Emp_Tilldate"));
					pages.Utill().input_text(getlocator("Emp_ReliveDt"), getnextMonth());
				} else {
					pages.Utill().input_text(getlocator("Emp_ToDt"), getvalue("Emp_ToDt"));
				}
				pages.Utill().input_text(getlocator("Emp_EmpCode"), getvalue("Emp_EmpCode"));
				pages.Utill().select_by_label(getlocator("Emp_EmplType"), getvalue("Emp_EmplType"));
				if (getvalue("Emp_EmplType").equals("Contract")) {
					pages.Utill().input_text(getlocator("Emp_AgencyDetails"), getvalue("Emp_AgencyDetails"));
				}
				pages.Utill().select_by_label(getlocator("Emp_CurrencyType"), getvalue("Emp_CurrencyType"));
				pages.Utill().input_text(getlocator("Emp_LastSalary"), getvalue("Emp_LastSalary"));
				pages.Utill().select_by_label(getlocator("Emp_SalType"), getvalue("Emp_SalType"));
				pages.Utill().input_text(getlocator("Emp_RepAuthName"), getvalue("Emp_RepAuthName"));
				pages.Utill().input_text(getlocator("Emp_RepAuthDesig"), getvalue("Emp_RepAuthDesig"));
				pages.Utill().input_text(getlocator("Emp_RepAuthMobile1"), getvalue("Emp_RepAuthMobile1"));
				pages.Utill().input_text(getlocator("Emp_RepAuthEmail"), getvalue("Emp_RepAuthEmail"));
				pages.Utill().input_text(getlocator("Emp_HRName"), getvalue("Emp_HRName"));
				pages.Utill().input_text(getlocator("Emp_ReasonLeave"), getvalue("Emp_ReasonLeave"));
				pages.Utill().select_by_label(getlocator("Emp_YTR"), getvalue("Emp_YTR"));
				try {
					pages.Utill().select_by_label(
							"ctl00_ContentPlaceHolder1_TabContainer1_TabPanel4_CandidateEmployment1_CdtCompany_FullTime_PartTime",
							"Full Time");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage().toString());
				}
				pages.Utill().find(getlocator("Emp_Applicable")).click();
				pages.Utill().find(getlocator("Emp_save")).click();
				pages.Wait().wait_until_loader_is_invisible();
				String ss = pages.Utill().clickAlertbox();
				// System.out.println("Employment " + ss);
				logger.pass("Employment " + ss);
			}
		} else {
			System.out.println("employment skipped");
		}
	}

	public void ReeferenceCheck() throws Exception {
		logger.info("Reference check starting");
		if (getvalue("ref").equalsIgnoreCase("yes")) {
			WebDriverWait w = new WebDriverWait(driver, 10);
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("Referene"))));
			pages.Utill().click_element(getlocator("Referene"));
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("Ref_Type"))));
			pages.Utill().select_by_label(getlocator("Ref_Type"), getvalue("Ref_Type"));
			pages.Wait().wait_until_loader_is_invisible();
			try {
				pages.Utill().select_by_label(getlocator("Ref_CheckType"), getvalue("Ref_CheckType"));
			} catch (Exception e) {
			}
			pages.Utill().input_text(getlocator("Ref_RepName"), getvalue("Ref_RepName"));
			pages.Utill().input_text(getlocator("Ref_RepDesig"), getvalue("Ref_RepDesig"));
			pages.Utill().input_text(getlocator("Ref_RepMobile1"), getvalue("Ref_RepMobile1"));
			pages.Utill().input_text(getlocator("Ref_RepEmail"), getvalue("Ref_RepEmail"));
			pages.Utill().input_text(getlocator("Ref_OrgName"), getvalue("Ref_OrgName"));
			pages.Utill().input_text(getlocator("Ref_OrgAddr"), getvalue("Ref_OrgAddr"));
			pages.Utill().input_text(getlocator("Ref_HRName"), getvalue("Ref_HRName"));
			pages.Utill().input_text(getlocator("Ref_HRContact"), getvalue("Ref_HRContact"));
			pages.Utill().input_text(getlocator("Ref_HRMobile"), getvalue("Ref_HRMobile"));
			pages.Utill().input_text(getlocator("Ref_HREmail"), getvalue("Ref_HREmail"));
			pages.Utill().input_text(getlocator("Ref_HRDesig"), getvalue("Ref_HRDesig"));
			pages.Utill().select_by_label(getlocator("Ref_YTR"), getvalue("Ref_YTR"));
			pages.Utill().click_element(getlocator("Ref_Applicable"));
			pages.Utill().click_element(getlocator("Ref_save"));
			pages.Wait().wait_until_loader_is_invisible();
			String ss = pages.Utill().clickAlertbox();
			// System.out.println("Reference " + ss);
			logger.pass("Reference " + ss);
		} else {
			System.out.println("reference skipped");
		}
	}

	public void GapCheck() throws Exception {
		logger.info("Gap check starting");
		if (getvalue("Gap").equalsIgnoreCase("yes")) {
			WebDriverWait w = new WebDriverWait(driver, 10);
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("Gap"))));

			pages.Utill().click_element(getlocator("Gap"));
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("Gap_CheckType"))));
			pages.Utill().select_by_label(getlocator("Gap_CheckType"), getvalue("Gap_CheckType"));
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().select_by_label(getlocator("Gap_Type"), getvalue("Gap_Type"));
			pages.Utill().input_text(getlocator("Gap_FDate"), getvalue("Gap_FDate"));
			pages.Utill().input_text(getlocator("Gap_TDate"), getvalue("Gap_TDate"));
			pages.Utill().input_text(getlocator("Gap_Reason"), getvalue("Gap_Reason"));
			pages.Utill().select_by_label(getlocator("Gap_Applicable"), getvalue("Gap_Applicable"));
			pages.Utill().select_by_label(getlocator("Gap_AddType"), getvalue("Gap_AddType"));
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element(getlocator("Gap_Save"));
			pages.Wait().wait_until_loader_is_invisible();
			String ss = pages.Utill().clickAlertbox();
			// System.out.println("Gap " + ss);
			logger.pass("Gap " + ss);
		} else {
			System.out.println("gap check skipped");
		}
	}

	public void FacisCheck() throws Exception {
		logger.info("Facis check starting");
		if (getvalue("others").equalsIgnoreCase("yes") && getvalue("facis").equalsIgnoreCase("yes")
				&& getvalue("address").equalsIgnoreCase("yes")) {
			WebDriverWait w = new WebDriverWait(driver, 10);
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("Others"))));
			pages.Utill().click_element(getlocator("Others"));
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("facis"))));
			pages.Utill().select_by_label(getlocator("facis"), "Yes");
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("facis"))));
			pages.Utill().select_by_label(getlocator("FACIS_Type"), getvalue("FACIS_Type"));
			pages.Utill().select_by_label(getlocator("FACIS_Level"), getvalue("FACIS_Level"));
			pages.Utill().select_by_label(getlocator("FACIS_AddressType"), getvalue("add_type"));
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element(getlocator("FACIS_save"));
			pages.Wait().wait_until_loader_is_invisible();
			String ss = pages.Utill().clickAlertbox();
			// System.out.println("facis " + ss);
			logger.pass("facis " + ss);
			logger.pass("facis " + ss);
		} else {
			System.out.println("facis checks skipped check status");
		}
	}

	public void CreditCheck() throws Exception {
		logger.info("Credit check starting");
		if (getvalue("others").equalsIgnoreCase("yes") && getvalue("credit").equalsIgnoreCase("yes")
				&& getvalue("address").equalsIgnoreCase("yes")) {
			WebDriverWait w = new WebDriverWait(driver, 10);
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("Others"))));
			pages.Utill().click_element(getlocator("Others"));
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("credit"))));
			pages.Utill().select_by_label(getlocator("credit"), "Yes");
			pages.Wait().wait_until_loader_is_invisible();
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("credit_AddressType"))));
			pages.Utill().select_by_label(getlocator("credit_AddressType"), getvalue("add_type"));
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().select_by_label(getlocator("credit_Category"), getvalue("credit_Category"));
			pages.Utill().select_by_label(getlocator("credit_ResidenceType"), getvalue("credit_ResidenceType"));
			pages.Utill().select_by_label(getlocator("credit_SalaryType"), getvalue("credit_SalaryType"));
			pages.Utill().select_by_label(getlocator("credit_IncomeType"), getvalue("credit_IncomeType"));
			pages.Utill().input_text(getlocator("credit_Salary"), getvalue("credit_Salary"));
			pages.Utill().click_element(getlocator("credit_save"));
			pages.Wait().wait_until_loader_is_invisible();
			String ss = pages.Utill().clickAlertbox();
			// System.out.println("credit " + ss);
			logger.pass("credit " + ss);
		} else {
			System.out.println("credit checks skipped check status");
		}
	}

	public void BvCheck() throws Exception {
		logger.info("BV check starting");
		if (getvalue("others").equalsIgnoreCase("yes") && getvalue("bank").equalsIgnoreCase("yes")
				&& getvalue("emp").equalsIgnoreCase("yes")) {
			WebDriverWait w = new WebDriverWait(driver, 20);
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("Others"))));
			pages.Utill().click_element(getlocator("Others"));
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("bank"))));
			pages.Utill().select_by_label(getlocator("bank"), "Yes");
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("BVType"))));
			pages.Utill().select_by_label(getlocator("BVType"), getvalue("BVType"));
			pages.Utill().select_by_label(getlocator("BVEmpType"), getvalue("emp_Type"));
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().input_text(getlocator("BVCountry"), getvalue("BVCountry"));
			// System.out.println("//*[text()='" + getvalue("BVCountry") + "']");
			w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[.='India']")));
			// pages.Utill().input_text(getlocator("BVCountry"), getvalue("BVCountry"));
			pages.Utill().click_element(("//li[.='India']"));
			pages.Utill().input_text(getlocator("BVState"), getvalue("BVState"));
			w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[.='Tamil nadu']")));
			pages.Utill().click_element("//li[.='Tamil nadu']");
			try {
				pages.Utill().input_text(getlocator("BVCity"), getvalue("BVCity"));
				w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[.='Chennai']")));
				pages.Utill().click_element("//li[.='Chennai']");
			} catch (TimeoutException e) {
				pages.Utill().find(getlocator("BVCity")).clear();
				pages.Utill().input_text(getlocator("BVCity"), getvalue("BVCity"));
				w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[.='Chennai']")));
				pages.Utill().click_element("//li[.='Chennai']");
			}
			pages.Utill().click_element(getlocator("BVSave"));
			pages.Wait().wait_until_loader_is_invisible();
			String ss = pages.Utill().clickAlertbox();
			// System.out.println("Bank Verification " + ss);
			logger.pass("Bank Verification " + ss);
		} else {
			System.out.println("bank checks skipped check status");
		}
	}

	public void ItCheck() throws Exception {
		logger.info("IT check starting");
		if (getvalue("others").equalsIgnoreCase("yes") && getvalue("it").equalsIgnoreCase("yes")
				&& getvalue("emp").equalsIgnoreCase("yes")) {
			WebDriverWait w = new WebDriverWait(driver, 10);
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("Others"))));
			pages.Utill().click_element(getlocator("Others"));
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("it"))));
			pages.Utill().select_by_label(getlocator("it"), "Yes");
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("ITType"))));
			pages.Utill().select_by_label(getlocator("ITType"), getvalue("ITType"));
			pages.Utill().select_by_label(getlocator("ITEmpType"), getvalue("emp_Type"));
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element(getlocator("ITSave"));
			pages.Wait().wait_until_loader_is_invisible();
			String ss = pages.Utill().clickAlertbox();
			// System.out.println("IT Verification " + ss);
			logger.pass("IT Verification " + ss);
		} else {
			System.out.println("IT checks skipped check status");
		}
	}

	public void PfCheck() throws Exception {
		logger.info("PF check starting");
		if (getvalue("others").equalsIgnoreCase("yes") && getvalue("it").equalsIgnoreCase("yes")
				&& getvalue("emp").equalsIgnoreCase("yes")) {
			WebDriverWait w = new WebDriverWait(driver, 10);
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("Others"))));
			pages.Utill().click_element(getlocator("Others"));
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("pf"))));
			pages.Utill().select_by_label(getlocator("pf"), "Yes");
			pages.Utill().select_by_label(getlocator("PFType"), getvalue("PFType"));
			pages.Utill().select_by_label(getlocator("PFEmpType"), getvalue("emp_Type"));
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().input_text(getlocator("PFNumber"), getvalue("PFNumber"));
			pages.Utill().click_element(getlocator("PFSave"));
			pages.Wait().wait_until_loader_is_invisible();
			String ss = pages.Utill().clickAlertbox();
			// System.out.println("PF Verification " + ss);
			logger.pass("PF Verification " + ss);
		} else {
			System.out.println("PF checks skipped check status");
		}
	}

	public void RaiseInduff(String check, String dd) throws Exception {
		// for raise button : //div[contains(@id,'Reference')]//a[text()='Raise']
		// for insuff dropdown //select[contains(@id,'insuffcmts')] and
		// //select[contains(@id,'Reference')]
		// for insuff text //textarea [contains(@id,'txtInsufficiency')] and //textarea
		// [contains(@id,'Reference')]
		// for insuff savebtn //input[contains(@id,'btnSaveInsuff')] and
		// //input[contains(@id,'Reference')]
		// span[text()='Address' and contains(@id,'tab')]
		try {
			if (check.equals("Address") || check.equals("Education") || check.equals("Employment")
					|| check.equals("Reference") || check.equals("Gap")) {
				pages.Utill().click_element("//span[contains(text(),'" + check + "') and contains(@id,'tab')]");
				pages.Utill().click_element("//div[contains(@id,'" + check + "')]//a[text()='Raise']");
				pages.Wait().presenceOfElement("//option[text()='" + dd + "']", 10);
				pages.Utill().select_by_label("//select[contains(@id,'insuffcmts') and contains(@id,'" + check + "')]",
						dd);
				pages.Utill().input_text(
						"//textarea[contains(@id,'txtInsufficiency') and contains(@id,'" + check + "')]",
						check + " insuff");
				pages.Utill().click_element("//input[contains(@id,'btnSaveInsuff') and contains(@id,'" + check + "')]");
				pages.Wait().wait_until_loader_is_invisible();
				pages.Utill().clickAlertbox();

			} else {
				pages.Utill().click_element("//div[contains(@id,'" + check + "')]//a[text()='Raise']");
				pages.Wait().presenceOfElement("//option[text()=\"" + dd + "\"]", 10);
				if (check.equalsIgnoreCase("it") || check.equalsIgnoreCase("pf")) {
					pages.Wait().wait_until_loader_is_invisible();
					pages.Utill().select_by_label(
							"//select[contains(@id,'insuffcmts') and contains(@id,\"" + check + "\")]", dd);
				} else {
					pages.Utill().select_by_label(
							"//select[contains(@id,'insuffcmts') and contains(@id,\"" + check.toLowerCase() + "\")]",
							dd);
				}

				pages.Utill().input_text(
						"//textarea[contains(@id,'txtInsufficiency') and contains(@id,'" + check + "')]",
						check + " insuff");
				pages.Utill().click_element("//input[contains(@id,'btnSaveInsuff') and contains(@id,'" + check + "')]");
				pages.Wait().wait_until_loader_is_invisible();
				pages.Utill().clickAlertbox();
			}

		} catch (UnhandledAlertException e) {

			pages.Utill().handle_Alert();
		}

	}
}
