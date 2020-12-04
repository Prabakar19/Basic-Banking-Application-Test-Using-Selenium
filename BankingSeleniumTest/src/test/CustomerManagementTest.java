package test;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;


public class CustomerManagementTest {

	WebDriver driver;
	String custAccNo1 = "300482";
	String custAccNo2 = "300483";
	String custAccNo3 = "300484";
	String custName = "Soumya Racha";
	
	String cName = "Nandu";
	String cAccNo = "300486";
	String cAge = "18";
	String cMinAmt = "1000";
	String cAddr = "MNLR, Trichy";
	String cMobNo = "8967451200";
	String cEmail = "nandakumar0203@gmail.com";
	String cPassword = "12345678";
	String cDob = "02/03/2002";
			
	
	
	  @BeforeClass
	  public void beforeClass() {
		  System.setProperty("webdriver.chrome.driver", "/home/lucifer/eclipse-workspace/Automation/chromedriver_linux64/chromedriver");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get("http://localhost/Banking/adminlogin.php");
	  }
	  
	  @BeforeMethod
	  public void beforeMethod() throws InterruptedException {
		  String pass = logIn("lucifer19", "12345");
		  Assert.assertEquals(pass, "Admin Homepage");
	  }
	  
	  @Test(priority = 1)
	  public void verifySearchCustomer_01() {
		  driver.findElement(By.linkText("Search customer")).click();
		  WebElement search = driver.findElement(By.xpath("//input[@name = \"acc_no\"]"));
		  search.sendKeys(custAccNo3);
		  driver.findElement(By.xpath("//input[@name = \"submit\"]")).click();
		  String name = driver.findElement(By.xpath("//table[2]/tbody/tr[2]/td[2]")).getText();
		  Assert.assertEquals(name, custName);
	  }
	  
	  @Test(priority = 2)
	  public void verifySearchCustomer_02() {
		  driver.findElement(By.linkText("Search customer")).click();
		  driver.findElement(By.xpath("//input[@name = \"acc_no\"]")).sendKeys("123456");
		  driver.findElement(By.xpath("//input[@name = \"submit\"]")).click();
		  String name = driver.findElement(By.xpath("//table[2]/tbody/tr[2]/td[2]")).getText();
		  Assert.assertEquals(name, "");
	  }
	  
	  @Test(priority = 3)
	  public void verifyViewCustomer() {
		  driver.findElement(By.linkText("View All Customer")).click();
		  String accNo1 = driver.findElement(By.xpath("//table[1]/tbody/tr[2]/td[2]")).getText();
		  String accNo2 = driver.findElement(By.xpath("//table[1]/tbody/tr[3]/td[2]")).getText();
		  String accNo3 = driver.findElement(By.xpath("//table[1]/tbody/tr[4]/td[2]")).getText();
		  
		  Assert.assertEquals(accNo1, custAccNo1);
		  Assert.assertEquals(accNo2, custAccNo2);
		  Assert.assertEquals(accNo3, custAccNo3);
	  }
	  
	  @Test(priority = 4)
	  public void verifyAddCustomer() throws InterruptedException {
		  driver.findElement(By.linkText("Add Customer")).click();
		  driver.findElement(By.xpath("//input[@name = \"name\"]")).sendKeys(cName);
		  driver.findElement(By.xpath("//input[@name = \"acc_no\"]")).sendKeys(cAccNo);
		  driver.findElement(By.xpath("//input[@name = \"age\"]")).sendKeys(cAge);
		  driver.findElement(By.xpath("//textarea")).sendKeys(cAddr);
		  driver.findElement(By.xpath("//input[@name = \"mobile\"]")).sendKeys(cMobNo);
		  driver.findElement(By.xpath("//input[@name = \"email\"]")).sendKeys(cEmail);
		  driver.findElement(By.xpath("//input[@name = \"pwd\"]")).sendKeys(cPassword);
		  driver.findElement(By.xpath("//input[@name = \"dob\"]")).sendKeys(cDob);
		  
		  driver.findElement(By.xpath("//input[@name = \"submit\"]")).click();
		  
		  Thread.sleep(2000);
		  Alert al = driver.switchTo().alert();
		  al.accept();
		  
		  driver.findElement(By.linkText("Admin Home")).click();
		  Assert.assertEquals(driver.getTitle(), "Admin Homepage");
	  }
	  
	  @AfterMethod
	  public void afterMethod() {
		  driver.findElement(By.linkText("Logout")).click();
		  System.out.println("LogOut...");
	  }
	  
	  @AfterClass
	  public void afterClass() {
		  System.out.println("Quiting Browser");
			driver.quit();
	  }
	  
	  
	  public String logIn(String email, String password) throws InterruptedException {
		  Assert.assertEquals(driver.getTitle(), "Admin Login - Online Banking");
		  
		  WebElement username = driver.findElement(By.name("uname"));
		  username.sendKeys(email);
		  
		  WebElement userpwd = driver.findElement(By.name("pwd"));
		  userpwd.sendKeys(password);
		  
		  driver.findElement(By.name("submitBtn")).click();
		  Thread.sleep(2000);
		  String title = driver.getTitle();
		  return title;
	  }
}
