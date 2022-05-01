package testCases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.CheckOutPage;
import pages.LoginPage;
import pages.StorePage;
import pages.TicketsPage;

public class CheckOut_TC {
	private WebDriver driver;
	Logger logger = LogManager.getLogger();

	@BeforeMethod
	public void setUp() throws IOException {
		driver = TestUtils.setUp();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void CheckOutCart() throws InterruptedException {
		LoginPage login = PageFactory.initElements(driver,LoginPage.class);
		login.login(ExcelReader.getsheet().getRow(1).getCell(0).getStringCellValue(),
				ExcelReader.getsheet().getRow(2).getCell(0).getStringCellValue(), logger);
		TicketsPage addToCart = PageFactory.initElements(driver,TicketsPage.class);
		addToCart.RunAddToCart(logger);
		CheckOutPage checkout = PageFactory.initElements(driver,CheckOutPage.class);
		if (checkout.CheckOutCart(logger))
			logger.info("checkout sucssefully");
		else
			logger.fatal("test failed, could not checkout");
	}
}
