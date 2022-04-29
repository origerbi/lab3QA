package Pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	private WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

    public String login(String email, String password, Logger logger) throws InterruptedException {
        driver.get("https://www.ticketor.com/demo/default");
        logger.info("Navigated to Ticketor main page");
    	driver.findElement(By.xpath("/html/body/div[1]/div/header/nav/ul/li[3]/a")).click();
        driver.findElement(By.xpath("/html/body/div[7]/div[2]/form/div[4]/input")).sendKeys(email);
        driver.findElement(By.xpath("/html/body/div[7]/div[2]/form/div[16]/div[1]/input")).sendKeys(password);
        driver.findElement(By.xpath("/html/body/div[7]/div[2]/form/div[18]/button")).click();
        logger.info("login preformed");
        Thread.sleep(500);
        String loginElement = driver.findElement(By.xpath("/html/body/div[1]/div/header/nav/ul/li[3]/a")).getText().trim();
        return loginElement;
    }
}
