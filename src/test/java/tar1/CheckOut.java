package tar1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;


public class CheckOut {
    private WebDriver driver;
    JavascriptExecutor js;
	Logger logger;
    
    @BeforeMethod
	public void setUp() {
    	System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        js = (JavascriptExecutor) driver;
		logger = Logger.getLogger(CheckOut.class);
    }
    
    @AfterMethod
	public void tearDown() {
        driver.quit();
    }
    
    @Test
    public void CheckOutCart() throws InterruptedException {
    	AddToCart.RunAddToCart(driver, logger);
    	AddItemToCart.addItemToCart(driver,2,1,1);
    	driver.get("https://www.ticketor.com/demo/members/checkout");
    	Thread.sleep(1000);
    	WebElement deliveyMethod = driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[2]/div/div[2]/table/tbody/tr[1]/td/span/label/div/span[1]"));
    	deliveyMethod.click();
    	WebElement nameOnCard = driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[2]/div/table[2]/tbody/tr[2]/td[2]/input"));
    	nameOnCard.click();
    	nameOnCard.sendKeys("Ori Gerbi");
        driver.switchTo().frame(0);
        WebElement cardNum = driver.findElement(By.xpath("/html/body/div/form/div/div[2]/span[1]/span[2]/div/div[2]/span/input"));//You can use xpath, ID or name whatever you like
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
    	Thread.sleep(3500);
    	driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/button")).click();
    	Thread.sleep(3000);
    	WebElement confirmed = driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div[1]/div[2]/p[3]/span"));
    	if (confirmed.getText().contains("Your purchase completed successfully"))
    		System.out.println("Print to log: Succseed");
    	else
    		System.out.println("Print to log: failed");
    	
        // TODO: add to log - fail or succeed
    }
}
