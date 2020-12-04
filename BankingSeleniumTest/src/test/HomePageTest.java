package test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class HomePageTest {
	WebDriver driver;

  @BeforeClass
  public void setUp() {
	  System.setProperty("webdriver.chrome.driver", "/home/lucifer/eclipse-workspace/Automation/chromedriver_linux64/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://localhost/Banking/index.php");
	}
  
  @BeforeMethod
  public void beforeMethod() {
	  
  }

  @Test
  public void verifyTitle() {
	  String title = driver.getTitle();
	  Assert.assertEquals(title, "Online Banking");
  }
  
  @Test
  public void verifyHeader() {
	  boolean homeflag = driver.findElement(By.linkText("Home")).isDisplayed();
	  boolean featureflag = driver.findElement(By.linkText("Features")).isDisplayed();
	  boolean contatUsflag = driver.findElement(By.id("last")).isDisplayed();
	  
	  Assert.assertTrue(homeflag);
	  Assert.assertTrue(featureflag);
	  Assert.assertTrue(contatUsflag);
  
  }
  @Test
  public void checkFooter() {
	  boolean footerflag = driver.findElement(By.className("footer")).isDisplayed();
	  boolean featureflag = driver.findElement(By.linkText("Features")).isDisplayed();
	  boolean contactflag = driver.findElement(By.linkText("Contact")).isDisplayed();
	  
	  Assert.assertTrue(footerflag);
	  Assert.assertTrue(featureflag);
	  Assert.assertTrue(contactflag);
  }
  
  @Test
  public void checkLoginPresent() {
	  boolean unameflag = driver.findElement(By.name("uname")).isDisplayed();
	  boolean pwdflag = driver.findElement(By.name("pwd")).isDisplayed();
	  
	  Assert.assertTrue(unameflag);
	  Assert.assertTrue(pwdflag);
  }
  
  @AfterMethod
  public void afterMethod() {
	  
  }

  @AfterClass
  public void quitBrowser() {
	  System.out.println("Quiting Browser");
		driver.quit();
  }

}
