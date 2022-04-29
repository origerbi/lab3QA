package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.apache.logging.log4j.*;

public class reviewsPage {
	private WebDriver driver;
	
	public reviewsPage(WebDriver driver) {
		this.driver = driver;
	}
	
    public String checkReview(String text, Logger logger) throws InterruptedException {
        driver.get("https://www.ticketor.com/demo/reviews");
        driver.findElement(By.id("ui-id-3")).click();
        Thread.sleep(1500);
        String gotText = driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[2]/div[2]/div[2]/div[2]/div[1]")).getText();
        return gotText;
    }

}
