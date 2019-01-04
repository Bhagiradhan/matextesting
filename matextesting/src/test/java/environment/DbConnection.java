package environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DbConnection {
	
	public static void main(String[] args)throws Exception {
		String pass =GetlocalPassword("demotl");
		System.out.println(pass);
	}
public static String GetlocalPassword(String uname) throws Exception{
	
		String pass=null;
		final String url="jdbc:sqlserver://192.168.2.16:1433;"+"databaseName=FinalMatrixTestingDB"+"";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		Connection conn = DriverManager.getConnection(url, "Sa", "Sql@123");	
			String query = "OPEN Symmetric KEy Mat_Key  DECRYPTION By Certificate Matrix_cert\r\n" + "   \r\n"
					+ "select User_name,convert(varchar(50),decryptbykey(User_password)),User_id,User_Userinfoid from tbl_master_webusers where User_name='"+uname+"'\r\n"
					+ "\r\n" + "close Symmetric KEy Mat_Key";
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			//System.out.println(rs);
			while (rs.next()) {
				//String us = rs.getString("User_name");
				pass = rs.getString("");
				//System.out.println(us + "  " + pass);
				
			}
			System.out.println("password is :"+pass);
			conn.close();
		return pass;
	
}
public static String GetuatPassword(String uname) throws Exception{
	String query = "OPEN Symmetric KEy Mat_Key  DECRYPTION By Certificate Matrix_cert\r\n" + "   \r\n"
			+ "select User_name,convert(varchar(50),decryptbykey(User_password)),User_id,User_Userinfoid from tbl_master_webusers where User_name='"+uname+"'\r\n"
			+ "\r\n" + "close Symmetric KEy Mat_Key";
	String chrome_path = System.getProperty("user.dir") + "\\src\\test\\resources\\driver\\chromedriver.exe";
	System.setProperty("webdriver.chrome.driver", chrome_path);
	ChromeOptions chromoption = new ChromeOptions();
	chromoption.setHeadless(true);
	WebDriver driver2 = new ChromeDriver(chromoption);
	Dimension d = new Dimension(1382, 744);
	driver2.manage().window().setSize(d);
	driver2.manage().window().maximize();
	driver2.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver2.get("http://103.60.137.37/webform1/WebForm1.aspx");
	driver2.findElement(By.id("txtQuery")).sendKeys(query);
	driver2.findElement(By.id("btnExec")).click();
	String pass = driver2.findElement(By.xpath(".//*[@id='panelData']/table/tbody/tr[2]/td[2]")).getText();
	System.out.println("password :"+pass);
	driver2.close();
	return pass;
}
public static String GetlivePassword(String uname) throws Exception{
	String query = "OPEN Symmetric KEy Mat_Key  DECRYPTION By Certificate Matrix_cert\r\n" + "   \r\n"
			+ "select User_name,convert(varchar(50),decryptbykey(User_password)),User_id,User_Userinfoid from tbl_master_webusers where User_name='"+uname+"'\r\n"
			+ "\r\n" + "close Symmetric KEy Mat_Key";
	String chrome_path = System.getProperty("user.dir") + "\\src\\test\\resources\\driver\\chromedriver.exe";
	System.setProperty("webdriver.chrome.driver", chrome_path);
	ChromeOptions chromoption = new ChromeOptions();
	chromoption.setHeadless(true);
	WebDriver driver2 = new ChromeDriver(chromoption);
	Dimension d = new Dimension(1382, 744);
	driver2.manage().window().setSize(d);
	driver2.manage().window().maximize();
	driver2.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver2.get("http://103.60.137.37/webformlive/WebForm1.aspx");
	driver2.findElement(By.id("txtQuery")).sendKeys(query);
	driver2.findElement(By.id("btnExec")).click();
	String pass = driver2.findElement(By.xpath(".//*[@id='panelData']/table/tbody/tr[2]/td[2]")).getText();
	System.out.println("password :"+pass);
	driver2.close();
	return pass;
}

}
