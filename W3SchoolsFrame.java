package week4.day1;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class W3SchoolsFrame {
	
	ChromeDriver driver = new ChromeDriver();
	
	public void frameTest() throws InterruptedException {
		// TODO Auto-generated method stub
		driver.get("https://www.w3schools.com/js/tryit.asp?filename=tryjs_confirm");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//Getting in to frame
		WebElement frame1 = driver.findElement(By.id("iframeResult"));
		driver.switchTo().frame(frame1).findElement(By.xpath("//button[text()='Try it']")).click();
		
		//getting in to alert displayed
		Alert alert1 = driver.switchTo().alert();
		alert1.accept();
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		
		//Switching back to Frame to print the alert button action value
		String alertActionText = driver.switchTo().frame(frame1).findElement(By.xpath("//p[@id='demo']")).getText();
		System.out.println("Action performed at Alert is '"+alertActionText+"'");
		
		driver.quit();
		
		

	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		W3SchoolsFrame obj = new W3SchoolsFrame();
		obj.frameTest();
		

	}

}
