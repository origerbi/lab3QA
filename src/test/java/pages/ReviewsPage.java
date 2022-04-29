package pages;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReviewsPage {
	private WebDriver driver;
	
	public ReviewsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(id = "ui-id-3") WebElement showReviews;
	@FindBy(xpath = "/html/body/div[1]/div/form/div[3]/div/div[2]/div[2]/div[2]/div[2]/div[1]") WebElement myReview;
	
    public String checkReview(String text, Logger logger) throws InterruptedException {
        driver.get("https://www.ticketor.com/demo/reviews");
        showReviews.click();
        Thread.sleep(1000);      
        return myReview.getText();
    }

}
