package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerProfileTest {
	WebDriver driver;
	UserLogInTest login;
	String testName = "prabakar";
	String testAccNo = "300482";
	String testAge = "22";
	String testAddress = "29, new street, chennai.";
	String testGender = "male";
	String testDob = "20-07-1998";
	String testEmail = "praphakar119@gmail.com";
	String testPhoneNo = "968441160";
	String testAccType = "S";
	String testPassword = "12345678";
	
	
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
	  public void beforeMethod() throws InterruptedException {
		System.out.println("beforemethod");
		login("praphakar119@gmail.com","12345678");
		
	  }
	@Test(priority = 1)
	public void verifyPersonalDetails() throws InterruptedException {
		System.out.println("test");
		WebElement personalDetails = driver.findElement(By.linkText("Personal Details"));
		personalDetails.click();
		String name = driver.findElement(By.xpath("//table/tbody/tr[1]/td[2]")).getText();
		String accNo = driver.findElement(By.xpath("//table/tbody/tr[2]/td[2]")).getText();
		String gender = driver.findElement(By.xpath("//table/tbody/tr[3]/td[2]")).getText();
		String age = driver.findElement(By.xpath("//table/tbody/tr[4]/td[2]")).getText();
		String dob = driver.findElement(By.xpath("//table/tbody/tr[5]/td[2]")).getText();
		String phoneNo = driver.findElement(By.xpath("//table/tbody/tr[6]/td[2]")).getText();
		String email = driver.findElement(By.xpath("//table/tbody/tr[7]/td[2]")).getText();
		String accType = driver.findElement(By.xpath("//table/tbody/tr[8]/td[2]")).getText();
		String address = driver.findElement(By.xpath("//table/tbody/tr[9]/td[2]")).getText();
		
		Assert.assertEquals(name, testName);
		Assert.assertEquals(accNo, testAccNo);
		Assert.assertEquals(gender, testGender);
		Assert.assertEquals(age, testAge);
		Assert.assertEquals(dob, testDob);
		Assert.assertEquals(phoneNo, testPhoneNo);
		Assert.assertEquals(email, testEmail);
		Assert.assertEquals(accType, testAccType);
		Assert.assertEquals(address, testAddress);
	}
	
	@Test(priority = 2)
	public void checkChangePasswordPass() throws InterruptedException {
		driver.findElement(By.linkText("Change Password")).click();
		
		WebElement oldpass = driver.findElement(By.xpath("//input[@name = \"old_password\"]"));
		oldpass.sendKeys(testPassword);
		WebElement newpass = driver.findElement(By.xpath("//input[@name = \"new_password\"]"));
		newpass.sendKeys("12345");
		WebElement againpass = driver.findElement(By.xpath("//input[@name = \"again_password\"]"));
		againpass.sendKeys("12345");
		
		driver.findElement(By.xpath("//input[@name = \"change_password\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Logout")).click();
		login(testEmail, "12345");
		Assert.assertEquals(driver.getTitle(), "Customer Homepage");
		
		//Test completed
		//Resetting password back to original
		driver.findElement(By.linkText("Change Password")).click();
		driver.findElement(By.xpath("//input[@name = \"old_password\"]")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name = \"new_password\"]")).sendKeys(testPassword);
		driver.findElement(By.xpath("//input[@name = \"again_password\"]")).sendKeys(testPassword);
		driver.findElement(By.xpath("//input[@name = \"change_password\"]")).click();
	}
	
	@Test(priority = 3)
	public void checkChangePasswordFail() throws InterruptedException {
		String wrongPassword = "abcde";
		driver.findElement(By.linkText("Change Password")).click();
		
		WebElement oldpass = driver.findElement(By.xpath("//input[@name = \"old_password\"]"));
		oldpass.sendKeys(wrongPassword);
		WebElement newpass = driver.findElement(By.xpath("//input[@name = \"new_password\"]"));
		newpass.sendKeys("12345");
		WebElement againpass = driver.findElement(By.xpath("//input[@name = \"again_password\"]"));
		againpass.sendKeys("12345");
		
		driver.findElement(By.xpath("//input[@name = \"change_password\"]")).click();
		Thread.sleep(1000);
		Assert.assertEquals(driver.getTitle(), "Change Password");
	}
	@AfterMethod
	public void afterMethod() throws InterruptedException {
		WebElement logout = driver.findElement(By.linkText("Logout"));
		logout.click();
		Thread.sleep(1000);
		System.out.println("Logout...");
	}
	
	@AfterClass
	  public void quitBrowser() {
		  System.out.println("Quiting Browser...");
			driver.quit();
	  }
	
	public void login(String email, String password) throws InterruptedException {
		WebElement username = driver.findElement(By.name("uname"));
		  username.sendKeys(email);
		  
		  WebElement userpwd = driver.findElement(By.name("pwd"));
		  userpwd.sendKeys(password);
		  
		  driver.findElement(By.name("submitBtn")).click();
		  Thread.sleep(3000);
	}
}
