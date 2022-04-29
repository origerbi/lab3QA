package pages;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyReviewsPage {
	private WebDriver driver;
	
	public MyReviewsPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "/html/body/div[1]/div/form/div[3]/div/div[2]/div[1]/div/div/div[3]/textarea") WebElement reviewText;
	@FindBy(xpath = "/html/body/div[1]/div/form/div[3]/div/div[1]/div") WebElement saveReview;
	
    public void writeReview(int star, String text, Logger logger) throws InterruptedException {
        driver.get("https://www.ticketor.com/demo/myreviews");
        logger.info("Moving to my reviews page");
		if (star < 1 || star > 5)
			logger.warn("star out of bounds");
		star = 6-star;
        driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[2]/div[1]/div/div/div[1]/label[" + star + "]")).click();
        reviewText.sendKeys(text);
        saveReview.click(); // click to save review
		Thread.sleep(1500);
    }

}
