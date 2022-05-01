package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StorePage {
	private WebDriver driver;

	public StorePage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(xpath = "/html/body/div[1]/div/form/div[3]/div[2]/div[2]/div[2]/div[1]/input") WebElement quantitySelector;
	@FindBy(xpath = "/html/body/div[1]/div/form/div[3]/div[2]/div[2]/div[2]/div[2]/div[1]/select") WebElement colorSelector;
	@FindBy(xpath = "/html/body/div[1]/div/form/div[3]/div[2]/div[2]/div[2]/div[2]/div[2]/select") WebElement sizeSelector;
	@FindBy(xpath = "/html/body/div[1]/div/form/div[3]/div[2]/div[2]/div[2]/div[4]/button") WebElement buyButton;
	
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
        quantitySelector.clear();
        quantitySelector.sendKeys(String.valueOf(quantity));
        colorSelector.findElement(By.xpath("//option[" + color + "]")).click();
        sizeSelector.findElement(By.xpath("//option[" + size + "]")).click();
    	buyButton.click();   
    }

}
