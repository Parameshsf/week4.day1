package week4.day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {
	ChromeDriver driver = new ChromeDriver();
	
	public void mergeContact() throws InterruptedException {
		// TODO Auto-generated method stub
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//Login to the Application
		driver.findElement(By.id("username")).sendKeys("Demosalesmanager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		
		//Click Contacts Menu
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		//Click Merge Contacts Menu
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
		driver.findElement(By.xpath("//img[@src='/images/fieldlookup.gif']")).click();
		Thread.sleep(5000);
		//A new window opens. Get the window handle id to work on this new window
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> winHan = new ArrayList<String>(windowHandles);
		System.out.println("The window Handle size is "+winHan.size());
		System.out.println("The window handle 1 value "+winHan.get(1));
		driver.switchTo().window(winHan.get(1));
		
		//Selecting first result displayed
		WebElement toContactId = driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]"));
		toContactId.click();
		Thread.sleep(3000);

		driver.switchTo().window(winHan.get(0));
		driver.findElement(By.xpath("(//img[@src='/images/fieldlookup.gif'])[2]")).click();
		Set<String> windowHandles2 = driver.getWindowHandles();
		List<String> winHan2 = new ArrayList<String>(windowHandles2);
		driver.switchTo().window(winHan2.get(1));
		//Selecting second result displayed
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[2]")).click();
		Thread.sleep(3000);
		driver.switchTo().window(winHan2.get(0));
		driver.findElement(By.xpath("//a[text()='Merge']")).click();
		Thread.sleep(2000);
		//Working on Alert
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(5000);
		String title = driver.getTitle();
		System.out.println("The title of the page: '"+title+"'");
		
		Thread.sleep(2000);
		
		driver.quit();
		
		
		

	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		MergeContact obj = new MergeContact();
		obj.mergeContact();

	}

}
