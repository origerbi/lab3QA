package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StorePage {
	private WebDriver driver;

	public StorePage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	
	/*
     * quantity
     * color = 0 - black
     * color = 1 - blue  +1.50$
     * size = 0 - small -1.00$
     * size = 1 - medium
     * size = 2 - large
     */
    public void addItemToCart(int quantity, int color, int size, Logger logger) throws InterruptedException {
        driver.get("https://www.ticketor.com/demo/shop/t-shirt-10520");
        logger.info("Navigated to shop item page");
        driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div[2]/div[2]/div[2]/div[1]/input")).sendKeys(String.valueOf(quantity));
    	driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div[2]/div[2]/div[2]/div[2]/div[1]/select")).findElement(By.xpath("//option[" + color + "]")).click();
    	driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div[2]/div[2]/div[2]/div[2]/div[2]/select")).findElement(By.xpath("//option[" + color + "]")).click();
    	driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div[2]/div[2]/div[2]/div[4]/button")).click();   
    }

}
