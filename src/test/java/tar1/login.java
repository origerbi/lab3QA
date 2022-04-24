package tar1;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class login {
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
    public void loginTest() throws InterruptedException {
        driver.get("https://www.ticketor.com/demo/tickets?pageid=343");
		login(driver,"origerbi@gmail.com","9012761");
        String loginElement = driver.findElement(By.xpath("/html/body/div[1]/div/header/nav/ul/li[3]/a")).getText();
        loginElement = loginElement.trim();
        AssertJUnit.assertEquals("equals","Welcome Ori",loginElement);
    }
    
    @Test
    public void failedLoginPassword() throws InterruptedException {
    	driver.get("https://www.ticketor.com/demo/tickets?pageid=343");
    	login(driver,"origerbi@gmail.com","654365363");
		String loginElement = driver.findElement(By.xpath("/html/body/div[7]/div[2]/form/div[17]/p")).getText();
		AssertJUnit.assertEquals("Invalid password!", loginElement);
    }
    
    
    public static void login(WebDriver driver, String email, String password) throws InterruptedException {
    	driver.findElement(By.xpath("/html/body/div[1]/div/header/nav/ul/li[3]/a")).click();
        driver.findElement(By.xpath("/html/body/div[7]/div[2]/form/div[4]/input")).sendKeys(email);
        driver.findElement(By.xpath("/html/body/div[7]/div[2]/form/div[16]/div[1]/input")).sendKeys(password);
        driver.findElement(By.xpath("/html/body/div[7]/div[2]/form/div[18]/button")).click();
        Thread.sleep(500);
    }
}