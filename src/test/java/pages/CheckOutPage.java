package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testCases.ExcelReader;

public class CheckOutPage {
	private WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(xpath = "/html/body/div[1]/div/form/div[3]/div/div[2]/div/div[2]/table/tbody/tr[1]/td/span/label/div/span[1]") WebElement deliveyMethod;
	@FindBy(xpath = "/html/body/div[1]/div/form/div[3]/div/div[2]/div/table[2]/tbody/tr[2]/td[2]/input") WebElement nameOnCard;
	@FindBy(xpath = "/html/body/div/form/div/div[2]/span[1]/span[2]/div/div[2]/span/input") WebElement cardNum;
	@FindBy(xpath = "/html/body/div/form/div/div[2]/span[2]/span/span/input") WebElement exp;
	@FindBy(xpath = "/html/body/div/form/div/div[2]/span[3]/span/span/input") WebElement cvc;
	@FindBy(xpath = "/html/body/div[1]/div/form/div[3]/div/div[2]/div/table[1]/tbody/tr[7]/td[2]/input") WebElement city;
	@FindBy(xpath = "/html/body/div[1]/div/form/div[3]/div/div[2]/div/table[1]/tbody/tr[5]/td[2]/input") WebElement billAddr;
	@FindBy(xpath = "/html/body/div[1]/div/form/div[3]/div/div[2]/div/table[1]/tbody/tr[9]/td[2]/input") WebElement zipCode;
	@FindBy(xpath = "/html/body/div[1]/div/form/div[3]/div/div[2]/div/div[9]") WebElement confirmedAge;
	@FindBy(id = "ctl00_CPMain_Buy") WebElement buyButton;
	@FindBy(xpath = "/html/body/div[4]/div[2]/div/div[2]/button") WebElement infoCloseButton;
	@FindBy(xpath = "/html/body/div[1]/div/form/div[3]/div[1]/div[2]/p[3]/span") WebElement confirmOrder;
	
	private String zeroPadString(int number, int length) {
		String numberString = Integer.toString(number);
		while (numberString.length() < length) {
			numberString = "0" + numberString;
		}
		return numberString;
	}
	
	public boolean CheckOutCart() throws InterruptedException {
    	driver.get("https://www.ticketor.com/demo/members/checkout");
    	Thread.sleep(1000);
    	deliveyMethod.click();
    	nameOnCard.click();
    	nameOnCard.sendKeys(ExcelReader.getsheet().getRow(1).getCell(4).getStringCellValue());
        driver.switchTo().frame(0);
        cardNum.sendKeys(zeroPadString((int)ExcelReader.getsheet().getRow(2).getCell(4).getNumericCellValue(), 8) + zeroPadString((int)ExcelReader.getsheet().getRow(3).getCell(4).getNumericCellValue(), 8));
    	exp.sendKeys(zeroPadString((int)ExcelReader.getsheet().getRow(4).getCell(4).getNumericCellValue(), 4)); // 4 letters (mm/yy)
    	cvc.sendKeys(zeroPadString((int)ExcelReader.getsheet().getRow(5).getCell(4).getNumericCellValue(), 3));
    	driver.switchTo().defaultContent();
    	city.sendKeys(ExcelReader.getsheet().getRow(6).getCell(4).getStringCellValue());
    	billAddr.sendKeys(ExcelReader.getsheet().getRow(7).getCell(4).getStringCellValue());
    	zipCode.sendKeys(((int)ExcelReader.getsheet().getRow(8).getCell(4).getNumericCellValue()) + "");
    	confirmedAge.click();
    	buyButton.click();
    	Thread.sleep(5000);
    	infoCloseButton.click();
    	Thread.sleep(3000);
    	return confirmOrder.getText().contains("Your purchase completed successfully");
    }
}

