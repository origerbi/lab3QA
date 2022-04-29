package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.AssertJUnit;

import testCases.AddItemToCart;
import testCases.AddToCart_TC;

import org.apache.logging.log4j.*;

public class CheckOutPage {
	private WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean CheckOutCart(Logger logger) throws InterruptedException {
    	driver.get("https://www.ticketor.com/demo/members/checkout");
    	Thread.sleep(1000);
    	WebElement deliveyMethod = driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[2]/div/div[2]/table/tbody/tr[1]/td/span/label/div/span[1]"));
    	deliveyMethod.click();
    	WebElement nameOnCard = driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[2]/div/table[2]/tbody/tr[2]/td[2]/input"));
    	nameOnCard.click();
    	nameOnCard.sendKeys("Ori Gerbi");
        driver.switchTo().frame(0);
        WebElement cardNum = driver.findElement(By.xpath("/html/body/div/form/div/div[2]/span[1]/span[2]/div/div[2]/span/input"));
        cardNum.sendKeys("4111111111111111");
        
    	WebElement exp = driver.findElement(By.xpath("/html/body/div/form/div/div[2]/span[2]/span/span/input"));
    	exp.sendKeys("0129"); // 4 letters (mm/yy)
    	WebElement cvc = driver.findElement(By.xpath("/html/body/div/form/div/div[2]/span[3]/span/span/input"));
    	cvc.sendKeys("123");
    	driver.switchTo().defaultContent();
    	WebElement city = driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[2]/div/table[1]/tbody/tr[7]/td[2]/input"));
    	city.sendKeys("Rosh Hayaain");
    	WebElement billAddr = driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[2]/div/table[1]/tbody/tr[5]/td[2]/input"));
    	billAddr.sendKeys("ANYTHING");
    	WebElement zipCode = driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[2]/div/table[1]/tbody/tr[9]/td[2]/input"));
    	zipCode.sendKeys("12345");
    	driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[2]/div/div[9]")).click();
    	driver.findElement(By.id("ctl00_CPMain_Buy")).click();
    	Thread.sleep(7000);
    	driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/button")).click();
    	Thread.sleep(3000);
    	WebElement confirmed = driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div[1]/div[2]/p[3]/span"));
    	return confirmed.getText().contains("Your purchase completed successfully");
    }
}

