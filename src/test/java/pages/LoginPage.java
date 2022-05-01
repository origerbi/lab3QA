package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class LoginPage {
	private WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(xpath = "/html/body/div[1]/div/header/nav/ul/li[3]/a") WebElement loginButton;
	@FindBy(xpath = "/html/body/div[7]/div[2]/form/div[4]/input") WebElement emailField;
	@FindBy(xpath = "/html/body/div[7]/div[2]/form/div[16]/div[1]/input") WebElement passwordField;
	@FindBy(xpath = "/html/body/div[7]/div[2]/form/div[18]/button") WebElement signInLogin;
	@FindBy(xpath = "/html/body/div[1]/div/header/nav/ul/li[3]/a") WebElement signInConfirmation;

    public String login(String email, String password) throws InterruptedException {
        driver.get("https://www.ticketor.com/demo/default");
        Reporter.log("Navigated to Ticketor main page");
        loginButton.click();
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        signInLogin.click();
        Reporter.log("login preformed");
        Thread.sleep(1000);
        return signInConfirmation.getText().trim();
    }
}
