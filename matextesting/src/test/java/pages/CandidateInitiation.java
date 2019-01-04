package pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.function.Function;
import java.time.Duration;
import org.fluttercode.datafactory.impl.DataFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import environment.BaseClass;
import environment.Utill;
import testCases.Pages;

public class CandidateInitiation {
	protected final WebDriver driver;
	protected final Pages pages;
	protected final WebDriverWait aleterwait;
	ExtentTest logger;

	public CandidateInitiation(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		pages = new Pages(driver, logger);
		aleterwait = new WebDriverWait(driver, 200);

	}

	private String getvalue(String key) throws FileNotFoundException, IOException {
		Properties pr = BaseClass.getvalue();
		return pr.getProperty(key);
	}

	private String getlocator(String key) throws FileNotFoundException, IOException {
		Properties pr = BaseClass.getlocator();
		return pr.getProperty(key);
	}

	public String InitiateCandidate(String name, int id) throws Exception {
		pages.Utill().click_element(getlocator("cc_Client_namedd"));
		pages.Utill().input_text(getlocator("cc_Client_name"), getvalue("cc_Client_name"));
		pages.Utill().click_element(".//*[@class='active-result highlighted']");
		pages.Wait().wait_until_loader_is_invisible();
		pages.Utill().input_text(getlocator("cc_CandidateName"), name);
		pages.Utill().click_element(getlocator("cc_MobileNo"));
		pages.Wait().wait_until_loader_is_invisible();
		pages.Utill().input_text(getlocator("cc_MobileNo"), getvalue("cc_MobileNo"));
		pages.Utill().input_text(getlocator("cc_EmailID"), getvalue("cc_EmailID"));
		pages.Utill().input_text(getlocator("cc_HREmail"), getvalue("cc_HREmail"));
		pages.Utill().input_text(getlocator("cc_CanId"), Integer.toString(id));
		if (getlocator("cc_Client_name").equals("automation")) {
			pages.Utill().select_by_label(getlocator("cc_Region"), "East");
		}
		pages.Utill().click_element(getlocator("cc_RadioButtonList"));
		String loginId = pages.Utill().find(getlocator("cc_LoginID")).getAttribute("value");
		pages.Utill().click_element(getlocator("cc_save"));
		pages.Wait().wait_until_loader_is_invisible();
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkbxlast");
		// pages.Utill().select_by_label("ctl00_ContentPlaceHolder1_DropDownListempyear",
		// "2");

		pages.Utill().select_by_label("ctl00_ContentPlaceHolder1_DropDownListemp", "2");
		pages.Utill().select_by_label("ctl00_ContentPlaceHolder1_DropDownListref", "2");
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkbxedu");
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkbx10");
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkbx12");
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkbxhighest");
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkbxug1");
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkbxdiploma");
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkbxpg1");
		pages.Utill().click_element(getlocator("cc_initiate"));
		pages.Wait().wait_until_loader_is_invisible();
		pages.Utill().clickAlertbox();
		System.out.println("------------loginid is :_" + loginId + "--------------------------");
		return loginId;

	}

	public void Personal() throws Exception {
		wait_until_loader_is_invisible();
		String temp = Utill.getScreenshot(driver);
		logger.info("after login", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		pages.Utill().SwitchFramebyIndex(0);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(pages.Utill().find(getlocator("cp_Middlename"))));
		pages.Utill().input_text(getlocator("cp_Middlename"), getvalue("cp_Middlename"));
		pages.Utill().input_text(getlocator("cp_Fathername"), getvalue("cp_Fathername"));
		pages.Utill().select_by_label(getlocator("cp_Gender"), getvalue("cp_Gender"));
		pages.Utill().select_by_label(getlocator("cp_MaritalStatus"), getvalue("cp_MaritalStatus"));
		pages.Utill().click_element(getlocator("cp_DOB"));
		EnterDate("01-January-1984", "CandidateHome1_CanDobdate_title", "CandidateHome1_CanDobdate_prevArrow");
		pages.Utill().input_text(getlocator("cpHR_Name"), getvalue("cpHR_Name"));
		pages.Utill().input_text(getlocator("cp_EmpNo"), getvalue("cp_EmpNo"));
		pages.Utill().input_text(getlocator("cp_Designation"), getvalue("cp_Designation"));
		pages.Utill().input_text(getlocator("cp_Grade"), getvalue("cp_Grade"));
		pages.Utill().input_text(getlocator("cp_JoinPlace"), getvalue("cp_JoinPlace"));
		pages.Utill().input_text(getlocator("cp_PanNo"), getvalue("cp_PanNo"));
		pages.Utill().input_text(getlocator("cp_ITPinNo"), getvalue("cp_ITPinNo"));
		pages.Utill().input_text(getlocator("cp_PassportNo"), getvalue("cp_PassportNo"));
		pages.Utill().input_text(getlocator("cp_Issue_Place"), getvalue("cp_Issue_Place"));
		pages.Utill().input_text(getlocator("cp_Issue_State"), getvalue("cp_Issue_State"));
		pages.Utill().input_text(getlocator("cp_Issue_Country"), getvalue("cp_Issue_Country"));
		pages.Utill().scrollTo(getlocator("cp_Expire_Date"));
		pages.Utill().click_element(getlocator("cp_Expire_Date"));
		EnterDate("20-June-2018", "CandidateHome1_Candidate_EXdate_title", "CandidateHome1_Candidate_EXdate_prevArrow");
		pages.Utill().input_text(getlocator("cp_MobileTwo1"), getvalue("cp_MobileTwo1"));
		pages.Utill().input_text(getlocator("cp_VoterId"), getvalue("cp_VoterId"));
		pages.Utill().input_text(getlocator("cp_VoterAddress"), getvalue("cp_VoterAddress"));
		pages.Utill().input_text(getlocator("cp_IDProofNo"), getvalue("cp_IDProofNo"));
		pages.Utill().input_text(getlocator("cp_SSNNo"), getvalue("cp_SSNNo"));
		pages.Utill().click_element(getlocator("cp_Issue_Date"));
		EnterDate("15-July-2010", "CandidateHome1_CandidateIsDate_title", "CandidateHome1_CandidateIsDate_prevArrow");
		pages.Utill().input_text(getlocator("cp_AadharName"),
				pages.Utill().find(getlocator("cc_Candidate_Name")).getAttribute("value"));
		pages.Utill().input_text(getlocator("cp_DrivingLicenseNo"), getvalue("cp_DrivingLicenseNo"));
		pages.Utill().input_text(getlocator("cp_AadharNumber"), getvalue("cp_AadharNumber"));
		pages.Utill().input_text(getlocator("cp_AadharEnrollNumber"), getvalue("cp_AadharEnrollNumber"));
		pages.Utill().input_text(getlocator("cp_Comments"), getvalue("cp_Comments"));
		// Thread.sleep(5000);
		pages.Utill().select_by_label(getlocator("cp_IsFresher"), getvalue("cp_IsFresher"));
		pages.Utill().scrollTo(getlocator("cp_save"));
		pages.Wait().visibilityOfElement(getlocator("cp_save"));
		pages.Utill().click_element(getlocator("cp_save"));
		// System.out.println("wait starting");
		driver.switchTo().defaultContent();
		wait_until_loader_is_invisible();
		pages.Utill().SwitchFramebyIndex(0);
		aleterwait.until(ExpectedConditions.visibilityOf(pages.Utill().find("regMessage")));
		System.out.println(pages.Utill().get_text("regMessage"));
		pages.Utill().click_element("//span[text()='Ok']");
	}

	public void address(String id) throws Exception {
		DataFactory df = new DataFactory();
		// for (int i = 0; i < count; i++) {
		pages.Utill().input_text(id + "_ClientAddr_Address", df.getAddress());
		pages.Utill().input_text(id + "_ClientAddr_State", "Tamil nadu");
		WebDriverWait w = new WebDriverWait(driver, 10);
		w.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[text()='Tamil nadu']")));
		pages.Utill().click_element(".//*[text()='Tamil nadu']");
		pages.Utill().input_text(id + "_ClientAddr_City", "Chennai");
		w.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[text()='Chennai']")));
		pages.Utill().click_element(".//*[text()='Chennai']");
		pages.Utill().input_text(id + "_ClientAddr_Pincode", "123456");
		pages.Utill().select_by_label(id + "_ClientAddr_ResiType", "Hostel");
		pages.Utill().input_text(id + "_ClientAddr_Landmark", df.getAddress());
		if (id.equals("ctl05")) {
			pages.Utill().click_element(id + "_ClientAddr_FromDt");
			EnterDate("10-May-2010", id + "_ClientAddrFrmDate_title", id + "_ClientAddrFrmDate_prevArrow");
			pages.Utill().click_element(id + "_ClientAddr_Tilldate");
		} else if (id.equals("ctl08")) {
			pages.Utill().click_element(id + "_ClientAddr_FromDt");
			EnterDate("21-April-1998", id + "_ClientAddrFrmDate_title", id + "_ClientAddrFrmDate_prevArrow");
			pages.Utill().click_element(id + "_ClientAddr_ToDt");
			EnterDate("20-May-2010", id + "_ClientAddrToDate_title", id + "_ClientAddrToDate_prevArrow");
		}
		pages.Utill().input_text(id + "_ClientAddr_PoliceSt", "near" + df.getCity());
		pages.Utill().input_text(id + "_ClientAddr_StayPeriod", "2");
		pages.Utill().input_text(id + "_ClientAddr_PoliceSt", "station" + df.getCity());
		pages.Utill().input_text(id + "_ClientAddr_Mobile1", df.getNumberText(10));
		pages.Utill().input_text(id + "_ClientAddr_MobileTwo1", df.getNumberText(10));
		pages.Utill().select_by_label(id + "_ClientAddr_LocType", "Local");
		pages.Utill().input_text(id + "_ClientAddr_Loc", df.getCity());
		pages.Utill().input_text(id + "_ClientAddr_OthLocation", "location");
		pages.Utill().input_text(id + "_ClientAddr_Others", "others");
		pages.Utill().input_text(id + "_ClientAddr_Comments", "comments");
		pages.Utill().click_element("ctl07_ClientAddr_sameas");
		pages.Utill().scrollTo("btnsavehtml");
		pages.Wait().visibilityOfElement("btnsavehtml");
		pages.Utill().click_element("btnsavehtml");
		// System.out.println("wait starting");
		driver.switchTo().defaultContent();
		wait_until_loader_is_invisible();
		pages.Utill().SwitchFramebyIndex(1);
		aleterwait.until(ExpectedConditions.visibilityOf(pages.Utill().find("regMessage")));
		System.out.println(pages.Utill().get_text("regMessage"));
		pages.Utill().click_element("//span[text()='Ok']");
	}

	public String Edcucation(String check) throws Exception {
		String result = "";
		String id = "";
		switch (check) {
		case "10":
			id = "ctl04";
			try {
				pages.Utill().input_text(id + "_ClientEdu_Name", "Carmel Convent School");
				WebDriverWait w = new WebDriverWait(driver, 10);
				w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Carmel Convent School']")));
				pages.Utill().click_element("//*[text()='Carmel Convent School']");
				pages.Utill().input_text(id + "_ClientEdu_Address", "chennai");
				pages.Utill().input_text(id + "_ClientEdu_DegreeName", "10");
				pages.Utill().input_text(id + "_ClientEdu_StudentID", "897987");
				pages.Utill().input_text(id + "_ClientEdu_Gap", "no");

				pages.Utill().click_element(id + "_ClientEdu_StartDt");
				EnterEdudate("Apr-2009", "MonthPicker_" + id + "_ClientEdu_StartDt");
				pages.Utill().input_text(id + "_ClientEdu_NameofDegree", "10");
				pages.Utill().click_element(id + "_ClientEdu_EndDt");
				EnterEdudate("Apr-2010", "MonthPicker_" + id + "_ClientEdu_EndDt");
				pages.Utill().input_text(id + "_ClientEdu_MajorSubject", "tamil");
				pages.Utill().click_element(id + "_ClientEdu_PassYear");
				EnterEdudate("Apr-2010", "MonthPicker_" + id + "_ClientEdu_PassYear");
				pages.Utill().select_by_label(id + "_ClientEdu_DegreeType", "CBSE");
				pages.Utill().select_by_label(id + "_ClientEdu_Graduated", "Yes");
				pages.Utill().select_by_label(id + "_ClientEdu_ProgramType", "Full Time");
				pages.Utill().input_text(id + "_ClientEdu_CGPA", "10");
				pages.Utill().input_text(id + "_ClientEdu_ClassObtained", "A");
				pages.Utill().input_text(id + "_ClientEdu_Others", "othes comments");
				pages.Utill().input_text(id + "_ClientEdu_Comments", "additional comments");
				result = "success";
			} catch (Exception e) {
				// e.printStackTrace();
				result = e.getMessage().toString();
			}
			break;
		case "12":
			id = "ctl06";
			try {
				pages.Utill().input_text(id + "_ClientEdu_Name", "Abhinav Public School");
				WebDriverWait w = new WebDriverWait(driver, 10);
				w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Abhinav Public School']")));
				pages.Utill().click_element("//*[text()='Abhinav Public School']");
				pages.Utill().input_text(id + "_ClientEdu_Address", "chennai");
				pages.Utill().input_text(id + "_ClientEdu_DegreeName", "12");
				pages.Utill().input_text(id + "_ClientEdu_StudentID", "897987");
				pages.Utill().input_text(id + "_ClientEdu_Gap", "no");

				pages.Utill().click_element(id + "_ClientEdu_StartDt");
				EnterEdudate("Apr-2011", "MonthPicker_" + id + "_ClientEdu_StartDt");
				pages.Utill().input_text(id + "_ClientEdu_NameofDegree", "12");
				pages.Utill().click_element(id + "_ClientEdu_EndDt");
				EnterEdudate("Apr-2012", "MonthPicker_" + id + "_ClientEdu_EndDt");
				pages.Utill().input_text(id + "_ClientEdu_MajorSubject", "tamil");
				pages.Utill().click_element(id + "_ClientEdu_PassYear");
				EnterEdudate("Apr-2012", "MonthPicker_" + id + "_ClientEdu_PassYear");
				pages.Utill().select_by_label(id + "_ClientEdu_DegreeType", "Diploma");
				pages.Utill().select_by_label(id + "_ClientEdu_Graduated", "Yes");
				pages.Utill().select_by_label(id + "_ClientEdu_ProgramType", "Full Time");
				pages.Utill().input_text(id + "_ClientEdu_CGPA", "10");
				pages.Utill().input_text(id + "_ClientEdu_ClassObtained", "A");
				pages.Utill().input_text(id + "_ClientEdu_Others", "othes comments");
				pages.Utill().input_text(id + "_ClientEdu_Comments", "additional comments");
				result = "success";
			} catch (Exception e) {
				// e.printStackTrace();
				result = e.getMessage().toString();
			}
			break;
		case "Diploma":
			id = "ctl07";
			try {
				pages.Utill().input_text(id + "_ClientEdu_Name", "Acharya Diploma Education(D.Ed) College");
				WebDriverWait w = new WebDriverWait(driver, 10);
				w.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[text()='Acharya Diploma Education(D.Ed) College']")));
				pages.Utill().click_element("//*[text()='Acharya Diploma Education(D.Ed) College']");
				pages.Utill().input_text(id + "_ClientEdu_Address", "chennai");
				pages.Utill().input_text(id + "_ClientEdu_DegreeName", "diploma");
				pages.Utill().input_text(id + "_ClientEdu_StudentID", "897987");
				pages.Utill().input_text(id + "_ClientEdu_Gap", "no");

				pages.Utill().click_element(id + "_ClientEdu_StartDt");
				EnterEdudate("Apr-2012", "MonthPicker_" + id + "_ClientEdu_StartDt");
				pages.Utill().input_text(id + "_ClientEdu_NameofDegree", "diploma");
				pages.Utill().click_element(id + "_ClientEdu_EndDt");
				EnterEdudate("Apr-2013", "MonthPicker_" + id + "_ClientEdu_EndDt");
				pages.Utill().input_text(id + "_ClientEdu_MajorSubject", "ECE");
				pages.Utill().click_element(id + "_ClientEdu_PassYear");
				EnterEdudate("Apr-2013", "MonthPicker_" + id + "_ClientEdu_PassYear");
				pages.Utill().select_by_label(id + "_ClientEdu_DegreeType", "CBSE");
				pages.Utill().select_by_label(id + "_ClientEdu_Graduated", "Yes");
				pages.Utill().select_by_label(id + "_ClientEdu_ProgramType", "Full Time");
				pages.Utill().input_text(id + "_ClientEdu_CGPA", "10");
				pages.Utill().input_text(id + "_ClientEdu_ClassObtained", "A");
				pages.Utill().input_text(id + "_ClientEdu_Others", "othes comments");
				pages.Utill().input_text(id + "_ClientEdu_Comments", "additional comments");
				result = "success";
			} catch (Exception e) {
				// e.printStackTrace();
				result = e.getMessage().toString();
			}
			break;
		case "UG1":
			id = "ctl08";
			try {
				String college = "Arignar Anna Engineering College";
				pages.Utill().input_text(id + "_ClientEdu_Name", college);
				WebDriverWait w = new WebDriverWait(driver, 10);
				w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='" + college + "']")));
				pages.Utill().click_element("//*[text()='" + college + "']");
				pages.Utill().input_text(id + "_ClientEdu_Address", "chennai");
				pages.Utill().input_text(id + "_ClientEdu_DegreeName", "UG1");
				pages.Utill().input_text(id + "_ClientEdu_StudentID", "5325434");
				pages.Utill().input_text(id + "_ClientEdu_Gap", "no");

				pages.Utill().click_element(id + "_ClientEdu_StartDt");
				EnterEdudate("Apr-2013", "MonthPicker_" + id + "_ClientEdu_StartDt");
				pages.Utill().input_text(id + "_ClientEdu_NameofDegree", "UG1");
				pages.Utill().click_element(id + "_ClientEdu_EndDt");
				EnterEdudate("Apr-2015", "MonthPicker_" + id + "_ClientEdu_EndDt");
				pages.Utill().input_text(id + "_ClientEdu_MajorSubject", "ECE");
				pages.Utill().click_element(id + "_ClientEdu_PassYear");
				EnterEdudate("Apr-2015", "MonthPicker_" + id + "_ClientEdu_PassYear");
				pages.Utill().select_by_label(id + "_ClientEdu_DegreeType", "CBSE");
				pages.Utill().select_by_label(id + "_ClientEdu_Graduated", "Yes");
				pages.Utill().select_by_label(id + "_ClientEdu_ProgramType", "Full Time");
				pages.Utill().input_text(id + "_ClientEdu_CGPA", "10");
				pages.Utill().input_text(id + "_ClientEdu_ClassObtained", "A");
				pages.Utill().input_text(id + "_ClientEdu_Others", "othes comments");
				pages.Utill().input_text(id + "_ClientEdu_Comments", "additional comments");
				result = "success";
			} catch (Exception e) {
				// e.printStackTrace();
				result = e.getMessage().toString();
			}
			break;
		case "PG1":
			id = "ctl10";
			try {
				String college = "Arignar Anna College, Aralvaimozhi";
				pages.Utill().input_text(id + "_ClientEdu_Name", college);
				WebDriverWait w = new WebDriverWait(driver, 10);
				w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='" + college + "']")));
				pages.Utill().click_element("//*[text()='" + college + "']");
				pages.Utill().input_text(id + "_ClientEdu_Address", "chennai");
				pages.Utill().input_text(id + "_ClientEdu_DegreeName", "PG1");
				pages.Utill().input_text(id + "_ClientEdu_StudentID", "542354");
				pages.Utill().input_text(id + "_ClientEdu_Gap", "no");

				pages.Utill().click_element(id + "_ClientEdu_StartDt");
				EnterEdudate("Apr-2015", "MonthPicker_" + id + "_ClientEdu_StartDt");
				pages.Utill().input_text(id + "_ClientEdu_NameofDegree", "PG1");
				pages.Utill().click_element(id + "_ClientEdu_EndDt");
				EnterEdudate("Apr-2016", "MonthPicker_" + id + "_ClientEdu_EndDt");
				pages.Utill().input_text(id + "_ClientEdu_MajorSubject", "ECE");
				pages.Utill().click_element(id + "_ClientEdu_PassYear");
				EnterEdudate("Apr-2016", "MonthPicker_" + id + "_ClientEdu_PassYear");
				pages.Utill().select_by_label(id + "_ClientEdu_DegreeType", "CBSE");
				pages.Utill().select_by_label(id + "_ClientEdu_Graduated", "Yes");
				pages.Utill().select_by_label(id + "_ClientEdu_ProgramType", "Full Time");
				pages.Utill().input_text(id + "_ClientEdu_CGPA", "10");
				pages.Utill().input_text(id + "_ClientEdu_ClassObtained", "A");
				pages.Utill().input_text(id + "_ClientEdu_Others", "othes comments");
				pages.Utill().input_text(id + "_ClientEdu_Comments", "additional comments");
				result = "success";
			} catch (Exception e) {
				// e.printStackTrace();
				result = e.getMessage().toString();
			}
			break;
		case "Highest":
			id = "ctl12";
			try {
				String college = "Anna University of Technology, Coimbatore";
				pages.Utill().input_text(id + "_ClientEdu_Name", college);
				WebDriverWait w = new WebDriverWait(driver, 10);
				w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='" + college + "']")));
				pages.Utill().click_element("//*[text()='" + college + "']");
				pages.Utill().input_text(id + "_ClientEdu_Address", "chennai");
				pages.Utill().input_text(id + "_ClientEdu_DegreeName", "highest");
				pages.Utill().input_text(id + "_ClientEdu_StudentID", "52545354");
				pages.Utill().input_text(id + "_ClientEdu_Gap", "no");

				pages.Utill().click_element(id + "_ClientEdu_StartDt");
				EnterEdudate("Apr-2016", "MonthPicker_" + id + "_ClientEdu_StartDt");
				pages.Utill().input_text(id + "_ClientEdu_NameofDegree", "highest");
				pages.Utill().click_element(id + "_ClientEdu_EndDt");
				EnterEdudate("Apr-2017", "MonthPicker_" + id + "_ClientEdu_EndDt");
				pages.Utill().input_text(id + "_ClientEdu_MajorSubject", "ECE");
				pages.Utill().click_element(id + "_ClientEdu_PassYear");
				EnterEdudate("Apr-2017", "MonthPicker_" + id + "_ClientEdu_PassYear");
				pages.Utill().select_by_label(id + "_ClientEdu_DegreeType", "CBSE");
				pages.Utill().select_by_label(id + "_ClientEdu_Graduated", "Yes");
				pages.Utill().select_by_label(id + "_ClientEdu_ProgramType", "Full Time");
				pages.Utill().input_text(id + "_ClientEdu_CGPA", "10");
				pages.Utill().input_text(id + "_ClientEdu_ClassObtained", "A");
				pages.Utill().input_text(id + "_ClientEdu_Others", "othes comments");
				pages.Utill().input_text(id + "_ClientEdu_Comments", "additional comments");

				result = "success";
				pages.Wait().visibilityOfElement("btnsaveedu");
				pages.Utill().click_element("btnsaveedu");
				// System.out.println("wait starting");
				driver.switchTo().defaultContent();
				wait_until_loader_is_invisible();
				pages.Utill().SwitchFramebyIndex(2);
				aleterwait.until(ExpectedConditions.visibilityOf(pages.Utill().find("regMessage")));
				System.out.println(pages.Utill().get_text("regMessage"));
				pages.Utill().click_element("//span[text()='Ok']");
			} catch (Exception e) {
				// e.printStackTrace();
				result = e.getMessage().toString();
			}

			break;
		default:
			throw new Exception("no matching found for :" + check);
		}

		return result;
	}

	public String reference(String checkName) throws Exception {
		String id = "";
		try {
			switch (checkName) {
			case "Ref 1":
				id = "ctl04";
				break;
			case "Ref 2":
				id = "ctl07";
				break;
			default:
				System.out.println("not matching");
			}

			DataFactory df = new DataFactory();
			pages.Utill().input_text(id + "_ClientRef_RepName", df.getFirstName());
			pages.Utill().input_text(id + "_ClientRef_RepDesig", "ref designation");
			pages.Utill().input_text(id + "_ClientRef_RepMobile1", df.getNumberText(10));
			pages.Utill().input_text(id + "_ClientRef_RepEmail", df.getEmailAddress());
			pages.Utill().input_text(id + "_ClientRef_OrgName", df.getBusinessName());
			pages.Utill().input_text(id + "_ClientRef_OrgAddr", df.getAddress());
			pages.Utill().input_text(id + "_ClientRef_HRName", df.getFirstName());
			pages.Utill().input_text(id + "_ClientRef_HRContact", df.getNumberText(10));
			pages.Utill().input_text(id + "_ClientRef_HRMobile", df.getNumberText(10));
			pages.Utill().input_text(id + "_ClientRef_HRDesignation", "HR");
			pages.Utill().input_text(id + "_ClientRef_HREmail", df.getEmailAddress());
			pages.Utill().input_text(id + "_ClientRef_CanKnown", "working with me");
			pages.Utill().input_text(id + "_ClientRef_CanAsso", "2 years");
			// System.out.println("id is :" + id.equals("ctl07"));
			if (id.equals("ctl07")) {
				// System.out.println("passing inside if condition");
				pages.Wait().visibilityOfElement("btnsaveref");
				pages.Utill().click_element("btnsaveref");
				// System.out.println("wait starting");
				driver.switchTo().defaultContent();
				wait_until_loader_is_invisible();
				pages.Utill().SwitchFramebyIndex(4);
				aleterwait.until(ExpectedConditions.visibilityOf(pages.Utill().find("regMessage")));
				System.out.println(pages.Utill().get_text("regMessage"));
				pages.Utill().click_element("//span[text()='Ok']");
			}
			return "success";
		} catch (Exception e) {
			return e.getMessage().toString();
		}
	}

	public void Employment(String checkName, String companyname) throws Exception {
		String id = "";
		DataFactory df = new DataFactory();

		switch (checkName) {
		case "Emp 1(Latest)":
			id = "ctl04";

			pages.Utill().input_text(id + "_ClientEmpl_CmpName", companyname);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(
					ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[text()='" + companyname + "']")));
			// pages.Wait().visibilityOfElement("//*[text()='"+companyname+"']");
			pages.Utill().click_element("//*[text()='" + companyname + "']");
			pages.Utill().input_text(id + "_ClientEmpl_CmpAddr", df.getAddress());
			pages.Utill().input_text(id + "_ClientEmpl_Position", "Developer");
			pages.Utill().input_text(id + "_ClientEmpl_Department", "IT");
			pages.Utill().input_text(id + "_ClientEmpl_HOAddr", df.getAddress());
			pages.Utill().input_text(id + "_ClientEmpl_CanCmpAddr", df.getAddress());
			pages.Utill().input_text(id + "_ClientEmpl_EmpCode", "42134234");
			pages.Utill().input_text(id + "_ClientEmpl_OhrId", "3245234");
			pages.Utill().input_text(id + "_ClientEmpl_LastSalary", "500000");
			pages.Utill().input_text(id + "_ClientEmpl_RepAuthName", df.getFirstName());
			pages.Utill().input_text(id + "_ClientEmpl_RepAuthDesig", "hr");
			pages.Utill().input_text(id + "_ClientEmpl_RepAuthMobile1", df.getNumberText(10));
			pages.Utill().input_text(id + "_ClientEmpl_RepAuthEmail", df.getEmailAddress());
			pages.Utill().input_text(id + "_ClientEmpl_HRName", "hr");
			pages.Utill().input_text(id + "_ClientEmpl_HRMobile1", df.getNumberText(10));
			pages.Utill().input_text(id + "_ClientEmpl_ReferenceNow", "YES");
			pages.Utill().input_text(id + "_ClientEmpl_Gap", "no");
			pages.Utill().input_text(id + "_ClientEmpl_ReasonLeave", "ReasonLeave");
			pages.Utill().input_text(id + "_ClientEmpl_Others", "others");
			pages.Utill().input_text(id + "_ClientEmpl_Comments", "comments");
			pages.Utill().input_text(id + "_ClientEmpl_HREmail", df.getEmailAddress());
			pages.Utill().select_by_label(id + "_ClientEmpl_EmplType", "Permanent");
			pages.Utill().select_by_label(id + "_ClientEmpl_CurrencyType", "INR");
			pages.Utill().select_by_label(id + "_ClientEmpl_SalType", "Annum");

			break;
		case "Emp 2":
			id = "ctl07";
			pages.Utill().input_text(id + "_ClientEmpl_CmpName", companyname);
			WebDriverWait waitt = new WebDriverWait(driver, 10);
			waitt.until(
					ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[text()='" + companyname + "']")));
			// pages.Wait().visibilityOfElement("//*[text()='"+companyname+"']");
			pages.Utill().click_element("//*[text()='" + companyname + "']");
			pages.Utill().input_text(id + "_ClientEmpl_CmpAddr", df.getAddress());
			pages.Utill().input_text(id + "_ClientEmpl_Position", "Developer");
			pages.Utill().input_text(id + "_ClientEmpl_Department", "IT");
			pages.Utill().input_text(id + "_ClientEmpl_HOAddr", df.getAddress());
			pages.Utill().input_text(id + "_ClientEmpl_CanCmpAddr", df.getAddress());
			pages.Utill().input_text(id + "_ClientEmpl_EmpCode", "42134234");
			pages.Utill().input_text(id + "_ClientEmpl_OhrId", "3245234");
			pages.Utill().input_text(id + "_ClientEmpl_LastSalary", "500000");
			pages.Utill().input_text(id + "_ClientEmpl_RepAuthName", df.getFirstName());
			pages.Utill().input_text(id + "_ClientEmpl_RepAuthDesig", "hr");
			pages.Utill().input_text(id + "_ClientEmpl_RepAuthMobile1", df.getNumberText(10));
			pages.Utill().input_text(id + "_ClientEmpl_RepAuthEmail", df.getEmailAddress());
			pages.Utill().input_text(id + "_ClientEmpl_HRName", "hr");
			pages.Utill().input_text(id + "_ClientEmpl_HRMobile1", df.getNumberText(10));
			pages.Utill().input_text(id + "_ClientEmpl_ReferenceNow", "YES");
			pages.Utill().input_text(id + "_ClientEmpl_Gap", "no");
			pages.Utill().input_text(id + "_ClientEmpl_ReasonLeave", "ReasonLeave");
			pages.Utill().input_text(id + "_ClientEmpl_Others", "others");
			pages.Utill().input_text(id + "_ClientEmpl_Comments", "comments");
			pages.Utill().input_text(id + "_ClientEmpl_HREmail", df.getEmailAddress());
			pages.Utill().select_by_label(id + "_ClientEmpl_EmplType", "Permanent");
			pages.Utill().select_by_label(id + "_ClientEmpl_CurrencyType", "INR");
			pages.Utill().select_by_label(id + "_ClientEmpl_SalType", "Annum");
			pages.Wait().visibilityOfElement("btnsavehtml");
			pages.Utill().click_element("btnsavehtml");
			// System.out.println("wait starting");
			driver.switchTo().defaultContent();
			wait_until_loader_is_invisible();
			pages.Utill().SwitchFramebyIndex(3);
			aleterwait.until(ExpectedConditions.visibilityOf(pages.Utill().find("regMessage")));
			System.out.println(pages.Utill().get_text("regMessage"));
			pages.Utill().click_element("//span[text()='Ok']");
			break;
		default:
			System.out.println("not matching");
		}

		// pages.Wait().visibilityOfElement("btnsavehtml");
		// pages.Utill().click_element("btnsavehtml");
		// System.out.println("wait starting");
		// driver.switchTo().defaultContent();
		// wait_until_loader_is_invisible();
		// pages.Utill().SwitchFramebyIndex(3);
		// aleterwait.until(ExpectedConditions.visibilityOf(pages.Utill().find("regMessage")));
		// System.out.println(pages.Utill().get_text("regMessage"));
		// pages.Utill().click_element("//span[text()='Ok']");

	}

	public void wait_until_loader_is_invisible() throws InterruptedException {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(200));
		// .ignoring(NoSuchElementException.class);
		WebElement ele = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement ele = pages.Utill().find("ctl00_ContentPlaceHolder1_DivMainFrame");
				String res = ele.getCssValue("display");
				if (res.equals("none")) {
					// System.out.println("success " + res);
					return ele;
				} else {
					// System.out.println("failed :" + res);
					return null;
				}
			}
		});
	}

	public void FluentWait() {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(200));
		// .ignoring(NoSuchElementException.class);
		WebElement ele = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement ele = pages.Utill().find("ctl00_ContentPlaceHolder1_DivMainFrame");
				String res = ele.getCssValue("display");
				if (res.equals("none")) {
					// System.out.println("success " + res);
					return ele;
				} else {
					// System.out.println("failed :" + res);
					return null;
				}
			}
		});
	}

	public void EnterDate(String date, String id, String arrow) throws Exception {
		// "CandidateHome1_CanDobdate_title"
		// "CandidateHome1_CanDobdate_prevArrow"
		String t = pages.Utill().get_text(id);
		// System.out.println(date);
		String[] pdate = date.replaceAll("-", " ").split("\\s+");
		String[] gdate = t.replaceAll(",", "").split("\\s+");
		if (pdate[2].equals(gdate[1])) {
			if (pdate[1].equals(gdate[0])) {
				// System.out.println("click " + pdate[0]);
				pages.Utill().click_element("//*[contains(@title,'" + pdate[1] + " " + pdate[0] + "')]");
			} else {

				// System.out.println("click title");
				pages.Utill().click_element(id);

				Thread.sleep(1000);
				pages.Utill().click_element("//*[contains(@title,'" + pdate[1] + ", " + pdate[2] + "')]");
				Thread.sleep(1000);
				pages.Utill().click_element("//*[contains(@title,'" + pdate[1] + " " + pdate[0] + "')]");

			}
		} else {
			pages.Utill().click_element(id);
			Thread.sleep(1000);
			pages.Utill().click_element(id);
			Thread.sleep(1000);
			String year = pages.Utill().get_text(id);
			String y[] = year.split("-");
			while (!(Integer.parseInt(y[0]) - 1 <= Integer.parseInt(pdate[2])
					&& Integer.parseInt(y[1]) + 1 >= Integer.parseInt(pdate[2]))) {
				pages.Utill().click_element(arrow);
				year = pages.Utill().get_text(id);
				y = year.split("-");

			}
			Thread.sleep(1000);
			pages.Utill().click_element("//div[text()='" + pdate[2] + "']");
			Thread.sleep(1500);
			pages.Utill().click_element("//*[contains(@title,'" + pdate[1] + ", " + pdate[2] + "')]");
			Thread.sleep(1500);
			// System.out.println("//*[contains(@title,'" + pdate[1] + " " + pdate[0] +
			// "')]");
			pages.Utill().click_element("//*[contains(@title,'" + pdate[1] + " " + pdate[0] + "')]");
			//
		}
	}

	public void EnterEdudate(String dates, String id) throws Exception {
		// String dates = "Feb-2000";
		// String id="MonthPicker_ctl04_ClientEdu_EndD";

		String mon = dates.split("-")[0];
		String year = dates.split("-")[1];
		pages.Utill().click_element(".//*[@id='" + id + "']/div[1]/table/tbody/tr/td[2]/a/span");
		String status = "true";
		while (status.equals("true")) {
			try {
				status = pages.Utill().find("//span[text()='" + year + "']/..").getAttribute("aria-disabled");
			} catch (NoSuchElementException e) {
				// System.out.println("exception raised");
			}
			// System.out.println("statsu is :" + status);
			if (status.equals("true")) {
				pages.Utill().click_element("//*[@id='" + id + "']/div[1]/table/tbody/tr/td[1]/a/span[1]/i");
				// System.out.println("clicking on arrow");
			}
			Thread.sleep(1000);
		}
		// System.out.println("found");

		// pages.Utill().click_element("//span[text()='"+year+"']");
		// pages.Utill().find(id).findElement(By.xpath("//span[text()='"+year+"']")).click();
		pages.Utill().click_element("//*[@id='" + id + "']//span[text()='" + year + "']");
		Thread.sleep(1000);
		pages.Utill().click_element("//*[@id='" + id + "']//span[text()='" + mon + ".']");
		// pages.Wait().visibilityOfElement("//span[text()='"+mon+".']");
		// pages.Utill().click_element("//span[text()='"+mon+".']");
		// pages.Utill().find(id).findElement(By.xpath("//span[text()='"+mon+".']")).click();

	}

	public void attachefile(String filename, String doctype, String checktype) throws Exception {

		String file = System.getProperty("user.dir") + "\\Documents\\" + filename;
		pages.Utill().FileUpload("//*[@id='ctl00_Panel3']/input[8]", file);
		pages.Utill().clickAlertbox();
		pages.Utill().click_element("//*[text()='" + filename + "']/following-sibling::td[1]/select");
		pages.Utill().click_element(
				"//*[text()='" + filename + "']/following-sibling::td[1]//option[text()='" + doctype + "']");
		if (!(checktype.isEmpty())) {
			pages.Utill().click_element("//*[text()='" + filename + "']/following-sibling::td[2]/select");
			pages.Utill().click_element("//*[text()='" + filename
					+ "']/following-sibling::td[2]//option[contains(text(),'" + checktype + "')]");
			// *[text()='emp_one.pdf']/following-sibling::td[2]//option[contains(text(),'Emp
			// 2')]
		}

	}
}
