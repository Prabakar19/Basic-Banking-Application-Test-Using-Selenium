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

public class CustomerBankingTest {
	WebDriver driver;
	String recAccNo = "300483";
	
	
  @BeforeClass
  public void SetUp() {
	  System.setProperty("webdriver.chrome.driver", "/home/lucifer/eclipse-workspace/Automation/chromedriver_linux64/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://localhost/Banking/index.php");

	}
  @BeforeMethod
  public void beforeMethod() throws InterruptedException {
	  System.out.println("beforemethod");
		login("praphakar119@gmail.com","12345678");
  }
  
  @Test(priority = 1)
  public void verifyDeposite() {
	  Assert.assertEquals(driver.getTitle(), "Customer Homepage");
	  double b = checkBalance();
	  System.out.println(b);
	  
	  driver.findElement(By.linkText("Deposite")).click();
	  driver.findElement(By.xpath("//input[@name = \"amt\"]")).sendKeys("100");
	  driver.findElement(By.xpath("//input[@name = \"submit\"]")).click();
	  Alert al = driver.switchTo().alert();
	  al.accept();
	  
	  double b1 = checkBalance();
	  System.out.println(b1);
	  
	  Assert.assertEquals(b1-b, 100);
  }

  
  @Test(priority = 3)
  public void verifyWithdraw_01() {
	  Assert.assertEquals(driver.getTitle(), "Customer Homepage");
	  Assert.assertEquals(withdraw("100"), 100);
  }
  
  @Test(priority = 4)
  public void verifyWithdraw_02() {
	  Assert.assertEquals(driver.getTitle(), "Customer Homepage");
	  double amt = withdraw("1000000");
	  Assert.assertEquals(amt, 1000000);
  }
  
  @Test(priority = 2)
  public void verifyMoneyTransfer() throws InterruptedException {
	  driver.findElement(By.linkText("Logout")).click();
	  login("nandanbala@123.in","12345");
	  Assert.assertEquals(driver.getTitle(), "Customer Homepage");
	  double recAmt = checkBalance();
	  driver.findElement(By.linkText("Logout")).click();
	  
	  login("praphakar119@gmail.com","12345678");
	  Assert.assertEquals(driver.getTitle(), "Customer Homepage");
	  double b = checkBalance();
	  System.out.println(b);
	  
	  driver.findElement(By.linkText("Transfer Funds")).click();
	  driver.findElement(By.xpath("//input[@name = \"acc\"]")).sendKeys(recAccNo);
	  driver.findElement(By.xpath("//input[@name = \"amt\"]")).sendKeys("500");
	  driver.findElement(By.xpath("//input[@name = \"submit\"]")).click();
	  Alert al = driver.switchTo().alert();
	  al.accept();
	  
	  double b1 = checkBalance();
	  System.out.println(b1);
	  
	  Assert.assertEquals(b-b1, 500);
	  driver.findElement(By.linkText("Logout")).click();
	  
	  login("nandanbala@123.in","12345");
	  Assert.assertEquals(driver.getTitle(), "Customer Homepage");
	  double recAmt1 = checkBalance();
	  
	  Assert.assertEquals(recAmt1-recAmt, 500);
  }

  @AfterMethod
	public void afterMethod() throws InterruptedException {
		WebElement logout = driver.findElement(By.linkText("Logout"));
		logout.click();
		Thread.sleep(2000);
		System.out.println("Logout...");
	}
	
	@AfterClass
	  public void quitBrowser() {
		  System.out.println("Quiting Browser...");
			driver.quit();
	  }
	
	public double checkBalance() {
		driver.findElement(By.linkText("Check Balance")).click();
		  String balance = driver.findElement(By.xpath("//h1[1]")).getText();
		  balance = balance.substring(balance.lastIndexOf(" ")+1);
		  return Double.parseDouble(balance);
	}

	public double withdraw(String amt) {
		double b = checkBalance();
		
		  System.out.println(b);
		  
		  driver.findElement(By.linkText("Withdraw")).click();
		  driver.findElement(By.xpath("//input[@name = \"amt\"]")).sendKeys(amt);
		  driver.findElement(By.xpath("//input[@name = \"submit\"]")).click();
		  Alert al = driver.switchTo().alert();
		  al.accept();
		  
		  double b1 = checkBalance();
		  System.out.println(b1);
		  Assert.assertTrue(b1>0);
		  return b-b1;
	}
	
   public void login(String email, String password) throws InterruptedException {
		WebElement username = driver.findElement(By.name("uname"));
		  username.sendKeys(email);
		  
		  WebElement userpwd = driver.findElement(By.name("pwd"));
		  userpwd.sendKeys(password);
		  
		  driver.findElement(By.name("submitBtn")).click();
		  Thread.sleep(2000);
	}
}
