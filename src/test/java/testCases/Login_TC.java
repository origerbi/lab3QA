package testCases;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Reporter;
import pages.LoginPage;


public class Login_TC {
    private WebDriver driver;
    
    @BeforeMethod
	public void setUp() throws IOException {
        driver = TestUtils.setUp();
    }
    
    @AfterMethod
	public void tearDown() {
        driver.quit();
    }
    
    @Test
    public void loginTest() throws InterruptedException {
        LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);
        String result = loginPage.login(ExcelReader.getsheet().getRow(1).getCell(0).getStringCellValue(), ExcelReader.getsheet().getRow(2).getCell(0).getStringCellValue());
        Assert.assertNotEquals("Sign In / Sign Up",result);
        Reporter.log("login sucssefull");
    }
}