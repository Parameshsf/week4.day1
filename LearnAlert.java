package week4.day1;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LearnAlert {
	
	

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leafground.com/pages/Alert.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.findElement(By.xpath("//button[text()='Alert Box']")).click();
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		
		driver.findElement(By.xpath("//button[text()='Confirm Box']")).click();
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		
		String text2 = driver.findElement(By.xpath("//p[text()='You pressed OK!']")).getText();
		if (text2.contains("You pressed OK!")) {
			System.out.println("You have pressed OK button");
			
		}
		else {
			System.out.println("You pressed Cencel button");
		}
		
		driver.findElement(By.xpath("//button[text()='Prompt Box']")).click();
		Thread.sleep(3000);
		driver.switchTo().alert().sendKeys("Testing Alert");
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//button[text()='Line Breaks?']")).click();
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		System.out.println("Text from Line Breaks alert box "+text);
		alert.accept();
		Thread.sleep(3000);
		
		//Sweet Alert can be inpected
		driver.findElement(By.xpath("//button[@id='btn']")).click();	
		String text3 = driver.findElement(By.xpath("//div[text()='Happy Coding!']")).getText();
		System.out.println("Text from Sweet ALert "+text3);
		driver.findElement(By.xpath("//button[text()='OK']")).click();
		
		driver.close();
		
		

	}

}
