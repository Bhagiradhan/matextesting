package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

class temp

{
	public static void main(String[] args) throws Exception {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://192.168.2.17:5000/#/");
		driver.findElement(By.xpath("html/body/div[1]/form/div[1]/div/div[2]/div[2]/div[1]/div/input")).sendKeys("shield");
		driver.findElement(By.xpath("html/body/div[1]/form/div[1]/div/div[2]/div[2]/div[2]/div/input")).sendKeys("Shield@1011");
		driver.findElement(By.xpath("html/body/div[1]/form/div[1]/div/div[2]/div[2]/div[5]/button")).click();
		Thread.sleep(2000);
		driver.get("http://192.168.2.17:5000/#/createcontract");
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='client_chzn']/a/div/b")).click();
		driver.findElement(By.xpath(".//*[@id='client_chzn']/div/ul/li[3]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='contract-accordion']/div/div/accordion/div/div[6]/div/div[1]/h4/a/span[1]")).click();
		
//		WebElement sc=driver.findElement(By.xpath(".//*[@id='contract-accordion']/div/div/accordion/div/div[6]/div/div[2]/div/div/ul/div[1]"));
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", sc);
		Thread.sleep(2000);
		List<WebElement> parent= driver.findElements(By.xpath("//div[@class='panel-collapse collapse in']//div[@class='ng-binding ng-scope']"));
		if(parent.size()>0) {
			for (int i = 0; i < parent.size(); i++) {
				System.out.println(parent.get(i).getText());
				if(!driver.findElement(By.xpath("(//div[@class='panel-collapse collapse in']//div[@class='ng-binding ng-scope']/input)["+(i+1)+"]")).isSelected()) {
					driver.findElement(By.xpath("(//div[@class='panel-collapse collapse in']//div[@class='ng-binding ng-scope']/input)["+(i+1)+"]")).click();	
				}
			}
		}
		else {
			System.out.println("lenght is : "+parent.size());
		}
		
	}
	    
		
	
}