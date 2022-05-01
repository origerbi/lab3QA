package testCases;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.TicketsPage;


public class AddToCart_TC {
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
    public void AddToCartTest() throws InterruptedException {
    	LoginPage login = PageFactory.initElements(driver,LoginPage.class);
    	login.login(ExcelReader.getsheet().getRow(1).getCell(0).getStringCellValue(),ExcelReader.getsheet().getRow(2).getCell(0).getStringCellValue());
    	try {
    		TicketsPage addToCart = PageFactory.initElements(driver,TicketsPage.class);
    		addToCart.RunAddToCart();				
    		Reporter.log("Tickets added to cart");
		} catch (Exception e) {
			Reporter.log("test failed, could not add tickets to cart");
			throw e;
		}
    }
    
    

}
