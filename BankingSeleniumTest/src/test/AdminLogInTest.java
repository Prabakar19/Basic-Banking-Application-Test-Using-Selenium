package test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class AdminLogInTest {
  WebDriver driver;
  @BeforeMethod
  public void SetUp() {
	  System.setProperty("webdriver.chrome.driver", "/home/lucifer/eclipse-workspace/Automation/chromedriver_linux64/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://localhost/Banking/adminlogin.php");
  }

  @Test
  public void LogInCheckPass_01() throws InterruptedException {
	  String pass = logIn("lucifer19", "12345");
	  Assert.assertEquals(pass, "Admin Homepage");
	  driver.findElement(By.linkText("Logout")).click();
	  
  }
  
  @Test
  public void LogInCheckFailed_01() throws InterruptedException {
	  String failed = logIn("praphakar119", "123678");
	  Assert.assertEquals(failed, "Admin Login - Online Banking");
	  
  }
  @Test
  public void LogInCheckFailed_02() throws InterruptedException {
	  
	  String failed = logIn("soumya123@gmail", "1");
	  Assert.assertEquals(failed, "Admin Login - Online Banking");
  }
  @Test
  public void LogInCheckFailed_03() throws InterruptedException {
	  
	  String failed = logIn("soumya123@gmail.com", "12345678");
	  Assert.assertEquals(failed, "Admin Login - Online Banking");
  }
  
  @AfterMethod
  public void quitBrowser() {
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
