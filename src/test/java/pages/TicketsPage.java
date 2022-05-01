package pages;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import testCases.ExcelReader;

public class TicketsPage {
	private WebDriver driver;

	public TicketsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(xpath="/html/body/div[1]/div/form/div[3]/div/div[2]/div[7]/div[2]/div[2]/div/div[2]/div[3]/div/a") WebElement eventButton;
	@FindBy(xpath="html/body/div[1]/div/form/div[3]/div[2]/div[2]/div[4]/form/div[4]/div[2]/table/tbody/tr[2]/td[2]/label/select") WebElement ticketCountSelector;
	@FindBy(xpath="/html/body/div[1]/div/form/div[3]/div[2]/div[2]/div[4]/form/div[4]/div[2]/div/button") WebElement addToCartButton;
	@FindBy(id="search") WebElement seachField;
	@FindBy(xpath="/html/body/div[1]/div/form/div[3]/div/div[2]/div[7]/div[2]") WebElement allShows;
	
	public void RunAddToCart() throws InterruptedException {
        Reporter.log("Running add to cart");
        eventButton.click();
        List<WebElement> events = driver.findElements(By.className("cCalendarEventTitle"));
        events.get(0).click(); // order tickets for the closest event
        Thread.sleep(500);
        Reporter.log("Adding tickets to cart...");
        ticketCountSelector.findElement(By.xpath("//option[" + (int)(ExcelReader.getsheet().getRow(1).getCell(1).getNumericCellValue() + 1)+ "]")).click();
        addToCartButton.click();
    }
	
	
	public List<WebElement> filterTickets() throws InterruptedException {
		driver.get("https://www.ticketor.com/demo/tickets");
        Reporter.log("Navigated to tickets page");
        seachField.sendKeys(ExcelReader.getsheet().getRow(1).getCell(2).getStringCellValue());
        Thread.sleep(500);
        Reporter.log("Filter applied");
        List<WebElement> events = allShows.findElements(By.className("upcomingEvent"));
        return events;
	}

}
