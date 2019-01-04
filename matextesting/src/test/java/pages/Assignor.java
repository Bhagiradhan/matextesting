package pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import environment.BaseClass;
import environment.Utill;
import testCases.Pages;

public class Assignor {
	protected final WebDriver driver;
	protected final Pages pages;
	ExtentTest logger;

	public Assignor(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		pages = new Pages(driver, logger);
	}

	private String getlocator(String key) throws FileNotFoundException, IOException {
		Properties pr = BaseClass.getlocator();
		return pr.getProperty(key);
	}

	public void assign_Employment(String refno) throws Exception {
		WebElement ele = pages.Utill().find(getlocator("ass_Employment"));
		int b = 0;

		if (!(ele.getText().equals("0"))) {
			try {
				ele.click();
			} catch (WebDriverException e) {
				logger.log(Status.WARNING, e.toString());
				pages.Utill().clickAlertbox();
				ele.click();
			}
			logger.info("Clicking element '" + getlocator("ass_Employment") + "'");
			pages.Wait().wait_until_loader_is_invisible();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_ContentPlaceHolder1_lbtnNormal")));
			ele = pages.Utill().find("ctl00_ContentPlaceHolder1_lbtnNormal");

			if (!(ele.getText().equals("0"))) {
				System.out.println("passing not equal to zero");
				ele.click();
				logger.info("Clicking element 'ctl00_ContentPlaceHolder1_lbtnNormal'");
				pages.Wait().wait_until_loader_is_invisible();
				String table = ".//*[@id='" + getlocator("ass_emp_table") + "']/tbody/tr/td[3]/a";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(table)));
				List<WebElement> table_data = driver.findElements(By.xpath(table));

				for (int i = 0; i < table_data.size(); i++) {
					String id = table_data.get(i).getText();
					System.out.println("id is " + id);
					if (id.equals(refno)) {
						System.out.println("passing ref no condition" + i);
						int k = i + 2;
						pages.Utill().click_element(
								".//*[@id='" + getlocator("ass_emp_table") + "']/tbody/tr[" + k + "]/td[1]/input[1]");
						// pages.Utill().find("//*[text()='"+refno+"']/../../td[1]/input[1]").click();
						pages.Utill().select_by_label(getlocator("ass_emp_dd"), "demotl - Chennai");
						// pages.Utill().click_element("ctl00_ContentPlaceHolder1_ing"+checkname+"Assign");
						pages.Utill().click_element(getlocator("ass_emp_assign"));
						pages.Wait().wait_until_loader_is_invisible();
						pages.Utill().clickAlertbox();

						b++;

						break;

					} else {
						continue;
					}
				}
				if (b == 0) {
					logger.log(Status.WARNING, refno + " not found in table");
					String temp = Utill.getScreenshot(driver);
					logger.warning(refno + " not found in table",
							MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

				} else {
					logger.pass("Employment ");
					// return true;
				}
			} else {
				logger.fail("no value on normal for Emplyment");
				System.out.println("no value on normal for Emplyment");
				// return false;
			}

		} else {
			logger.log(Status.WARNING, "empty record for employment check");
			String temp = Utill.getScreenshot(driver);
			logger.warning(refno + "empty record for pf check",
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
	}

	public void assign_Education(String refno) throws Exception {
		WebElement ele = pages.Utill().find(getlocator("ass_Education"));
		int b = 0;
		String ss = "";
		if (!(ele.getText().equals("0"))) {
			try {
				ele.click();
			} catch (WebDriverException e) {
				logger.log(Status.WARNING, e.toString());
				pages.Utill().clickAlertbox();
				ele.click();
			}
			logger.info("Clicking element '" + getlocator("ass_Education") + "'");
			pages.Wait().wait_until_loader_is_invisible();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_ContentPlaceHolder1_lbtnNormal")));
			ele = pages.Utill().find("ctl00_ContentPlaceHolder1_lbtnNormal");

			if (!(ele.getText().equals("0"))) {
				System.out.println("passing not equal to zero");
				ele.click();
				logger.info("Clicking element 'ctl00_ContentPlaceHolder1_lbtnNormal'");
				pages.Wait().wait_until_loader_is_invisible();
				String table = ".//*[@id='" + getlocator("ass_edu_table") + "']/tbody/tr/td[3]/a";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(table)));
				List<WebElement> table_data = driver.findElements(By.xpath(table));

				for (int i = 0; i < table_data.size(); i++) {
					String id = table_data.get(i).getText();
					System.out.println("id is " + id);
					if (id.equals(refno)) {
						System.out.println("passing ref no condition" + i);
						int k = i + 2;
						pages.Utill().click_element(
								".//*[@id='" + getlocator("ass_edu_table") + "']/tbody/tr[" + k + "]/td[1]/input[1]");
						// pages.Utill().find("//*[text()='"+refno+"']/../../td[1]/input[1]").click();
						pages.Utill().select_by_label(getlocator("ass_edu_dd"), "demotl - Chennai");
						// pages.Utill().click_element("ctl00_ContentPlaceHolder1_ing"+checkname+"Assign");
						pages.Utill().click_element(getlocator("ass_edu_assign"));
						pages.Wait().wait_until_loader_is_invisible();
						ss = pages.Utill().clickAlertbox();
						// System.out.println(ss);
						b++;
						break;
					} else {
						continue;
					}
				}
				if (b == 0) {
					logger.log(Status.WARNING, refno + " not found in table");
					String temp = Utill.getScreenshot(driver);
					logger.warning(refno + " not found in table",
							MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

				} else {
					logger.pass("Education " + ss);
					// return true;
				}
			} else {
				logger.fail("no value on normal for education check");
				System.out.println("no value on normal for education check");
				// return false;
			}

		} else {
			logger.log(Status.WARNING, "empty record for education check");
			String temp = Utill.getScreenshot(driver);
			logger.warning(refno + "empty record for pf check",
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
	}

	public void assign_Reference(String refno) throws Exception {
		WebElement ele = pages.Utill().find(getlocator("ass_Reference"));
		int b = 0;
		String ss = "";
		if (!(ele.getText().equals("0"))) {
			try {
				ele.click();
			} catch (WebDriverException e) {
				logger.log(Status.WARNING, e.toString());
				pages.Utill().clickAlertbox();
				ele.click();
			}
			logger.info("Clicking element '" + getlocator("ass_Reference") + "'");
			pages.Wait().wait_until_loader_is_invisible();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_ContentPlaceHolder1_lbtnNormal")));
			ele = pages.Utill().find("ctl00_ContentPlaceHolder1_lbtnNormal");

			if (!(ele.getText().equals("0"))) {
				System.out.println("passing not equal to zero");
				ele.click();
				logger.info("Clicking element 'ctl00_ContentPlaceHolder1_lbtnNormal'");
				pages.Wait().wait_until_loader_is_invisible();
				String table = ".//*[@id='" + getlocator("ass_ref_table") + "']/tbody/tr/td[3]/a";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(table)));
				List<WebElement> table_data = driver.findElements(By.xpath(table));

				for (int i = 0; i < table_data.size(); i++) {
					String id = table_data.get(i).getText();
					System.out.println("id is " + id);
					if (id.equals(refno)) {
						System.out.println("passing ref no condition" + i);
						int k = i + 2;
						pages.Utill().click_element(
								".//*[@id='" + getlocator("ass_ref_table") + "']/tbody/tr[" + k + "]/td[1]/input[1]");
						// pages.Utill().find("//*[text()='"+refno+"']/../../td[1]/input[1]").click();
						pages.Utill().select_by_label(getlocator("ass_ref_dd"), "demotl - Chennai");
						// pages.Utill().click_element("ctl00_ContentPlaceHolder1_ing"+checkname+"Assign");
						pages.Utill().click_element(getlocator("ass_ref_assign"));
						pages.Wait().wait_until_loader_is_invisible();
						ss = pages.Utill().clickAlertbox();
						// System.out.println(ss);
						b++;
						break;
					} else {
						continue;
					}
				}
				if (b == 0) {
					logger.log(Status.WARNING, refno + " not found in table");
					String temp = Utill.getScreenshot(driver);
					logger.warning(refno + " not found in table",
							MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

				} else {
					logger.pass("Reference " + ss);
					// return true;
				}
			} else {
				logger.fail("no value on normal for ref check");
				System.out.println("no value on normal for ref check");
				// return false;
			}

		} else {
			logger.log(Status.WARNING, "empty record for reference check");
			String temp = Utill.getScreenshot(driver);
			logger.warning(refno + "empty record for pf check",
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
	}

	public void assign_Address(String refno) throws Exception {
		WebElement ele = pages.Utill().find(getlocator("ass_Address"));
		int b = 0;
		String ss = "";
		// String s=ele.getText();
		// System.out.println(s);
		// System.out.println(!(s.equals("0")));
		if (!(ele.getText().equals("0"))) {
			try {
				ele.click();
			} catch (WebDriverException e) {
				logger.log(Status.WARNING, e.toString());
				pages.Utill().clickAlertbox();
				ele.click();
			}
			logger.info("Clicking element '" + getlocator("ass_Address") + "'");
			pages.Wait().wait_until_loader_is_invisible();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_ContentPlaceHolder1_lbtnNormal")));
			ele = pages.Utill().find("ctl00_ContentPlaceHolder1_lbtnNormal");

			if (!(ele.getText().equals("0"))) {
				System.out.println("passing not equal to zero");
				ele.click();
				pages.Wait().wait_until_loader_is_invisible();
				logger.info("Clicking element 'ctl00_ContentPlaceHolder1_lbtnNormal'");
				String table = ".//*[@id='" + getlocator("ass_address_table") + "']/tbody/tr/td[3]/a";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(table)));
				List<WebElement> table_data = driver.findElements(By.xpath(table));

				for (int i = 0; i < table_data.size(); i++) {
					String id = table_data.get(i).getText();
					System.out.println("id is " + id);
					if (id.equals(refno)) {
						System.out.println("passing ref no condition" + i);
						int k = i + 2;
						pages.Utill().click_element(".//*[@id='" + getlocator("ass_address_table") + "']/tbody/tr[" + k
								+ "]/td[1]/input[1]");
						// pages.Utill().find("//*[text()='"+refno+"']/../../td[1]/input[1]").click();
						pages.Utill().select_by_label(getlocator("ass_address_dd"), "demotl - Chennai");
						// pages.Utill().click_element("ctl00_ContentPlaceHolder1_ing"+checkname+"Assign");
						pages.Utill().click_element(getlocator("ass_address_assign"));
						pages.Wait().wait_until_loader_is_invisible();
						ss = pages.Utill().clickAlertbox();
						// System.out.println(ss);
						b++;
						break;
					} else {
						continue;
					}
				}
				if (b == 0) {
					logger.log(Status.WARNING, refno + " not found in table");
					String temp = Utill.getScreenshot(driver);
					logger.warning(refno + " not found in table",
							MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

				} else {
					logger.pass("Address " + ss);
					// return true;
				}
			} else {
				logger.fail("no value on normal for address check");
				System.out.println("no value on normal for address check");
				// return false;
			}

		} else {
			logger.log(Status.WARNING, "empty record for address check");
			String temp = Utill.getScreenshot(driver);
			logger.warning(refno + "empty record for pf check",
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
	}

	public void assign_Criminal(String refno) throws Exception {
		WebElement ele = pages.Utill().find(getlocator("ass_Criminal"));
		int b = 0;
		String ss = "";
		if (!(ele.getText().equals("0"))) {
			try {
				ele.click();
			} catch (WebDriverException e) {
				logger.log(Status.WARNING, e.toString());
				pages.Utill().clickAlertbox();
				ele.click();
			}
			logger.info("Clicking element '" + getlocator("ass_Criminal") + "'");
			pages.Wait().wait_until_loader_is_invisible();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_ContentPlaceHolder1_lbtnNormal")));
			ele = pages.Utill().find("ctl00_ContentPlaceHolder1_lbtnNormal");

			if (!(ele.getText().equals("0"))) {
				System.out.println("passing not equal to zero");
				ele.click();
				logger.info("Clicking element 'ctl00_ContentPlaceHolder1_lbtnNormal'");
				pages.Wait().wait_until_loader_is_invisible();
				String table = ".//*[@id='" + getlocator("ass_criminal_table") + "']/tbody/tr/td[3]/a";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(table)));
				List<WebElement> table_data = driver.findElements(By.xpath(table));

				for (int i = 0; i < table_data.size(); i++) {
					String id = table_data.get(i).getText();
					System.out.println("id is " + id);
					if (id.equals(refno)) {
						System.out.println("passing ref no condition" + i);
						int k = i + 2;
						pages.Utill().click_element(".//*[@id='" + getlocator("ass_criminal_table") + "']/tbody/tr[" + k
								+ "]/td[1]/input[1]");
						// pages.Utill().find("//*[text()='"+refno+"']/../../td[1]/input[1]").click();
						pages.Utill().select_by_label(getlocator("ass_criminal_dd"), "demotl - Chennai");
						// pages.Utill().click_element("ctl00_ContentPlaceHolder1_ing"+checkname+"Assign");
						pages.Utill().click_element(getlocator("ass_criminal_assign"));
						pages.Wait().wait_until_loader_is_invisible();
						ss = pages.Utill().clickAlertbox();
						// System.out.println(ss);
						b++;
						break;
					} else {
						continue;
					}
				}
				if (b == 0) {
					logger.log(Status.WARNING, refno + " not found in table");
					String temp = Utill.getScreenshot(driver);
					logger.warning(refno + " not found in table",
							MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

				} else {
					logger.pass("Criminal " + ss);
					// return true;
				}
			} else {
				logger.fail("no value on normal for criminal check");
				System.out.println("no value on normal for criminal check");
				// return false;
			}

		} else {
			logger.log(Status.WARNING, "empty record for criminal check");
			String temp = Utill.getScreenshot(driver);
			logger.warning(refno + "empty record for pf check",
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
	}

	public void assign_DB(String refno) throws Exception {
		WebElement ele = pages.Utill().find(getlocator("ass_Db"));
		int b = 0;
		String ss = "";
		if (!(ele.getText().equals("0"))) {
			try {
				ele.click();
			} catch (WebDriverException e) {
				logger.log(Status.WARNING, e.toString());
				pages.Utill().clickAlertbox();
				ele.click();
			}
			logger.info("Clicking element '" + getlocator("ass_Db") + "'");
			pages.Wait().wait_until_loader_is_invisible();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_ContentPlaceHolder1_lbtnNormal")));
			ele = pages.Utill().find("ctl00_ContentPlaceHolder1_lbtnNormal");

			if (!(ele.getText().equals("0"))) {
				System.out.println("passing not equal to zero");
				ele.click();
				pages.Wait().wait_until_loader_is_invisible();
				logger.info("Clicking element 'ctl00_ContentPlaceHolder1_lbtnNormal'");
				String table = ".//*[@id='" + getlocator("ass_db_table") + "']/tbody/tr/td[3]/a";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(table)));
				List<WebElement> table_data = driver.findElements(By.xpath(table));

				for (int i = 0; i < table_data.size(); i++) {
					String id = table_data.get(i).getText();
					System.out.println("id is " + id);
					if (id.equals(refno)) {
						System.out.println("passing ref no condition" + i);
						int k = i + 2;
						pages.Utill().click_element(
								".//*[@id='" + getlocator("ass_db_table") + "']/tbody/tr[" + k + "]/td[1]/input[1]");
						// pages.Utill().find("//*[text()='"+refno+"']/../../td[1]/input[1]").click();
						pages.Utill().select_by_label(getlocator("ass_db_dd"), "demotl - Chennai");
						// pages.Utill().click_element("ctl00_ContentPlaceHolder1_ing"+checkname+"Assign");
						pages.Utill().click_element(getlocator("ass_db_assign"));
						pages.Wait().wait_until_loader_is_invisible();
						ss = pages.Utill().clickAlertbox();
						// System.out.println(ss);
						b++;
						break;
					} else {
						continue;
					}
				}
				if (b == 0) {
					logger.log(Status.WARNING, refno + " not found in table");
					String temp = Utill.getScreenshot(driver);
					logger.warning(refno + " not found in table",
							MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
				} else {
					logger.pass("DB " + ss);
					// return true;
				}
			} else {
				logger.fail("no value on normal for db check");
				System.out.println("no value on normal for db check");
				// return false;
			}

		} else {
			logger.log(Status.WARNING, "empty record for db check");
			String temp = Utill.getScreenshot(driver);
			logger.warning(refno + "empty record for db check",
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
	}

	public void assign_Drug(String refno) throws Exception {
		WebElement ele = pages.Utill().find(getlocator("ass_Drug"));
		int b = 0;
		String ss = "";
		if (!(ele.getText().equals("0"))) {
			try {
				ele.click();
			} catch (WebDriverException e) {
				logger.log(Status.WARNING, e.toString());
				pages.Utill().clickAlertbox();
				ele.click();
			}
			logger.info("Clicking element '" + getlocator("ass_Drug") + "'");
			pages.Wait().wait_until_loader_is_invisible();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_ContentPlaceHolder1_lbtnNormal")));
			ele = pages.Utill().find("ctl00_ContentPlaceHolder1_lbtnNormal");

			if (!(ele.getText().equals("0"))) {
				System.out.println("passing not equal to zero");
				ele.click();
				logger.info("Clicking element 'ctl00_ContentPlaceHolder1_lbtnNormal'");
				pages.Wait().wait_until_loader_is_invisible();
				String table = ".//*[@id='" + getlocator("ass_drug_table") + "']/tbody/tr/td[3]/a";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(table)));
				List<WebElement> table_data = driver.findElements(By.xpath(table));

				for (int i = 0; i < table_data.size(); i++) {
					String id = table_data.get(i).getText();
					System.out.println("id is " + id);
					if (id.equals(refno)) {
						System.out.println("passing ref no condition" + i);
						int k = i + 2;
						pages.Utill().click_element(
								".//*[@id='" + getlocator("ass_drug_table") + "']/tbody/tr[" + k + "]/td[1]/input[1]");
						// pages.Utill().find("//*[text()='"+refno+"']/../../td[1]/input[1]").click();
						pages.Utill().select_by_label(getlocator("ass_drug_dd"), "demotl - Chennai");
						// pages.Utill().click_element("ctl00_ContentPlaceHolder1_ing"+checkname+"Assign");
						pages.Utill().click_element(getlocator("ass_drug_assign"));
						pages.Wait().wait_until_loader_is_invisible();
						ss = pages.Utill().clickAlertbox();
						// System.out.println(ss);
						b++;
						break;
					} else {
						continue;
					}
				}
				if (b == 0) {
					logger.log(Status.WARNING, refno + " not found in table");
					String temp = Utill.getScreenshot(driver);
					logger.warning(refno + " not found in table",
							MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

				} else {
					logger.pass("Drug " + ss);
					// return true;
				}
			} else {
				logger.fail("no value on normal for drug check");
				System.out.println("no value on normal for drug check");
				// return false;
			}

		} else {
			logger.log(Status.WARNING, "empty record for drug check");
			String temp = Utill.getScreenshot(driver);
			logger.warning(refno + "empty record for pf check",
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
	}

	public void assign_ID(String refno) throws Exception {
		WebElement ele = pages.Utill().find(getlocator("ass_ID"));
		int b = 0;
		String ss = "";
		if (!(ele.getText().equals("0"))) {
			try {
				ele.click();
			} catch (WebDriverException e) {
				logger.log(Status.WARNING, e.toString());
				pages.Utill().clickAlertbox();
				ele.click();
			}
			logger.info("Clicking element '" + getlocator("ass_ID") + "'");
			pages.Wait().wait_until_loader_is_invisible();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_ContentPlaceHolder1_lbtnNormal")));
			ele = pages.Utill().find("ctl00_ContentPlaceHolder1_lbtnNormal");

			if (!(ele.getText().equals("0"))) {
				System.out.println("passing not equal to zero");
				ele.click();
				logger.info("Clicking element 'ctl00_ContentPlaceHolder1_lbtnNormal'");
				pages.Wait().wait_until_loader_is_invisible();
				String table = ".//*[@id='" + getlocator("ass_id_table") + "']/tbody/tr/td[3]/a";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(table)));
				List<WebElement> table_data = driver.findElements(By.xpath(table));
				for (int i = 0; i < table_data.size(); i++) {
					String id = table_data.get(i).getText();
					System.out.println("id is " + id);
					if (id.equals(refno)) {
						System.out.println("passing ref no condition" + i);
						int k = i + 2;
						pages.Utill().click_element(
								".//*[@id='" + getlocator("ass_id_table") + "']/tbody/tr[" + k + "]/td[1]/input[1]");
						pages.Utill().select_by_label(getlocator("ass_id_dd"), "demotl - Chennai");
						pages.Utill().click_element(getlocator("ass_id_assign"));
						pages.Wait().wait_until_loader_is_invisible();
						ss = pages.Utill().clickAlertbox();
						// System.out.println(ss);

						b++;
						break;
					} else {
						continue;
					}
				}
				if (b == 0) {
					logger.log(Status.WARNING, refno + " not found in table");
					String temp = Utill.getScreenshot(driver);
					logger.warning(refno + " not found in table",
							MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

				} else {
					logger.pass("ID " + ss);
					// return true;
				}
			} else {
				logger.fail("no value on normal for id check");
				System.out.println("no value on normal for id check");
				// return false;
			}

		} else {
			logger.log(Status.WARNING, "empty record for id check");
			String temp = Utill.getScreenshot(driver);
			logger.warning(refno + "empty record for pf check",
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
	}

	public void assign_Court(String refno) throws Exception {
		WebElement ele = pages.Utill().find(getlocator("ass_Court"));
		int b = 0;
		String ss = "";
		if (!(ele.getText().equals("0"))) {
			try {
				ele.click();
			} catch (WebDriverException e) {
				logger.log(Status.WARNING, e.toString());
				pages.Utill().clickAlertbox();
				ele.click();
			}
			logger.info("Clicking element '" + getlocator("ass_Court") + "'");
			pages.Wait().wait_until_loader_is_invisible();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_ContentPlaceHolder1_lbtnNormal")));
			ele = pages.Utill().find("ctl00_ContentPlaceHolder1_lbtnNormal");

			if (!(ele.getText().equals("0"))) {
				System.out.println("passing not equal to zero");
				ele.click();
				logger.info("Clicking element 'ctl00_ContentPlaceHolder1_lbtnNormal'");
				pages.Wait().wait_until_loader_is_invisible();
				String table = ".//*[@id='" + getlocator("ass_court_table") + "']/tbody/tr/td[3]/a";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(table)));
				List<WebElement> table_data = driver.findElements(By.xpath(table));

				for (int i = 0; i < table_data.size(); i++) {
					String id = table_data.get(i).getText();
					System.out.println("id is " + id);
					if (id.equals(refno)) {
						System.out.println("passing ref no condition" + i);
						int k = i + 2;
						pages.Utill().click_element(
								".//*[@id='" + getlocator("ass_court_table") + "']/tbody/tr[" + k + "]/td[1]/input[1]");
						// pages.Utill().find("//*[text()='"+refno+"']/../../td[1]/input[1]").click();
						pages.Utill().select_by_label(getlocator("ass_court_dd"), "demotl - Chennai");
						// pages.Utill().click_element("ctl00_ContentPlaceHolder1_ing"+checkname+"Assign");
						pages.Utill().click_element(getlocator("ass_court_assign"));
						pages.Wait().wait_until_loader_is_invisible();
						ss = pages.Utill().clickAlertbox();
						// System.out.println(ss);
						b++;
						break;
					} else {
						continue;
					}
				}
				if (b == 0) {
					logger.log(Status.WARNING, refno + " not found in table");
					String temp = Utill.getScreenshot(driver);
					logger.warning(refno + " not found in table",
							MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

				} else {
					logger.pass("Court " + ss);
					// return true;
				}
			} else {
				logger.fail("no value on normal for court check");
				System.out.println("no value on normal for court check");
				// return false;
			}

		} else {
			logger.log(Status.WARNING, "empty record for court check");
			String temp = Utill.getScreenshot(driver);
			logger.warning(refno + "empty record for pf check",
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
	}

	public void assign_Facis(String refno) throws Exception {
		WebElement ele = pages.Utill().find(getlocator("ass_Facis"));
		int b = 0;
		String ss = "";
		if (!(ele.getText().equals("0"))) {
			try {
				ele.click();
			} catch (WebDriverException e) {
				logger.log(Status.WARNING, e.toString());
				pages.Utill().clickAlertbox();
				ele.click();
			}
			logger.info("Clicking element '" + getlocator("ass_Facis") + "'");
			pages.Wait().wait_until_loader_is_invisible();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_ContentPlaceHolder1_lbtnNormal")));
			ele = pages.Utill().find("ctl00_ContentPlaceHolder1_lbtnNormal");

			if (!(ele.getText().equals("0"))) {
				System.out.println("passing not equal to zero");
				ele.click();
				logger.info("Clicking element 'ctl00_ContentPlaceHolder1_lbtnNormal'");
				pages.Wait().wait_until_loader_is_invisible();
				String table = ".//*[@id='" + getlocator("ass_Facis_table") + "']/tbody/tr/td[3]/a";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(table)));
				List<WebElement> table_data = driver.findElements(By.xpath(table));

				for (int i = 0; i < table_data.size(); i++) {
					String id = table_data.get(i).getText();
					System.out.println("id is " + id);
					if (id.equals(refno)) {
						System.out.println("passing ref no condition" + i);
						int k = i + 2;
						pages.Utill().click_element(
								".//*[@id='" + getlocator("ass_Facis_table") + "']/tbody/tr[" + k + "]/td[1]/input[1]");
						// pages.Utill().find("//*[text()='"+refno+"']/../../td[1]/input[1]").click();
						pages.Utill().select_by_label(getlocator("ass_Facis_dd"), "demotl - Chennai");
						// pages.Utill().click_element("ctl00_ContentPlaceHolder1_ing"+checkname+"Assign");
						pages.Utill().click_element(getlocator("ass_Facis_assign"));
						pages.Wait().wait_until_loader_is_invisible();
						ss = pages.Utill().clickAlertbox();
						// System.out.println(ss);
						b++;
						break;
					} else {
						continue;
					}
				}
				if (b == 0) {
					logger.log(Status.WARNING, refno + " not found in table");
					String temp = Utill.getScreenshot(driver);
					logger.warning(refno + " not found in table",
							MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

				} else {
					logger.pass("Facis " + ss);
					// return true;
				}
			} else {
				logger.fail("no value on normal for Facis check");
				System.out.println("no value on normal for Facis check");
				// return false;
			}

		} else {
			logger.log(Status.WARNING, "empty record for facis check");
			String temp = Utill.getScreenshot(driver);
			logger.warning(refno + "empty record for pf check",
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
	}

	public void assign_Credit(String refno) throws Exception {
		WebElement ele = pages.Utill().find(getlocator("ass_Credit"));
		int b = 0;
		String ss = "";
		if (!(ele.getText().equals("0"))) {
			try {
				ele.click();
			} catch (WebDriverException e) {
				logger.log(Status.WARNING, e.toString());
				pages.Utill().clickAlertbox();
				ele.click();
			}
			logger.info("Clicking element '" + getlocator("ass_Credit") + "'");
			pages.Wait().wait_until_loader_is_invisible();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_ContentPlaceHolder1_lbtnNormal")));
			ele = pages.Utill().find("ctl00_ContentPlaceHolder1_lbtnNormal");

			if (!(ele.getText().equals("0"))) {
				System.out.println("passing not equal to zero");
				ele.click();
				logger.info("Clicking element 'ctl00_ContentPlaceHolder1_lbtnNormal'");
				pages.Wait().wait_until_loader_is_invisible();
				String table = ".//*[@id='" + getlocator("ass_Credit_table") + "']/tbody/tr/td[3]/a";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(table)));
				List<WebElement> table_data = driver.findElements(By.xpath(table));

				for (int i = 0; i < table_data.size(); i++) {
					String id = table_data.get(i).getText();
					System.out.println("id is " + id);
					if (id.equals(refno)) {
						System.out.println("passing ref no condition" + i);
						int k = i + 2;
						pages.Utill().click_element(".//*[@id='" + getlocator("ass_Credit_table") + "']/tbody/tr[" + k
								+ "]/td[1]/input[1]");
						// pages.Utill().find("//*[text()='"+refno+"']/../../td[1]/input[1]").click();
						pages.Utill().select_by_label(getlocator("ass_Credit_dd"), "demotl - Chennai");
						// pages.Utill().click_element("ctl00_ContentPlaceHolder1_ing"+checkname+"Assign");
						pages.Utill().click_element(getlocator("ass_Credit_assign"));
						pages.Wait().wait_until_loader_is_invisible();
						ss = pages.Utill().clickAlertbox();
						// System.out.println(ss);
						b++;
						break;
					} else {
						continue;
					}
				}
				if (b == 0) {
					logger.log(Status.WARNING, refno + " not found in table");
					String temp = Utill.getScreenshot(driver);
					logger.warning(refno + " not found in table",
							MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

				} else {
					logger.pass("Credit " + ss);
					// return true;
				}
			} else {
				logger.fail("no value on normal for Credit check");
				System.out.println("no value on normal for Credit check");
				// return false;
			}

		} else {
			logger.log(Status.WARNING, "empty record for Credit check");
			String temp = Utill.getScreenshot(driver);
			logger.warning(refno + "empty record for pf check",
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
	}

	public void assign_IT(String refno) throws Exception {
		WebElement ele = pages.Utill().find(getlocator("ass_IT"));
		int b = 0;
		String ss = "";
		if (!(ele.getText().equals("0"))) {
			try {
				ele.click();
			} catch (WebDriverException e) {
				logger.log(Status.WARNING, e.toString());
				pages.Utill().clickAlertbox();
				ele.click();
			}
			logger.info("Clicking element '" + getlocator("ass_IT") + "'");
			pages.Wait().wait_until_loader_is_invisible();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_ContentPlaceHolder1_lbtnNormal")));
			ele = pages.Utill().find("ctl00_ContentPlaceHolder1_lbtnNormal");

			if (!(ele.getText().equals("0"))) {
				System.out.println("passing not equal to zero");
				ele.click();
				logger.info("Clicking element 'ctl00_ContentPlaceHolder1_lbtnNormal'");
				pages.Wait().wait_until_loader_is_invisible();
				String table = ".//*[@id='" + getlocator("ass_it_table") + "']/tbody/tr/td[3]/a";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(table)));
				List<WebElement> table_data = driver.findElements(By.xpath(table));

				for (int i = 0; i < table_data.size(); i++) {
					String id = table_data.get(i).getText();
					System.out.println("id is " + id);
					if (id.equals(refno)) {
						System.out.println("passing ref no condition" + i);
						int k = i + 2;
						pages.Utill().click_element(
								".//*[@id='" + getlocator("ass_it_table") + "']/tbody/tr[" + k + "]/td[1]/input[1]");
						// pages.Utill().find("//*[text()='"+refno+"']/../../td[1]/input[1]").click();
						pages.Utill().select_by_label(getlocator("ass_it_dd"), "demotl - Chennai");
						// pages.Utill().click_element("ctl00_ContentPlaceHolder1_ing"+checkname+"Assign");
						pages.Utill().click_element(getlocator("ass_it_assign"));
						pages.Wait().wait_until_loader_is_invisible();
						ss = pages.Utill().clickAlertbox();
						// System.out.println(ss);
						b++;
						break;
					} else {
						continue;
					}
				}
				if (b == 0) {
					logger.log(Status.WARNING, refno + " not found in table");
					String temp = Utill.getScreenshot(driver);
					logger.warning(refno + " not found in table",
							MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

				} else {
					logger.pass("IT " + ss);
					// return true;
				}
			} else {
				logger.fail("no value on normal for it check");
				System.out.println("no value on normal for it check");
				// return false;
			}

		} else {
			logger.log(Status.WARNING, "empty record for it check");
			String temp = Utill.getScreenshot(driver);
			logger.warning(refno + "empty record for pf check",
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
	}

	public void assign_BV(String refno) throws Exception {
		WebElement ele = pages.Utill().find(getlocator("ass_BV"));
		int b = 0;
		String ss = "";
		if (!(ele.getText().equals("0"))) {
			try {
				ele.click();
			} catch (WebDriverException e) {
				logger.log(Status.WARNING, e.toString());
				pages.Utill().clickAlertbox();
				ele.click();
			}
			logger.info("Clicking element '" + getlocator("ass_BV") + "'");
			pages.Wait().wait_until_loader_is_invisible();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_ContentPlaceHolder1_lbtnNormal")));
			ele = pages.Utill().find("ctl00_ContentPlaceHolder1_lbtnNormal");

			if (!(ele.getText().equals("0"))) {
				System.out.println("passing not equal to zero");
				ele.click();
				logger.info("Clicking element 'ctl00_ContentPlaceHolder1_lbtnNormal'");
				pages.Wait().wait_until_loader_is_invisible();
				String table = ".//*[@id='" + getlocator("ass_bv_table") + "']/tbody/tr/td[3]/a";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(table)));
				List<WebElement> table_data = driver.findElements(By.xpath(table));

				for (int i = 0; i < table_data.size(); i++) {
					String id = table_data.get(i).getText();
					System.out.println("id is " + id);
					if (id.equals(refno)) {
						System.out.println("passing ref no condition" + i);
						int k = i + 2;
						pages.Utill().click_element(
								".//*[@id='" + getlocator("ass_bv_table") + "']/tbody/tr[" + k + "]/td[1]/input[1]");
						// pages.Utill().find("//*[text()='"+refno+"']/../../td[1]/input[1]").click();
						pages.Utill().select_by_label(getlocator("ass_bv_dd"), "demotl - Chennai");
						// pages.Utill().click_element("ctl00_ContentPlaceHolder1_ing"+checkname+"Assign");
						pages.Utill().click_element(getlocator("ass_bv_assign"));
						pages.Wait().wait_until_loader_is_invisible();
						ss = pages.Utill().clickAlertbox();
						// System.out.println(ss);
						b++;
						break;
					} else {
						continue;
					}
				}
				if (b == 0) {
					logger.log(Status.WARNING, refno + " not found in table");
					String temp = Utill.getScreenshot(driver);
					logger.warning(refno + " not found in table",
							MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

				} else {
					logger.pass("BV " + ss);
					// return true;
				}
			} else {
				logger.fail("no value on normal for bv check");
				System.out.println("no value on normal for bv check");
				// return false;
			}

		} else {
			logger.log(Status.WARNING, "empty record for bv check");
			String temp = Utill.getScreenshot(driver);
			logger.warning(refno + "empty record for pf check",
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
	}

	public void assign_PF(String refno) throws Exception {
		WebElement ele = pages.Utill().find(getlocator("ass_PF"));
		int b = 0;
		String ss = "";
		if (!(ele.getText().equals("0"))) {
			try {
				ele.click();
			} catch (WebDriverException e) {
				logger.log(Status.WARNING, e.toString());
				pages.Utill().clickAlertbox();
				ele.click();
			}
			logger.info("Clicking element '" + getlocator("ass_PF") + "'");
			pages.Wait().wait_until_loader_is_invisible();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_ContentPlaceHolder1_lbtnNormal")));
			ele = pages.Utill().find("ctl00_ContentPlaceHolder1_lbtnNormal");

			if (!(ele.getText().equals("0"))) {
				System.out.println("passing not equal to zero");
				ele.click();
				logger.info("Clicking element 'ctl00_ContentPlaceHolder1_lbtnNormal'");
				pages.Wait().wait_until_loader_is_invisible();
				String table = ".//*[@id='" + getlocator("ass_pf_table") + "']/tbody/tr/td[3]/a";
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(table)));
				List<WebElement> table_data = driver.findElements(By.xpath(table));

				for (int i = 0; i < table_data.size(); i++) {
					String id = table_data.get(i).getText();
					System.out.println("id is " + id);
					if (id.equals(refno)) {
						System.out.println("passing ref no condition" + i);
						int k = i + 2;
						pages.Utill().click_element(
								".//*[@id='" + getlocator("ass_pf_table") + "']/tbody/tr[" + k + "]/td[1]/input[1]");
						// pages.Utill().find("//*[text()='"+refno+"']/../../td[1]/input[1]").click();
						pages.Utill().select_by_label(getlocator("ass_pf_dd"), "demotl - Chennai");
						// pages.Utill().click_element("ctl00_ContentPlaceHolder1_ing"+checkname+"Assign");
						pages.Utill().click_element(getlocator("ass_pf_assign"));
						pages.Wait().wait_until_loader_is_invisible();
						ss = pages.Utill().clickAlertbox();
						// System.out.println(ss);
						b++;
						break;
					} else {
						continue;
					}
				}
				if (b == 0) {
					logger.log(Status.WARNING, refno + " not found in table");
					String temp = Utill.getScreenshot(driver);
					logger.warning(refno + " not found in table",
							MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

				} else {
					logger.pass("PF " + ss);
					// return true;
				}
			} else {
				logger.fail("no value on normal for pf check");
				System.out.println("no value on normal for pf check");
				// return false;
			}

		} else {
			logger.log(Status.WARNING, "empty record for pf check");
			String temp = Utill.getScreenshot(driver);
			logger.warning(refno + "empty record for pf check",
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
	}
}
