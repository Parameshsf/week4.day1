package week4.day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IRCTCClassRoomExercise {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);

		driver.get("https://www.irctc.co.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		driver.findElement(By.xpath("(//i[@class='fa fa-align-justify'])[1]")).click();
		driver.findElement(By.xpath("(//label[text()='FLIGHTS'])[1]")).click();
		Thread.sleep(5000);
		System.out.println("The Window Title " + driver.getTitle()); // This gives the first page title not the current
																		// page
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> lstWindowHandles = new ArrayList<String>(windowHandles);
		driver.switchTo().window(lstWindowHandles.get(1));
		System.out.println("The Window Title after Flights clicked " + driver.getTitle());
		String printEmail = driver.findElement(By.xpath("(//a[@href='mailto:flights@irctc.co.in'])[1]")).getText();
		
		System.out.println("Customer Care Email ID is " + printEmail);

		driver.switchTo().window(lstWindowHandles.get(0));
		String firstPage = driver.getTitle();
		if (firstPage.contains("IRCTC Next Generation")) {
			System.out.println("The current Window Title is " + firstPage);
			driver.close();
		}

	}

}
