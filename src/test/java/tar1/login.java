package tar1;

import org.junit.Test;
import org.openqa.selenium.interactions.Actions;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.net.MalformedURLException;
import java.net.URL;

public class login {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    
    @Test
    public void loginTest() throws InterruptedException {
        driver.get("https://www.ticketor.com/demo/tickets?pageid=343");
		login(driver,"origerbi@gmail.com","9012761");
        String loginElement = driver.findElement(By.xpath("/html/body/div[1]/div/header/nav/ul/li[3]/a")).getText();
        loginElement = loginElement.trim();
        assertEquals("equals","Welcome Ori",loginElement);
    }
    
    @Test
    public void failedLoginPassword() throws InterruptedException {
    	driver.get("https://www.ticketor.com/demo/tickets?pageid=343");
    	login(driver,"origerbi@gmail.com","654365363");
		String loginElement = driver.findElement(By.xpath("/html/body/div[7]/div[2]/form/div[17]/p")).getText();
		assertEquals("Invalid password!", loginElement);
    }
    
    public static void login(WebDriver driver, String email, String password) throws InterruptedException {
    	driver.findElement(By.xpath("/html/body/div[1]/div/header/nav/ul/li[3]/a")).click();
        driver.findElement(By.xpath("/html/body/div[7]/div[2]/form/div[4]/input")).sendKeys(email);
        driver.findElement(By.xpath("/html/body/div[7]/div[2]/form/div[16]/div[1]/input")).sendKeys(password);
        driver.findElement(By.xpath("/html/body/div[7]/div[2]/form/div[18]/button")).click();
        Thread.sleep(500);
    }
}