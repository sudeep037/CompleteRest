package ApachePoi;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class dataProvider {

	WebDriver driver;
	
	@BeforeMethod
	public void setUP()
	{
		WebDriverManager.firefoxdriver().setup();
	    driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test(dataProvider="LoginData")
	public void loginTest(String userName, String password, String result)
	{
		driver.get("http://106.51.87.42:9007/");
		WebElement unText = driver.findElement(By.xpath("//input[@id='email']"));
		unText.clear();
		unText.sendKeys(userName);
		
		WebElement unPass = driver.findElement(By.xpath("//input[@id='password']"));
		unPass.clear();
		unPass.sendKeys(password);
		
		WebElement unLogin = driver.findElement(By.xpath("//button[@type='submit']"));
		unLogin.click();
		
		String expTitle = "Doctor Dashboard - Hospital Management System";
		String actTitle = driver.getTitle();
		
		if(expTitle.equals("valid"))
		{
			if(expTitle.equals(actTitle))
			{
				driver.findElement(By.xpath("//a[contains(.,'Log Out')]")).click();
				Assert.assertTrue(true);
			}
			else 
			{
				Assert.assertTrue(false);
			}
		}
		
		else if(expTitle.equals("Invalid"))
		{
			if(expTitle.equals(actTitle))
			{
				driver.findElement(By.xpath("//a[contains(.,'Log Out')]")).click();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		
		
	}
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		 /*String loginData[][] = {
				{"aunty@gmail.com","12345678", "valid"},
				{"auntY1@gmail.com","12345678", "Invalid"},
				{"aunty@gmail.com", "12345879", "Invalid"}
		}; */
		
		String path = "./testData/hmsData1.xlsx";
		
		ExcelUtilityFile xlUtil = new ExcelUtilityFile(path);
		int totalRow = xlUtil.getRowCount("Sheet1");
		int totalCol = xlUtil.getCellCount("Sheet1", 1);
		
		String loginData[][]=new String[totalRow][totalCol];
		
		for(int i = 1; i<=totalRow; i++)
		{
			for(int j = 0; j<totalCol; j++)
			{
				loginData[i-1][j]=xlUtil.getCellData("Sheet1", i, j);
			}
		}
		
		return loginData;
		
	}
	
	@AfterClass
	void tearDown()
	{
		driver.close();
	}
}
