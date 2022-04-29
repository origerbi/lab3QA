package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.apache.logging.log4j.*;

public class myReviewsPage {
	private WebDriver driver;
	
	public myReviewsPage(WebDriver driver) {
		this.driver = driver;
	}
	
    public void writeReview(int star, String text, Logger logger) throws InterruptedException {
        driver.get("https://www.ticketor.com/demo/myreviews");
        logger.info("Moving to my reviews page");
		if (star < 1 || star > 5)
			logger.warn("star out of bounds");
		star = 6-star;
        driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[2]/div[1]/div/div/div[1]/label[" + star + "]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[2]/div[1]/div/div/div[3]/textarea")).sendKeys(text);
        driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[1]/div")).click(); // click to save review
		Thread.sleep(1500);
    }

}
