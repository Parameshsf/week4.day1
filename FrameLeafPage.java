package week4.day1;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FrameLeafPage {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leafground.com/pages/frame.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//Take the screenshot of click me button of first frame
		WebElement frame1 = driver.findElement(By.xpath("//div[@id='wrapframe']/iframe"));
		driver.switchTo().frame(frame1);
		WebElement firstClickMe = driver.findElement(By.xpath("//button[@id='Click']"));
		firstClickMe.click();
		
		File source = firstClickMe.getScreenshotAs(OutputType.FILE);
		File dest = new File("./Screenshot/firstClickMe.png");
		FileUtils.copyFile(source, dest);
		driver.switchTo().defaultContent();
		
		//Find the count of frame
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		System.out.println("The count of frames in a web page is "+frames.size());
		
		driver.quit();
		

	}

}
