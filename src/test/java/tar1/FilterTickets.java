package tar1;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FilterTickets {
    private WebDriver driver;
    JavascriptExecutor js;
    
    @BeforeMethod
	public void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        js = (JavascriptExecutor) driver;
        ExcelReader.readExcel("", "data.xls", "Sheet1");
    }
    @AfterMethod
	public void tearDown() {
        driver.quit();
    }
    
    @Test
    public void filterTickets() throws InterruptedException {
        driver.get("https://www.ticketor.com/demo/tickets");
        driver.findElement(By.id("search")).sendKeys(ExcelReader.getsheet().getRow(1).getCell(2).getStringCellValue());			//TODO: Change to variable from file
        Thread.sleep(500);
        WebElement allShows = driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[2]/div[7]/div[2]"));
        List<WebElement> tickets = allShows.findElements(By.className("upcomingEvent"));
        for (WebElement webElement : tickets) {
			if( webElement.isDisplayed())
				Assert.assertTrue(webElement.getText().contains(ExcelReader.getsheet().getRow(1).getCell(2).toString()));	//TODO: Change to variable
		}
        // TODO: add to log - fail or succeed
    }

}