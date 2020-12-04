//package test;
//
//import java.util.concurrent.TimeUnit;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//public class FirstTest {
//	WebDriver driver;
//	
//	@BeforeMethod
//	public void setUp() {
//		System.setProperty("webdriver.chrome.driver", "/home/lucifer/eclipse-workspace/Automation/chromedriver_linux64/chromedriver");
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.manage().deleteAllCookies();
//		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.get(" www.caloriemama.ai/api");
//	}
//	
//	@Test
//	public void getFoodItem1() throws InterruptedException {
//	
//		WebElement fileUpload = driver.findElement(By.xpath("//*[@id=\"try\"]/div/div/div[1]/div[2]/a/input"));
//		fileUpload.sendKeys("/media/lucifer/New Volume/PRABA/Code/python/pizza.jpeg");
//		Thread.sleep(3000);
//		System.out.println("Image Uploaded");
//		WebElement foodname = driver.findElement(By.xpath("//*[@id=\"test-result\"]/div[2]/div[1]/h4/div[2]"));
//		System.out.println(foodname.getText());
//	}
//	
////	@Test
////	public void getFoodItem2() throws InterruptedException {
////		WebElement fileUpload = driver.findElement(By.xpath("//*[@id=\"try\"]/div/div/div[1]/div[2]/a/input"));
////		fileUpload.sendKeys("/media/lucifer/New Volume/PRABA/Code/python/dog.jpg");
////		Thread.sleep(3000);
////		System.out.println("Image Uploaded");
////	}
////	@Test(dependsOnMethods = "getFoodItem2")
////	public void getItemname() {
////		WebElement foodname = driver.findElement(By.xpath("//*[@id=\"test-result\"]/div[2]/div[1]/h4/div[2]"));
////		System.out.println(foodname.getText());
////	}
//	
//	@Test
//	public void getFoodItem3() throws InterruptedException {
//		WebElement fileUpload = driver.findElement(By.xpath("//*[@id=\"try\"]/div/div/div[1]/div[2]/a/input"));
//		fileUpload.sendKeys("/media/lucifer/New Volume/PRABA/Code/python/taco.jpg");
//		Thread.sleep(3000);
//		System.out.println("Image Uploaded");
//		WebElement foodname = driver.findElement(By.xpath("//*[@id=\"test-result\"]/div[2]/div[1]/h4/div[2]"));
//		System.out.println(foodname.getText());
//	}
//	
//	@AfterMethod
//	public void quitBrowser() {
//		System.out.println("Quiting Browser");
//		driver.quit();
//	}
//}