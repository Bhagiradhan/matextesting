package environment;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.net.UrlChecker.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import testCases.Pages;

public class Wait {
	WebDriver driver;
	WebDriverWait waitt;
	ExtentTest logger;
	protected final Pages pages;

	public Wait(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		pages = new Pages(driver, logger);
	}

	public WebElement visibilityOfElement(String ele) {

		waitt = new WebDriverWait(driver, 10);
		waitt.until(ExpectedConditions.visibilityOf(pages.Utill().find(ele)));
		logger.log(Status.PASS, "waiting for :"+ele);
		return pages.Utill().find(ele);
	}

	public void presenceOfElement(String ele, int timeout) {

		waitt = new WebDriverWait(driver, timeout);
		if (ele.startsWith("./") || ele.startsWith("/") || ele.startsWith("(//")) {
			waitt.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ele)));
		} else {
			waitt.until(ExpectedConditions.presenceOfElementLocated(By.id(ele)));
		}

	}

	public void waitForVisibilityOfData(String path, int timeout) throws java.util.concurrent.TimeoutException {
		// Pages pages=new Pages(driver);
		String result = pages.Utill().find(path).getCssValue("visibility");
		// System.out.println(result);
		int time = 0;
		int t = 2 * timeout;
		while (result.equals("hidden") && time < t) {
			try {
				Thread.sleep(500);
				// System.out.println("running loop"+result);
				result = pages.Utill().find(path).getCssValue("visibility");
				System.out.println("next" + result + time + result);
				if (result.equals("visible")) {
					break;
				} else {
					time++;
					continue;
				}
			} catch (Exception e) {
				System.out.println(e);
			}

		}
		if (time >= t) {
			throw new java.util.concurrent.TimeoutException("element is not visible after " + timeout + " seconds");
		}

	}

	public void wait_until_alert_isvisible() throws InterruptedException {
		// Pages pages=new Pages(driver);
		WebDriverWait w = new WebDriverWait(driver, 10);
		w.until(ExpectedConditions.presenceOfElementLocated(By.id("cOK")));
		String value = pages.Utill().find("//div[@id='confirmBox']").getCssValue("display");
		while (!(value.equals("block"))) {
			Thread.sleep(200);
			value = pages.Utill().find("//div[@id='confirmBox']").getCssValue("display");
			if (value.equals("block")) {
				break;
			} else {
				continue;
			}
		}

	}

	public void wait_until_loader_is_invisible() throws InterruptedException {
		// Pages pages=new Pages(driver);
		// System.out.println("Start time"+java.time.LocalTime.now());
		String res;
		try {
			res = pages.Utill().find("ctl00_UpdateProgress1").getCssValue("display");
		} catch (StaleElementReferenceException e) {
			Thread.sleep(1000);
			res = pages.Utill().find("ctl00_UpdateProgress1").getCssValue("display");
		}
		while (res.equals("block")) {
			Thread.sleep(200);
			res = pages.Utill().find("ctl00_UpdateProgress1").getCssValue("display");
			if (!(res.equals("block"))) {
				// System.out.println("end time"+java.time.LocalTime.now());
				break;
			} else {
				// System.out.println(res);
				continue;

			}
		}
	}
	public void wait_until_loader_is_invisible(int TimeOut) throws InterruptedException {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(TimeOut))
				.pollingEvery(Duration.ofMillis(200))
				.ignoring(NoSuchElementException.class);
		WebElement ele = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement ele = pages.Utill().find("ctl00_UpdateProgress1");
				String res = ele.getCssValue("display");
				if (res.equals("none")) {
//					System.out.println("success " + res);
					return ele;
				} else {
//					System.out.println("failed :" + res);
					return null;
				}
			}
		});
//		// Pages pages=new Pages(driver);
//		// System.out.println("Start time"+java.time.LocalTime.now());
//		String res;
//		try {
//			res = pages.Utill().find("ctl00_UpdateProgress1").getCssValue("display");
//		} catch (StaleElementReferenceException e) {
//			Thread.sleep(1000);
//			res = pages.Utill().find("ctl00_UpdateProgress1").getCssValue("display");
//		}
//		while (res.equals("block")) {
//			Thread.sleep(200);
//			res = pages.Utill().find("ctl00_UpdateProgress1").getCssValue("display");
//			if (!(res.equals("block"))) {
//				// System.out.println("end time"+java.time.LocalTime.now());
//				break;
//			} else {
//				// System.out.println(res);
//				continue;
//
//			}
//		}
	}

	public void wait_until_spinner_is_invisible(String id) throws InterruptedException {
		// Pages pages=new Pages(driver);
		// System.out.println("Start time"+java.time.LocalTime.now());
		//ctl00_ContentPlaceHolder1_overlayScreen_Laod_11
		//ctl00_ContentPlaceHolder1_overlayScreen
		String res;
		try {
			res = pages.Utill().find(id).getCssValue("display");
		} catch (StaleElementReferenceException e) {
			Thread.sleep(1000);
			res = pages.Utill().find(id).getCssValue("display");
		}
		while (res.equals("block")) {
			Thread.sleep(200);
			res = pages.Utill().find(id).getCssValue("display");
			if (!(res.equals("block"))) {
				// System.out.println("end time"+java.time.LocalTime.now());
				break;
			} else {
				// System.out.println(res);
				continue;

			}
		}
	}

	//
	public void wait_until_crtspinner_is_invisible() throws InterruptedException {
		// Pages pages=new Pages(driver);
		// System.out.println("Start time"+java.time.LocalTime.now());
		String res;
		try {
			res = pages.Utill().find("ctl00_ContentPlaceHolder1_overlay1").getCssValue("display");
		} catch (StaleElementReferenceException e) {
			Thread.sleep(1000);
			res = pages.Utill().find("ctl00_ContentPlaceHolder1_overlay1").getCssValue("display");
		}
		while (res.equals("block")) {
			Thread.sleep(200);
			res = pages.Utill().find("ctl00_ContentPlaceHolder1_overlay1").getCssValue("display");
			if (!(res.equals("block"))) {
				// System.out.println("end time"+java.time.LocalTime.now());
				break;
			} else {
				// System.out.println(res);
				continue;

			}
		}
	}
}
