package test;

import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
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

public class UserLogInTest {
	WebDriver driver;
  @BeforeMethod
  public void beforeMethod() {
	  System.setProperty("webdriver.chrome.driver", "/home/lucifer/eclipse-workspace/Automation/chromedriver_linux64/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://localhost/Banking/index.php");
  }
  
  @Test
  public void LogInCheck_01() throws InterruptedException {

	  String result = logInPass("praphakar119@gmail.com", "12345678");
	  Assert.assertEquals(result,"WELCOME prabakar");
  }
  @Test
  public void LogInCheck_02() throws InterruptedException {
	  String result = logInPass("nandanbala@123.in", "12345");
	  Assert.assertEquals(result,"WELCOME Nandan Bala");
  }
  @Test
  public void LogInCheck_03() throws InterruptedException {
	  
	  String result = logInPass("soumya123@gmail.com", "12345");
	  Assert.assertEquals(result,"WELCOME Soumya Racha");
  }
  
  @Test
  public void LogInCheckFailed_01() throws InterruptedException {
	  boolean failed = logInFail("praphakar119", "123678");
	  Assert.assertTrue(failed);
	  
  }
  @Test
  public void LogInCheckFailed_02() throws InterruptedException {
	  
	  boolean failed = logInFail("soumya123@gmail", "1");
	  Assert.assertTrue(failed);
  }
  @Test
  public void LogInCheckFailed_03() throws InterruptedException {
	  
	  boolean failed = logInFail("soumya123@gmail.com", "12345678");
	  Assert.assertTrue(failed);
  }
  

  @AfterMethod
  public void quitBrowser() {
	  System.out.println("Quiting Browser");
		driver.quit();
  }
  
  public String logInPass(String email, String password) throws InterruptedException {
	  WebElement username = driver.findElement(By.name("uname"));
	  username.sendKeys(email);
	  
	  WebElement userpwd = driver.findElement(By.name("pwd"));
	  userpwd.sendKeys(password);
	  
	  driver.findElement(By.name("submitBtn")).click();
	  Thread.sleep(3000);
	  Assert.assertEquals(driver.getTitle(), "Customer Homepage");
	  WebElement result = driver.findElement(By.xpath("//h1[1]"));
	  return result.getText();
  }
  
  public boolean logInFail(String email, String password) throws InterruptedException {
	  WebElement username = driver.findElement(By.name("uname"));
	  username.sendKeys(email);
	  
	  WebElement userpwd = driver.findElement(By.name("pwd"));
	  userpwd.sendKeys(password);
	  
	  driver.findElement(By.name("submitBtn")).click();
	  Thread.sleep(2000);
	  boolean failed = driver.findElement(By.xpath("//span[@class = \"caption\"]")).isDisplayed();
	  return failed;
  }
}
