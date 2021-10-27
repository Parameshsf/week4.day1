package week4.day1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FramesExercise1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//Find a webElement inside a frame
		WebElement frame1 = driver.findElement(By.id("frame1"));
		driver.switchTo().frame(frame1);
		driver.findElement(By.xpath("//b[@id='topic']//following-sibling::input")).sendKeys("Frames Automation");
		//driver.switchTo().defaultContent();
		
		//Find a Checkbox inside a frame
		WebElement frame3 = driver.findElement(By.id("frame3"));
		driver.switchTo().frame(frame3);
		driver.findElement(By.xpath("//b[text()='Inner Frame Check box :']//following-sibling::input")).click();
		driver.switchTo().defaultContent();
		
		//Find a dropdown inside a frame
		WebElement frame2 = driver.findElement(By.id("frame2"));
		driver.switchTo().frame(frame2);
		//WebElement animalsDD = driver.findElement(By.xpath("//select[@id='animals']"));
		Select animalsDD = new Select(driver.findElement(By.xpath("//select[@id='animals']")));
		animalsDD.selectByIndex(1);
		System.out.println(animalsDD.getOptions().get(0).getText());
		driver.switchTo().defaultContent();
		
		driver.quit();
	}

}
