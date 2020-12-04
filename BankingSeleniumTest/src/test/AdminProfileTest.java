package test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class AdminProfileTest {
  WebDriver driver;

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

  @Test
  public void changePasswordPassTest() throws InterruptedException {
	  String password = "12345";
	  String check = changePassword(password, "12345678");
	  Assert.assertEquals(check, "Admin Homepage");
	  changePassword("12345678", "12345");
  }
  
  @Test
  public void changePasswordFailTest() throws InterruptedException {
	  String password = "abcde";
	  String check = changePassword(password, "myname");
	  Assert.assertEquals(check, "Change Password");
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

  public String changePassword(String pwd, String newpwd) throws InterruptedException {
	  driver.findElement(By.linkText("Change password")).click();
	  WebElement oldpass = driver.findElement(By.xpath("//input[@name = \"old_password\"]"));
		oldpass.sendKeys(pwd);
		WebElement newpass = driver.findElement(By.xpath("//input[@name = \"new_password\"]"));
		newpass.sendKeys(newpwd);
		WebElement againpass = driver.findElement(By.xpath("//input[@name = \"again_password\"]"));
		againpass.sendKeys(newpwd);
		
		driver.findElement(By.xpath("//input[@name = \"change_password\"]")).click();
		Thread.sleep(1000);
		return driver.getTitle();
	  
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
