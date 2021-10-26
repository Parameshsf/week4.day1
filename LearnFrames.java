package week4.day1;

import java.time.Duration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LearnFrames {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		driver.get("http://leafground.com/pages/frame.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Find the frame using xpath
		WebElement frame = driver.findElement(By.xpath("(//div[@id='wrapframe']/iframe)[1]"));
		driver.switchTo().frame(frame);
		driver.findElement(By.xpath("//button[@id='Click']")).click();
		
		driver.switchTo().defaultContent();// It will take the control to the main page and comes out of a frame

		WebElement secondFrame = driver.findElement(By.xpath("(//div[@id='wrapframe']/iframe)[2]"));
		driver.switchTo().frame(secondFrame);
		driver.switchTo().frame("frame2");
		driver.findElement(By.id("Click1")).click();
		//driver.findElement(By.xpath("//button[@id='Click1']")).click();
		driver.switchTo().defaultContent();
		
		WebElement thirdFrame = driver.findElement(By.xpath("(//div[@id='wrapframe']/iframe)[3]"));
		driver.switchTo().frame(thirdFrame);
		driver.switchTo().frame("frame2");
		String text = driver.findElement(By.xpath("//body[text()='Find total number of frames.']")).getText();
		System.out.println("Text from third frame "+text);
		driver.switchTo().defaultContent();
		
		
		int count = 0;
		List<WebElement> elements = driver.findElements(By.xpath("//div[@id='wrapframe']/iframe"));
		for (WebElement webElement : elements) {
			count = count+1;
			
		}
		System.out.println("The count of iframes in a web page is "+count);
		
		
	}

}
