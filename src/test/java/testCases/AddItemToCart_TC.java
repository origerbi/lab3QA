package testCases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.StorePage;

public class AddItemToCart_TC {
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
    	StorePage store = PageFactory.initElements(driver,StorePage.class);
    	try {
    		store.addItemToCart((int)ExcelReader.getsheet().getRow(1).getCell(3).getNumericCellValue(),(int)ExcelReader.getsheet().getRow(2).getCell(3).getNumericCellValue(),(int)ExcelReader.getsheet().getRow(3).getCell(3).getNumericCellValue());			
		} catch (Exception e) {
			Reporter.log("test failed. could not add item to cart");
			throw e;
		}
    	
    }
	

}
