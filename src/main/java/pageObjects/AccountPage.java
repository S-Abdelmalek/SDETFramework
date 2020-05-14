package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {
	
	WebDriver driver;
	
	public AccountPage (WebDriver driver)
	{
		this.driver = driver;
	}
	
	By welcomeTitle = By.xpath("//h3[@class = 'text-align-left']");
	By accountBtn = By.xpath("//div[@class='dropdown dropdown-login dropdown-tab']//a[@id='dropdownCurrency']");
	public String getPageTitle ()
	{
		return driver.findElement(welcomeTitle).getText();
	}
	public String getAccountBtnTxt ()
	{
		return driver.findElement(accountBtn).getText();
	}
	

}
