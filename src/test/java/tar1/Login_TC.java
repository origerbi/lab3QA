package tar1;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.LoginPage;


public class Login_TC {
    private WebDriver driver;
    JavascriptExecutor js;
    Logger logger;
    
    @BeforeMethod
	public void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        js = (JavascriptExecutor) driver;
        ExcelReader.readExcel("", "data.xls", "Sheet1");
        logger = LogManager.getLogger();
    }
    @AfterMethod
	public void tearDown() {
        driver.quit();
        LogManager.shutdown();
    }
    
    @Test
    public void loginTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        String result = loginPage.login(ExcelReader.getsheet().getRow(1).getCell(0).getStringCellValue(), ExcelReader.getsheet().getRow(2).getCell(0).getStringCellValue(), logger);
        Assert.assertNotEquals("Sign In / Sign Up",result);
    }
}