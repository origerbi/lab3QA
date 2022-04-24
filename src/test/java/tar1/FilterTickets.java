package tar1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Map<String, Object> vars;
    JavascriptExecutor js;
    
    @BeforeMethod
	public void setUp() {
        System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @AfterMethod
	public void tearDown() {
        driver.quit();
    }
    
    @Test
    public void FilterTickets() throws InterruptedException {
        driver.get("https://www.ticketor.com/demo/tickets");
		login.login(driver,"origerbi@gmail.com","9012761");
        driver.findElement(By.id("search")).sendKeys("California");			//TODO: Change to variable from file
        Thread.sleep(500);
        WebElement allShows = driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[2]/div[7]/div[2]"));
        List<WebElement> tickets = allShows.findElements(By.className("upcomingEvent"));
        for (WebElement webElement : tickets) {
			if( webElement.isDisplayed())
				System.out.println(webElement.getText().contains("California"));	//TODO: Change to variable
		}
        Thread.sleep(5000);
        // TODO: add to log - fail or succeed
    }

}