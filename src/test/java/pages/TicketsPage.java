package pages;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import testCases.ExcelReader;

public class TicketsPage {
	private WebDriver driver;

	public TicketsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void RunAddToCart(Logger logger) throws InterruptedException {
        logger.info("Running add to cart");
        driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[2]/div[7]/div[2]/div[2]/div/div[2]/div[3]/div/a")).click();
        List<WebElement> events = driver.findElements(By.className("cCalendarEventTitle"));
        events.get(0).click(); // order tickets for the closest event
        Thread.sleep(500);
        logger.info("Adding tickets to cart...");
        WebElement ticketCount = driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div[2]/div[2]/div[4]/form/div[4]/div[2]/table/tbody/tr[2]/td[2]/label/select"));
        ticketCount.findElement(By.xpath("//option[" + (int)(ExcelReader.getsheet().getRow(1).getCell(1).getNumericCellValue() + 1)+ "]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div[2]/div[2]/div[4]/form/div[4]/div[2]/div/button")).click();
    }

}
