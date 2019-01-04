package pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import environment.BaseClass;
import environment.Utill;
import testCases.Pages;

public class OperationTM {
	protected final WebDriver driver;
	protected final Pages pages;
	ExtentTest logger;
	WebDriverWait w;

	public OperationTM(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		pages = new Pages(driver, logger);
		w = new WebDriverWait(driver, 20);
	}

	private String getvalue(String key) throws FileNotFoundException, IOException {
		Properties pr = BaseClass.getvalue();
		return pr.getProperty(key);
	}

	private String getlocator(String key) throws FileNotFoundException, IOException {
		Properties pr = BaseClass.getlocator();
		return pr.getProperty(key);
	}

	public void Education(String no) throws Exception {
		try {
			try {
				pages.CaseRegistration().navigateTo("Dashboard", "Academic TM");
			} catch (Exception e) {
				logger.log(Status.WARNING, e.getMessage().toString());
				pages.Utill().GoTo(getvalue("url") + "/Matrix/EducationTMHomepage.aspx");
				// pages.Utill().GoTo("http://192.168.2.16/MatexTesting/Matrix/EducationTMHomepage.aspx");

			}
			String x = pages.Utill().get_text(getlocator("v_all_out"));
			if (!(x.equals("0"))) {
				pages.Utill().click_element(getlocator("v_all_out"));
				pages.Wait().wait_until_loader_is_invisible();
				w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("v_normal"))));
				String y = pages.Utill().get_text(getlocator("v_normal"));
				if (!(y.equals("0"))) {
					pages.Utill().click_element(getlocator("v_normal"));
					pages.Wait().wait_until_loader_is_invisible();
					w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("v_table"))));
					int index = 0;
					String ss = "";
					int b = 0;
					List<WebElement> table_list = driver
							.findElements(By.xpath("//*[@id='" + getlocator("v_table") + "']/tbody/tr/td[8]/a"));
					for (int i = 0; i < table_list.size(); i++) {
						String n = table_list.get(i).getText();
						if (n.equals(no)) {
							index = i + 2;
							pages.Utill().click_element(
									"//*[@id='" + getlocator("v_table") + "']/tbody/tr[" + index + "]/td[5]/input");
							pages.Wait().wait_until_loader_is_invisible();
							w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("v_table"))));
							pages.Utill().select_by_label(getlocator("v_doctype"), "Email ID Proof");
							String file = System.getProperty("user.dir") + "\\Documents\\edu_one.pdf";
							pages.Utill().choose_file(getlocator("v_upload"), file);

							ss = pages.Utill().clickAlertbox();
							System.out.println(ss);
							pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnclose2");
							pages.Utill().click_element(
									"//*[@id='" + getlocator("v_table") + "']/tbody/tr[" + index + "]/td[1]/input[8]");
							pages.Utill().click_element(getlocator("v_edu_vr"));
							pages.Wait().wait_until_loader_is_invisible();
							// pages.Utill().select_by_label("ctl00_ContentPlaceHolder1_ddlInitiateMode",
							// "Fax");
							w.until(ExpectedConditions
									.presenceOfElementLocated(By.id("ctl00_ContentPlaceHolder1_btnInitiateVR")));

							pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnInitiateVR");
							pages.Wait().wait_until_loader_is_invisible();
							ss = pages.Utill().clickAlertbox();
							System.out.println(ss);
							b++;
							break;
						}
					}
					if ((b == 0)) {
						System.out.println("education vr initiation failed");
						logger.fail("education vr initiation failed");
					} else {
						logger.pass("education vr initiation completed");
						x = "";
						x = pages.Utill().get_text(getlocator("v_updation_out"));
						if (!(x.equals("0"))) {
							pages.Utill().click_element(getlocator("v_updation_out"));
							pages.Wait().wait_until_loader_is_invisible();
							y = "";
							y = pages.Utill().get_text(getlocator("vedu_upda_normal"));
							if (!(y.equals("0"))) {
								pages.Utill().click_element(getlocator("vedu_upda_normal"));
								pages.Wait().wait_until_loader_is_invisible();
								// .//*[@id='ctl00_ContentPlaceHolder1_grdAllocFW']/tbody/tr[2]/td[8]
								// .//*[@id='ctl00_ContentPlaceHolder1_grdAllocFW']/tbody/tr[2]/td[2]/input
								w.until(ExpectedConditions
										.presenceOfElementLocated(By.id(getlocator("vedu_upda_table"))));
								index = 0;
								ss = "";
								table_list = driver.findElements(
										By.xpath("//*[@id='" + getlocator("vedu_upda_table") + "']/tbody/tr/td[8]"));
								for (int i = 0; i < table_list.size(); i++) {
									String n = table_list.get(i).getText();
									if (n.equals(no)) {
										index = i + 2;
										pages.Utill().click_element("//*[@id='" + getlocator("vedu_upda_table")
												+ "']/tbody/tr[" + index + "]/td[2]/input");
										WebDriverWait wait = new WebDriverWait(driver, 100);
										wait.until(ExpectedConditions
												.presenceOfElementLocated(By.id(getlocator("vEdu_EducationType"))));

										pages.Utill().input_text((getlocator("vEdu_Name")), (getvalue("vEdu_Name")));
										w.until(ExpectedConditions.presenceOfElementLocated(
												By.xpath("//*[text()='" + getvalue("vEdu_Name") + "']")));
										pages.Utill().click_element("//*[text()='" + getvalue("vEdu_Name") + "']");
										pages.Wait().wait_until_loader_is_invisible();
										pages.Utill().input_text(getlocator("vEdu_Address"), getvalue("vEdu_Address"));
										pages.Utill().input_text(getlocator("vEdu_StudentID"),
												getvalue("vEdu_StudentID"));
										pages.Utill().input_text(getlocator("vEdu_Gap"), getvalue("vEdu_Gap"));
										pages.Utill().input_text(getlocator("vEdu_StartDt"), getvalue("vEdu_StartDt"));
										pages.Utill().input_text(getlocator("vEdu_EndDt"), getvalue("vEdu_EndDt"));
										pages.Utill().input_text(getlocator("vEdu_PassYear"),
												getvalue("vEdu_PassYear"));
										pages.Utill().select_by_label(getlocator("vEdu_DegreeType"),
												getvalue("vEdu_DegreeType"));
										pages.Utill().input_text(getlocator("vEdu_NameofDegree"),
												getvalue("vEdu_NameofDegree"));
										pages.Utill().input_text(getlocator("vEdu_MajorSubject"),
												getvalue("vEdu_MajorSubject"));
										pages.Utill().select_by_label(getlocator("vEdu_Graduated"),
												getvalue("vEdu_Graduated"));
										pages.Utill().select_by_label(getlocator("vEdu_ProgramType"),
												getvalue("vEdu_ProgramType"));
										pages.Utill().input_text(getlocator("vEdu_CGPA"), getvalue("vEdu_CGPA"));
										pages.Utill().input_text(getlocator("vEdu_ClassObtained"),
												getvalue("vEdu_ClassObtained"));
										pages.Utill().input_text(getlocator("vEdu_VerifierName"),
												getvalue("vEdu_VerifierName"));
										pages.Utill().input_text(getlocator("vEdu_VerifierDesignation"),
												getvalue("vEdu_VerifierDesignation"));
										pages.Utill().input_text(getlocator("vEdu_VerifierContact"),
												getvalue("vEdu_VerifierContact"));
										pages.Utill().input_text(getlocator("vEdu_VerifierEmail"),
												getvalue("vEdu_VerifierEmail"));
										pages.Utill().select_by_label(getlocator("vEdu_Confirmfrom"),
												getvalue("vEdu_Confirmfrom"));
										pages.Utill().select_by_label(getlocator("vEdu_Typeofrevert"),
												getvalue("vEdu_Typeofrevert"));
										pages.Utill().select_by_label(getlocator("vEdu_Mode"), getvalue("vEdu_Mode"));
										pages.Utill().input_text(getlocator("vEdu_VerComments"),
												getvalue("vEdu_VerComments"));
										pages.Utill().click_element(getlocator("vedu_date"));
										w.until(ExpectedConditions
												.presenceOfElementLocated(By.id(getlocator("vedu_today"))));
										pages.Utill().click_element(getlocator("vedu_today"));
										pages.Utill().click_element(getlocator("vedu_doc_pro_cand"));
										pages.Utill().click_element(getlocator("vedu_doc_pro_cand_clkall"));
										pages.Utill().click_element(getlocator("vedu_doc_pro_cand"));
										Thread.sleep(3000);

										//
										pages.Utill().select_by_label(getlocator("vedu_sel_doc_type"), "Proof of Payment");
										Thread.sleep(2000);

										String file = System.getProperty("user.dir") + "\\Documents\\edu_six.pdf";
										pages.Utill().choose_file(getlocator("vedu_doc_uplload"), file);

										pages.Utill().clickAlertbox();
										//
										Thread.sleep(2000);
										pages.Utill().click_element(getlocator("vedu_green"));
										Thread.sleep(1000);
										pages.Utill().click_element(getlocator("Vedu_vresult"));
										System.out.println("Verification result");
										Thread.sleep(2000);
										pages.Utill().handle_Alert();
										driver.switchTo().defaultContent();
										break;
									}
								}
							}
						} else {
							logger.fail("no value in education updation dashboard");
							System.out.println("no value in updation allocation dashboard");
							String temp = Utill.getScreenshot(driver);
							logger.fail("", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
						}
					}
				} else {
					logger.fail("no value in education allocation dashboard");
					System.out.println("no value in education allocation dashboard");
					String temp = Utill.getScreenshot(driver);
					logger.fail("", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
				}
			} else {
				logger.fail("no value in education allocation dashboard");
				System.out.println("no value in education allocation dashboard");
				String temp = Utill.getScreenshot(driver);
				logger.fail("", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			}
		} catch (Exception e) {
			logger.fail(e.getMessage().toString());
			String temp = Utill.getScreenshot(driver);
			logger.fail(e.getMessage().toString(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			pages.Utill().GoTo(getvalue("url") + "/Matrix/UserHome.aspx");
			// pages.Utill().GoTo(getvalue("url")+"/Matrix/UserHome.aspx");
		}

	}

	public void Employment(String no) throws Exception {
		try {
			try {
				pages.CaseRegistration().navigateTo("Dashboard", "Prior TM");
			} catch (Exception e) {
				logger.log(Status.WARNING, e.getMessage().toString());
				pages.Utill().GoTo(getvalue("url") + "/Matrix/EmploymentTMHomepage.aspx");

			}
			String x = pages.Utill().get_text(getlocator("vemp_all_out"));
			if (!(x.equals("0"))) {
				pages.Utill().click_element(getlocator("vemp_all_out"));
				pages.Wait().wait_until_loader_is_invisible();
				w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("v_normal"))));
				String y = pages.Utill().get_text(getlocator("v_normal"));
				if (!(y.equals("0"))) {
					pages.Utill().click_element(getlocator("v_normal"));
					pages.Wait().wait_until_loader_is_invisible();
					w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("vemp_table"))));
					int index = 0;
					int b = 0;
					String ss = "";
					List<WebElement> table_list = driver
							.findElements(By.xpath("//*[@id='" + getlocator("vemp_table") + "']/tbody/tr/td[8]/a"));
					logger.info("getting webelement list of " + "//*[@id='" + getlocator("vemp_table")
							+ "']/tbody/tr/td[8]/a");
					System.out.println(table_list.size());
					for (int i = 0; i < table_list.size(); i++) {
						System.out.println(no);
						String n = table_list.get(i).getText();
						System.out.println(n);
						if (n.equals(no)) {
							index = i + 2;
							System.out.println("entering if " + index);
							pages.Utill().click_element("//*[@id='" + getlocator("vemp_table") + "']/tbody/tr[" + index
									+ "]/td[1]/input[9]");
							pages.Utill().click_element(getlocator("v_edu_vr"));
							pages.Wait().wait_until_loader_is_invisible();
							// pages.Utill().select_by_label("ctl00_ContentPlaceHolder1_ddlInitiateMode",
							// "Fax");
							// pages.Wait().wait_until_loader_is_invisible();
							w.until(ExpectedConditions
									.presenceOfElementLocated(By.id("ctl00_ContentPlaceHolder1_btnInitiateVR")));
							pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnInitiateVR");
							pages.Wait().wait_until_loader_is_invisible();
							ss = pages.Utill().clickAlertbox();
							System.out.println(ss);
							b++;
							break;
						}
					}
					System.out.println("before " + index);
					if ((b == 0)) {
						System.out.println("Employment vr initiation failed");
						logger.fail("Employment vr initiation failed");
						String temp = Utill.getScreenshot(driver);
						logger.fail("", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
					} else {
						logger.pass("Employment vr initiation completed");
						x = "";
						x = pages.Utill().get_text(getlocator("v_updation_out"));
						if (!(x.equals("0"))) {
							pages.Utill().click_element(getlocator("v_updation_out"));
							pages.Wait().wait_until_loader_is_invisible();
							y = "";
							y = pages.Utill().get_text(getlocator("vemp_upda_normal"));
							if (!(y.equals("0"))) {
								pages.Utill().click_element(getlocator("vemp_upda_normal"));
								pages.Wait().wait_until_loader_is_invisible();
								// .//*[@id='ctl00_ContentPlaceHolder1_grdAllocFW']/tbody/tr[2]/td[8]
								// .//*[@id='ctl00_ContentPlaceHolder1_grdAllocFW']/tbody/tr[2]/td[2]/input
								w.until(ExpectedConditions
										.presenceOfElementLocated(By.id(getlocator("vemp_upda_table"))));
								index = 0;
								ss = "";
								table_list = driver.findElements(
										By.xpath("//*[@id='" + getlocator("vemp_upda_table") + "']/tbody/tr/td[8]"));
								for (int i = 0; i < table_list.size(); i++) {
									String n = table_list.get(i).getText();
									if (n.equals(no)) {
										index = i + 2;
										pages.Utill().click_element("//*[@id='" + getlocator("vemp_upda_table")
												+ "']/tbody/tr[" + index + "]/td[2]/input");

										Thread.sleep(1000);
										String currentWindow = driver.getWindowHandle();
										Set<String> handles = driver.getWindowHandles();
										System.out.println(handles);
										for (String e : handles) {
											if (!(e.equals(currentWindow))) {
												driver.switchTo().window(e);
												break;
											}
										}
										System.out.println(driver.getTitle());
										w.until(ExpectedConditions
												.presenceOfElementLocated(By.id(getlocator("vCompany_Type"))));

										pages.Utill().input_text((getlocator("Empl_CmpName")),
												(getvalue("Empl_CmpName")));
										w.until(ExpectedConditions.presenceOfElementLocated(
												By.xpath("//*[text()='" + getvalue("Empl_CmpName") + "']")));
										pages.Utill().click_element("//*[text()='" + getvalue("Empl_CmpName") + "']");
										pages.Wait().wait_until_loader_is_invisible();
										pages.Utill().input_text(getlocator("Empl_CmpAddr"), getvalue("Empl_CmpAddr"));
										pages.Utill().input_text(getlocator("Empl_Position"),
												getvalue("Empl_Position"));
										pages.Utill().input_text(getlocator("Empl_Department"),
												getvalue("Empl_Department"));
										pages.Utill().input_text(getlocator("Empl_HOAddr"), getvalue("Empl_HOAddr"));
										pages.Utill().input_text(getlocator("Empl_FromDt"), getvalue("Empl_FromDt"));
										pages.Utill().input_text(getlocator("Empl_ToDt"), getvalue("Empl_ToDt"));
										pages.Utill().input_text(getlocator("Empl_EmpCode"), getvalue("Empl_EmpCode"));

										pages.Utill().select_by_label(getlocator("Empl_EmplType"),
												getvalue("Empl_EmplType"));
										pages.Utill().input_text(getlocator("Empl_LastSalary"),
												getvalue("Empl_LastSalary"));

										pages.Utill().select_by_label(getlocator("Empl_CurrencyType"),
												getvalue("Empl_CurrencyType"));
										pages.Utill().select_by_label(getlocator("Empl_SalType"),
												getvalue("Empl_SalType"));

										// pages.Utill().input_text(getlocator("Empl_RepAuthName"),
										// getvalue("Empl_RepAuthName"));
										// pages.Utill().input_text(getlocator("Empl_RepAuthDesig"),
										// getvalue("Empl_RepAuthDesig"));
										// pages.Utill().input_text(getlocator("Empl_RepAuthMobile1"),
										// getvalue("Empl_RepAuthMobile1"));
										// pages.Utill().input_text(getlocator("Empl_RepAuthEmail"),
										// getvalue("Empl_RepAuthEmail"));
										// pages.Utill().input_text(getlocator("Empl_HRName"), getvalue("Empl_HRName"));
										pages.Utill().input_text(getlocator("Empl_ReasonLeave"),
												getvalue("Empl_ReasonLeave"));
										pages.Utill().input_text(getlocator("Company_VerifierName"),
												getvalue("Company_VerifierName"));
										pages.Utill().input_text(getlocator("vemp_VerifierDesignation"),
												getvalue("vemp_VerifierDesignation"));
										pages.Utill().input_text(getlocator("vemp_VerifierContact"),
												getvalue("vemp_VerifierContact"));
										pages.Utill().input_text(getlocator("vemp_VerifierEmail"),
												getvalue("vemp_VerifierEmail"));

										pages.Utill().select_by_label(getlocator("vemp_ConfirmationMode"),
												getvalue("vemp_ConfirmationMode"));

										pages.Utill().select_by_label(
												"ctl00_ContentPlaceHolder1_RightCandidateEmployment1_CdtCompany_RevertReceivedFrom",
												"Reporting Authority");
//										pages.Utill().input_text(
//												"ctl00_ContentPlaceHolder1_RightCandidateEmployment1_CdtCompany_RespondentName",
//												"ragavan");
//										pages.Utill().select_by_label(
//												"ctl00_ContentPlaceHolder1_RightCandidateEmployment1_CdtCompany_RespondentDesig",
//												"Assistant General Manager, HR");
//										pages.Utill().select_by_label(
//												"ctl00_ContentPlaceHolder1_RightCandidateEmployment1_CdtCompany_RespondentDepartment",
//												"Admin");
//										pages.Utill().input_text(
//												"ctl00_ContentPlaceHolder1_RightCandidateEmployment1_CdtCompany_RespondentContact",
//												getvalue("Empl_RepAuthMobile1"));
//										pages.Utill().input_text(
//												"ctl00_ContentPlaceHolder1_RightCandidateEmployment1_CdtCompany_RespondentMobile",
//												getvalue("Empl_RepAuthMobile1"));
//										pages.Utill().input_text(
//												"ctl00_ContentPlaceHolder1_RightCandidateEmployment1_CdtCompany_RespondentEmail",
//												getvalue("Empl_RepAuthEmail"));

										pages.Utill().select_by_label(
												"ctl00_ContentPlaceHolder1_RightCandidateEmployment1_CdtCompany_FullTime_PartTime",
												"Full Time");
										pages.Utill().select_by_label(
												"ctl00_ContentPlaceHolder1_RightCandidateEmployment1_CdtCompanyEligibleforrehire",
												"Yes");
										pages.Utill().input_text(
												"ctl00_ContentPlaceHolder1_RightCandidateEmployment1_TxtRehireComments",
												"Eligible for rehire");
										pages.Utill().select_by_label(
												"ctl00_ContentPlaceHolder1_RightCandidateEmployment1_CdtCompanyIsthedocumentauthentic",
												"Yes");
										pages.Utill().input_text(
												"ctl00_ContentPlaceHolder1_RightCandidateEmployment1_TxtIsthedocumentauthentic",
												"document authentic");
										pages.Utill().select_by_label(
												"ctl00_ContentPlaceHolder1_RightCandidateEmployment1_CdtCompanyAnyIssuesPers",
												"No");
										pages.Utill().input_text(
												"ctl00_ContentPlaceHolder1_RightCandidateEmployment1_TxtIsAnyPertaining",
												"issues pertaining");
										pages.Utill().select_by_label(
												"ctl00_ContentPlaceHolder1_RightCandidateEmployment1_CdtCompanyNoticeServe",
												"Yes");
										pages.Utill().input_text(
												"ctl00_ContentPlaceHolder1_RightCandidateEmployment1_TxtNoticeServe",
												"Sufficient notice period serve");

										pages.Utill().select_by_label(getlocator("vemp_Typeofrevert"),
												getvalue("vemp_Typeofrevert"));
										pages.Utill().select_by_label(getlocator("vemp_VerificationSource"),
												getvalue("vemp_VerificationSource"));
										pages.Utill().input_text(getlocator("vemp_VerComments"),
												getvalue("vemp_VerComments"));
										pages.Utill().click_element(getlocator("vemp_date"));
										w.until(ExpectedConditions
												.presenceOfElementLocated(By.id(getlocator("vemp_today"))));
										pages.Utill().click_element(getlocator("vemp_today"));
										pages.Utill().click_element(getlocator("vemp_Doc_pro_cand"));
										pages.Utill().click_element(getlocator("vemp_Doc_pro_cand_clk"));
										Thread.sleep(2000);
										pages.Utill().click_element(getlocator("vemp_green"));
										Thread.sleep(1000);
										pages.Utill().click_element(getlocator("vemp_vresult"));
										System.out.println("Verification result");
										Thread.sleep(2000);
										pages.Utill().handle_Alert();
										driver.switchTo().window(currentWindow);
										break;
									}
								}
							}
						} else {
							logger.fail("no value in Employment updation dashboard");
							System.out.println("no value in updation allocation dashboard");
							String temp = Utill.getScreenshot(driver);
							logger.fail("", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
						}
					}
				} else {
					logger.fail("no value in Employment allocation dashboard");
					System.out.println("no value in education allocation dashboard");
					String temp = Utill.getScreenshot(driver);
					logger.fail("", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
				}
			} else {
				logger.fail("no value in Employment allocation dashboard");
				System.out.println("no value in education allocation dashboard");
				String temp = Utill.getScreenshot(driver);
				logger.fail("", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			}
		} catch (Exception e) {
			// driver.navigate().to(getlocator("home_page"));
			logger.fail(e.getMessage().toString());

			String temp = Utill.getScreenshot(driver);
			logger.fail(driver.getTitle() + e.getMessage().toString(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
	}

	public void Address(String no) throws Exception {
		try {
			try {

				pages.CaseRegistration().navigateTo("Dashboard", "Residence TM");
			} catch (Exception e) {
				logger.log(Status.WARNING, e.getMessage().toString());
				pages.Utill().GoTo(getvalue("url") + "/Matrix/MobResidence.aspx");
			}
			System.out.println("Before address"+no);
			driver.findElement(By.linkText(no)).click();
			logger.log(Status.PASS, "Clicking element '" + no + "' by link text");
			pages.Utill().input_text(getlocator("vClientAddr_Address"), getvalue("vClientAddr_Address"));
			pages.Utill().input_text(getlocator("vAddr_Pincode"), getvalue("vAddr_Pincode"));
			w.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//*[text()='" + getvalue("vAddr_Pincode") + "']")));
			pages.Utill().click_element("//*[text()='" + getvalue("vAddr_Pincode") + "']");
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().select_by_label(getlocator("vAddr_ResiType"), getvalue("vAddr_ResiType"));
			pages.Utill().input_text(getlocator("vAddr_Landmark"), getvalue("vAddr_Landmark"));
			pages.Utill().input_text(getlocator("vAddr_FromDt"), getvalue("vAddr_FromDt"));
			pages.Utill().input_text(getlocator("vAddr_ToDt"), getvalue("vAddr_ToDt"));
			pages.Utill().input_text(getlocator("vAddr_StayPeriod"), getvalue("vAddr_StayPeriod"));
			pages.Utill().input_text(getlocator("vAddr_PoliceSt"), getvalue("vAddr_PoliceSt"));
			pages.Utill().input_text(getlocator("vAddr_Mobile1"), getvalue("vAddr_Mobile1"));
			pages.Utill().select_by_label(getlocator("vAddr_LocType"), getvalue("vAddr_LocType"));
			pages.Utill().input_text(getlocator("vAddr_Loc"), getvalue("vAddr_Loc"));
			pages.Utill().input_text(getlocator("vAddr_Others"), getvalue("vAddr_Others"));
			pages.Utill().input_text(getlocator("vAddr_AddrRespName"), getvalue("vAddr_AddrRespName"));
			pages.Utill().input_text(getlocator("vAddr_AddrRelResp"), getvalue("vAddr_AddrRelResp"));
			pages.Utill().input_text(getlocator("vAddr_NatureLoc"), getvalue("vAddr_NatureLoc"));
			pages.Utill().input_text(getlocator("vAddr_LocVisitDt"), getvalue("vAddr_LocVisitDt"));
			pages.Utill().select_by_label(getlocator("vAddressProofVerify"), getvalue("vAddressProofVerify"));
			pages.Utill().input_text(getlocator("vAddr_VerifierName"), getvalue("vAddr_VerifierName"));
			pages.Utill().input_text(getlocator("vAddr_VerifierDesignation"), getvalue("vAddr_VerifierDesignation"));
			pages.Utill().input_text(getlocator("vAddr_VerifierContact"), getvalue("vAddr_VerifierContact"));
			pages.Utill().input_text(getlocator("vAddr_VerifierEmail"), getvalue("vAddr_VerifierEmail"));
			pages.Utill().select_by_label(getlocator("vAddr_Typeofrevert"), getvalue("vAddr_Typeofrevert"));
			pages.Utill().input_text(getlocator("vAddr_VerComments"), getvalue("vAddr_VerComments"));

			pages.Utill().select_by_label("ctl00_ContentPlaceHolder1_CandidateAddressFile_Upload1_ddlDocumentType",
					"Physical Verification Report");
			pages.Wait().wait_until_loader_is_invisible(20);
			String file = System.getProperty("user.dir") + "\\Documents\\vpresent.pdf";
			pages.Utill().choose_file("ctl00_ContentPlaceHolder1_CandidateAddressFile_Upload1_fup", file);
			pages.Wait().wait_until_loader_is_invisible(60);
			pages.Utill().clickAlertbox();

			pages.Utill().click_element(getlocator("vdd_vdate"));
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("vadd_Extender1_today"))));
			
			pages.Utill().click_element(getlocator("vadd_Extender1_today"));
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("vadd_green"))));
			pages.Utill().click_element(getlocator("vadd_green"));
			Thread.sleep(2000);
			pages.Utill().click_element(getlocator("vadd_vresult"));
			System.out.println("Verification result");
			Thread.sleep(2000);
			pages.Utill().handle_Alert();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// logger.fail(e.getMessage().toString());
			logger.log(Status.FAIL, e.getMessage().toString());
			String temp = Utill.getScreenshot(driver);
			logger.fail(driver.getTitle() + e.getMessage().toString(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

		} finally {
			pages.Utill().GoTo(getvalue("url") + "/Matrix/UserHome.aspx");
		}
	}

	public void Reference(String no) throws Exception {
		try {
			pages.CaseRegistration().navigateTo("Dashboard", "Reference TM");
		} catch (Exception e) {
			logger.log(Status.WARNING, e.getMessage().toString());
			pages.Utill().GoTo(getvalue("url") + "/Matrix/ReferenceTMHomepage.aspx");
		}
		String tat = pages.Utill().get_text("ctl00_ContentPlaceHolder1_lbtnWithinTAT");
		if (!(tat.equals("0"))) {
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_lbtnWithinTAT");
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_lbtnNormal");
			pages.Wait().wait_until_loader_is_invisible();
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("vemp_table"))));
			pages.Utill().input_text(getlocator("vref_tmsearch"), no);
			pages.Utill().click_element(getlocator("vref_tmsearchbtn"));
			pages.Wait().wait_until_loader_is_invisible();
			try {
				// vemp_table
				// .//*[@id='ctl00_ContentPlaceHolder1_grdOutstanding']/tbody/tr[2]/td[6]
				// .//*[@id='ctl00_ContentPlaceHolder1_grdOutstanding']/tbody/tr[2]/td[1]/input
				String ref = pages.Utill().get_text(".//*[@id='" + getlocator("vemp_table") + "']/tbody/tr[2]/td[6]");
				if (ref.equals(no)) {
					pages.Utill().click_element(".//*[@id='" + getlocator("vemp_table") + "']/tbody/tr[2]/td[1]/input");
					pages.Wait().wait_until_loader_is_invisible();
					System.out.println(driver.getTitle());
					logger.log(Status.INFO, driver.getTitle());
					try {
						pages.Utill().select_by_label(getlocator("vRef_ReferencesCheckType"),
								getvalue("vRef_ReferencesCheckType"));

					} catch (Exception e) {
						logger.log(Status.INFO, e.getMessage().toString());
					}
					pages.Utill().input_text(getlocator("vRef_RepName"), getvalue("vRef_RepName"));
					pages.Utill().input_text(getlocator("vRef_RepDesig"), getvalue("vRef_RepDesig"));
					pages.Utill().input_text(getlocator("vRef_RepMobile1"), getvalue("vRef_RepMobile1"));
					pages.Utill().input_text(getlocator("vRef_RepEmail"), getvalue("vRef_RepEmail"));
					pages.Utill().input_text(getlocator("vRef_OrgName"), getvalue("vRef_OrgName"));
					pages.Utill().input_text(getlocator("vRef_OrgAddr"), getvalue("vRef_OrgAddr"));
					pages.Utill().input_text(getlocator("vRef_HRName"), getvalue("vRef_HRName"));
					pages.Utill().input_text(getlocator("vRef_HRContact"), getvalue("vRef_HRContact"));
					pages.Utill().input_text(getlocator("vRef_HRMobile"), getvalue("vRef_HRMobile"));
					pages.Utill().input_text(getlocator("vRef_HRDesignation"), getvalue("vRef_HRDesignation"));
					pages.Utill().input_text(getlocator("vRef_HREmail"), getvalue("vRef_HREmail"));
					pages.Utill().input_text(getlocator("vRef_CanKnown"), getvalue("vRef_CanKnown"));
					pages.Utill().input_text(getlocator("vRef_CanAsso"), getvalue("vRef_CanAsso"));
					pages.Utill().input_text(getlocator("vRef_Comments"), getvalue("vRef_Comments"));
					pages.Utill().input_text(getlocator("vRef_Location"), getvalue("vRef_Location"));
					pages.Utill().input_text(getlocator("vRef_Primary"), getvalue("vRef_Primary"));
					pages.Utill().input_text(getlocator("vRef_CanKnownPeriod"), getvalue("vRef_CanKnownPeriod"));
					pages.Utill().input_text(getlocator("vRef_Strength"), getvalue("vRef_Strength"));
					pages.Utill().input_text(getlocator("vRef_Improvement"), getvalue("vRef_Improvement"));
					pages.Utill().input_text(getlocator("vRef_Involvement"), getvalue("vRef_Involvement"));
					pages.Utill().input_text(getlocator("vRef_CandAttitude"), getvalue("vRef_CandAttitude"));
					pages.Utill().input_text(getlocator("vRef_Sincerity"), getvalue("vRef_Sincerity"));
					pages.Utill().select_by_label(getlocator("vRef_EthicScale"), getvalue("vRef_EthicScale"));
					pages.Utill().input_text(getlocator("vRef_CandSuitable"), getvalue("vRef_CandSuitable"));
					pages.Utill().select_by_label(getlocator("vRef_TeamInvolvement"), getvalue("vRef_TeamInvolvement"));

					pages.Utill().input_text(getlocator("vRef_CandAnyOther"), getvalue("vRef_CandAnyOther"));
					pages.Utill().input_text(getlocator("vRef_VerifierName"), getvalue("vRef_VerifierName"));
					pages.Utill().input_text(getlocator("vRef_VerifierContact"), getvalue("vRef_VerifierContact"));
					pages.Utill().input_text(getlocator("vRef_VerifierEmail"), getvalue("vRef_VerifierEmail"));
					pages.Utill().input_text(getlocator("vRef_VerifierDesignation"),
							getvalue("vRef_VerifierDesignation"));
					pages.Utill().select_by_label(getlocator("vRef_Typeofrevert"), getvalue("vRef_Typeofrevert"));
					pages.Utill().input_text(getlocator("vRef_VerComments"), getvalue("vRef_VerComments"));

					pages.Utill().click_element(getlocator("vref_date"));
					w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("vref_today"))));
					pages.Utill().click_element(getlocator("vref_today"));
					Thread.sleep(2000);
					pages.Utill().click_element(getlocator("vref_green"));
					Thread.sleep(1000);
					pages.Utill().click_element(getlocator("vref_vresult"));
					System.out.println("Verification result");
					Thread.sleep(2000);
					pages.Utill().handle_Alert();

				} else {
					System.out.println(no + " no not found");
					logger.log(Status.FAIL, no + " no not found");
					String temp = Utill.getScreenshot(driver);
					logger.fail("", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
				}
			} catch (Exception e) {
				System.out.println(e.getMessage().toString());
				logger.log(Status.FAIL, e.getMessage().toString());
				String temp = Utill.getScreenshot(driver);
				logger.fail(driver.getTitle() + e.getMessage().toString(),
						MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
				pages.Utill().GoTo(getvalue("url") + "/Matrix/UserHome.aspx");
			}
		} else {
			logger.fail("no value in Reference dashboard");
			System.out.println("no value in Reference dashboard");
			String temp = Utill.getScreenshot(driver);
			logger.fail("", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
	}

	public void Criminal(String no) throws Exception {
		try {
			try {
				pages.CaseRegistration().navigateTo("Dashboard", "Criminal TM");
			} catch (Exception e) {
				logger.log(Status.WARNING, e.getMessage().toString());
				pages.Utill().GoTo(getvalue("url") + "/Matrix/CriminalTMHomePage.aspx");
			}
			String tat = pages.Utill().get_text("ctl00_ContentPlaceHolder1_lbtnWithinTAT");
			int b = 0;
			if (!(tat.equals("0"))) {
				pages.Utill().click_element("ctl00_ContentPlaceHolder1_lbtnWithinTAT");
				pages.Wait().wait_until_loader_is_invisible();
				pages.Utill().click_element("ctl00_ContentPlaceHolder1_lbtnWithinTATOS");
				pages.Wait().wait_until_loader_is_invisible();
				w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("op_emp_table"))));
				// .//*[@id='ctl00_ContentPlaceHolder1_grdUnassigned']/tbody/tr/td[7]
				List<WebElement> tabledata = driver
						.findElements(By.xpath(".//*[@id='" + getlocator("op_emp_table") + "']/tbody/tr/td[7]"));
				logger.log(Status.PASS, "getting web element list '" + no + "'");
				for (int i = 0; i < tabledata.size(); i++) {
					tat = tabledata.get(i).getText();
					if (tat.equals(no)) {
						int index = i + 2;
						pages.Utill().click_element(
								".//*[@id='" + getlocator("op_emp_table") + "']/tbody/tr[" + index + "]/td/input[15]");
						pages.Utill().select_by_label("ctl00_ContentPlaceHolder1_ddlMembers",
								"test fe -Field Executive");
						pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnAssign");
						pages.Wait().wait_until_loader_is_invisible();
						String ss = pages.Utill().clickAlertbox();
						System.out.println(ss);
						logger.log(Status.PASS, ss);
						b++;
						break;
					}

				}
				if (b == 0) {
					System.out.println("initiation for criminal check failed");
					logger.log(Status.FAIL, "initiation for criminal check failed");
					String temp = Utill.getScreenshot(driver);
					logger.fail("", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
				} else {
					// updation dashboard
					String altat = pages.Utill().get_text("ctl00_ContentPlaceHolder1_lnkOutstanding_updation");
					if (!(altat.equals("0"))) {
						pages.Utill().click_element("ctl00_ContentPlaceHolder1_lnkOutstanding_updation");
						pages.Wait().wait_until_loader_is_invisible();
						pages.Utill().click_element("ctl00_ContentPlaceHolder1_lbtnNormalUpt");
						pages.Wait().wait_until_loader_is_invisible();
						// pages.Utill().input_text(getlocator("vref_tmsearch"), no);
						// pages.Utill().click_element(getlocator("vref_tmsearchbtn"));
						// pages.Wait().wait_until_loader_is_invisible(30);
						// --------------------------
						pages.Utill().click_element("//td[text()='" + no + "']/../td/input");
						// td[text()='+"mat"+']/../td/input
						// w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("vcri_table"))));
						// .//*[@id='ctl00_ContentPlaceHolder1_grupdation']/tbody/tr[2]/td[6]
						// String refno = pages.Utill()
						// .get_text(".//*[@id='" + getlocator("vcri_table") + "']/tbody/tr[2]/td[6]");
						// if (refno.equals(no)) {
						// pages.Utill().click_element("ctl00_ContentPlaceHolder1_grupdation_ctl02_btnShowAddress");
						// pages.Wait().wait_until_loader_is_invisible();
						w.until(ExpectedConditions.visibilityOf(pages.Utill().find(getlocator("vcAddr_Address"))));
						pages.Utill().input_text(getlocator("vcAddr_Address"), getvalue("vcAddr_Address"));
						pages.Utill().input_text(getlocator("vcAddr_Pincode"), getvalue("vcAddr_Pincode"));
						w.until(ExpectedConditions.presenceOfElementLocated(
								By.xpath("//*[text()='" + getvalue("vcAddr_Pincode") + "']")));
						pages.Utill().click_element("//*[text()='" + getvalue("vcAddr_Pincode") + "']");
						pages.Utill().select_by_label(getlocator("vcAddr_ResiType"), getvalue("vcAddr_ResiType"));
						pages.Utill().input_text(getlocator("vcAddr_Landmark"), getvalue("vcAddr_Landmark"));
						pages.Utill().input_text(getlocator("vcAddr_FromDt"), getvalue("vcAddr_FromDt"));
						pages.Utill().input_text(getlocator("vcAddr_ToDt"), getvalue("vcAddr_ToDt"));
						pages.Utill().input_text(getlocator("vcAddr_StayPeriod"), getvalue("vcAddr_StayPeriod"));
						pages.Utill().input_text(getlocator("vcAddr_PoliceSt"), getvalue("vcAddr_PoliceSt"));
						pages.Utill().input_text(getlocator("vcCrim_PoliceAddr"), getvalue("vcCrim_PoliceAddr"));
						pages.Utill().input_text(getlocator("vcCrim_RespName"), getvalue("vcCrim_RespName"));
						pages.Utill().input_text(getlocator("vcCrim_Designation"), getvalue("vcCrim_Designation"));
						pages.Utill().input_text(getlocator("vcCrim_ContNo"), getvalue("vcCrim_ContNo"));
						pages.Utill().input_text(getlocator("vcCrim_VisitDt"), pages.Utill().getcurrentdate());

						pages.Utill().select_by_label(getlocator("vcCrim_Result"), getvalue("vcCrim_Result"));
						pages.Utill().select_by_label(getlocator("vcCrim_YearsCovered"),
								getvalue("vcCrim_YearsCovered"));
						pages.Utill().select_by_label(getlocator("vcCrim_Typeofrevert"),
								getvalue("vcCrim_Typeofrevert"));
						pages.Utill().input_text(getlocator("vcCrim_Remarks"), getvalue("vcCrim_Remarks"));
						pages.Utill().click_element(getlocator("vcrim_date"));
						w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("vcrim_today"))));
						pages.Utill().click_element(getlocator("vcrim_today"));
						Thread.sleep(2000);
						pages.Utill().click_element(getlocator("vcrim_green"));
						Thread.sleep(1000);
						pages.Utill().click_element(getlocator("vcrim_vresult"));
						System.out.println("Verification result");
						Thread.sleep(2000);
						pages.Utill().handle_Alert();
						// } else {
						// System.out.println(no + " ref no not found in search");
						// logger.log(Status.FAIL, no + " ref no not found in search");
						// String temp = Utill.getScreenshot(driver);
						// logger.fail("ref no not found in search",
						// MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
						// }
					} else {
						logger.log(Status.FAIL, "no value in Criminal Updation dashboard");
						System.out.println("no value in Criminal Updation dashboard");
						String temp = Utill.getScreenshot(driver);
						logger.fail("no value in Criminal Updation dashboard",
								MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
					}
				}

			} else {
				// logger.fail("no value in Criminal allocation dashboard");
				logger.log(Status.FAIL, "no value in Criminal allocation dashboard");
				System.out.println("no value in Criminal allocation dashboard");
				String temp = Utill.getScreenshot(driver);
				logger.fail("", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			logger.log(Status.FAIL, e.getMessage().toString());
			String temp = Utill.getScreenshot(driver);
			logger.fail(driver.getTitle() + e.getMessage().toString(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			pages.Utill().GoTo(getvalue("url") + "/Matrix/UserHome.aspx");
		}

	}

	public void DB(String no) throws Exception {
		try {
			try {
				pages.CaseRegistration().navigateTo("Dashboard", "DB Check TM");
			} catch (Exception e) {
				logger.log(Status.WARNING, e.getMessage().toString());
				pages.Utill().GoTo(getvalue("url") + "/Matrix/DBTMHomePage.aspx");
			}
			String tat = pages.Utill().get_text("ctl00_ContentPlaceHolder1_btnOutStanding");
			if (!(tat.equals("0"))) {
				pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnOutStanding");
				pages.Wait().wait_until_loader_is_invisible();
				pages.Utill().click_element("ctl00_ContentPlaceHolder1_lbtnNormal");
				pages.Wait().wait_until_loader_is_invisible();
				w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("vdb_table"))));
				List<WebElement> tabledata = driver
						.findElements(By.xpath(".//*[@id='" + getlocator("vdb_table") + "']/tbody/tr/td[4]"));
				logger.log(Status.PASS, "getting web element list '" + no + "'");
				int b = 0;
				for (int i = 0; i < tabledata.size(); i++) {
					String refno = tabledata.get(i).getText();
					if (refno.equalsIgnoreCase(no)) {
						int index = i + 2;
						w.until(ExpectedConditions.visibilityOf(pages.Utill().find(
								".//*[@id='" + getlocator("vdb_table") + "']/tbody/tr[" + index + "]/td[1]/input")));
						pages.Utill().click_element(
								".//*[@id='" + getlocator("vdb_table") + "']/tbody/tr[" + index + "]/td[1]/input");
						b++;
						break;
						// .//*[@id='ctl00_ContentPlaceHolder1_grdCandidate']/tbody/tr[2]/td[4]
						// .//*[@id='ctl00_ContentPlaceHolder1_grdCandidate']/tbody/tr[2]/td[1]/input
					}
				}
				if (b == 0) {
					System.out.println(no + "not found in grid");
					String temp = Utill.getScreenshot(driver);
					logger.fail(driver.getTitle() + no + "not found in grid",
							MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
				} else {
					// do db data entry
					System.out.println("do db data entry");
//					w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(getlocator("drplevel1"))));
//					pages.Utill().select_by_label(getlocator("drplevel1"), getvalue("drplevel1"));
//					pages.Utill().select_by_label(getlocator("drplevel2"), getvalue("drplevel2"));
//					pages.Utill().select_by_label(getlocator("drplevel3"), getvalue("drplevel3"));
//					pages.Utill().select_by_label(getlocator("drplevel4"), getvalue("drplevel4"));
//					pages.Utill().select_by_label(getlocator("drplevel5"), getvalue("drplevel5"));
//					pages.Utill().click_element(getlocator("vdb_add"));
//					pages.Wait().wait_until_loader_is_invisible();
//					String ss = pages.Utill().clickAlertbox();
//					System.out.println(ss);
//					logger.log(Status.PASS, ss);
//					pages.Utill().input_text(getlocator("vdb_ReportRunDate"), pages.Utill().getcurrentdate());
//					pages.Utill().input_text(getlocator("vdb_DatabaseLastUpDate"), pages.Utill().getcurrentdate());
					pages.Utill().input_text(getlocator("vdb_comments"), getvalue("vdb_comments"));
					pages.Utill().input_text(getlocator("vdb_remarks"), getvalue("vdb_remarks"));
					pages.Utill().click_element(getlocator("vdb_verify"));
					Thread.sleep(1000);
					pages.Utill().click_element(getlocator("vdb_vresult"));
					System.out.println("Verification result");
					Thread.sleep(2000);
//					w.until(ExpectedConditions.presenceOfElementLocated(By.id("cOK")));
//					pages.Utill().click_element("cOK");
					//pages.Utill().handle_Alert();
				}

			} else {
				System.out.println("no record in Outstanding within TAT");
				String temp = Utill.getScreenshot(driver);
				logger.fail(driver.getTitle() + tat + "no record in Outstanding within TAT",
						MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			String temp = Utill.getScreenshot(driver);
			logger.fail(e.getMessage().toString(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
	}

	public void Drug(String no) throws Exception {
		try {
			try {
				pages.CaseRegistration().navigateTo("Dashboard", "Drug Check TM");
			} catch (Exception e) {
				logger.log(Status.WARNING, e.getMessage().toString());
				pages.Utill().GoTo(getvalue("url") + "/Matrix/DrugcheckTMHomePage.aspx");
			}
			String alltat = pages.Utill().get_text("ctl00_ContentPlaceHolder1_btnOutStanding");
			if (!(alltat.equalsIgnoreCase("0"))) {
				pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnOutStanding");
				pages.Wait().wait_until_loader_is_invisible();
				pages.Utill().click_element("ctl00_ContentPlaceHolder1_lbtnNormal");
				pages.Wait().wait_until_loader_is_invisible();
				w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("vdrug_table"))));
				// .//*[@id='ctl00_ContentPlaceHolder1_grdCandidate_1']/tbody/tr/td[5]/a
				// .//*[@id='ctl00_ContentPlaceHolder1_grdCandidate_1']/tbody/tr[2]/td[1]/input[4]
				List<WebElement> alltabledata = driver
						.findElements(By.xpath("//*[@id='" + getlocator("vdrug_table") + "']/tbody/tr/td[5]/a"));
				int b = 0;
				for (int i = 0; i < alltabledata.size(); i++) {

					String refno = alltabledata.get(i).getText();
					if (refno.equalsIgnoreCase(no)) {
						int index = i + 2;
						pages.Utill().click_element(
								"//*[@id='" + getlocator("vdrug_table") + "']/tbody/tr[" + index + "]/td[1]/input[4]");
						b++;
						break;
					}

				}
				if (b == 0) {
					System.out.println(no + " is not found in allocation dashboard grid");
					logger.log(Status.FAIL, no + " is not found in allocation dashboard grid");
					String temp = Utill.getScreenshot(driver);
					logger.fail(no + " is not found in allocation dashboard grid",
							MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
				} else {
					pages.Utill().click_element(getlocator("v_edu_vr"));
					pages.Wait().wait_until_loader_is_invisible();
					w.until(ExpectedConditions
							.presenceOfAllElementsLocatedBy(By.id(getlocator("vdrugall_InitiateTo"))));
					pages.Utill().select_by_label(getlocator("vdrugall_InitiateTo"), getvalue("vdrugall_InitiateTo"));
					pages.Utill().click_element(getlocator("vdrugall_InitiateVR"));
					pages.Wait().wait_until_loader_is_invisible();
					pages.Utill().clickAlertbox();
					String uptat = pages.Utill().get_text("ctl00_ContentPlaceHolder1_btnOutStanding_1");
					if (!(uptat.equalsIgnoreCase("0"))) {
						pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnOutStanding_1");
						pages.Wait().wait_until_loader_is_invisible();
						pages.Utill().click_element("ctl00_ContentPlaceHolder1_lbtnNormalUpt");
						pages.Wait().wait_until_loader_is_invisible();
						w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("vdb_table"))));
						List<WebElement> uptabledata = driver
								.findElements(By.xpath(".//*[@id='" + getlocator("vdb_table") + "']/tbody/tr/td[4]"));
						// .//*[@id='ctl00_ContentPlaceHolder1_grdCandidate']/tbody/tr/td[4]
						// .//*[@id='ctl00_ContentPlaceHolder1_grdCandidate']/tbody/tr/td[1]/input
						int c = 0;
						for (int i = 0; i < uptabledata.size(); i++) {
							int index = i + 2;
							String refno = uptabledata.get(i).getText();
							if (refno.equalsIgnoreCase(no)) {
								pages.Utill().click_element(".//*[@id='" + getlocator("vdb_table") + "']/tbody/tr["
										+ index + "]/td[1]/input");
								c++;
								break;
							}

						}
						if (c == 0) {
							System.out.println(no + " is not found in updation dashboard grid");
							logger.log(Status.FAIL, no + " is not found in updation dashboard grid");
							String temp = Utill.getScreenshot(driver);
							logger.fail(no + " is not found in updation dashboard grid",
									MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
						} else {
							// do
							pages.Wait().wait_until_loader_is_invisible();
							w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("vdrug_panel"))));
							pages.Utill().select_by_label(getlocator("vdrug_panel"), getvalue("vdrug_panel"));
							pages.Wait().wait_until_loader_is_invisible();
							w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
									By.id("ctl00_ContentPlaceHolder1_grdDrugChkverifi_ctl02_ddlflow")));
							pages.Utill().select_by_label("ctl00_ContentPlaceHolder1_grdDrugChkverifi_ctl02_ddlflow",
									"Detected");
							pages.Utill().input_text(getlocator("vdrug_fitness"), getvalue("vdrug_fitness"));
							pages.Utill().input_text(getlocator("vdrug_con"), getvalue("vdrug_con"));
							pages.Utill().input_text(getlocator("vdrug_comm"), getvalue("vdrug_comm"));
							// pages.Utill().click_element(getlocator("vdrug_date1"));
							// w.until(ExpectedConditions.visibilityOf(pages.Utill().find(getlocator("vdrug_today1"))));
							// pages.Utill().click_element(getlocator("vdrug_today1"));
							// pages.Utill().click_element(getlocator("vdrug_date2"));
							// w.until(ExpectedConditions.visibilityOf(pages.Utill().find(getlocator("vdrug_today2"))));
							// pages.Utill().click_element(getlocator("vdrug_today2"));
							pages.Utill().input_text(getlocator("vdrug_remarks"), getvalue("vdrug_remarks"));
							pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnAdd");
							// pages.Wait().wait_until_alert_isvisible();
							Thread.sleep(2000);
							pages.Utill().clickAlertbox();
							pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnVerify");
							Thread.sleep(1000);
							pages.Utill().click_element(getlocator("vdrug_vresult"));
							System.out.println("Verification result");
							Thread.sleep(2000);
							pages.Utill().handle_Alert();
						}
					} else {
						System.out.println();
						System.out.println("no data in outstanding with in tat of updation dashboard");
						String temp = Utill.getScreenshot(driver);
						logger.fail("no data in outstanding with in tat of updation dashboard",
								MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
					}
				}

			} else {
				System.out.println("no data in outstanding with in tat");
				String temp = Utill.getScreenshot(driver);
				logger.fail("no data in outstanding with in tat",
						MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			String temp = Utill.getScreenshot(driver);
			logger.fail(e.getMessage().toString(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}

	}

	public void ID(String no) throws Exception {
		try {
			try {
				pages.CaseRegistration().navigateTo("Dashboard", "ID Check TM");
			} catch (Exception e) {
				logger.log(Status.WARNING, e.getMessage().toString());
				pages.Utill().GoTo(getvalue("url") + "/Matrix/IDcheckTMHomepage.aspx");
			}
			String tat = pages.Utill().get_text("ctl00_ContentPlaceHolder1_btnOutStanding");
			if (!(tat.equalsIgnoreCase("0"))) {
				pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnOutStanding");
				pages.Wait().wait_until_loader_is_invisible();
				pages.Utill().click_element("ctl00_ContentPlaceHolder1_lbtnNormal");
				pages.Wait().wait_until_loader_is_invisible();
				w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(getlocator("vdb_table"))));
				int b = 0;
				List<WebElement> tabledata = driver
						.findElements(By.xpath(".//*[@id='" + getlocator("vdb_table") + "']/tbody/tr/td[4]"));
				for (int i = 0; i < tabledata.size(); i++) {
					String refno = tabledata.get(i).getText();
					if (refno.equalsIgnoreCase(no)) {
						int index = i + 2;
						pages.Utill().click_element(
								".//*[@id='" + getlocator("vdb_table") + "']/tbody/tr[" + index + "]/td[1]/input");
						b++;
						break;
					}
				}
				if (b == 0) {
					System.out.println(no + "  not found in table");
					String temp = Utill.getScreenshot(driver);
					logger.fail(no + "  not found in table",
							MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
				} else {
					// do work
					WebDriverWait wait = new WebDriverWait(driver, 60);
					wait.until(ExpectedConditions
							.presenceOfElementLocated(By.id("ctl00_ContentPlaceHolder1_lblCheckName")));
					String id = pages.Utill().get_text("ctl00_ContentPlaceHolder1_lblCheckName");
					switch (id) {
					case "Right To Work Check":
						System.out.println("Right To Work Check");
						logger.log(Status.INFO, "Right To Work Check");
						pages.Utill().select_by_label(getlocator("vid_color"), getvalue("vid_color"));
						pages.Utill().input_text(getlocator("vid_comments"), getvalue("vid_comments"));
						pages.Utill().input_text(getlocator("vid_remarks"), getvalue("vid_remarks"));
						break;
					case "Aadhar ID Check":
						System.out.println("Aadhar ID Check");
						logger.log(Status.INFO, "Aadhar ID Check");
						pages.Utill().input_text(getlocator("vid_value"), getvalue("vid_value"));
						pages.Utill().input_text(getlocator("vid_no"), getvalue("vid_no"));
						pages.Utill().select_by_label(getlocator("vid_color"), getvalue("vid_color"));
						pages.Utill().input_text(getlocator("vid_comments"), getvalue("vid_comments"));
						pages.Utill().input_text(getlocator("vid_remarks"), getvalue("vid_remarks"));

						break;
					default:
						System.out.println("no matching found");
					}
					pages.Utill().click_element(getlocator("vid_verify"));
					Thread.sleep(1000);
					pages.Utill().click_element(getlocator("vid_vresult"));
					System.out.println("Verification result");
					Thread.sleep(2000);
					pages.Utill().handle_Alert();
					Thread.sleep(1000);
					pages.Utill().handle_Alert();
				}
				// .//*[@id='ctl00_ContentPlaceHolder1_grdCandidate']/tbody/tr[2]/td[4]
				// .//*[@id='ctl00_ContentPlaceHolder1_grdCandidate']/tbody/tr[2]/td[1]/input

			} else {
				System.out.println("no data in outstanding with in tat");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			String temp = Utill.getScreenshot(driver);
			logger.fail(e.getMessage().toString(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
	}

	public void Court(String no) throws Exception {
		try {
			try {
				pages.CaseRegistration().navigateTo("Dashboard", "CourtTm");
			} catch (Exception e) {
				logger.log(Status.WARNING, e.getMessage().toString());
				pages.Utill().GoTo(getvalue("url") + "/Matrix/CourtCheckTMHomepage.aspx");
			}
			String alltat = pages.Utill().get_text("ctl00_ContentPlaceHolder1_btnOutStanding");
			if (!(alltat.equalsIgnoreCase("0"))) {
				pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnOutStanding");
				pages.Wait().wait_until_loader_is_invisible();
				pages.Utill().click_element("ctl00_ContentPlaceHolder1_lbtnNormal");
				pages.Wait().wait_until_loader_is_invisible();
				w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("vcourt_table"))));
				// .//*[@id='ctl00_ContentPlaceHolder1_grdCandidate_1']/tbody/tr/td[5]/a
				// .//*[@id='ctl00_ContentPlaceHolder1_grdCandidate_1']/tbody/tr[2]/td[1]/input[4]
				List<WebElement> alltabledata = driver
						.findElements(By.xpath("//*[@id='" + getlocator("vcourt_table") + "']/tbody/tr/td[5]/a"));
				int b = 0;
				for (int i = 0; i < alltabledata.size(); i++) {

					String refno = alltabledata.get(i).getText();
					if (refno.equalsIgnoreCase(no)) {
						int index = i + 2;
						pages.Utill().click_element(
								"//*[@id='" + getlocator("vcourt_table") + "']/tbody/tr[" + index + "]/td[1]/input[8]");
						b++;
						break;
					}

				}
				if (b == 0) {
					System.out.println(no + " is not found in allocation dashboard grid");
					logger.log(Status.FAIL, no + " is not found in allocation dashboard grid");
					String temp = Utill.getScreenshot(driver);
					logger.fail(no + " is not found in allocation dashboard grid",
							MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
				} else {
					pages.Utill().click_element(getlocator("v_edu_vr"));
					pages.Wait().wait_until_loader_is_invisible();
					w.until(ExpectedConditions
							.presenceOfAllElementsLocatedBy(By.id(getlocator("vdrugall_InitiateTo"))));
					pages.Utill().select_by_label(getlocator("vdrugall_InitiateTo"), "Demo Vendor");
					pages.Utill().click_element(getlocator("vdrugall_InitiateVR"));
					pages.Wait().wait_until_loader_is_invisible();
					pages.Utill().clickAlertbox();
					String uptat = pages.Utill().get_text("ctl00_ContentPlaceHolder1_btnOutStandingUpt");
					if (!(uptat.equalsIgnoreCase("0"))) {
						pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnOutStandingUpt");
						pages.Wait().wait_until_loader_is_invisible();
						pages.Utill().click_element("ctl00_ContentPlaceHolder1_lbtnNormalUpt");
						pages.Wait().wait_until_loader_is_invisible();
						w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("vcourtup_table"))));
						List<WebElement> uptabledata = driver.findElements(
								By.xpath(".//*[@id='" + getlocator("vcourtup_table") + "']/tbody/tr/td[4]"));
						// .//*[@id='ctl00_ContentPlaceHolder1_grdCandidate']/tbody/tr/td[4]
						// .//*[@id='ctl00_ContentPlaceHolder1_grdCandidate']/tbody/tr/td[1]/input
						int c = 0;
						for (int i = 0; i < uptabledata.size(); i++) {
							int index = i + 2;
							String refno = uptabledata.get(i).getText();
							if (refno.equalsIgnoreCase(no)) {
								pages.Utill().click_element(".//*[@id='" + getlocator("vcourtup_table") + "']/tbody/tr["
										+ index + "]/td[1]/input");
								c++;
								break;
							}

						}
						if (c == 0) {
							System.out.println(no + " is not found in updation dashboard grid");
							logger.log(Status.FAIL, no + " is not found in updation dashboard grid");
							String temp = Utill.getScreenshot(driver);
							logger.fail(no + " is not found in updation dashboard grid",
									MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
						} else {
							// do
							pages.Utill().search_and_select(getlocator("vcourt1"), getvalue("vcourt1"));
							pages.Wait().wait_until_loader_is_invisible();
							pages.Utill().search_and_select(getlocator("vcourt2"), getvalue("vcourt2"));
							pages.Wait().wait_until_loader_is_invisible();
							pages.Utill().search_and_select(getlocator("vcourt3"), getvalue("vcourt3"));
							pages.Wait().wait_until_loader_is_invisible();
							pages.Utill().search_and_select(getlocator("vcourt4"), getvalue("vcourt4"));
							pages.Wait().wait_until_loader_is_invisible();
							pages.Utill().input_text(getlocator("vcourt_comm"), getvalue("vcourt_comm"));
							pages.Utill().input_text(getlocator("vcourt_remarks"), getvalue("vcourt_remarks"));
							pages.Utill().click_element(getlocator("vcourt_save"));
							pages.Wait().wait_until_loader_is_invisible();
							Thread.sleep(1000);
							pages.Utill().click_element(getlocator("vcourt_vresult"));
							System.out.println("Verification result");
							Thread.sleep(2000);
							pages.Utill().handle_Alert();

						}
					} else {
						System.out.println();
						System.out.println("no data in outstanding with in tat of updation dashboard");
						String temp = Utill.getScreenshot(driver);
						logger.fail("no data in outstanding with in tat of updation dashboard",
								MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
					}
				}

			} else {
				System.out.println("no data in outstanding with in tat");
				String temp = Utill.getScreenshot(driver);
				logger.fail("no data in outstanding with in tat",
						MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			String temp = Utill.getScreenshot(driver);
			logger.fail(e.getMessage().toString(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
	}

	public void Facis(String no) throws Exception {
		try {
			try {
				pages.CaseRegistration().navigateTo("Dashboard", "FACIS TM");
			} catch (Exception e) {
				logger.log(Status.WARNING, e.getMessage().toString());
				pages.Utill().GoTo(getvalue("url") + "/Matrix/FACISTMHomePage.aspx");
			}
			String tat = pages.Utill().get_text(getlocator("others_withintat"));
			if (!(tat.equalsIgnoreCase("0"))) {
				pages.Utill().click_element(getlocator("others_withintat"));
				pages.Wait().wait_until_loader_is_invisible();
				pages.Utill().click_element("ctl00_ContentPlaceHolder1_lbtnNormal");
				pages.Wait().wait_until_loader_is_invisible();
				w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("others_refno"))));
				pages.Utill().input_text(getlocator("others_refno"), no);
				pages.Utill().click_element(getlocator("others_search"));
				pages.Wait().wait_until_loader_is_invisible();
				// .//*[@id='ctl00_ContentPlaceHolder1_grdOutstanding']/tbody/tr[2]/td[5]
				// .//*[@id='ctl00_ContentPlaceHolder1_grdOutstanding']/tbody/tr[2]/td[1]/input
				w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("vemp_table"))));
				String refno = pages.Utill().get_text("//*[@id='" + getlocator("vemp_table") + "']/tbody/tr[2]/td[5]");
				if (refno.equals(no)) {
					pages.Utill().click_element("//*[@id='" + getlocator("vemp_table") + "']/tbody/tr[2]/td[1]/input");

					pages.Wait().wait_until_loader_is_invisible();
					w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("vfacis_status"))));
					pages.Utill().select_by_label(getlocator("vfacis_status"), getvalue("vfacis_status"));
					pages.Wait().wait_until_loader_is_invisible();
					pages.Utill().input_text(getlocator("vfacis_comments"), getvalue("vfacis_comments"));
					pages.Utill().input_text(getlocator("vfacis_remarks"), getvalue("vfacis_remarks"));
					pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnverify");
					Thread.sleep(1000);
					pages.Utill().click_element(getlocator("vfacis_vresult"));
					System.out.println("Verification result");
					Thread.sleep(2000);
					pages.Utill().handle_Alert();
					Thread.sleep(1000);
					pages.Utill().handle_Alert();
				}

				else {
					System.out.println(no + "  not found in table, we get as :" + refno);
					String temp = Utill.getScreenshot(driver);
					logger.fail(no + "  not found in table, we get as :" + refno,
							MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
				}
			} else {
				System.out.println("no data in outstanding with in tat");
				String temp = Utill.getScreenshot(driver);
				logger.fail("no data in outstanding with in tat",
						MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			}
		} catch (Exception e) {

			if (e.getMessage().toString().contains(
					"waiting for presence of element located by: By.id: ctl00_ContentPlaceHolder1_grdOutstanding")) {
				System.out.println(no + "  not found in table");
				String temp = Utill.getScreenshot(driver);
				logger.fail(no + "  not found in table", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			} else {
				System.out.println(e.getMessage().toString());
				String temp = Utill.getScreenshot(driver);
				logger.fail(e.getMessage().toString(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			}
		}

	}

	public void IT(String no) throws Exception {
		try {
			try {
				pages.CaseRegistration().navigateTo("Dashboard", "IT TM");
			} catch (Exception e) {
				logger.log(Status.WARNING, e.getMessage().toString());
				pages.Utill().GoTo(getvalue("url") + "/Matrix/ITTMHomePage.aspx");
			}
			String tat = pages.Utill().get_text(getlocator("others_beyondtat"));
			if (!(tat.equalsIgnoreCase("0"))) {
				pages.Utill().click_element(getlocator("others_beyondtat"));
				pages.Wait().wait_until_loader_is_invisible();
				pages.Utill().click_element("ctl00_ContentPlaceHolder1_lbtnNormal");
				pages.Wait().wait_until_loader_is_invisible();
				w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("others_refno"))));
				pages.Utill().input_text(getlocator("others_refno"), no);
				pages.Utill().click_element(getlocator("others_search"));
				pages.Wait().wait_until_loader_is_invisible();
				// .//*[@id='ctl00_ContentPlaceHolder1_grdOutstanding']/tbody/tr[2]/td[5]
				// .//*[@id='ctl00_ContentPlaceHolder1_grdOutstanding']/tbody/tr[2]/td[1]/input
				w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("vemp_table"))));
				String refno = pages.Utill().get_text("//*[@id='" + getlocator("vemp_table") + "']/tbody/tr[2]/td[5]");
				if (refno.equals(no)) {
					pages.Utill().click_element("//*[@id='" + getlocator("vemp_table") + "']/tbody/tr[2]/td[1]/input");

					pages.Wait().wait_until_loader_is_invisible();
					w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("others_checktype"))));
					pages.Utill().select_by_label(getlocator("others_status"), getvalue("others_status"));
					pages.Wait().wait_until_loader_is_invisible();
					pages.Utill().input_text(getlocator("others_comments"), getvalue("others_comments"));
					pages.Utill().input_text(getlocator("others_remarks"), getvalue("others_remarks"));
					pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnverify");
					Thread.sleep(1000);
					pages.Utill().click_element(getlocator("it_vresult"));
					System.out.println("Verification result");
					Thread.sleep(2000);
					pages.Utill().handle_Alert();
					Thread.sleep(1000);
					pages.Utill().handle_Alert();
				}

				else {
					System.out.println(no + "  not found in table, we get as :" + refno);
					String temp = Utill.getScreenshot(driver);
					logger.fail(no + "  not found in table, we get as :" + refno,
							MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
				}
			} else {
				System.out.println("no data in outstanding with in tat");
				String temp = Utill.getScreenshot(driver);
				logger.fail("no data in outstanding with in tat",
						MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			}
		} catch (Exception e) {
			if (e.getMessage().toString().contains(
					"waiting for presence of element located by: By.id: ctl00_ContentPlaceHolder1_grdOutstanding")) {
				System.out.println(no + "  not found in table");
				String temp = Utill.getScreenshot(driver);
				logger.fail(no + "  not found in table", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			} else {
				System.out.println(e.getMessage().toString());
				String temp = Utill.getScreenshot(driver);
				logger.fail(e.getMessage().toString(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			}
		}

	}

	public void BV(String no) throws Exception {
		try {
			try {
				pages.CaseRegistration().navigateTo("Dashboard", "BV TM");
			} catch (Exception e) {
				logger.log(Status.WARNING, e.getMessage().toString());
				pages.Utill().GoTo(getvalue("url") + "/Matrix/BVTMHomePage.aspx");
			}
			String tat = pages.Utill().get_text(getlocator("others_beyondtat"));
			if (!(tat.equalsIgnoreCase("0"))) {
				pages.Utill().click_element(getlocator("others_beyondtat"));
				pages.Wait().wait_until_loader_is_invisible();
				pages.Utill().click_element("ctl00_ContentPlaceHolder1_lbtnNormal");
				pages.Wait().wait_until_loader_is_invisible();
				w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("others_refno"))));
				pages.Utill().input_text(getlocator("others_refno"), no);
				pages.Utill().click_element(getlocator("others_search"));
				pages.Wait().wait_until_loader_is_invisible();
				// .//*[@id='ctl00_ContentPlaceHolder1_grdOutstanding']/tbody/tr[2]/td[5]
				// .//*[@id='ctl00_ContentPlaceHolder1_grdOutstanding']/tbody/tr[2]/td[1]/input
				w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("vemp_table"))));
				String refno = pages.Utill().get_text("//*[@id='" + getlocator("vemp_table") + "']/tbody/tr[2]/td[4]");
				if (refno.equals(no)) {
					pages.Utill().click_element("//*[@id='" + getlocator("vemp_table") + "']/tbody/tr[2]/td[1]/input");

					pages.Wait().wait_until_loader_is_invisible();
					w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("others_checktype"))));
					pages.Utill().select_by_label(getlocator("others_status"), getvalue("others_status"));
					pages.Wait().wait_until_loader_is_invisible();
					pages.Utill().input_text(getlocator("others_comments"), getvalue("others_comments"));
					pages.Utill().input_text(getlocator("others_remarks"), getvalue("others_remarks"));
					pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnverify");
					Thread.sleep(1000);
					pages.Utill().click_element(getlocator("bv_vresult"));
					System.out.println("Verification result");
					Thread.sleep(2000);
					pages.Utill().handle_Alert();
					Thread.sleep(1000);
					pages.Utill().handle_Alert();

				}

				else {
					System.out.println(no + "  not found in table, we get as :" + refno);
					String temp = Utill.getScreenshot(driver);
					logger.fail(no + "  not found in table, we get as :" + refno,
							MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
				}
			} else {
				System.out.println("no data in outstanding with in tat");
				String temp = Utill.getScreenshot(driver);
				logger.fail("no data in outstanding with in tat",
						MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			}
		} catch (Exception e) {
			if (e.getMessage().toString().contains(
					"waiting for presence of element located by: By.id: ctl00_ContentPlaceHolder1_grdOutstanding")) {
				System.out.println(no + "  not found in table");
				String temp = Utill.getScreenshot(driver);
				logger.fail(no + "  not found in table", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			} else {
				System.out.println(e.getMessage().toString());
				String temp = Utill.getScreenshot(driver);
				logger.fail(e.getMessage().toString(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			}
		}

	}

	public void Credit(String no) throws Exception {
		try {
			try {
				pages.CaseRegistration().navigateTo("Dashboard", "Credit TM");
			} catch (Exception e) {
				logger.log(Status.WARNING, e.getMessage().toString());
				pages.Utill().GoTo(getvalue("url") + "/Matrix/CIBILTMHomePage.aspx");
			}
			String tat = pages.Utill().get_text(getlocator("others_beyondtat"));
			if (!(tat.equalsIgnoreCase("0"))) {
				pages.Utill().click_element(getlocator("others_beyondtat"));
				pages.Wait().wait_until_loader_is_invisible();
				pages.Utill().click_element("ctl00_ContentPlaceHolder1_lbtnNormal");
				pages.Wait().wait_until_loader_is_invisible();
				w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("others_refno"))));
				pages.Utill().input_text(getlocator("others_refno"), no);
				pages.Utill().click_element(getlocator("others_search"));
				pages.Wait().wait_until_loader_is_invisible();
				// .//*[@id='ctl00_ContentPlaceHolder1_grdOutstanding']/tbody/tr[2]/td[5]
				// .//*[@id='ctl00_ContentPlaceHolder1_grdOutstanding']/tbody/tr[2]/td[1]/input
				w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("vemp_table"))));
				String refno = pages.Utill().get_text("//*[@id='" + getlocator("vemp_table") + "']/tbody/tr[2]/td[5]");
				if (refno.equals(no)) {
					pages.Utill().click_element("//*[@id='" + getlocator("vemp_table") + "']/tbody/tr[2]/td[1]/input");

					pages.Wait().wait_until_loader_is_invisible();
					w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("others_checktype"))));

					pages.Utill().input_text(getlocator("credit_Designation"), getvalue("credit_Designation"));
					pages.Utill().input_text(getlocator("credit_ContactNo"), getvalue("credit_ContactNo"));
					pages.Utill().input_text(getlocator("credit_TransunionScore"), getvalue("credit_TransunionScore"));
					pages.Utill().input_text(getlocator("credit_VerifierName"), getvalue("credit_VerifierName"));
					pages.Utill().input_text(getlocator("credit_EmailID"), getvalue("credit_EmailID"));
					pages.Utill().input_text(getlocator("credit_DateofSearch"), pages.Utill().getcurrentdate());

					pages.Utill().select_by_label(getlocator("others_status"), "Positive");
					pages.Wait().wait_until_loader_is_invisible();
					pages.Utill().input_text(getlocator("others_comments"), getvalue("others_comments"));
					pages.Utill().input_text(getlocator("others_remarks"), getvalue("others_remarks"));
					pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnverify");
					Thread.sleep(1000);
					pages.Utill().click_element(getlocator("cbil_vresult"));
					System.out.println("Verification result");
					Thread.sleep(2000);
					pages.Utill().handle_Alert();
					Thread.sleep(1000);
					pages.Utill().handle_Alert();

				}

				else {
					System.out.println(no + "  not found in table, we get as :" + refno);
					String temp = Utill.getScreenshot(driver);
					logger.fail(no + "  not found in table, we get as :" + refno,
							MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
				}
			} else {
				System.out.println("no data in outstanding with in tat");
				String temp = Utill.getScreenshot(driver);
				logger.fail("no data in outstanding with in tat",
						MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			}
		} catch (Exception e) {
			if (e.getMessage().toString().contains(
					"waiting for presence of element located by: By.id: ctl00_ContentPlaceHolder1_grdOutstanding")) {
				System.out.println(no + "  not found in table");
				String temp = Utill.getScreenshot(driver);
				logger.fail(no + "  not found in table", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			} else {
				System.out.println(e.getMessage().toString());
				String temp = Utill.getScreenshot(driver);
				logger.fail(e.getMessage().toString(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			}
		}
	}

	public void PF(String no) throws Exception {
		try {
			try {
				pages.CaseRegistration().navigateTo("Dashboard", "PF TM");
			} catch (Exception e) {
				logger.log(Status.WARNING, e.getMessage().toString());
				pages.Utill().GoTo(getvalue("url") + "/Matrix/PFTMHomePage.aspx");
			}
			String tat = pages.Utill().get_text(getlocator("others_beyondtat"));
			if (!(tat.equalsIgnoreCase("0"))) {
				pages.Utill().click_element(getlocator("others_beyondtat"));
				pages.Wait().wait_until_loader_is_invisible();
				pages.Utill().click_element("ctl00_ContentPlaceHolder1_lbtnNormal");
				pages.Wait().wait_until_loader_is_invisible();
				w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("others_refno"))));
				pages.Utill().input_text(getlocator("others_refno"), no);
				pages.Utill().click_element(getlocator("others_search"));
				pages.Wait().wait_until_loader_is_invisible();
				// .//*[@id='ctl00_ContentPlaceHolder1_grdOutstanding']/tbody/tr[2]/td[5]
				// .//*[@id='ctl00_ContentPlaceHolder1_grdOutstanding']/tbody/tr[2]/td[1]/input
				w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("vemp_table"))));
				String refno = pages.Utill().get_text("//*[@id='" + getlocator("vemp_table") + "']/tbody/tr[2]/td[4]");
				if (refno.equals(no)) {
					pages.Utill().click_element("//*[@id='" + getlocator("vemp_table") + "']/tbody/tr[2]/td[1]/input");

					pages.Wait().wait_until_loader_is_invisible();
					w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("others_checktype"))));
					pages.Utill().input_text(getlocator("pf_CompilerName"), getvalue("pf_CompilerName"));
					pages.Utill().select_by_label(getlocator("others_status"), getvalue("others_status"));
					pages.Wait().wait_until_loader_is_invisible();
					pages.Utill().input_text(getlocator("others_comments"), getvalue("others_comments"));
					pages.Utill().input_text(getlocator("others_remarks"), getvalue("others_remarks"));
					pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnverify");
					Thread.sleep(1000);
					pages.Utill().click_element(getlocator("pf_vresult"));
					System.out.println("Verification result");
					Thread.sleep(2000);
					pages.Utill().handle_Alert();
					Thread.sleep(1000);
					pages.Utill().handle_Alert();

				}

				else {
					System.out.println(no + "  not found in table, we get as :" + refno);
					String temp = Utill.getScreenshot(driver);
					logger.fail(no + "  not found in table, we get as :" + refno,
							MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
				}
			} else {
				System.out.println("no data in outstanding with in tat");
				String temp = Utill.getScreenshot(driver);
				logger.fail("no data in outstanding with in tat",
						MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			}
		} catch (Exception e) {
			if (e.getMessage().toString().contains(
					"waiting for presence of element located by: By.id: ctl00_ContentPlaceHolder1_grdOutstanding")) {
				System.out.println(no + "  not found in table");
				String temp = Utill.getScreenshot(driver);
				logger.fail(no + "  not found in table", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			} else {
				System.out.println(e.getMessage().toString());
				String temp = Utill.getScreenshot(driver);
				logger.fail(e.getMessage().toString(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			}
		}
	}
}
