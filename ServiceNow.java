package week4.day1;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

//		Opening ServiceNow Application
		driver.get("https://dev112979.service-now.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

//		SwitchTo frame by using index and Login ServiceNow Application
		driver.switchTo().frame(0);
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("ft0IiI0UGnQj");
		driver.findElement(By.id("sysverb_login")).click();

//		Switch back frame to main page and search "incident" in Filter Navigator then click All
		driver.switchTo().defaultContent();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("filter")).sendKeys("incident");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement eleSelectAll = driver
				.findElement(By.xpath("(//div[@class='sn-widget-list-content']/div[text()='All'])[2]"));
		wait.until(ExpectedConditions.visibilityOf(eleSelectAll));
		eleSelectAll.click();

//		SwitchTo frame and Click New after clicked Switch back to main page
		wait.until(ExpectedConditions
				.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//iframe[@id='gsft_main']"))));
		driver.findElement(By.id("sysverb_new")).click();
		driver.switchTo().defaultContent();

//		SwitchTo frame and click caller and select caller list by using windowHandeles
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("gsft_main")));
		driver.findElement(By.id("lookup.incident.caller_id")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowHandlesList.get(1));
		driver.findElement(By.xpath("(//a[@class='glide_ref_item_link'])[2]")).click();

//		Switch back to parent window and SwitchTo frame then clicked short description
		driver.switchTo().window(windowHandlesList.get(0));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("gsft_main")));
		driver.findElement(By.id("lookup.incident.short_description")).click();

//		By using windowHandles selected the short description
		Set<String> windowHandles2 = driver.getWindowHandles();
		List<String> windowHandlesList2 = new ArrayList<String>(windowHandles2);
		driver.switchTo().window(windowHandlesList2.get(1));
		driver.findElement(By.linkText("Issue with a web page")).click();

//		Switch back to parent window and SwitchTo frame then get the incident number
		driver.switchTo().window(windowHandlesList2.get(0));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("gsft_main")));
		String incidentNumber = driver.findElement(By.id("incident.number")).getAttribute("value");
		System.out.println("Created Incident Number is " + incidentNumber);

//		Click Submit Button and switch back from frame to main page
		driver.findElement(By.id("sysverb_insert_bottom")).click();
		driver.switchTo().defaultContent();

//		SwitchTo frame and search the Incident number then switch back to main page
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("gsft_main")));
		driver.findElement(By.xpath("//span[@id='incident_hide_search']//input[@placeholder='Search']"))
				.sendKeys(incidentNumber + Keys.ENTER);
		driver.switchTo().defaultContent();

//		SwitchTo frame get the Text of Incident Number and check whether created Inc matching with listed INC number
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("gsft_main")));
		String getIncidentNum = driver.findElement(By.xpath("//td[@class='vt']/a[@class='linked formlink']")).getText();
		driver.switchTo().defaultContent();
		if (getIncidentNum.equals(incidentNumber)) {
			System.out.println("Incident Created Successfully with the Incident Number is " + incidentNumber);
			File screenShotOfInc = driver.getScreenshotAs(OutputType.FILE);
			File saveScreenShot = new File("./serviceNowScreenShot/INC.png");
			FileUtils.copyFile(screenShotOfInc, saveScreenShot);
		} else {
			System.out.println("Incident Not Created Successfully as Expected. Created Incident Number "
					+ incidentNumber + " not found in the List ");
			File screenShotOfInc = driver.getScreenshotAs(OutputType.FILE);
			File saveScreenShot = new File("./serviceNowScreenShot/INC.png");
			FileUtils.copyFile(screenShotOfInc, saveScreenShot);
		}

//		Logout the ServiceNow Application and Close the Browser.
		driver.findElement(By.id("user_info_dropdown")).click();
		driver.findElement(By.linkText("Logout")).click();
		//driver.close();
	}

	}


