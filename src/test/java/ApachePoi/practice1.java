package ApachePoi;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class practice1 {

	@Test
	public void test1()
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.cricketworldcup.com");

		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement rejectCookie = driver.findElement(By.xpath("//div[@class='onetrust-banner-options' and contains(.,'Reject All')]"));
		wait.until(ExpectedConditions.visibilityOf(rejectCookie));
		rejectCookie.click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String expSection = "ODI - TEAM RANKINGS";
		WebElement actSection = driver.findElement(By.xpath("//h4[contains(.,'odi') and contains(.,' -  team Rankings')]"));
		
		for(;;)
		{
			js.executeScript("window.scrollBy(0,30)");
			if(expSection.equalsIgnoreCase(actSection.getText()))
			{
				break;
			}
		}
	}
}
