package week4.day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowHandlesSample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		
		driver.get("http://leafground.com/pages/Window.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.findElement(By.xpath("//button[text()='Open Home Page']")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> lstHandles = new ArrayList<String>(windowHandles);
		driver.switchTo().window(lstHandles.get(0));
		System.out.println("The window title "+driver.getTitle());
		
		//driver.close();
		
		
		
	}

}
